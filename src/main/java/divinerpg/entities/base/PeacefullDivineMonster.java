package divinerpg.entities.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public abstract class PeacefullDivineMonster extends MonsterEntity {
    private EntityType<? extends MonsterEntity> type;
    private SoundEvent hurt;
    private SoundEvent ambient;
    private float eyeHight;

    protected PeacefullDivineMonster(World world) {
        this(EntityType.ZOMBIE, world, SoundEvents.ENTITY_HOSTILE_HURT, null, 1);
    }

    protected PeacefullDivineMonster(EntityType<? extends MonsterEntity> type, World world, SoundEvent hurt, SoundEvent ambient, float eyeHight) {
        super(type, world);
        this.type = type;
        this.hurt = hurt;
        this.ambient = ambient;
        this.eyeHight = eyeHight;
    }

    @Override
    public EntityType<?> getType() {
        return type;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return hurt;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return hurt;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ambient;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return eyeHight;
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        return 0;
    }
}
