package divinerpg.utils.portal.description;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class NetherLikeDescription implements IPortalDescription {
    private final Block frame;
    private final Block portal;
    private final BlockPattern fullPattern;
    private final BlockPattern framePattern;

    public NetherLikeDescription(Block frame, Block portal) {
        this.frame = frame;
        this.portal = portal;

        fullPattern = BlockPatternBuilder.start()
                .aisle("?xx?")
                .aisle("x..x")
                .aisle("x..x")
                .aisle("x..x")
                .aisle("?xx?")
                .where('?', CachedBlockInfo.hasState(BlockStateMatcher.ANY))
                .where('x', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(frame)))
                .where('.', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(portal)))
                .build();

        framePattern = BlockPatternBuilder.start()
                .aisle("?xx?")
                .aisle("x??x")
                .aisle("x??x")
                .aisle("x??x")
                .aisle("?xx?")
                .where('?', CachedBlockInfo.hasState(BlockStateMatcher.ANY))
                .where('x', CachedBlockInfo.hasState(BlockStateMatcher.forBlock(frame)))
                .build();
    }

    @Override
    public void createPortal(World world, BlockPos pos) {
        // starting from botton right corener
        final BlockPos rightBottom = pos.east();
        final BlockPos leftTop = rightBottom.offset(Direction.WEST, 3).offset(Direction.UP, 4);

        for (int i = 0; i < 5; i++) {
            placeBlock(world, leftTop.down(i), frame.getDefaultState());
            placeBlock(world, rightBottom.up(i), frame.getDefaultState());
        }

        for (int i = 0; i < 4; i++) {
            placeBlock(world, leftTop.east(i), frame.getDefaultState());
            placeBlock(world, rightBottom.west(i), frame.getDefaultState());
        }

        // create platform is needed
        List<BlockPos> platformBlocks = Arrays.asList(
                rightBottom.west().north(),
                rightBottom.west().south(),

                rightBottom.west(2).north(),
                rightBottom.west(2).south()
        );

        if (platformBlocks.stream().allMatch(x -> world.getBlockState(x).isAir(world, x))) {
            platformBlocks.forEach(x -> placeBlock(world, x, frame.getDefaultState()));
        }

        lightPortal(world, leftTop, Direction.SOUTH, Direction.DOWN);
    }

    @Override
    public void lightPortal(World world, BlockPattern.PatternHelper match) {
        Direction right = match.getUp();
        Direction down = match.getForwards();
        BlockPos topLeft = match.getFrontTopLeft();

        lightPortal(world, topLeft, right, down);
    }


    private void lightPortal(World world, BlockPos topLeft, Direction right, Direction down) {
        right = right.rotateYCCW();
        topLeft = topLeft.offset(right, 1).offset(down, 1);

        BlockState state = portal.getDefaultState();

        Direction.Axis axis = right.getAxis();

        if (state.has(BlockStateProperties.AXIS)) {
            state = state.with(BlockStateProperties.AXIS, axis);
        }
        // nether portal has that prop
        if (state.has(BlockStateProperties.HORIZONTAL_AXIS)) {
            state = state.with(BlockStateProperties.HORIZONTAL_AXIS, axis);
        }

        for (int i = 0; i < 3; i++) {
            placeBlock(world, topLeft.offset(down, i), state);
            placeBlock(world, topLeft.offset(down, i).offset(right), state);
        }
    }

    @Override
    public Block getFrame() {
        return frame;
    }

    @Nullable
    @Override
    public BlockPattern.PatternHelper matchFull(World world, BlockPos pos) {
        return fullPattern.match(world, pos);
    }

    @Nullable
    @Override
    public BlockPattern.PatternHelper matchFrame(World world, BlockPos pos) {
        return framePattern.match(world, pos);
    }

    @Override
    public double maxSize() {
        return Math.max(Math.max(fullPattern.getPalmLength(), fullPattern.getThumbLength()), fullPattern.getFingerLength());
    }

    @Override
    public BlockPos getPlayerPosition(BlockPattern.PatternHelper match) {
        BlockPos result = match.getFrontTopLeft()
                .offset(match.getUp(), 1)
                .offset(match.getForwards(), 3);

        return result;
    }
}
