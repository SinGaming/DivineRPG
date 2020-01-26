package divinerpg.world.arcana.maze;

import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * WIP
 * Make random path
 */
public class MazeConfig {
    private final int size;
    private final Map<ChunkPos, RoomDescription> rooms = new LinkedHashMap<>();
    private Random random;

    /**
     * Generates new maze
     *
     * @param size
     */
    public MazeConfig(Random random, int size) {
        this.random = random;
        this.size = size;
    }

    public static void main(String[] args) {
        MazeConfig config = new MazeConfig(new Random(), 6);

        config.generateMaze(new ChunkPos(0, 0));

        Map<ChunkPos, RoomDescription> rooms = config.rooms;
    }

    public void generateMaze(ChunkPos start) {
        addSpawnRoom(0, 0, Direction.NORTH, Direction.WEST);
        addSpawnRoom(0, size - 1, Direction.SOUTH, Direction.WEST);
        addSpawnRoom(size - 1, 0, Direction.SOUTH, Direction.WEST);
        addSpawnRoom(size - 1, size - 1, Direction.SOUTH, Direction.EAST);

        addBossRoom();

        // Generates 4 mazes from square corners
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {

                int halfSize = size / 2;
                Cell maze = MazeGenerator.generateMap(halfSize).move(x * halfSize, y * halfSize);

                while (maze != null) {
                    ChunkPos pos = maze.getPos();

                    if (!rooms.containsKey(pos)) {
                        rooms.put(pos, new RoomDescription(pos, maze.getEntries()));
                    }

                    maze = maze.getNext();
                }
            }
        }
    }

    private void addBossRoom() {
        // todo location
    }

    private void addSpawnRoom(int x, int y, Direction... directions) {
        // todo location
        rooms.put(new ChunkPos(x, y), new RoomDescription(new ChunkPos(x, y), Arrays.asList(directions), new ResourceLocation("")));
    }

}
