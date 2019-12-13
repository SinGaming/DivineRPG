package divinerpg.entities.goal;

import divinerpg.utils.properties.item.ICreateFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class GhastAttackGoal extends Goal {
    private final GhastEntity parentEntity;
    public int attackTimer;
    private ICreateFireball func;
    private SoundEvent shootSound;

    public GhastAttackGoal(GhastEntity ghast, ICreateFireball func, SoundEvent shootSound) {
        this.parentEntity = ghast;
        this.func = func;
        this.shootSound = shootSound;
    }


    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return this.parentEntity.getAttackTarget() != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.attackTimer = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.parentEntity.setAttacking(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        LivingEntity livingentity = this.parentEntity.getAttackTarget();
        if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(livingentity)) {
            World world = this.parentEntity.world;
            Random random = world.getRandom();
            ++this.attackTimer;
            if (this.attackTimer == 10) {
                world.playSound(null, parentEntity.getPosition(), shootSound, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            }

            if (this.attackTimer == 20) {
                Vec3d vec3d = this.parentEntity.getLook(1.0F);
                double d2 = livingentity.posX - (this.parentEntity.posX + vec3d.x * 4.0D);
                double d3 = livingentity.getBoundingBox().minY + (double) (livingentity.getHeight() / 2.0F) - (0.5D + this.parentEntity.posY + (double) (this.parentEntity.getHeight() / 2.0F));
                double d4 = livingentity.posZ - (this.parentEntity.posZ + vec3d.z * 4.0D);
                world.playSound(null, parentEntity.getPosition(), shootSound, SoundCategory.HOSTILE, 10.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);

                Entity fireballentity = func.createFireball(world, this.parentEntity, d2, d3, d4);

                if (fireballentity instanceof FireballEntity) {
                    ((FireballEntity) fireballentity).explosionPower = this.parentEntity.getFireballStrength();
                }

                fireballentity.posX = this.parentEntity.posX + vec3d.x * 4.0D;
                fireballentity.posY = this.parentEntity.posY + (double) (this.parentEntity.getHeight() / 2.0F) + 0.5D;
                fireballentity.posZ = this.parentEntity.posZ + vec3d.z * 4.0D;
                world.addEntity(fireballentity);
                this.attackTimer = -40;
            }
        } else if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        this.parentEntity.setAttacking(this.attackTimer > 10);
    }
}
