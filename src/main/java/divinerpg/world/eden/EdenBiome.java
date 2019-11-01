package divinerpg.world.eden;

import divinerpg.registry.BlockRegistry;
import divinerpg.registry.FeatureRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class EdenBiome extends Biome {

    protected EdenBiome(Builder biomeBuilder) {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockRegistry.edenGrass.getDefaultState(),
                        BlockRegistry.edenDirt.getDefaultState(), BlockRegistry.edenDirt.getDefaultState()))
                .precipitation(RainType.RAIN)
                .category(Category.THEEND)
                .waterColor(Color.YELLOW.getRGB())
                .waterFogColor(Color.YELLOW.brighter().getRGB()));

        // Base gen
        this.addFeature(GenerationStage.Decoration.RAW_GENERATION,
                createDecoratedFeature(FeatureRegistry.twilight_stone_islands,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.END_ISLAND,
                        IPlacementConfig.NO_PLACEMENT_CONFIG));

        // Flowers gen
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(FeatureRegistry.eden_flowers_feature,
                IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(100)));
    }
}
