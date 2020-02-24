package divinerpg.blocks;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Custom tree with own gen feature
 */
public class DivineTree extends Tree {
    private Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> feature;

    public DivineTree(Supplier<ConfiguredFeature<TreeFeatureConfig, ?>> feature) {
        this.feature = feature;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random p_225546_1_, boolean p_225546_2_) {
        return feature.get();
    }
}
