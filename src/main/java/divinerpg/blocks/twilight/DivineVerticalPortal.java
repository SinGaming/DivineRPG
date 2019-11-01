package divinerpg.blocks.twilight;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class DivineVerticalPortal extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;

    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
    private final DimensionType type;
    private final BasicParticleType particle;

    public DivineVerticalPortal(Properties properties, DimensionType type, BasicParticleType particle) {
        super(properties.tickRandomly().doesNotBlockMovement().hardnessAndResistance(-1.0F).sound(SoundType.GLASS).lightValue(11).noDrops());
        this.type = type;
        this.particle = particle;

        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(AXIS) == Direction.Axis.Z)
            return Z_AABB;

        return X_AABB;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     *
     * @deprecated call via {@link BlockState#rotate(Rotation)} (Rotation)} whenever possible. Implementing/overriding is
     * fine.
     */
    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.get(AXIS)) {
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
     * will always be called regardless
     * of whether the block can receive random update ticks
     */
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double) ((float) pos.getX() + rand.nextFloat());
        double d1 = (double) ((float) pos.getY() + 0.8F);
        double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
        worldIn.addParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote
                && !entityIn.isPassenger()
                && !entityIn.isBeingRidden()
                && entityIn.isNonBoss()) {
            entityIn.changeDimension(worldIn.dimension.getType() == type
                    ? DimensionType.OVERWORLD
                    : type);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }
}
