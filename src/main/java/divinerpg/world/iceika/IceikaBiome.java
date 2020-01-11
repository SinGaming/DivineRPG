package divinerpg.world.iceika;

import divinerpg.registry.BlockRegistry;
import divinerpg.registry.FeatureRegistry;
import divinerpg.world.iceika.feature.HugeDivineTree;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.awt.*;
import java.util.Arrays;

public class IceikaBiome extends Biome {
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
                .temperature(-1)
                // TODO some unknown values below. If we can live with it, remove this line
                .parent("")
                .scale(0.025F)
                .downfall(0.0F)
                .depth(1.5F)
                .downfall(1)
        );

        // add giant tree
        addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
                createDecoratedFeature(
                        new HugeDivineTree(false, true, BlockRegistry.frozen_log, BlockRegistry.brittle_leaves, null),
                        IFeatureConfig.NO_FEATURE_CONFIG,
                        Placement.COUNT_EXTRA_HEIGHTMAP,
                        new AtSurfaceWithExtraConfig(3, 0.1F, 1)
                )
        );

        // lakes
        addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
                createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.ICE.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(4)));

        Arrays.asList(FeatureRegistry.COALSTONE_LAMP_1, FeatureRegistry.COALSTONE_LAMP_2, FeatureRegistry.COALSTONE_LAMP_3)
                .forEach(x -> addStructure(x, new ProbabilityConfig(1 / 10F)));
    }

    @Override
    public boolean doesWaterFreeze(IWorldReader worldIn, BlockPos pos) {
        return super.doesWaterFreeze(worldIn, pos);
    }
}
