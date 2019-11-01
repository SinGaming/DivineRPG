package divinerpg.utils.biome;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

@FunctionalInterface
public interface IRandomFlower {
    /**
     * Gets random flower for decoration
     *
     * @param pos - current position
     * @return blockstate of flower
     */
    BlockState getRandomFlower(Random random, BlockPos pos);
}
