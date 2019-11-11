package divinerpg.world.twilight;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.EndGenerationSettings;

import javax.annotation.Nullable;
import java.util.Random;

public class FloatingDimension extends Dimension {
    private final SingleBiomeProvider biomeProvider;
    private final Vec3d fog;
    private final EndGenerationSettings settings;

    public FloatingDimension(World worldIn, DimensionType typeIn, BlockState defaultBlock, Biome singleBiome, Vec3d fog) {
        super(worldIn, typeIn);

        biomeProvider = new SingleBiomeProvider(new SingleBiomeProviderSettings().setBiome(singleBiome));
        this.fog = fog;
        settings = ChunkGeneratorType.FLOATING_ISLANDS.createSettings();

        settings.setDefaultBlock(defaultBlock);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return ChunkGeneratorType.FLOATING_ISLANDS.create(this.world, this.biomeProvider, this.settings);
    }

    @Nullable
    @Override
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        // Stolen from end
        Random random = new Random(this.world.getSeed());
        BlockPos blockpos = new BlockPos(chunkPosIn.getXStart() + random.nextInt(15), 0, chunkPosIn.getZEnd() + random.nextInt(15));
        return this.world.getGroundAboveSeaLevel(blockpos).getMaterial().blocksMovement() ? blockpos : null;
    }

    @Nullable
    @Override
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        return this.findSpawn(new ChunkPos(posX >> 4, posZ >> 4), checkValid);
    }

    @Override
    public int getSeaLevel() {
        return 100;
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
