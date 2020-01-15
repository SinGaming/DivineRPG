package divinerpg.utils;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public static boolean checkSquare(World world, BlockPos pos, int xSize, int zSize, Block block) {
        if (world == null || pos == null || block == null)
            return false;

        BlockPos.MutableBlockPos i = new BlockPos.MutableBlockPos();

        try {
            world.getProfiler().startSection("DRPG.WorldGenUtil.checkSquare");

            for (int x = pos.getX(), endX = xSize + x; x <= endX; x++) {
                for (int z = pos.getZ(), endZ = zSize + z; z <= endZ; z++) {
                    i.setPos(x, pos.getY(), z);
                    if (world.getWorld().getBlockState(i).getBlock() != block) {
                        return false;
                    }
                }
            }

            return true;
        } finally {
            world.getProfiler().endSection();
        }
    }
}
