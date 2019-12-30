package divinerpg.utils.portal;

import divinerpg.utils.portal.description.IPortalDescription;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;

public class PortalHelper {
    /**
     * Legacy copy of {@link Entity} changeDimension
     *
     * @param traveler    - enriry traveler
     * @param destination - dim destination
     * @param description
     * @return success of operation
     */
    public static boolean tryChangeDimention(ServerPlayerEntity traveler, DimensionType destination, IPortalDescription description) {
        // todo sometimes fall back to overworld

        // block traveling
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(traveler, destination))
            return false;

        DimensionType currentDimention = traveler.dimension;
        ServerWorld currentWorld = traveler.server.getWorld(currentDimention);

        traveler.dimension = destination;
        ServerWorld destinationWorld = traveler.server.getWorld(destination);
        WorldInfo worldinfo = traveler.world.getWorldInfo();
        traveler.connection.sendPacket(new SRespawnPacket(destination, worldinfo.getGenerator(), traveler.interactionManager.getGameType()));
        traveler.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = traveler.server.getPlayerList();
        playerlist.updatePermissionLevel(traveler);
        currentWorld.removeEntity(traveler, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
        traveler.revive();
        double d0 = traveler.posX;
        double d1 = traveler.posY;
        double d2 = traveler.posZ;
        float f = traveler.rotationPitch;
        float f1 = traveler.rotationYaw;
        double d3 = 8.0D;
        float f2 = f1;
        currentWorld.getProfiler().startSection("moving");
        double moveFactor = currentWorld.getDimension().getMovementFactor() / destinationWorld.getDimension().getMovementFactor();
        d0 *= moveFactor;
        d2 *= moveFactor;

        traveler.setLocationAndAngles(d0, d1, d2, f1, f);
        currentWorld.getProfiler().endSection();
        currentWorld.getProfiler().startSection("placing");
        double d7 = Math.min(-2.9999872E7D, destinationWorld.getWorldBorder().minX() + 16.0D);
        double d4 = Math.min(-2.9999872E7D, destinationWorld.getWorldBorder().minZ() + 16.0D);
        double d5 = Math.min(2.9999872E7D, destinationWorld.getWorldBorder().maxX() - 16.0D);
        double d6 = Math.min(2.9999872E7D, destinationWorld.getWorldBorder().maxZ() - 16.0D);
        d0 = MathHelper.clamp(d0, d7, d5);
        d2 = MathHelper.clamp(d2, d4, d6);
        traveler.setLocationAndAngles(d0, d1, d2, f1, f);

        CustomTeleporter teleporter = destinationWorld.customTeleporters.stream()
                .filter(x -> x instanceof CustomTeleporter)
                .map(x -> ((CustomTeleporter) x))
                // check correct world
                .filter(x -> x.sameType(currentWorld, destinationWorld))
                .findFirst().orElse(new CustomTeleporter(destinationWorld, currentWorld, description));

        if (!teleporter.func_222268_a(traveler, f2)) {
            teleporter.makePortal(traveler);
            teleporter.func_222268_a(traveler, f2);
        }

        currentWorld.getProfiler().endSection();
        traveler.setWorld(destinationWorld);
        destinationWorld.func_217447_b(traveler);
        traveler.connection.setPlayerLocation(traveler.posX, traveler.posY, traveler.posZ, f1, f);
        traveler.interactionManager.setWorld(destinationWorld);
        traveler.connection.sendPacket(new SPlayerAbilitiesPacket(traveler.abilities));
        playerlist.sendWorldInfo(traveler, destinationWorld);
        playerlist.sendInventory(traveler);

        for (EffectInstance effectinstance : traveler.getActivePotionEffects()) {
            traveler.connection.sendPacket(new SPlayEntityEffectPacket(traveler.getEntityId(), effectinstance));
        }

        traveler.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
        // todo make smth
        //traveler.lastHealth = -1.0F;
        //traveler.lastFoodLevel = -1;
        //traveler.lastExperience = -1;
        net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(traveler, currentDimention, destination);
        return true;
    }

//    public static BlockPattern createNetherLikePattern(Block frame, Predicate<BlockState> portalMatcher) {
//        return BlockPatternBuilder.start()
//                .aisle("?xx?")
//                .aisle("x..x")
//                .aisle("x..x")
//                .aisle("x..x")
//                .aisle("?xx?")
//                .where('?', CachedBlockInfo.hasState(BlockStateMatcher.ANY))
//                .where('x', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(frame)))
//                .where('.', CachedBlockInfo.hasState(portalMatcher))
//                .build();
//    }
//
//    public static void placeNetherLikePortal(World world, BlockPos topLeft, Direction right, Direction down, Block frame, Block portal) {
//        if (frame == null && portal == null)
//            return;
//
//        if (frame != null) {
//            // width
//            for (int i = 0; i < 4; i++) {
//                // height
//                for (int z = 0; z < 5; z++) {
//                    BlockPos portalPlace = topLeft
//                            .offset(right, i)
//                            .offset(down, z);
//
//                    world.setBlockState(portalPlace, frame.getDefaultState());
//                }
//            }
//        }
//
//        if (portal == null) {
//            // Making hole in portal
//            portal = Blocks.AIR;
//        }
//
//        BlockState state = portal.getDefaultState();
//
//        if (state.has(BlockStateProperties.AXIS)) {
//            state.with(BlockStateProperties.AXIS, right.getAxis());
//        }
//        // nether portal has that prop
//        if (state.has(BlockStateProperties.HORIZONTAL_AXIS)) {
//            state.with(BlockStateProperties.HORIZONTAL_AXIS, right.getAxis());
//        }
//
//        // width
//        for (int i = 0; i < 2; i++) {
//            // height
//            for (int z = 0; z < 3; z++) {
//                BlockPos portalPlace = topLeft
//                        // need to add 1 block offset because it is frame
//                        .offset(right, i + 1)
//                        .offset(down, z + 1);
//
//
//                world.setBlockState(portalPlace, state);
//            }
//        }
//    }
//
//    /**
//     * Create nether like portal pattern with frame and portal
//     *
//     * @param frame
//     * @param portal
//     * @return
//     */
//    public static BlockPattern createNetherLikePattern(Block frame, Block portal) {
//        return createNetherLikePattern(frame, BlockStateMatcher.forBlock(portal));
//    }
//
//
//
//    public static BlockPattern.PortalInfo createPortalInfo(BlockPattern.PatternHelper helper, Direction portalDirection,
//                                                           BlockPos pos, double chunkX, Vec3d vec, double chunkZ) {
//        if (helper == null)
//            return null;
//
//        Direction direction = helper.getForwards();
//        if (direction.getAxis() == Direction.Axis.Z) {
//            direction = helper.getUp();
//        }
//
//        Direction direction1 = direction.rotateY();
//        double d1 = (double) (helper.getFrontTopLeft().getY() + 1) - chunkX * (double) helper.getHeight();
//        double d0;
//        double d2;
//        if (direction1 == Direction.NORTH) {
//            d0 = (double) pos.getX() + 0.5D;
//            d2 = (double) (helper.getFrontTopLeft().getZ() + 1) - (1.0D - chunkZ) * (double) helper.getWidth();
//        } else if (direction1 == Direction.SOUTH) {
//            d0 = (double) pos.getX() + 0.5D;
//            d2 = (double) helper.getFrontTopLeft().getZ() + (1.0D - chunkZ) * (double) helper.getWidth();
//        } else if (direction1 == Direction.WEST) {
//            d0 = (double) (helper.getFrontTopLeft().getX() + 1) - (1.0D - chunkZ) * (double) helper.getWidth();
//            d2 = (double) pos.getZ() + 0.5D;
//        } else {
//            d0 = (double) helper.getFrontTopLeft().getX() + (1.0D - chunkZ) * (double) helper.getWidth();
//            d2 = (double) pos.getZ() + 0.5D;
//        }
//
//        double d3;
//        double d4;
//        if (direction.getOpposite() == portalDirection) {
//            d3 = vec.x;
//            d4 = vec.z;
//        } else if (direction.getOpposite() == portalDirection.getOpposite()) {
//            d3 = -vec.x;
//            d4 = -vec.z;
//        } else if (direction.getOpposite() == portalDirection.rotateY()) {
//            d3 = -vec.z;
//            d4 = vec.x;
//        } else {
//            d3 = vec.z;
//            d4 = -vec.x;
//        }
//
//        int i = (direction.getHorizontalIndex() - portalDirection.getOpposite().getHorizontalIndex()) * 90;
//        return new BlockPattern.PortalInfo(new Vec3d(d0, d1, d2), new Vec3d(d3, vec.y, d4), i);
//    }
//
//    /**
//     * Stolen from nether. Work with vertical nether-like portals
//     *
//     * @param world  - world
//     * @param pos    - pos
//     * @param frame  - frame block
//     * @param portal - portal block
//     */
//    public static BlockPattern.PatternHelper createHelperForTeleporter(World world, BlockPos pos, Block frame, DivinePortalBlock portal) {
//        Direction.Axis direction$axis = Direction.Axis.Z;
//        DivinePortalSize portalSize = new DivinePortalSize(frame, portal, world, pos, Direction.Axis.X);
//        LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(world, true);
//        if (!portalSize.isValid()) {
//            direction$axis = Direction.Axis.X;
//            portalSize = new DivinePortalSize(frame, portal, world, pos, Direction.Axis.Z);
//        }
//
//        if (!portalSize.isValid()) {
//            return new BlockPattern.PatternHelper(pos, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
//        } else {
//            int[] aint = new int[Direction.AxisDirection.values().length];
//            Direction direction = portalSize.rightDir.rotateYCCW();
//            BlockPos blockpos = portalSize.bottomLeft.up(portalSize.getHeight() - 1);
//
//            for (Direction.AxisDirection dir : Direction.AxisDirection.values()) {
//                BlockPattern.PatternHelper helper = new BlockPattern.PatternHelper(direction.getAxisDirection() == dir ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(dir, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);
//
//                for (int i = 0; i < portalSize.getWidth(); ++i) {
//                    for (int j = 0; j < portalSize.getHeight(); ++j) {
//                        CachedBlockInfo cachedblockinfo = helper.translateOffset(i, j, 1);
//                        if (!cachedblockinfo.getBlockState().isAir()) {
//                            ++aint[dir.ordinal()];
//                        }
//                    }
//                }
//            }
//
//            Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;
//
//            for (Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
//                if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
//                    direction$axisdirection1 = direction$axisdirection2;
//                }
//            }
//
//            return new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection1 ? blockpos : blockpos.offset(portalSize.rightDir, portalSize.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection1, direction$axis), Direction.UP, loadingcache, portalSize.getWidth(), portalSize.getHeight(), 1);
//        }
//    }
}
