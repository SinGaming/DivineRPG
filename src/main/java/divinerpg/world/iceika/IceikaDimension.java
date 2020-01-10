package divinerpg.world.iceika;

import divinerpg.registry.BiomeRegisty;
import divinerpg.registry.BlockRegistry;
import divinerpg.utils.RGBHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.OverworldGenSettings;

import javax.annotation.Nullable;
import java.awt.*;

public class IceikaDimension extends Dimension {
    final BiomeProvider biomeProvider;
    final private OverworldGenSettings settings;
    final private Vec3d fog;

    public IceikaDimension(World worldIn, DimensionType type) {
        super(worldIn, type);

        this.biomeProvider = new SingleBiomeProvider(new SingleBiomeProviderSettings().setBiome(BiomeRegisty.ICEIKA));
        fog = RGBHelper.vecFromColor(Color.BLUE.darker());

        this.settings = new OverworldGenSettings();
        settings.setDefaultFluid(Blocks.ICE.getDefaultState());
        settings.setDefaultFluid(BlockRegistry.frozen_stone.getDefaultState());
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return new IceikaChunkGenerator(world, biomeProvider, settings);
    }

    // Stolen from overworld
    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        for(int i = chunkPosIn.getXStart(); i <= chunkPosIn.getXEnd(); ++i) {
            for(int j = chunkPosIn.getZStart(); j <= chunkPosIn.getZEnd(); ++j) {
                BlockPos blockpos = this.findSpawn(i, j, checkValid);
                if (blockpos != null) {
                    return blockpos;
                }
            }
        }

        return null;
    }

    // Still stolen from overworld
    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(posX, 0, posZ);
        Biome biome = this.world.getBiome(blockpos$mutableblockpos);
        BlockState blockstate = biome.getSurfaceBuilderConfig().getTop();
        if (checkValid && !blockstate.getBlock().isIn(BlockTags.VALID_SPAWN)) {
            return null;
        } else {
            Chunk chunk = this.world.getChunk(posX >> 4, posZ >> 4);
            int i = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, posX & 15, posZ & 15);
            if (i < 0) {
                return null;
            } else if (chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, posX & 15, posZ & 15) > chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR, posX & 15, posZ & 15)) {
                return null;
            } else {
                for(int j = i + 1; j >= 0; --j) {
                    blockpos$mutableblockpos.setPos(posX, j, posZ);
                    BlockState blockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
                    if (!blockstate1.getFluidState().isEmpty()) {
                        break;
                    }

                    if (blockstate1.equals(blockstate)) {
                        return blockpos$mutableblockpos.up().toImmutable();
                    }
                }

                return null;
            }
        }
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        double d0 = MathHelper.frac((double)worldTime / 24000.0D - 0.25D);
        double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
        return (float)(d0 * 2.0D + d1) / 3.0F;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return fog;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }
}
