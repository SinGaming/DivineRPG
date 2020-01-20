package divinerpg.blocks;

import divinerpg.registry.BlockRegistry;
import divinerpg.registry.FluidRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class TarFluid extends LavaFluid {
    private final boolean isSource;

    public TarFluid(boolean isSource) {
        this.isSource = isSource;
    }

    @Override
    public Fluid getFlowingFluid() {
        return FluidRegistry.flowing_tar;
    }

    @Override
    public Fluid getStillFluid() {
        return FluidRegistry.tar;
    }

    @Override
    public BlockState getBlockState(IFluidState state) {
        // todo correct block
        return Blocks.LAVA.getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn) {
        return fluidIn == getFlowingFluid() || fluidIn == getStillFluid();
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_) {
        // A bit slowlier than lava
        return super.getTickRate(p_205569_1_) * 5;
    }

    @Override
    protected void flowInto(IWorld worldIn, BlockPos pos, BlockState blockStateIn, Direction direction, IFluidState fluidStateIn) {
        if (direction == Direction.DOWN) {
            IFluidState ifluidstate = worldIn.getFluidState(pos);
            if (this.isIn(FluidTags.LAVA) && ifluidstate.isTagged(FluidTags.WATER)) {
                if (blockStateIn.getBlock() instanceof FlowingFluidBlock) {
                    worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, BlockRegistry.twilightStone.getDefaultState()), 3);
                }

                this.triggerEffects(worldIn, pos);
                return;
            }
        }

        super.flowInto(worldIn, pos, blockStateIn, direction, fluidStateIn);
    }

    private void triggerEffects(IWorld world, BlockPos pos) {
        world.playEvent(1501, pos, 0);
    }

    @Override
    public boolean isSource(IFluidState state) {
        return isSource;
    }

    @Override
    public int getLevel(IFluidState state) {
        return isSource ? 8 : state.get(BlockStateProperties.LEVEL_1_8);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
        super.fillStateContainer(builder);

        if (!isSource)
            builder.add(BlockStateProperties.LEVEL_1_8);
    }
}
