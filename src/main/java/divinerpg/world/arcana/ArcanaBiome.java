package divinerpg.world.arcana;

import divinerpg.registry.FeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ArcanaBiome extends Biome {
    public ArcanaBiome() {
        super(CreateArcanaBuilder());

        addStructure(FeatureRegistry.arcana_maze, NoFeatureConfig.NO_FEATURE_CONFIG);
    }

    private static Builder CreateArcanaBuilder() {
        Builder builder = new Builder();
        BlockState state = Blocks.AIR.getDefaultState();

        builder.surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), state))
                .parent("")
                .scale(0.025F)
                .downfall(0.0F)
                .depth(1.5F)
                .downfall(1)
                .temperature(30)
                .category(Category.NONE);

        return builder;
    }
}
