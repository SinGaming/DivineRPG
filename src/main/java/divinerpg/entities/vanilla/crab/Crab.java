package divinerpg.entities.vanilla.crab;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class Crab extends MonsterEntity {

    public Crab(World world) {
        this(EntitiesRegistry.crab, world);
    }

    public Crab(EntityType<? extends MonsterEntity> type, World world) {
        super(type, world);
        experienceValue = 40;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        int i = 0;
        this.goalSelector.addGoal(i++, new SwimGoal(this));
        this.goalSelector.addGoal(i++, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(i++, new LookRandomlyGoal(this));

        i = 0;
        this.targetSelector.addGoal(i++, new HurtByTargetGoal(this));

    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.CRAB;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.CRAB_HURT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return getDeathSound();
    }

    @Override
    public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) {
        return worldIn.getDimension().isSurfaceWorld()
                && BiomeDictionary.hasType(worldIn.getBiome(this.getPosition()), BiomeDictionary.Type.BEACH)
                && super.canSpawn(worldIn, spawnReasonIn);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
