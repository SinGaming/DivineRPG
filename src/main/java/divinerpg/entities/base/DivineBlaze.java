package divinerpg.entities.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DivineBlaze extends DivineMonster {
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;


    @Deprecated
    protected DivineBlaze(World world) {
        super(world);
    }

    protected DivineBlaze(EntityType<? extends MonsterEntity> type, World world, SoundEvent hurt, SoundEvent ambient, float eyeHight) {
        super(type, world, hurt, ambient, eyeHight);

        this.setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        // ignore fall damage
        if (damageSrc.getDamageType().equals("fall")) {
            return;
        }

        super.damageEntity(damageSrc, damageAmount);
    }

    @Override
    protected void updateAITasks() {
        if (this.isInWaterRainOrBubbleColumn()) {
            this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        }

        --this.heightOffsetUpdateTime;
        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
        }

        LivingEntity livingentity = this.getAttackTarget();
        if (livingentity != null && livingentity.posY + (double) livingentity.getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset && this.canAttack(livingentity)) {
            Vec3d vec3d = this.getMotion();
            this.setMotion(this.getMotion().add(0.0D, ((double) 0.3F - vec3d.y) * (double) 0.3F, 0.0D));
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    @Override
    public void livingTick() {
        // Taken from blaze
        if (!this.onGround && this.getMotion().y < 0.0D) {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }

        super.livingTick();
    }
}
