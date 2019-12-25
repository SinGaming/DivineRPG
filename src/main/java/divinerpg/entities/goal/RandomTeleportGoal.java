package divinerpg.entities.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;

public class RandomTeleportGoal extends Goal {
    private LivingEntity entity;
    private int delay;
    private int lastTeleportedTime;

    public RandomTeleportGoal(LivingEntity entity, int delay) {
        super();
        this.entity = entity;
        this.delay = delay;
    }

    @Override
    public boolean shouldExecute() {
        return entity != null && entity.isAlive() && entity.ticksExisted - lastTeleportedTime >= delay;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();

        if (teleportRandomly()) {
            lastTeleportedTime = this.entity.ticksExisted;
        }
    }

    /**
     * Teleport the enderman to a random nearby position
     */
    protected boolean teleportRandomly() {
        double d0 = this.entity.posX + (this.entity.world.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.entity.posY + (double) (this.entity.world.rand.nextInt(64) - 32);
        double d2 = this.entity.posZ + (this.entity.world.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(d0, d1, d2);
    }

    /**
     * Teleport the enderman
     */
    private boolean teleportTo(double x, double y, double z) {
        // can teleport when stucked in liquid
        if (entity.isInWater() || entity.isInLava())
            return false;

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(x, y, z);

        while (blockpos$mutableblockpos.getY() > 0 && !this.entity.world.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMovement()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        if (!this.entity.world.getBlockState(blockpos$mutableblockpos).getMaterial().blocksMovement()) {
            return false;
        } else {
            net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this.entity, x, y, z, 0);
            if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
            boolean flag = this.entity.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag) {
                this.entity.world.playSound(null, this.entity.prevPosX, this.entity.prevPosY, this.entity.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.entity.getSoundCategory(), 1.0F, 1.0F);
                this.entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return flag;
        }
    }
}
