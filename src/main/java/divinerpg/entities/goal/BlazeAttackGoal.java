package divinerpg.entities.goal;

import divinerpg.utils.properties.item.ICreateFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;

import java.util.EnumSet;

public class BlazeAttackGoal extends Goal {
    private final MobEntity blaze;
    private ICreateFireball func;
    private int attackStep;
    private int attackTime;
    private int ticks;

    public BlazeAttackGoal(MobEntity blazeIn, ICreateFireball func) {
        this.blaze = blazeIn;
        this.func = func;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        LivingEntity livingentity = this.blaze.getAttackTarget();
        return livingentity != null && livingentity.isAlive() && this.blaze.canAttack(livingentity);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.attackStep = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.ticks = 0;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        --this.attackTime;
        LivingEntity livingentity = this.blaze.getAttackTarget();
        if (livingentity != null) {
            boolean flag = this.blaze.getEntitySenses().canSee(livingentity);
            if (flag) {
                this.ticks = 0;
            } else {
                ++this.ticks;
            }

            double d0 = this.blaze.getDistanceSq(livingentity);
            if (d0 < 4.0D) {
                if (!flag) {
                    return;
                }

                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.blaze.attackEntityAsMob(livingentity);
                }

                this.blaze.getMoveHelper().setMoveTo(livingentity.serverPosX, livingentity.serverPosY, livingentity.serverPosZ, 1.0D);
            } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                if (this.attackTime <= 0) {
                    ++this.attackStep;
                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                    } else if (this.attackStep <= 4) {
                        this.attackTime = 6;
                    } else {
                        this.attackTime = 100;
                        this.attackStep = 0;
                    }

                    if (this.attackStep > 1) {
                        this.blaze.world.playSound(null, this.blaze.getPosition(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1, 1);

                        for (int i = 0; i < 1; ++i) {
                            Entity fireballEntity = createFireball(this.blaze, livingentity);
                            fireballEntity.serverPosY = (long) (this.blaze.serverPosY + (this.blaze.getHeight() / 2.0F) + 0.5D);
                            this.blaze.world.addEntity(fireballEntity);
                        }
                    }
                }

                this.blaze.getLookController().setLookPositionWithEntity(livingentity, 10.0F, 10.0F);
            } else if (this.ticks < 5) {
                this.blaze.getMoveHelper().setMoveTo(livingentity.serverPosX, livingentity.serverPosY, livingentity.serverPosZ, 1.0D);
            }

            super.tick();
        }
    }

    private double getFollowDistance() {
        return this.blaze.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getValue();
    }

    private Entity createFireball(MobEntity entity, LivingEntity target) {
        double distance = entity.getDistanceSq(target);
        float f = MathHelper.sqrt(MathHelper.sqrt(distance)) * 0.5F;

        double x = target.serverPosX - entity.serverPosX;
        double y = target.getBoundingBox().minY + (double) (target.getHeight() / 2.0F) - (entity.serverPosY + (double) (entity.getHeight() / 2.0F));
        double z = target.serverPosZ - entity.serverPosZ;

        return func.createFireball(entity.world, entity, x + entity.getRNG().nextGaussian() * (double) f, y, z + entity.getRNG().nextGaussian() * (double) f);
    }
}
