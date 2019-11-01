package divinerpg.world.eden;

import divinerpg.DivineRPG;
import divinerpg.registry.BlockRegistry;
import divinerpg.registry.FeatureRegistry;
import divinerpg.world.feature.config.CustomFillerBlockType;
import divinerpg.world.feature.config.DivineCountRangeConfig;
import divinerpg.world.feature.config.DivineOreFeatureConfig;
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

    public EdenBiome() {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockRegistry.edenGrass.getDefaultState(),
                        BlockRegistry.edenDirt.getDefaultState(), BlockRegistry.edenDirt.getDefaultState()))
                .precipitation(RainType.RAIN)
                .category(Category.THEEND)
                .waterColor(Color.YELLOW.getRGB())
                .waterFogColor(Color.YELLOW.brighter().getRGB())
                .parent("")
                .temperature(0.7F)
                .scale(0.025F)
                .downfall(0.0F)
                .depth(1.5F)
                .downfall(1)
        );

        // Base gen
        this.addFeature(GenerationStage.Decoration.RAW_GENERATION,
                createDecoratedFeature(FeatureRegistry.twilight_stone_islands,
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.END_ISLAND,
                        IPlacementConfig.NO_PLACEMENT_CONFIG));

        // Flowers gen
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(FeatureRegistry.eden_flowers_feature,
                IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(100)));

        // ore gen
        addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(FeatureRegistry.ORE,
                new DivineOreFeatureConfig(CustomFillerBlockType.TWILIGHT, BlockRegistry.edenOre.getDefaultState(),
                        DivineRPG.CONFIG.worlgen.twilights.vein.get()),
                Placement.COUNT_RANGE,
                new DivineCountRangeConfig(DivineRPG.CONFIG.worlgen.twilights)));
    }
}
