package divinerpg.blocks.twilight;

import divinerpg.tile.statue.StatueConstants;
import divinerpg.tile.statue.TileEntityStatue;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

// TODO breaking particles
public class StatueBlock extends ContainerBlock {
    private final String name;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public StatueBlock(String name) {
        super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(6).harvestTool(ToolType.PICKAXE).harvestLevel(0)
        // notSolid
        .func_226896_b_());
        this.name = name;

        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return createNewTileEntity(world);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new TileEntityStatue(name);
    }

    /**
     * Render only model, do not need to render block
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return StatueConstants.getShape(name);
    }

    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    /**
     * Adds FACING prop to builder
     */
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlayer().getHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(FACING, mirrorIn.mirror(state.get(FACING)));
    }

    /**
     * Gets name of statue
     */
    public String getName() {
        return name;
    }
}
