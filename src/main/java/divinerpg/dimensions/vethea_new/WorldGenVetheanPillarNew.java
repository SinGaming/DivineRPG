package divinerpg.dimensions.vethea_new;

import divinerpg.registry.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenVetheanPillarNew extends WorldGenerator {
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int var1 = 34;
        int var2 = rand.nextInt(4) + 3;

        for (int i = 0; i < var1; i++) {
            this.placeBlockCircle(worldIn, position.add(0, i, 0), Math.abs(((var1 / 2) - i)) / 5 + var2);
        }

        return false;
    }

    private void placeBlockCircle(World worldIn, BlockPos pos, int radius) {
        if (radius >= 9)
            radius = 8;

        for (float i = 0; i < radius; i += 0.5) {
            for (float j = 0; j < 2 * Math.PI * i; j += 0.5) {
                BlockPos position = pos.add(
                        Math.sin(j) * i,
                        0,
                        Math.cos(j) * i);

                worldIn.setBlockState(position, ModBlocks.dreamStone.getDefaultState());
            }
        }
    }
}
