package divinerpg.dimensions.vethea;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenVetheanFlower extends WorldGenerator {

    private Block flower;

    public WorldGenVetheanFlower(Block flowerIn) {
        flower = flowerIn;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 64; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8) + 8,
                    rand.nextInt(4) - rand.nextInt(4),
                    rand.nextInt(8) - rand.nextInt(8) + 8);


            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && this.flower.canPlaceBlockAt(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos, this.flower.getDefaultState(), 2);
            }
        }

        return true;
    }
}