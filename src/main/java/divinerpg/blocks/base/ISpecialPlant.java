package divinerpg.blocks.base;

import divinerpg.utils.properties.block.IPlacementCheck;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public interface ISpecialPlant {
    /**
     * Perform check on post placement.
     *
     * @param validGround - valid groung callback
     * @param state       - current state
     * @param world       - current world
     * @param pos         - plant pos
     */
    default boolean canStandOnPostPlacement(IPlacementCheck validGround, BlockState state, IWorld world, BlockPos pos) {
        if (validGround != null) {
            BlockState down = world.getBlockState(pos.down());

            // Double plant can stand on itself
            if (this instanceof DoublePlantBlock && down.getBlock() == this) {
                return true;
            }

            // perform checks than
            return validGround.canPlace(state, world, pos);
        }

        return true;
    }
}
