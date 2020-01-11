package divinerpg.world.iceika.feature;

import divinerpg.world.structure.TemplateStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Random;

public class ChanceStructure extends TemplateStructure<ProbabilityConfig> {
    public ChanceStructure(ResourceLocation res, int sizeInChunks) {
        super(ProbabilityConfig::deserialize, res, sizeInChunks);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, ProbabilityConfig config) {
        return rand.nextFloat() < config.probability && super.place(worldIn, generator, rand, pos, config);
    }
}
