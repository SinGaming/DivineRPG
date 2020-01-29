package divinerpg.world.arcana;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.FlatGenerationSettings;

import javax.annotation.Nullable;

public class ArcanaDimension extends Dimension {

    private final FlatGenerationSettings settings;
    private final SingleBiomeProvider biomeProvider;

    public ArcanaDimension(World worldIn, DimensionType typeIn) {
        super(worldIn, typeIn);

        Biome biome = Biomes.FLOWER_FOREST;

        biomeProvider = new SingleBiomeProvider(new SingleBiomeProviderSettings().setBiome(biome));

        settings = new FlatGenerationSettings();
        settings.setBiome(biome);
        settings.setDefaultBlock(Blocks.GRASS_BLOCK.getDefaultState());
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return ChunkGeneratorType.FLAT.create(this.world, biomeProvider, settings);
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        return null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return null;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return Vec3d.ZERO;
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
