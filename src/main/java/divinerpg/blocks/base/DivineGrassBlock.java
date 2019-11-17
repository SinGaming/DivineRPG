package divinerpg.blocks.base;

import divinerpg.utils.properties.block.ExtendedBlockProperties;
import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Basic grass block. Overrided for custom block spreading
 */
public class DivineGrassBlock extends GrassBlock {
    private final List<Supplier<Block>> dirts;

    public DivineGrassBlock(ExtendedBlockProperties properties) {
        super(properties.props.sound(SoundType.PLANT));
        dirts = properties.dirts;
    }

    private static boolean checkLightLevel(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = world.getBlockState(blockpos);
        if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else {
            int currentLightLevel = LightEngine.func_215613_a(world, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(world, blockpos));
            return currentLightLevel < world.getMaxLightLevel();
        }
    }

    private static boolean checkUpperBlock(BlockState state, IWorldReader world, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return checkLightLevel(state, world, pos) && !world.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
            if (!dirts.isEmpty() && !checkLightLevel(state, worldIn, pos)) {
                worldIn.setBlockState(pos, dirts.get(0).get().getDefaultState());
            } else {
                if (worldIn.getLight(pos.up()) >= 9) {
                    BlockState blockstate = this.getDefaultState();

                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (dirts.stream().anyMatch(x -> worldIn.getBlockState(blockpos).getBlock().equals(x.get())) && checkUpperBlock(blockstate, worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos, blockstate.with(SNOWY, worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW));
                        }
                    }
                }

            }
        }
    }
}
