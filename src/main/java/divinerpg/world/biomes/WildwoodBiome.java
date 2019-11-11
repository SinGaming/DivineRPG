package divinerpg.world.biomes;

import divinerpg.DivineRPG;
import divinerpg.registry.BlockRegistry;
import divinerpg.registry.FeatureRegistry;
import divinerpg.world.feature.config.CustomFillerBlockType;
import divinerpg.world.feature.config.DivineCountRangeConfig;
import divinerpg.world.feature.config.DivineOreFeatureConfig;
import divinerpg.world.feature.config.FlowerFeatureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class WildwoodBiome extends Biome {

    public WildwoodBiome() {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlockRegistry.wildwoodGrass.getDefaultState(),
                        BlockRegistry.wildwoodDirt.getDefaultState(), BlockRegistry.twilightStone.getDefaultState()))
                .precipitation(RainType.RAIN)
                .category(Category.NONE)
                .waterColor(Color.BLUE.getRGB())
                .waterFogColor(Color.BLUE.brighter().getRGB())
                // TODO some unknown values below. If we can live with it, remove this line
                .parent("")
                .temperature(0.7F)
                .scale(0.025F)
                .downfall(0.0F)
                .depth(1.5F)
                .downfall(1)
        );

        // Tree gen
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(FeatureRegistry.wildwood_tree_feature,
                IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP, new FrequencyConfig(8)));

        // Flowers gen
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, createDecoratedFeature(FeatureRegistry.flowers_feature,
                new FlowerFeatureConfig(10, BlockRegistry.sunbloom.getDefaultState(),
                        40, BlockRegistry.moonlightFern.getDefaultState(),
                        50, BlockRegistry.moonBud.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(100)));

        // Add twilight stone gen
//        this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
//                Biome.createDecoratedFeature(Feature.DISK, new SphereReplaceConfig(BlockRegistry.twilightStone.getDefaultState(),
//                                8, 8, Lists.newArrayList(BlockRegistry.wildwoodGrass.getDefaultState(), BlockRegistry.wildwoodDirt.getDefaultState())),
//                        Placement.CHANCE_PASSTHROUGH, new ChanceConfig(10)));

        // ore gen
        addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(FeatureRegistry.ORE,
                new DivineOreFeatureConfig(CustomFillerBlockType.TWILIGHT, BlockRegistry.wildwoodOre.getDefaultState(),
                        DivineRPG.CONFIG.worlgen.twilights.vein.get()),
                Placement.COUNT_RANGE,
                new DivineCountRangeConfig(DivineRPG.CONFIG.worlgen.twilights)));
    }
}
