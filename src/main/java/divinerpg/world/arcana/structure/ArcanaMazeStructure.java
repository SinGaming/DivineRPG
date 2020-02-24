package divinerpg.world.arcana.structure;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Random;

public class ArcanaMazeStructure extends Structure<NoFeatureConfig> {
    public ArcanaMazeStructure() {
        super(NoFeatureConfig::deserialize);
    }

//    @Override
//    public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
//        return chunkGen.hasStructure(chunkGen.func_226691_t_Provider().func_226691_t_(new ChunkPos(chunkPosX, chunkPosZ).asBlockPos()), this);
//    }

    @Override
    public boolean func_225558_a_(BiomeManager p_225558_1_, ChunkGenerator<?> p_225558_2_, Random p_225558_3_, int p_225558_4_, int p_225558_5_, Biome p_225558_6_) {
        return false;
    }

    @Override
    public IStartFactory getStartFactory() {
        return ArcanaMazeStart::new;
    }

    @Override
    public String getStructureName() {
        return "ArcanaMaze";
    }

    // Chunk size (square)
    @Override
    public int getSize() {
        return 32;
    }
}
