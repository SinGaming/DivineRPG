package divinerpg.world.surface;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class DivineCaveSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    protected OctavesNoiseGenerator noiseGenerator;
    protected long seed;
    protected BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();

    public DivineCaveSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> factory) {
        super(factory);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock,
                             BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        BlockState TOP = config.getTop();
        BlockState TWILIGHT_STONE = config.getUnderWaterMaterial();
        BlockState SOUL_SAND = config.getUnder();

        int i = seaLevel + 1;
        int j = x & 15;
        int k = z & 15;
        double d0 = 0.03125D;
        boolean flag = this.noiseGenerator.func_205563_a((double) x * 0.03125D, (double) z * 0.03125D, 0.0D) + random.nextDouble() * 0.2D > 0.0D;
        boolean flag1 = this.noiseGenerator.func_205563_a((double) x * 0.03125D, 109.0D, (double) z * 0.03125D) + random.nextDouble() * 0.2D > 0.0D;
        int l = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int i1 = -1;
        BlockState blockstate = TOP;
        BlockState blockstate1 = TOP;

        for (int j1 = 127; j1 >= 0; --j1) {
            blockpos$mutableblockpos.setPos(j, j1, k);
            BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutableblockpos);
            if (blockstate2.getBlock() != null && !blockstate2.isAir()) {
                if (blockstate2.getBlock() == defaultBlock.getBlock()) {
                    if (i1 == -1) {
                        if (l <= 0) {
                            blockstate = CAVE_AIR;
                            blockstate1 = TOP;
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            blockstate = TOP;
                            blockstate1 = TOP;
                            if (flag1) {
                                blockstate = TWILIGHT_STONE;
                                blockstate1 = TOP;
                            }

                            if (flag) {
                                blockstate = SOUL_SAND;
                                blockstate1 = SOUL_SAND;
                            }
                        }

                        if (j1 < i && (blockstate == null || blockstate.isAir())) {
                            blockstate = defaultFluid;
                        }

                        i1 = l;
                        if (j1 >= i - 1) {
                            chunkIn.setBlockState(blockpos$mutableblockpos, blockstate, false);
                        } else {
                            chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
                        }
                    } else if (i1 > 0) {
                        --i1;
                        chunkIn.setBlockState(blockpos$mutableblockpos, blockstate1, false);
                    }
                }
            } else {
                i1 = -1;
            }
        }
    }

    public void setSeed(long seed) {
        if (this.seed != seed || this.noiseGenerator == null) {
            this.noiseGenerator = new OctavesNoiseGenerator(new SharedSeedRandom(seed), 4);
        }

        this.seed = seed;
    }
}
