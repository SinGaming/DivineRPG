package divinerpg.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Supplier;

public class IslandFeature extends Feature<NoFeatureConfig> {
    private Supplier<BlockState> stateFunc;

    public IslandFeature(Supplier<BlockState> state) {
        super(NoFeatureConfig::deserialize);
        this.stateFunc = state;
    }

    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config) {
        float f = (float) (rand.nextInt(3) + 4);

        for (int i = 0; f > 0.5F; --i) {
            for (int j = MathHelper.floor(-f); j <= MathHelper.ceil(f); ++j) {
                for (int k = MathHelper.floor(-f); k <= MathHelper.ceil(f); ++k) {
                    if ((float) (j * j + k * k) <= (f + 1.0F) * (f + 1.0F)) {
                        this.setBlockState(world, pos.add(j, i, k), stateFunc.get());
                    }
                }
            }

            f = (float) ((double) f - ((double) rand.nextInt(2) + 0.5D));
        }

        return true;
    }
}
