package divinerpg.items;

import divinerpg.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;

import static divinerpg.utils.PortalHelper.createNetherLikePattern;

public class TwilightClock extends Item {

    private final HashMap<Block, Block> netherLikePortals = new HashMap<>();

    public TwilightClock(Properties properties) {
        super(properties);

        // TODO add actual portals
        netherLikePortals.put(Blocks.OBSIDIAN, Blocks.NETHER_PORTAL);
        netherLikePortals.put(BlockRegistry.divineRock, BlockRegistry.edenPortal);
        netherLikePortals.put(BlockRegistry.edenBlock, BlockRegistry.wildwoodPortal);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (super.onItemUse(context) == ActionResultType.FAIL)
            return ActionResultType.FAIL;

        World world = context.getWorld();
        BlockPos pos = context.getPos();
        Block frame = world.getBlockState(pos).getBlock();

        if (netherLikePortals.containsKey(frame)
                && isWithinFrame(world, pos, Direction.UP, 4)
                && tryCreateNetherLikePortal(world, pos, frame, netherLikePortals.get(frame))) {
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    /**
     * Check if we clicked on bottom of the frame
     *
     * @param world        - world
     * @param pos          - clicked position
     * @param face         - player sight direction
     * @param portalHeight - portal frame height
     * @return - if we clicked inside portal
     */
    private boolean isWithinFrame(World world, BlockPos pos, Direction face, int portalHeight) {
        Block frame = world.getBlockState(pos).getBlock();

        // Must click within frame
        if (!world.isAirBlock(pos.offset(face))) {
            return false;
        }

        for (int i = 1; i <= portalHeight; i++) {
            if (world.getBlockState(pos.offset(face, i)).getBlock() == frame) {
                return true;
            }
        }

        return false;
    }

    /**
     * Trying to create nether like portal.
     *
     * @param pos    - pos of clicked block
     * @param frame  - clicked block. Already checked above if it one of well-known portal frames
     * @param portal - assotiated portal block with current frame
     * @return true if activated
     */
    private boolean tryCreateNetherLikePortal(World world, BlockPos pos, Block frame, Block portal) {
        // Pretty heavy function, think about it
        // TODO think how to replace deprecated method
        BlockPattern.PatternHelper match = createNetherLikePattern(frame, BlockState::isAir).match(world, pos);
        if (match == null)
            return false;

        Direction rightDir = match.getUp().rotateYCCW();
        Direction downDir = match.getForwards();

        ArrayList<BlockPos> airBlocks = new ArrayList<>();

        // width
        for (int i = 0; i < 2; i++) {
            // height
            for (int z = 0; z < 3; z++) {
                BlockPos portalPlace = match.getFrontTopLeft()
                        // need to add 1 block offset because it is frame
                        .offset(rightDir, i + 1)
                        .offset(downDir, z + 1);

                airBlocks.add(portalPlace);
            }
        }

        BlockState portalState = portal.getDefaultState();

        // set block orientation
        if (portalState.has(BlockStateProperties.AXIS)) {
            portalState = portalState.with(BlockStateProperties.AXIS, rightDir.getAxis());
        }

        for (BlockPos x : airBlocks) {
            world.setBlockState(x, portalState);
        }

        return true;
    }
}
