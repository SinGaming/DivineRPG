package divinerpg.blocks;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Custom tree with own gen feature
 */
public class DivineTree extends Tree {

    private AbstractTreeFeature<NoFeatureConfig> feature;

    public DivineTree(AbstractTreeFeature<NoFeatureConfig> feature) {
        this.feature = feature;
    }

    @Nullable
    @Override
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
        return feature;
    }
}
