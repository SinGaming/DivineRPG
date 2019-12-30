package divinerpg.items;

import divinerpg.utils.portal.PortalConstants;
import divinerpg.utils.portal.description.IPortalDescription;
import net.minecraft.block.Block;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TwilightClock extends Item {

    public TwilightClock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (super.onItemUse(context) == ActionResultType.FAIL)
            return ActionResultType.FAIL;

        World world = context.getWorld();
        BlockPos pos = context.getPos();
        Block frame = world.getBlockState(pos).getBlock();

        // frame can create portal
        IPortalDescription description = PortalConstants.findByFrame(frame);

        if (description != null
                // clicked within frame
                && isWithinFrame(world, pos, Direction.UP, 4)) {

            // is frame valid
            BlockPattern.PatternHelper match = description.matchFrame(world, pos);
            if (match != null) {
                // light portal
                description.lightPortal(world, match);
                return ActionResultType.SUCCESS;
            }
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
}
