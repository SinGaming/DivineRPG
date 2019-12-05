package divinerpg.entities.base;

import divinerpg.entities.goal.RandomFlyGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DivineFlying extends GhastEntity {

    private final SoundEvent hurt;
    private final SoundEvent ambient;
    private float eyeHight;

    @Deprecated
    public DivineFlying(World world) {
        this(EntityType.GHAST, world, SoundEvents.ENTITY_GHAST_HURT, SoundEvents.ENTITY_GHAST_AMBIENT, 1);
    }

    public DivineFlying(EntityType<? extends GhastEntity> type, World world, SoundEvent hurt, SoundEvent ambient, float eyeHight) {
        super(type, world);
        this.hurt = hurt;
        this.ambient = ambient;
        this.eyeHight = eyeHight;
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        this.goalSelector.addGoal(5, new RandomFlyGoal(this));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }
//
//    @Override
//    public boolean attackEntityFrom(DamageSource source, float amount) {
//        return ((LivingEntity)this).attackEntityFrom(source, amount);
//    }

    @Override
    protected SoundEvent getDeathSound() {
        return hurt;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return hurt;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ambient;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return eyeHight;
    }
}
