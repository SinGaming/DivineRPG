package divinerpg.world.feature;

import divinerpg.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.state.BooleanProperty;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Copy of TreeFeature. Fully customizable
 */
public class DivineTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    protected final int minTreeHeight;

    private final Supplier<Block> trunk;
    private final Supplier<Block> leaf;
    private final Supplier<Block> cocoa;
    private final Supplier<Block> vien;
    private Supplier<SaplingBlock> saplingFunc;

    public DivineTreeFeature(boolean notifyClient, int minHeight, Supplier<SaplingBlock> sapling, Supplier<Block> trunkState, Supplier<Block> leafState) {
        this(notifyClient, minHeight, sapling, trunkState, leafState, null, null);
    }

    public DivineTreeFeature(boolean notifyClient, int minHeight, Supplier<SaplingBlock> sapling, Supplier<Block> trunkState, Supplier<Block> leafState,
                             Supplier<Block> cocoa, Supplier<Block> vien) {
        super(NoFeatureConfig::deserialize, notifyClient);
        this.minTreeHeight = minHeight;
        this.saplingFunc = sapling;
        this.trunk = trunkState;
        this.leaf = leafState;
        this.cocoa = cocoa;
        this.vien = vien;
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox size) {

        // Can grow vines
        boolean vinesGrow = vien != null;
        // Can grow cocoa
        boolean cocoaGrow = cocoa != null;

        int i = this.getHeight(rand);
        boolean canPlace = true;
        if (position.getY() >= 1 && position.getY() + i + 1 <= worldIn.getMaxHeight()) {

            // TODO not works in caves
            for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
                int k = 1;
                if (j == position.getY()) {
                    k = 0;
                }

                if (j >= position.getY() + 1 + i - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && canPlace; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && canPlace; ++i1) {
                        if (j >= 0 && j < worldIn.getMaxHeight()) {
                            if (!canPlaceSapling(worldIn, mutableBlockPos.setPos(l, j, i1), (SaplingBlock) getSapling())) {
                                canPlace = false;
                            }
                        } else {
                            canPlace = false;
                        }
                    }
                }
            }


            if (!canPlace) {
                return false;
            }


            if (isSoilOverrided(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - i - 1) {
                this.setDirtAt(worldIn, position.down(), position);
                BlockState cachedLeaf = leaf.get().getDefaultState();
                BlockState cachedTrunk = trunk.get().getDefaultState();

                for (int l2 = position.getY() - 3 + i; l2 <= position.getY() + i; ++l2) {
                    int l3 = l2 - (position.getY() + i);
                    int j4 = 1 - l3 / 2;

                    for (int j1 = position.getX() - j4; j1 <= position.getX() + j4; ++j1) {
                        int k1 = j1 - position.getX();

                        for (int l1 = position.getZ() - j4; l1 <= position.getZ() + j4; ++l1) {
                            int i2 = l1 - position.getZ();
                            if (Math.abs(k1) != j4 || Math.abs(i2) != j4 || rand.nextInt(2) != 0 && l3 != 0) {
                                BlockPos blockpos = new BlockPos(j1, l2, l1);
                                if (isAirOrLeaves(worldIn, blockpos) || func_214576_j(worldIn, blockpos)) {
                                    this.setLogState(changedBlocks, worldIn, blockpos, cachedLeaf, size);
                                }
                            }
                        }
                    }
                }

                for (int i3 = 0; i3 < i; ++i3) {
                    if (isAirOrLeaves(worldIn, position.up(i3)) || func_214576_j(worldIn, position.up(i3))) {
                        this.setLogState(changedBlocks, worldIn, position.up(i3), cachedTrunk, size);
                        if (vinesGrow && i3 > 0) {
                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(-1, i3, 0))) {
                                this.addVine(worldIn, position.add(-1, i3, 0), VineBlock.EAST);
                            }

                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(1, i3, 0))) {
                                this.addVine(worldIn, position.add(1, i3, 0), VineBlock.WEST);
                            }

                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(0, i3, -1))) {
                                this.addVine(worldIn, position.add(0, i3, -1), VineBlock.SOUTH);
                            }

                            if (rand.nextInt(3) > 0 && isAir(worldIn, position.add(0, i3, 1))) {
                                this.addVine(worldIn, position.add(0, i3, 1), VineBlock.NORTH);
                            }
                        }
                    }
                }

                if (vinesGrow) {
                    for (int j3 = position.getY() - 3 + i; j3 <= position.getY() + i; ++j3) {
                        int i4 = j3 - (position.getY() + i);
                        int k4 = 2 - i4 / 2;
                        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

                        for (int l4 = position.getX() - k4; l4 <= position.getX() + k4; ++l4) {
                            for (int i5 = position.getZ() - k4; i5 <= position.getZ() + k4; ++i5) {
                                mutableBlockPos.setPos(l4, j3, i5);
                                if (isLeaves(worldIn, mutableBlockPos)) {
                                    BlockPos blockpos3 = mutableBlockPos.west();
                                    BlockPos blockpos4 = mutableBlockPos.east();
                                    BlockPos blockpos1 = mutableBlockPos.north();
                                    BlockPos blockpos2 = mutableBlockPos.south();
                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos3)) {
                                        this.addHangingVine(worldIn, blockpos3, VineBlock.EAST);
                                    }

                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos4)) {
                                        this.addHangingVine(worldIn, blockpos4, VineBlock.WEST);
                                    }

                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos1)) {
                                        this.addHangingVine(worldIn, blockpos1, VineBlock.SOUTH);
                                    }

                                    if (rand.nextInt(4) == 0 && isAir(worldIn, blockpos2)) {
                                        this.addHangingVine(worldIn, blockpos2, VineBlock.NORTH);
                                    }
                                }
                            }
                        }
                    }

                    if (cocoaGrow) {
                        if (rand.nextInt(5) == 0 && i > 5) {
                            for (int k3 = 0; k3 < 2; ++k3) {
                                for (Direction direction : Direction.Plane.HORIZONTAL) {
                                    if (rand.nextInt(4 - k3) == 0) {
                                        Direction direction1 = direction.getOpposite();
                                        this.placeCocoa(worldIn, rand.nextInt(3), position.add(direction1.getXOffset(), i - 5 + k3, direction1.getZOffset()), direction);
                                    }
                                }
                            }
                        }
                    }
                }

                return true;
            }
        }

        return false;
    }

    protected int getHeight(Random random) {
        return this.minTreeHeight + random.nextInt(6);
    }

    private void placeCocoa(IWorldWriter worldIn, int age, BlockPos pos, Direction side) {
        this.setBlockState(worldIn, pos, cocoa.get().getDefaultState());
    }

    private void addVine(IWorldWriter worldIn, BlockPos pos, BooleanProperty prop) {
        this.setBlockState(worldIn, pos, vien.get().getDefaultState().with(prop, Boolean.TRUE));
    }

    private void addHangingVine(IWorldGenerationReader worldIn, BlockPos pos, BooleanProperty prop) {
        this.addVine(worldIn, pos, prop);
        int i = 4;

        for (BlockPos blockpos = pos.down(); isAir(worldIn, blockpos) && i > 0; --i) {
            this.addVine(worldIn, blockpos, prop);
            blockpos = blockpos.down();
        }

    }

    protected boolean canPlaceSapling(IWorldGenerationBaseReader world, BlockPos pos, SaplingBlock sapling) {
        // TODO work with tags, json currently not working
        return world.hasBlockState(pos, state -> (!(world instanceof IWorldReader) || sapling.isValidPosition(state, (IWorldReader) world, pos))
                || state.isIn(BlockTags.LEAVES)
                || state.isAir()
                || state.isIn(BlockTags.LOGS)
                || state.isIn(BlockTags.SAPLINGS)
                // TODO think about instanceof
                || state.getBlock() == Blocks.VINE);
    }

    @Override
    protected void func_214584_a(IWorldGenerationReader p_214584_1_, BlockPos p_214584_2_) {
        // ignore dirt replacing
    }

    protected boolean isSoilOverrided(IWorldGenerationBaseReader reader, BlockPos pos, net.minecraftforge.common.IPlantable sapling) {
        if (!(reader instanceof net.minecraft.world.IBlockReader) || sapling == null)
            return isDirtOrGrassBlockOverrided(reader, pos);
        return reader.hasBlockState(pos, state -> state.canSustainPlant((net.minecraft.world.IBlockReader) reader, pos, Direction.UP, sapling));
    }

    protected boolean isDirtOrGrassBlockOverrided(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        return worldIn.hasBlockState(pos, (p_214582_0_) -> {
            Block block = p_214582_0_.getBlock();
            return Block.isDirt(block) || BlockRegistry.DIVINE_GRASS.contains(block);
        });
    }

    @Override
    protected IPlantable getSapling() {
        return saplingFunc.get();
    }
}
