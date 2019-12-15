package divinerpg.utils.portal;

import com.google.common.cache.LoadingCache;
import divinerpg.blocks.twilight.DivinePortalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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

    public static void placeNetherLikePortal(World world, BlockPos topLeft, Direction right, Direction down, Block frame, Block portal) {
        if (frame == null && portal == null)
            return;

        if (frame != null) {
            // width
            for (int i = 0; i < 4; i++) {
                // height
                for (int z = 0; z < 5; z++) {
                    BlockPos portalPlace = topLeft
                            .offset(right, i)
                            .offset(down, z);

                    world.setBlockState(portalPlace, frame.getDefaultState());
                }
            }
        }

        if (portal == null) {
            // Making hole in portal
            portal = Blocks.AIR;
        }

        BlockState state = portal.getDefaultState();

        if (state.has(BlockStateProperties.AXIS)) {
            state.with(BlockStateProperties.AXIS, right.getAxis());
        }
        // nether portal has that prop
        if (state.has(BlockStateProperties.HORIZONTAL_AXIS)) {
            state.with(BlockStateProperties.HORIZONTAL_AXIS, right.getAxis());
        }

        // width
        for (int i = 0; i < 2; i++) {
            // height
            for (int z = 0; z < 3; z++) {
                BlockPos portalPlace = topLeft
                        // need to add 1 block offset because it is frame
                        .offset(right, i + 1)
                        .offset(down, z + 1);


                world.setBlockState(portalPlace, state);
            }
        }
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
     * @return success of operation
     */
    public static boolean tryChangeDimention(Entity traveler, DimensionType destination, Block portal, Block frame) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(traveler, destination)) return false;
        if (!traveler.world.isRemote() && traveler.isAlive()) {
            traveler.world.getProfiler().startSection("changeDimension");
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
                BlockPattern.PortalInfo blockpattern$portalinfo = new DivineTeleporter(serverworld1, portal, frame).func_222272_a(blockpos, vec3d, traveler.getTeleportDirection(), vec3d1.x, vec3d1.y, traveler instanceof PlayerEntity);
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

    /**
     * Stolen from nether. Work with vertical nether-like portals
     *
     * @param world  - world
     * @param pos    - pos
     * @param frame  - frame block
     * @param portal - portal block
     */
    public static BlockPattern.PatternHelper createHelperForTeleporter(World world, BlockPos pos, Block frame, DivinePortalBlock portal) {
        Direction.Axis direction$axis = Direction.Axis.Z;
        DivinePortalSize portalSize = new DivinePortalSize(frame, portal, world, pos, Direction.Axis.X);
        LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(world, true);
        if (!portalSize.isValid()) {
            direction$axis = Direction.Axis.X;
            portalSize = new DivinePortalSize(frame, portal, world, pos, Direction.Axis.Z);
        }

        if (!portalSize.isValid()) {
            return new BlockPattern.PatternHelper(pos, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
        } else {
            int[] aint = new int[Direction.AxisDirection.values().length];
            Direction direction = portalSize.rightDir.rotateYCCW();
            BlockPos blockpos = portalSize.bottomLeft.up(portalSize.getHeight() - 1);

            for (Direction.AxisDirection dir : Direction.AxisDirection.values()) {
                BlockPattern.PatternHelper helper = new BlockPattern.PatternHelper(direction.getAxisDirection() == dir ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(dir, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);

                for (int i = 0; i < portalSize.getWidth(); ++i) {
                    for (int j = 0; j < portalSize.getHeight(); ++j) {
                        CachedBlockInfo cachedblockinfo = helper.translateOffset(i, j, 1);
                        if (!cachedblockinfo.getBlockState().isAir()) {
                            ++aint[dir.ordinal()];
                        }
                    }
                }
            }

            Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;

            for (Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
                if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
                    direction$axisdirection1 = direction$axisdirection2;
                }
            }

            return new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection1 ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection1, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);
        }
    }
}
