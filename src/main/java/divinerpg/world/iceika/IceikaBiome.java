package divinerpg.world.iceika;

import divinerpg.DivineRPG;
import divinerpg.registry.BlockRegistry;
import divinerpg.world.structure.TemplateFeature;
import divinerpg.world.structure.TemplateFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;

public class IceikaBiome extends Biome {
    private final TemplateFeature<TemplateFeatureConfig> baseTemplateFeature = new TemplateFeature<>();

    public IceikaBiome() {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT,
                        new SurfaceBuilderConfig(BlockRegistry.frozen_grass.getDefaultState(),
                                BlockRegistry.frozen_dirt.getDefaultState(),
                                BlockRegistry.frozen_stone.getDefaultState()
                        )
                ).precipitation(RainType.SNOW)
                .category(Category.NONE)
                .waterColor(Color.BLUE.brighter().getRGB())
                .waterFogColor(Color.BLUE.brighter().brighter().getRGB())
                .temperature(0.01F)
                // TODO some unknown values below. If we can live with it, remove this line
                .parent("")
                .scale(0.025F)
                .depth(1.5F)
                .downfall(1)
        );

        // lakes
        addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
                Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState()))
                        .func_227228_a_(Placement.WATER_LAKE.func_227446_a_(new ChanceConfig(4))));

        // add giant tree
//        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
//                new HugeDivineTree(false, true, BlockRegistry.frozen_log, BlockRegistry.brittle_leaves, null).func_225566_b_(
//                        IFeatureConfig.NO_FEATURE_CONFIG)
//                        .func_227228_a_(Placement.COUNT_CHANCE_HEIGHTMAP.func_227446_a_(new HeightWithChanceConfig(3, 0.4F))));

        // lamps
        for (int i = 1; i <= 3; i++) {
            addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
                    baseTemplateFeature.func_225566_b_(
                            new TemplateFeatureConfig(new ResourceLocation(DivineRPG.MODID, "coalstone_lamp_" + i), BlockRegistry.frozen_grass.getDefaultState()))
                            .func_227228_a_(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.func_227446_a_(
                                    new ChanceConfig(25)
                            )));
        }

        // houses
        // todo add from 4 to 6
        for (int i = 1; i <= 3; i++) {
            addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
                    baseTemplateFeature.func_225566_b_(
                            new TemplateFeatureConfig(new ResourceLocation(DivineRPG.MODID, "workshop_house_" + i), BlockRegistry.frozen_grass.getDefaultState()))
                            .func_227228_a_(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.func_227446_a_(
                                    new ChanceConfig(10)
                            )));
        }
    }

    @Override
    public boolean doesWaterFreeze(IWorldReader worldIn, BlockPos pos) {
        return super.doesWaterFreeze(worldIn, pos);
    }
}
