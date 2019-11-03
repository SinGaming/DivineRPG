package divinerpg.blocks.base;

import divinerpg.utils.DivinePlantType;
import divinerpg.utils.properties.block.ExtendedBlockProperties;
import divinerpg.utils.properties.block.IPlacementCheck;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.PlantType;

/**
 * Divine sapling. Created for open ctor and valid groung checks
 */
public class DivineSaplingBlock extends SaplingBlock {
    private final IPlacementCheck validGround;
    private final VoxelShape shape;
    private final DivinePlantType specialType;

    public DivineSaplingBlock(Tree tree, ExtendedBlockProperties properties) {
        super(tree, properties.props.doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT));
        validGround = properties.validGround;
        specialType = properties.type;

        shape = properties.shape;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return validGround == null
                ? super.isValidGround(state, worldIn, pos)
                : validGround.canPlace(state, worldIn, pos);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return specialType.type;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        // TODO implement ground check
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}
