package divinerpg.world.mortum;

import divinerpg.utils.RGBHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.NetherGenSettings;

import java.awt.*;

public class CavesDimension extends NetherDimension {
    private final NetherGenSettings settings;
    private final SingleBiomeProvider biomeProvider;
    private final Vec3d fog;

    public CavesDimension(World worldIn, DimensionType typeIn, BlockState defaultBlock, Biome singleBiome, Color color) {
        super(worldIn, typeIn);
        this.doesWaterVaporize = false;

        biomeProvider = new SingleBiomeProvider(new SingleBiomeProviderSettings(worldIn.getWorldInfo()).setBiome(singleBiome));
        this.fog = RGBHelper.vecFromColor(color);

        settings = ChunkGeneratorType.CAVES.createSettings();
        settings.setDefaultBlock(defaultBlock);
        settings.setDefaultFluid(Blocks.AIR.getDefaultState());
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        return fog;
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        return ChunkGeneratorType.CAVES.create(this.world, this.biomeProvider, settings);
    }

//    @Override
//    public int getSeaLevel() {
//        return 0;
//    }
}
