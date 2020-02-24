package divinerpg.blocks;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.*;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Custom tree with own gen feature
 */
public class DivineTree extends Tree {

    private AbstractTreeFeature<BaseTreeFeatureConfig> feature;

    public DivineTree(AbstractTreeFeature<BaseTreeFeatureConfig> feature) {
        this.feature = feature;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random p_225546_1_, boolean p_225546_2_) {
        return Feature.NORMAL_TREE.func_225566_b_(p_225546_2_ ? DefaultBiomeFeatures.field_230136_s_ : DefaultBiomeFeatures.field_226812_g_);;
    }
}
