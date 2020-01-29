package divinerpg.world.arcana.structure;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.Random;

public class ArcanaMazeStructure extends Structure<NoFeatureConfig> {
    public ArcanaMazeStructure() {
        super(NoFeatureConfig::deserialize);
    }

    @Override
    public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
        return chunkGen.hasStructure(chunkGen.getBiomeProvider().getBiome(new ChunkPos(chunkPosX, chunkPosZ).asBlockPos()), this);
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
