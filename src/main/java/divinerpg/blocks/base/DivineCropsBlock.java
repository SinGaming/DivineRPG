package divinerpg.blocks.base;

import divinerpg.utils.DivinePlantType;
import divinerpg.utils.properties.block.ExtendedBlockProperties;
import divinerpg.utils.properties.block.IPlacementCheck;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.PlantType;

import java.util.Random;
import java.util.function.Supplier;

public class DivineCropsBlock extends CropsBlock implements IDivinePlant {
    private final IPlacementCheck validGround;
    private final DivinePlantType specialType;
    private final boolean canUseBonemeal;
    private final int maxAge;
    private final Supplier<IItemProvider> getSeed;

    public DivineCropsBlock(ExtendedBlockProperties builder) {
        super(builder.props.doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT).tickRandomly());
        validGround = builder.validGround;
        specialType = builder.type;
        canUseBonemeal = !builder.noBoneMeal;
        maxAge = builder.maxAge;

        getSeed = builder.getSeed == null
                ? () -> super.getSeedsItem()
                : builder.getSeed;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return validGround == null
                ? super.isValidGround(state, worldIn, pos)
                : validGround.canPlace(state, worldIn, pos);
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return specialType.type;
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!canStandOnPostPlacement(validGround, worldIn, currentPos)) {
            return Blocks.AIR.getDefaultState();
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    protected IItemProvider getSeedsItem() {
        return getSeed.get();
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return canUseBonemeal;
    }
}
