package divinerpg.utils.properties.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

@FunctionalInterface
public interface IPlacementCheck {

    /**
     * Detects if we can place current plant on block
     *
     * @param state   - grass block state
     * @param worldIn - current world
     * @param pos     - block position
     * @return - success of possibility to plant
     */
    boolean canPlace(BlockState state, IBlockReader worldIn, BlockPos pos);
}
