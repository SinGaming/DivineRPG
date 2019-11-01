package divinerpg.world.eden;

import divinerpg.registry.DimensionRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.gen.ChunkGenerator;

import javax.annotation.Nullable;

public class EdenDimension extends Dimension {

    public EdenDimension(World worldIn) {
        super(worldIn, DimensionRegistry.EDEN);
    }


    ///////////////////////////////////////
    // TODO Implement all below
    ///////////////////////////////////////


    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return null;
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
        return true;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        // TODO stolen from nether
        return new Vec3d((double) 0.2F, (double) 0.03F, (double) 0.03F);
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
