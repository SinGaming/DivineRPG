package divinerpg.blocks.twilight;

import divinerpg.utils.PortalHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Custom dim travel and particle
 */
public class DivinePortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    protected static final VoxelShape Y_AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
    private final Supplier<DimensionType> type;
    private final IParticleData particle;
    private final Supplier<Block> frame;

    public DivinePortalBlock(Properties properties, Supplier<DimensionType> type, Supplier<Block> frame, IParticleData particle) {
        this(properties, type, frame, particle, false);
    }

    public DivinePortalBlock(Properties properties, Supplier<DimensionType> type, Supplier<Block> frame,
                             IParticleData particle, boolean isHorizontal) {
        super(properties.tickRandomly().doesNotBlockMovement().hardnessAndResistance(-1.0F).sound(SoundType.GLASS).lightValue(11).noDrops());
        this.type = type;
        this.particle = particle;
        this.frame = frame;

        Direction.Axis defaultVal = isHorizontal
                ? Direction.Axis.Y
                : Direction.Axis.X;

        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, defaultVal));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
                return X_AABB;
            case Y:
                return Y_AABB;
        }

        return Z_AABB;
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
        double d0 = (float) pos.getX() + rand.nextFloat();
        double d1 = (float) pos.getY() + 0.8F;
        double d2 = (float) pos.getZ() + rand.nextFloat();
        worldIn.addParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote
                && type != null
                && !entityIn.isPassenger()
                && !entityIn.isBeingRidden()
                && entityIn.isNonBoss()
                && worldIn instanceof ServerWorld) {
            DimensionType destination = type.get();

            if (worldIn.getDimension().getType() == destination) {
                destination = DimensionType.OVERWORLD;
            }

            entityIn.changeDimension(destination);

            // TODO currently not working
//            PortalHelper.tryChangeDimention(entityIn, destination,
//                    new DivineTeleporter(((ServerWorld) worldIn), frame.get(), this));
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        // TODO optimize, heavy calculation
        BlockPattern.PatternHelper match = PortalHelper.createNetherLikePattern(frame.get(), this).match(worldIn, currentPos);
        if (match == null)
            return Blocks.AIR.getDefaultState();

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
}
