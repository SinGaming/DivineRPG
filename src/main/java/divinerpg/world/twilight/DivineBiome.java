package divinerpg.world.twilight;

import divinerpg.DivineRPG;
import divinerpg.registry.BlockRegistry;
import divinerpg.registry.FeatureRegistry;
import divinerpg.world.twilight.feature.config.CustomFillerBlockType;
import divinerpg.world.twilight.feature.config.DivineCountRangeConfig;
import divinerpg.world.twilight.feature.config.DivineOreFeatureConfig;
import divinerpg.world.twilight.feature.config.FlowerFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class DivineBiome extends Biome {
    /**
     * Divine biome
     *
     * @param grass         - biome grass
     * @param dirt          - biome dirt
     * @param biomeColor    - color of biome water and fog
     * @param dirtFiller    - filler for twilight stone gen
     * @param tree          - tree feature
     * @param rarestFlower  - the rarest flower (10%)
     * @param commonFlower  - common flower (40%)
     * @param regularFlower - the most popular flower (50%)
     * @param ore           - biome ore
     */
    public DivineBiome(Block grass, Block dirt, Color biomeColor, CustomFillerBlockType dirtFiller, TreeFeatureConfig tree,
                       Block rarestFlower, Block commonFlower, Block regularFlower, Block ore) {
        this(new Builder()
                        .surfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(grass.getDefaultState(),
                                dirt.getDefaultState(), BlockRegistry.twilightStone.getDefaultState())),
                biomeColor, dirtFiller, tree, rarestFlower, commonFlower, regularFlower, ore);

    }

    public DivineBiome(Builder builder, Color biomeColor, CustomFillerBlockType dirtFiller, TreeFeatureConfig treeConfig,
                       Block rarestFlower, Block commonFlower, Block regularFlower, Block ore) {
        super(builder
                .precipitation(RainType.RAIN)
                .category(Category.NONE)
                .waterColor(biomeColor.getRGB())
                .waterFogColor(biomeColor.brighter().getRGB())
                // TODO some unknown values below. If we can live with it, remove this line
                .parent("")
                .temperature(0.7F)
                .scale(0.025F)
                .downfall(0.0F)
                .depth(1.5F)
                .downfall(1));

        // Add twilight stone gen
        addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                FeatureRegistry.ORE.func_225566_b_(new DivineOreFeatureConfig(dirtFiller, BlockRegistry.twilightStone.getDefaultState(), 50))
                        .func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(
                                new CountRangeConfig(50, 0, 0, 256)))
        );

        // trees
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                Feature.NORMAL_TREE.func_225566_b_(treeConfig).func_227228_a_(
                        Placement.COUNT_HEIGHTMAP.func_227446_a_(
                                new FrequencyConfig(8)))
        );

        // Flowers gen
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                FeatureRegistry.flowers_feature.func_225566_b_(
                        new FlowerFeatureConfig(10, rarestFlower.getDefaultState(),
                                40, commonFlower.getDefaultState(),
                                50, regularFlower.getDefaultState())
                ).func_227228_a_(
                        Placement.COUNT_HEIGHTMAP_32.func_227446_a_(
                                new FrequencyConfig(100)))
        );

        // ore gen
        addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                FeatureRegistry.ORE.func_225566_b_(new DivineOreFeatureConfig(CustomFillerBlockType.TWILIGHT,
                        ore.getDefaultState(), DivineRPG.CONFIG.worlgen.twilights.vein.get()))
                        .func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(
                                new DivineCountRangeConfig(DivineRPG.CONFIG.worlgen.twilights)))
        );
    }
}
