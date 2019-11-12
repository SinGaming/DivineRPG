package divinerpg.utils.portal;

import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TeleportPlacement {
    private final BlockPos frontTopLeft;
    private BlockPattern.PatternHelper match;
    private BlockPattern pattern;


    public TeleportPlacement(BlockPattern portal, BlockPattern.PatternHelper match) {
        this.pattern = portal;
        this.match = match;
        frontTopLeft = match.getFrontTopLeft();
    }

    public boolean isNear(BlockPos pos, int raduis) {
        if (pattern == null)
            return false;

        double distance = new Vec3d(pos.getX(), pos.getY(), pos.getZ())
                .distanceTo(
                        new Vec3d(frontTopLeft.getX(), frontTopLeft.getY(), frontTopLeft.getZ()));

        return distance <= raduis;
    }

    /**
     * Heavy calculation!
     *
     * @param world - current world
     * @return
     */
    public boolean isValidStill(World world) {
        match = pattern.match(world, frontTopLeft);
        return match != null;
    }

    public BlockPos getPortalMiddle() {
        // TODO implement
        return frontTopLeft;
    }

    public void placeInside(World world, Entity entity) {
        BlockPos pos = getPortalMiddle();

        if (entity instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) entity).connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), entity.rotationYaw, entity.rotationPitch);
            ((ServerPlayerEntity) entity).connection.captureCurrentPosition();
        } else {
            entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), entity.rotationYaw, entity.rotationPitch);
        }
    }
}
