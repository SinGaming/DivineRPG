package divinerpg.utils.portal;

import divinerpg.blocks.twilight.DivinePortalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import javax.annotation.Nullable;

public class DivinePortalSize {
    public final Direction rightDir;
    public final Direction leftDir;
    private final Block FRAME;
    private final Block FIRE = Blocks.AIR;
    private final DivinePortalBlock PORTAL;
    private final IWorld world;
    private final Direction.Axis axis;
    @Nullable
    public BlockPos bottomLeft;
    private int portalBlockCount;
    private int height;
    private int width;

    public DivinePortalSize(Block frame, DivinePortalBlock portal, IWorld world, BlockPos pos, Direction.Axis axis) {
        FRAME = frame;
        PORTAL = portal;

        this.world = world;
        this.axis = axis;
        if (axis == Direction.Axis.X) {
            this.leftDir = Direction.EAST;
            this.rightDir = Direction.WEST;
        } else {
            this.leftDir = Direction.NORTH;
            this.rightDir = Direction.SOUTH;
        }

        for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.isValidPortalBlock(world.getBlockState(pos.down())); pos = pos.down()) {
        }

        int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
        if (i >= 0) {
            this.bottomLeft = pos.offset(this.leftDir, i);
            this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
            if (this.width < 2 || this.width > 21) {
                this.bottomLeft = null;
                this.width = 0;
            }
        }

        if (this.bottomLeft != null) {
            this.height = this.calculatePortalHeight();
        }

    }

    protected int getDistanceUntilEdge(BlockPos pos, Direction dir) {
        int i;
        for (i = 0; i < 22; ++i) {
            BlockPos blockpos = pos.offset(dir, i);
            if (!this.isValidPortalBlock(this.world.getBlockState(blockpos)) || this.world.getBlockState(blockpos.down()).getBlock() != this.FRAME) {
                break;
            }
        }

        Block block = this.world.getBlockState(pos.offset(dir, i)).getBlock();
        return block == this.FRAME ? i : 0;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    protected int calculatePortalHeight() {
        label56:
        for (this.height = 0; this.height < 21; ++this.height) {
            for (int i = 0; i < this.width; ++i) {
                BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
                BlockState blockstate = this.world.getBlockState(blockpos);
                if (!this.isValidPortalBlock(blockstate)) {
                    break label56;
                }

                Block block = blockstate.getBlock();
                if (block == this.PORTAL) {
                    ++this.portalBlockCount;
                }

                if (i == 0) {
                    block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
                    if (block != this.FRAME) {
                        break label56;
                    }
                } else if (i == this.width - 1) {
                    block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
                    if (block != this.FRAME) {
                        break label56;
                    }
                }
            }
        }

        for (int j = 0; j < this.width; ++j) {
            if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height)).getBlock() != this.FRAME) {
                this.height = 0;
                break;
            }
        }

        if (this.height <= 21 && this.height >= 3) {
            return this.height;
        } else {
            this.bottomLeft = null;
            this.width = 0;
            this.height = 0;
            return 0;
        }
    }

    protected boolean isValidPortalBlock(BlockState pos) {
        Block block = pos.getBlock();
        return pos.isAir() || block == this.FIRE || block == this.PORTAL;
    }

    public boolean isValid() {
        return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
    }

    public void placePortalBlocks() {
        for (int i = 0; i < this.width; ++i) {
            BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

            for (int j = 0; j < this.height; ++j) {
                this.world.setBlockState(blockpos.up(j), this.PORTAL.getDefaultState().with(DivinePortalBlock.AXIS, this.axis), 18);
            }
        }

    }

    private boolean func_196899_f() {
        return this.portalBlockCount >= this.width * this.height;
    }

    public boolean func_208508_f() {
        return this.isValid() && this.func_196899_f();
    }
}
