package divinerpg.entities.bosses.ayeraco;

import divinerpg.DivineRPG;
import divinerpg.entities.base.DivineBatBoss;
import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.bosses.ayeraco.manager.AyeracoManager;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;

import java.util.List;

// TODO check movement
public class Ayeraco extends DivineBatBoss {
    private final static String beamKey = "BeamPos";
    private final AyeracoManager model;
    private BlockPos beam;

    public Ayeraco(World world) {
        this(world, null, DivineBoss.randomColor(AyeracoManager.beamLocations.keySet()));
    }

    public Ayeraco(World world, BlockPos summoner, BossInfo.Color color) {
        super(EntitiesRegistry.ayeraco, world, SoundRegistry.AYERACO_HURT, SoundRegistry.AYERACO, color, 2000);
        model = new AyeracoManager(this);
        beam = model.getBeamLocation(summoner);
    }

    public void initGang(List<Ayeraco> ayeracos) {
        model.initGang(ayeracos);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(600, 5, 0);

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27 * 1.5);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(false, true);
    }

    public BossInfo.Color getColor() {
        return info.getColor();
    }

    public BlockPos getBeam() {
        return beam;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        model.read(compound);

        if (compound.contains(beamKey)) {
            beam = BlockPos.fromLong(compound.getLong(beamKey));
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        model.write(compound);

        if (beam != null) {
            compound.putLong(beamKey, beam.toLong());
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return !model.isProtected(source) && super.attackEntityFrom(source, amount);
    }

    @Override
    public void setHealth(float health) {
        if (model == null) {
            super.setHealth(health);
        } else {
            boolean wasAngry = model.isAngry(this);

            super.setHealth(health);

            // if become angry
            if (!wasAngry && model.isAngry(this)) {
                this.playSound(SoundRegistry.AYERACO_HALF_HEALTH, 20, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
            }
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        model.afterAITasks();
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (beam != null) {
            world.setBlockState(beam, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    protected void dropLoot(DamageSource p_213354_1_, boolean p_213354_2_) {
        super.dropLoot(p_213354_1_, p_213354_2_);

        ResourceLocation location = new ResourceLocation(DivineRPG.MODID, getType().getRegistryName().getPath() + "_" + getColor().getName());

        LootTable table = this.world.getServer().getLootTableManager().getLootTableFromLocation(location);
        LootContext.Builder builder = this.func_213363_a(p_213354_2_, p_213354_1_);
        table.generate(builder.build(LootParameterSets.ENTITY), this::entityDropItem);
    }
}
