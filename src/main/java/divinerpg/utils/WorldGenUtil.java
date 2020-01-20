package divinerpg.utils;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.concurrent.atomic.AtomicBoolean;

public class WorldGenUtil {

    /**
     * Pretty much optimized check if square of blocks
     *
     * @param world - world
     * @param pos   - position
     * @param xSize - x size of rectangle
     * @param zSize - z  size of rectangle
     * @param block - block below
     * @return
     */
    public static boolean checkSquare(IWorld world, BlockPos pos, int xSize, int zSize, Block block) {
        return checkCube(world, pos, pos.add(xSize, 0, zSize), block);
    }

    /**
     * Checks cube for single block
     *
     * @param world - world
     * @param pos   - start position
     * @param size  - size of cube (can be negative)
     * @param block - searching for current block
     * @return
     */
    public static boolean checkCube(IWorld world, BlockPos pos, BlockPos size, Block block) {
        if (world == null || pos == null || block == null || size == null)
            return false;

        world.getWorld().getProfiler().startSection("DRPG.WorldGenUtil.checkCube");

        AtomicBoolean result = new AtomicBoolean(true);

        BlockPos.getAllInBoxMutable(pos, pos.add(size)).forEach(x -> {
            if (world.getBlockState(x).getBlock() != block) {
                result.set(false);
                return;
            }
        });

        world.getWorld().getProfiler().endSection();

        return result.get();
    }
}
