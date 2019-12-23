package divinerpg.entities.base;

import divinerpg.entities.goal.GhastAttackGoal;
import divinerpg.entities.goal.MeleeGoal;
import divinerpg.entities.goal.RandomFlyGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.GameRules;
import net.minecraft.world.ServerBossInfo;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// TODO change on Divine Bat
public abstract class DivineGhastBoss extends DivineGhast {
    private final static String colorKey = "ProgressbarColor";
    private final static String specialNameKey = "EntityName";
    protected final ServerBossInfo info;

    private DivineGhastBoss(World world) {
        this(EntityType.GHAST, world, SoundEvents.ENTITY_GHAST_HURT, SoundEvents.ENTITY_GHAST_AMBIENT, 2.6F, DivineBoss.randomColor(), 2000);
    }

    public DivineGhastBoss(EntityType<? extends GhastEntity> type, World world, SoundEvent hurt, SoundEvent ambient, float eyeHight, BossInfo.Color color, int exp) {
        super(type, world, hurt, ambient, eyeHight);

        info = new ServerBossInfo(getDisplayName(), color, BossInfo.Overlay.PROGRESS);
        this.experienceValue = exp;
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
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        this.goalSelector.addGoal(5, new RandomFlyGoal(this));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    }

    /**
     * Init attack target ai
     */
    protected void initAI(boolean isArcher, boolean isMeelee) {
        if (isMeelee)
            this.goalSelector.addGoal(2, new MeleeGoal(this, 1, true));

        if (isArcher)
            this.goalSelector.addGoal(7, new GhastAttackGoal(this, this, shootSound()));
    }

    /////////////////////////
    // Copied from DivineBoss
    /////////////////////////

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);

        if (compound.contains(colorKey))
            info.setColor(BossInfo.Color.byName(compound.getString(colorKey)));

        if (compound.contains(specialNameKey))
            setCustomName(new StringTextComponent(compound.getString(specialNameKey)));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);

        compound.putString(colorKey, info.getColor().getName());

        if (hasCustomName() && getCustomName() != null)
            compound.putString(colorKey, this.getCustomName().getFormattedText());
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
