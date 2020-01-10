package divinerpg.world.iceika.feature;

import divinerpg.utils.DivineTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.HugeTreesFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

import java.util.Random;
import java.util.Set;

public class HugeDivineTree extends HugeTreesFeature<NoFeatureConfig> {
    private final boolean useBaseHeight;

    public HugeDivineTree(boolean doBlockNotifyOnPlace, boolean useBaseHeightIn,
                          Block log, Block leaf, IPlantable sapling) {
        super(NoFeatureConfig::deserialize, doBlockNotifyOnPlace, 13, 15, log.getDefaultState(), leaf.getDefaultState());
        this.useBaseHeight = useBaseHeightIn;

        setSapling(sapling);
    }

    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
        int i = this.getHeight(rand);
        if (!this.func_203427_a(worldIn, position, i)) {
            return false;
        } else {
            this.unknown_method(worldIn, position.getX(), position.getZ(), position.getY() + i, 0, rand, p_208519_5_, changedBlocks);

            for (int j = 0; j < i; ++j) {
                if (isAirOrLeaves(worldIn, position.up(j))) {
                    this.setLogState(changedBlocks, worldIn, position.up(j), this.trunk, p_208519_5_);
                }

                if (j < i - 1) {
                    if (isAirOrLeaves(worldIn, position.add(1, j, 0))) {
                        this.setLogState(changedBlocks, worldIn, position.add(1, j, 0), this.trunk, p_208519_5_);
                    }

                    if (isAirOrLeaves(worldIn, position.add(1, j, 1))) {
                        this.setLogState(changedBlocks, worldIn, position.add(1, j, 1), this.trunk, p_208519_5_);
                    }

                    if (isAirOrLeaves(worldIn, position.add(0, j, 1))) {
                        this.setLogState(changedBlocks, worldIn, position.add(0, j, 1), this.trunk, p_208519_5_);
                    }
                }
            }

            // this.generateSaplings(worldIn, rand, position);
            return true;
        }
    }

    private void unknown_method(IWorldGenerationReader p_214596_1_, int p_214596_2_, int p_214596_3_, int p_214596_4_, int p_214596_5_, Random p_214596_6_, MutableBoundingBox p_214596_7_, Set<BlockPos> p_214596_8_) {
        int i = p_214596_6_.nextInt(5) + (this.useBaseHeight ? this.baseHeight : 3);
        int j = 0;

        for (int k = p_214596_4_ - i; k <= p_214596_4_; ++k) {
            int l = p_214596_4_ - k;
            int i1 = p_214596_5_ + MathHelper.floor((float) l / (float) i * 3.5F);
            this.func_222839_a(p_214596_1_, new BlockPos(p_214596_2_, k, p_214596_3_), i1 + (l > 0 && i1 == j && (k & 1) == 0 ? 1 : 0), p_214596_7_, p_214596_8_);
            j = i1;
        }

    }

    /**
     * Can we place tree here
     *
     * @param worldIn
     * @param p_203427_2_
     * @param p_203427_3_
     * @return
     */
    protected boolean func_203427_a(IWorldGenerationReader worldIn, BlockPos p_203427_2_, int p_203427_3_) {
        return this.isSpaceAt(worldIn, p_203427_2_, p_203427_3_) && this.checkDirt(worldIn, p_203427_2_);
    }

    private boolean checkDirt(IWorldGenerationReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.down();

        if (isSoilInner(worldIn, blockpos, getSapling()) && pos.getY() >= 2) {
            setDirtAt(worldIn, blockpos, pos);
            setDirtAt(worldIn, blockpos.east(), pos);
            setDirtAt(worldIn, blockpos.south(), pos);
            setDirtAt(worldIn, blockpos.south().east(), pos);
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns whether or not there is space for a tree to grow at a certain position
     */
    private boolean isSpaceAt(IWorldGenerationBaseReader worldIn, BlockPos leavesPos, int height) {
        boolean flag = true;
        if (leavesPos.getY() >= 1 && leavesPos.getY() + height + 1 <= worldIn.getMaxHeight()) {
            for (int i = 0; i <= 1 + height; ++i) {
                int j = 2;
                if (i == 0) {
                    j = 1;
                } else if (i >= 1 + height - 2) {
                    j = 2;
                }

                for (int k = -j; k <= j && flag; ++k) {
                    for (int l = -j; l <= j && flag; ++l) {
                        if (leavesPos.getY() + i < 0 || leavesPos.getY() + i >= worldIn.getMaxHeight() || !isReplaceable(worldIn, leavesPos.add(k, i, l))) {
                            flag = false;
                        }
                    }
                }
            }

            return flag;
        } else {
            return false;
        }
    }

    @Override //Forge: moved to setDirtAt
    protected void func_214584_a(IWorldGenerationReader world, BlockPos pos) {
        // ignored
    }

    protected boolean isReplaceable(IWorldGenerationBaseReader world, BlockPos pos) {
        if (!(world instanceof net.minecraft.world.IWorldReader)) // FORGE: Redirect to state method when possible
            return world.hasBlockState(pos, (state) -> {
                Block block = state.getBlock();
                return state.isAir()
                        || state.isIn(BlockTags.LEAVES)
                        || DivineTags.GRASS.contains(block)
                        || Block.isDirt(block)
                        || block.isIn(BlockTags.LOGS)
                        || block.isIn(BlockTags.SAPLINGS)
                        || block == Blocks.VINE;
            });
        else
            return world.hasBlockState(pos, state -> state.canBeReplacedByLogs((net.minecraft.world.IWorldReader) world, pos));
    }

    protected boolean isSoilInner(IWorldGenerationBaseReader reader, BlockPos pos, net.minecraftforge.common.IPlantable sapling) {
        return reader.hasBlockState(pos, state -> {
            if (sapling != null)
                return state.canSustainPlant((net.minecraft.world.IBlockReader) reader, pos, Direction.UP, sapling);

            Block block = state.getBlock();
            return Block.isDirt(block) || DivineTags.GRASS.contains(block);
        });
    }
}
