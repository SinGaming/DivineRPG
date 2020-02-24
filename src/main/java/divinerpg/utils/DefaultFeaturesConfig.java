package divinerpg.utils;

import com.google.common.collect.ImmutableList;
import divinerpg.registry.BlockRegistry;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;

public class DefaultFeaturesConfig {
    public static TreeFeatureConfig eden_tree_config = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockRegistry.edenLog.getDefaultState()),
            new SimpleBlockStateProvider(BlockRegistry.edenLeaves.getDefaultState()),
            // radius, radius random
            new BlobFoliagePlacer(2, 0)))
            // height
            .func_225569_d_(7)
            // heightRandA
            .func_227354_b_(3)
            // foliageHeight
            .func_227360_i_(3)
            // ignoreVines
            .func_227352_a_()
            // decorations
            .func_227353_a_(ImmutableList.of(new BeehiveTreeDecorator(0.05F)))
            .setSapling(BlockRegistry.edenSapling).func_225568_b_();

    public static TreeFeatureConfig wildwood_tree_config = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockRegistry.wildwoodLog.getDefaultState()),
            new SimpleBlockStateProvider(BlockRegistry.wildwoodLeaves.getDefaultState()),
            // radius, radius random
            new BlobFoliagePlacer(2, 0)))
            // height
            .func_225569_d_(10)
            // heightRandA
            .func_227354_b_(5)
            // foliageHeight
            .func_227360_i_(3)
            // decorations
            .func_227353_a_(ImmutableList.of(new BeehiveTreeDecorator(0.05F)))
            .setSapling(BlockRegistry.wildwoodSapling).func_225568_b_();

    public static TreeFeatureConfig apalachia_tree_config = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockRegistry.apalachiaLog.getDefaultState()),
            new SimpleBlockStateProvider(BlockRegistry.apalachiaLeaves.getDefaultState()),
            // radius, radius random
            new BlobFoliagePlacer(2, 0)))
            // height
            .func_225569_d_(5)
            // heightRandA
            .func_227354_b_(2)
            // foliageHeight
            .func_227360_i_(3)
            // decorations
            .func_227353_a_(ImmutableList.of(new BeehiveTreeDecorator(0.05F)))
            .setSapling(BlockRegistry.apalachiaSapling).func_225568_b_();

    public static TreeFeatureConfig skythern_tree_config = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockRegistry.skythernLog.getDefaultState()),
            new SimpleBlockStateProvider(BlockRegistry.skythernLeaves.getDefaultState()),
            // radius, radius random
            new BlobFoliagePlacer(2, 0)))
            // height
            .func_225569_d_(7)
            // heightRandA
            .func_227354_b_(1)
            // foliageHeight
            .func_227360_i_(3)
            // decorations
            .func_227353_a_(ImmutableList.of(new BeehiveTreeDecorator(0.05F)))
            .setSapling(BlockRegistry.skythernSapling).func_225568_b_();

    public static TreeFeatureConfig mortum_tree_config = (new TreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BlockRegistry.mortumLog.getDefaultState()),
            new SimpleBlockStateProvider(BlockRegistry.mortumLeaves.getDefaultState()),
            // radius, radius random
            new BlobFoliagePlacer(2, 0)))
            // height
            .func_225569_d_(5)
            // heightRandA
            .func_227354_b_(0)
            // foliageHeight
            .func_227360_i_(3)
            // decorations
            .func_227353_a_(ImmutableList.of(new BeehiveTreeDecorator(0.05F)))
            .setSapling(BlockRegistry.mortumSapling).func_225568_b_();


}
