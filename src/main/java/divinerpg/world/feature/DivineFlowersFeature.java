package divinerpg.world.feature;

import divinerpg.world.feature.config.FlowerFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Customizable flower feature
 */
public class DivineFlowersFeature extends Feature<FlowerFeatureConfig> {
    public DivineFlowersFeature() {
        super(FlowerFeatureConfig::deserialize);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, FlowerFeatureConfig config) {
        BlockState blockstate = this.getRandomFlower(rand, pos, config);
        int i = 0;

        for (int j = 0; j < 64; ++j) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos, blockstate, 2);
                ++i;
            }
        }

        return i > 0;
    }

    public BlockState getRandomFlower(Random random, BlockPos pos, FlowerFeatureConfig config) {
        int max = config.bushW + config.flowerW + config.grassW;

        List<Tuple<Integer, BlockState>> tuples = Arrays.asList(
                new Tuple<>(config.bushW, config.bush),
                new Tuple<>(config.flowerW, config.flower),
                new Tuple<>(config.grassW, config.grass));

        // back sort
        tuples.sort((l, r) -> r.getA().compareTo(l.getA()));

        int rand = random.nextInt(max);
        int edge = max;

        for (Tuple<Integer, BlockState> keyVal : tuples) {
            edge -= keyVal.getA();

            if (rand >= edge)
                return keyVal.getB();
        }

        if (tuples.isEmpty())
            return Blocks.AIR.getDefaultState();

        return tuples.get(tuples.size() - 1).getB();
    }
}
