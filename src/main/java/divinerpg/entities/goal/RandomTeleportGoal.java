package divinerpg.entities.goal;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

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
        double d0 = this.entity.serverPosX + (this.entity.world.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.entity.serverPosY + (double) (this.entity.world.rand.nextInt(64) - 32);
        double d2 = this.entity.serverPosZ + (this.entity.world.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(d0, d1, d2);
    }

    /**
     * Teleport the enderman (legacy copy of EndermanEntity.teleportTo)
     */
    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);

        while (blockpos$mutable.getY() > 0 && !entity.world.getBlockState(blockpos$mutable).getMaterial().blocksMovement()) {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = entity.world.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMovement();
        boolean flag1 = blockstate.getFluidState().isTagged(FluidTags.WATER);
        if (flag && !flag1) {
            EnderTeleportEvent event = new EnderTeleportEvent(entity, x, y, z, 0);
            if (MinecraftForge.EVENT_BUS.post(event)) return false;
            boolean flag2 = entity.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2) {
                entity.world.playSound(null, entity.prevPosX, entity.prevPosY, entity.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
                entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return flag2;
        } else {
            return false;
        }
    }
}
