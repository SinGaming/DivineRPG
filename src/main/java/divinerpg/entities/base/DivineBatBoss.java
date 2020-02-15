package divinerpg.entities.base;

import divinerpg.entities.goal.MeleeGoal;
import divinerpg.entities.goal.RevengeGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DivineBatBoss extends DivineBat implements IRangedAttackMob {
    protected static final DataParameter<String> COLOR = EntityDataManager.createKey(DivineBatBoss.class, DataSerializers.STRING);
    private final static String specialNameKey = "EntityName";
    protected final ServerBossInfo info;

    private DivineBatBoss(World world) {
        this(EntityType.BAT, world, SoundEvents.ENTITY_BAT_HURT, SoundEvents.ENTITY_BAT_AMBIENT, DivineBoss.randomColor(), 2000);
    }

    public DivineBatBoss(EntityType<? extends BatEntity> type, World world, SoundEvent hurt, SoundEvent ambient, BossInfo.Color color, int exp) {
        super(type, world, hurt, ambient);

        info = new ServerBossInfo(getDisplayName(), color, BossInfo.Overlay.PROGRESS);
        this.experienceValue = exp;

        getDataManager().set(COLOR, color.getName());
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);

        info.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);

        info.removePlayer(player);
    }

    @Override
    protected void registerData() {
        super.registerData();

        getDataManager().register(COLOR, BossInfo.Color.GREEN.getName());
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new RevengeGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
    }

    /**
     * Init attack target ai
     */
    protected void initAI(boolean isArcher, boolean isMeelee) {
        if (isMeelee)
            this.goalSelector.addGoal(2, new MeleeGoal(this, 1, true));

        if (isArcher)
            this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1, 40, 20.0F));
    }

    /////////////////////////
    // Copied from DivineBoss
    /////////////////////////

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        if (compound.contains(specialNameKey))
            setCustomName(new StringTextComponent(compound.getString(specialNameKey)));

        info.setColor(BossInfo.Color.byName(getDataManager().get(COLOR)));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);

        if (hasCustomName() && getCustomName() != null)
            compound.putString(specialNameKey, this.getCustomName().getFormattedText());
    }

    /**
     * Init base attributes
     */
    protected void initAttr(float health, float attack, float armor) {
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attack);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(armor);
    }

    @Override
    protected void spawnDrops(DamageSource source) {
        Entity entity = source.getTrueSource();

        int i = net.minecraftforge.common.ForgeHooks.getLootingLevel(this, entity, source);
        this.captureDrops(new java.util.ArrayList<>());

        boolean flag = this.recentlyHit > 0;
        if (this.canDropLoot() && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropLoot(source, flag);
            this.dropSpecialItems(source, i, flag);
        }

        this.dropInventory();

        Collection<ItemEntity> drops = captureDrops(null);
        if (!net.minecraftforge.common.ForgeHooks.onLivingDrops(this, source, drops, i, recentlyHit > 0)) {

            onDrop(drops);
        }
    }

    protected void putInPlayerInventory(List<ItemEntity> drops, PlayerEntity player) {
        while (!drops.isEmpty()) {
            ItemStack stack = drops.get(0).getItem();
            if (!player.inventory.addItemStackToInventory(stack))
                break;

            drops.remove(0);
        }
    }

    protected void putDropInTheWorld(List<ItemEntity> drops) {
        while (!drops.isEmpty()) {
            world.addEntity(drops.get(0));
            drops.remove(0);
        }
    }

    private void onDrop(Collection<ItemEntity> items) {
        List<ItemEntity> actual = items.stream().filter(x -> x != null && !x.getItem().isEmpty()).collect(Collectors.toList());
        if (actual.isEmpty())
            return;

        if (attackingPlayer != null) {
            putInPlayerInventory(actual, attackingPlayer);
        }

        if (actual.isEmpty())
            return;

        putDropInTheWorld(actual);
    }
}
