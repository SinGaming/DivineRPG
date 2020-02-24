package divinerpg.world.arcana.structure;

import divinerpg.world.arcana.maze.MazeConfig;
import divinerpg.world.arcana.maze.RoomDescription;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Map;

public class ArcanaMazeStart extends StructureStart {
    private final MazeConfig config;

    public ArcanaMazeStart(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed) {
        super(structure, chunkX, chunkZ, boundsIn, referenceIn, seed);

        config = new MazeConfig(this.rand, this.getStructure().getSize());
    }

    @Override
    public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
        Map<ChunkPos, RoomDescription> rooms = config.generateMaze(new ChunkPos(chunkX, chunkZ));

        rooms.forEach((key, value) -> this.components.add(new ArcanaMazeRoomPiece(templateManagerIn, value)));

        this.recalculateStructureSize();
    }
}
