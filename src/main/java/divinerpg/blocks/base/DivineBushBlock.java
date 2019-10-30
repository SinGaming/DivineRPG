package divinerpg.blocks.base;

import divinerpg.utils.properties.block.ExtendedBlockProperties;
import divinerpg.utils.properties.block.IPlacementCheck;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;

public class DivineBushBlock extends BushBlock {
    private final IPlacementCheck validGround;
    private final PlantType type;
    private final VoxelShape shape;

    public DivineBushBlock(ExtendedBlockProperties props) {
        super(props.props.doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT));
        validGround = props.validGround;
        type = props.type;
        shape = props.shape;
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
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }


    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return type;
    }
}
