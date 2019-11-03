package divinerpg.world.eden;

import com.google.common.collect.Lists;
import divinerpg.DivineRPG;
import divinerpg.registry.BlockRegistry;
import divinerpg.registry.FeatureRegistry;
import divinerpg.world.feature.config.CustomFillerBlockType;
import divinerpg.world.feature.config.DivineCountRangeConfig;
import divinerpg.world.feature.config.DivineOreFeatureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
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
                .category(Category.NONE)
                .waterColor(Color.YELLOW.getRGB())
                .waterFogColor(Color.YELLOW.brighter().getRGB())
                // TODO some unknown values below. If we can live with it, remove this line
                .parent("")
                .temperature(0.7F)
                .scale(0.025F)
                .downfall(0.0F)
                .depth(1.5F)
                .downfall(1)
        );

        // Flowers gen
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(FeatureRegistry.eden_flowers_feature,
                IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(100)));

        // Add twilight stone gen
        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(BlockRegistry.twilightStone.getDefaultState(),
                                8, 8, Lists.newArrayList(BlockRegistry.edenGrass.getDefaultState(), BlockRegistry.edenDirt.getDefaultState())),
                        Placement.CHANCE_PASSTHROUGH, new ChanceConfig(10)));

        // Tree gen
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(FeatureRegistry.eden_tree_feature,
                IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(8)));

        // ore gen
        addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(FeatureRegistry.ORE,
                new DivineOreFeatureConfig(CustomFillerBlockType.TWILIGHT, BlockRegistry.edenOre.getDefaultState(),
                        DivineRPG.CONFIG.worlgen.twilights.vein.get()),
                Placement.COUNT_RANGE,
                new DivineCountRangeConfig(DivineRPG.CONFIG.worlgen.twilights)));
    }
}
