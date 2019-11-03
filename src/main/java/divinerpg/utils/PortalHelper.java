package divinerpg.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Teleporter;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;

import java.util.function.Predicate;

public class PortalHelper {

    public static BlockPattern createNetherLikePattern(Block frame, Predicate<BlockState> portalMatcher) {
        return BlockPatternBuilder.start()
                .aisle("?xx?")
                .aisle("x..x")
                .aisle("x..x")
                .aisle("x..x")
                .aisle("?xx?")
                .where('?', CachedBlockInfo.hasState(BlockStateMatcher.ANY))
                .where('x', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(frame)))
                .where('.', CachedBlockInfo.hasState(portalMatcher))
                .build();
    }

    /**
     * Create nether like portal pattern with frame and portal
     *
     * @param frame
     * @param portal
     * @return
     */
    public static BlockPattern createNetherLikePattern(Block frame, Block portal) {
        return createNetherLikePattern(frame, BlockStateMatcher.forBlock(portal));
    }

    /**
     * Legacy copy of {@link Entity} changeDimension
     *
     * @param traveler    - enriry traveler
     * @param destination - dim destination
     * @param teleporter  - custom teleporter
     * @return success of operation
     */
    public static boolean tryChangeDimention(Entity traveler, DimensionType destination, Teleporter teleporter) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(traveler, destination)) return false;
        if (!traveler.world.isRemote && traveler.isAlive()) {
            traveler.world.getProfiler().startSection("tryChangeDimention");
            MinecraftServer minecraftserver = traveler.getServer();
            DimensionType dimensiontype = traveler.dimension;
            ServerWorld serverworld = minecraftserver.getWorld(dimensiontype);
            ServerWorld serverworld1 = minecraftserver.getWorld(destination);
            traveler.dimension = destination;
            traveler.detach();
            traveler.world.getProfiler().startSection("reposition");
            Vec3d vec3d = traveler.getMotion();
            float f = 0.0F;
            BlockPos blockpos;
            if (dimensiontype == DimensionType.THE_END && destination == DimensionType.OVERWORLD) {
                blockpos = serverworld1.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, serverworld1.getSpawnPoint());
            } else if (destination == DimensionType.THE_END) {
                blockpos = serverworld1.getSpawnCoordinate();
            } else {
                double movementFactor = serverworld.getDimension().getMovementFactor() / serverworld1.getDimension().getMovementFactor();
                double d0 = traveler.posX * movementFactor;
                double d1 = traveler.posZ * movementFactor;

                double d3 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minX() + 16.0D);
                double d4 = Math.min(-2.9999872E7D, serverworld1.getWorldBorder().minZ() + 16.0D);
                double d5 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxX() - 16.0D);
                double d6 = Math.min(2.9999872E7D, serverworld1.getWorldBorder().maxZ() - 16.0D);
                d0 = MathHelper.clamp(d0, d3, d5);
                d1 = MathHelper.clamp(d1, d4, d6);
                Vec3d vec3d1 = traveler.getLastPortalVec();
                blockpos = new BlockPos(d0, traveler.posY, d1);
                BlockPattern.PortalInfo blockpattern$portalinfo = teleporter.func_222272_a(blockpos, vec3d, traveler.getTeleportDirection(), vec3d1.x, vec3d1.y, traveler instanceof PlayerEntity);
                if (blockpattern$portalinfo == null) {
                    return false;
                }

                blockpos = new BlockPos(blockpattern$portalinfo.field_222505_a);
                vec3d = blockpattern$portalinfo.field_222506_b;
                f = (float) blockpattern$portalinfo.field_222507_c;
            }

            traveler.world.getProfiler().endStartSection("reloading");
            Entity entity = traveler.getType().create(serverworld1);
            if (entity != null) {
                entity.copyDataFromOld(traveler);
                entity.moveToBlockPosAndAngles(blockpos, entity.rotationYaw + f, entity.rotationPitch);
                entity.setMotion(vec3d);
                serverworld1.func_217460_e(entity);
            }

            traveler.remove(false);
            traveler.world.getProfiler().endSection();
            serverworld.resetUpdateEntityTick();
            serverworld1.resetUpdateEntityTick();
            traveler.world.getProfiler().endSection();
            return true;
        } else {
            return false;
        }
    }

    public static BlockPattern.PortalInfo createPortalInfo(BlockPattern.PatternHelper helper, Direction portalDirection,
                                                           BlockPos pos, double chunkX, Vec3d vec, double chunkZ) {
        if (helper == null)
            return null;

        Direction direction = helper.getForwards();
        if (direction.getAxis() == Direction.Axis.Z) {
            direction = helper.getUp();
        }

        Direction direction1 = direction.rotateY();
        double d1 = (double) (helper.getFrontTopLeft().getY() + 1) - chunkX * (double) helper.getHeight();
        double d0;
        double d2;
        if (direction1 == Direction.NORTH) {
            d0 = (double) pos.getX() + 0.5D;
            d2 = (double) (helper.getFrontTopLeft().getZ() + 1) - (1.0D - chunkZ) * (double) helper.getWidth();
        } else if (direction1 == Direction.SOUTH) {
            d0 = (double) pos.getX() + 0.5D;
            d2 = (double) helper.getFrontTopLeft().getZ() + (1.0D - chunkZ) * (double) helper.getWidth();
        } else if (direction1 == Direction.WEST) {
            d0 = (double) (helper.getFrontTopLeft().getX() + 1) - (1.0D - chunkZ) * (double) helper.getWidth();
            d2 = (double) pos.getZ() + 0.5D;
        } else {
            d0 = (double) helper.getFrontTopLeft().getX() + (1.0D - chunkZ) * (double) helper.getWidth();
            d2 = (double) pos.getZ() + 0.5D;
        }

        double d3;
        double d4;
        if (direction.getOpposite() == portalDirection) {
            d3 = vec.x;
            d4 = vec.z;
        } else if (direction.getOpposite() == portalDirection.getOpposite()) {
            d3 = -vec.x;
            d4 = -vec.z;
        } else if (direction.getOpposite() == portalDirection.rotateY()) {
            d3 = -vec.z;
            d4 = vec.x;
        } else {
            d3 = vec.z;
            d4 = -vec.x;
        }

        int i = (direction.getHorizontalIndex() - portalDirection.getOpposite().getHorizontalIndex()) * 90;
        return new BlockPattern.PortalInfo(new Vec3d(d0, d1, d2), new Vec3d(d3, vec.y, d4), i);
    }
}
