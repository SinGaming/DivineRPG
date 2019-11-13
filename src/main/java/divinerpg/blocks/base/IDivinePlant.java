package divinerpg.blocks.base;

import divinerpg.utils.properties.block.IPlacementCheck;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

/**
 * Shared code beetween different plant types.
 * TODO Find out if overriding every crop type is not nessesary and we can
 * inherit directly from DivineBushBlock
 */
public interface IDivinePlant {
    /**
     * Perform check on post placement.
     *
     * @param validGround - valid groung callback
     * @param world       - current world
     * @param pos         - plant pos
     */
    default boolean canStandOnPostPlacement(IPlacementCheck validGround, IWorld world, BlockPos pos) {
        if (validGround != null) {
            BlockState down = world.getBlockState(pos.down());

            // Double plant can stand on itself
            if (this instanceof DoublePlantBlock && down.getBlock() == this) {
                return true;
            }

            // perform checks than
            return validGround.canPlace(down, world, pos);
        }

        return true;
    }
}
