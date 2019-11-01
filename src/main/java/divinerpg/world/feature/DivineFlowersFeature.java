package divinerpg.world.feature;

import divinerpg.utils.biome.IRandomFlower;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * Customizable flower feature
 */
public class DivineFlowersFeature extends FlowersFeature {
    private final IRandomFlower randomFlower;

    public DivineFlowersFeature(IRandomFlower randomFlower) {
        super(NoFeatureConfig::deserialize);
        this.randomFlower = randomFlower;
    }

    @Override
    public BlockState getRandomFlower(Random random, BlockPos pos) {
        return randomFlower.getRandomFlower(random, pos);
    }
}
