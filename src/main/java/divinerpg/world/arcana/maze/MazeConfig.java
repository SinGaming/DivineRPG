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
        Map<ChunkPos, RoomDescription> rooms = config.generateMaze(new ChunkPos(0, 0));
    }

    /**
     * Generates new maze
     *
     * @param start
     * @return
     */
    public Map<ChunkPos, RoomDescription> generateMaze(ChunkPos start) {
        Map<ChunkPos, RoomDescription> rooms = new LinkedHashMap<>();
        random.setSeed(random.nextLong());

        addSpawnRoom(rooms, 0, 0, Direction.NORTH, Direction.WEST);
        addSpawnRoom(rooms, 0, size - 1, Direction.SOUTH, Direction.WEST);
        addSpawnRoom(rooms, size - 1, 0, Direction.SOUTH, Direction.WEST);
        addSpawnRoom(rooms, size - 1, size - 1, Direction.SOUTH, Direction.EAST);

        addBossRoom(rooms);

        // Generates 4 mazes from square corners
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {

                int halfSize = size / 2;
                Cell maze = MazeGenerator.generateMap(random, halfSize).move(x * halfSize, y * halfSize);

                maze.move(start.x, start.z);

                while (maze != null) {
                    ChunkPos pos = maze.getPos();

                    if (!rooms.containsKey(pos)) {
                        rooms.put(pos, new RoomDescription(pos, maze.getEntries()));
                    }

                    maze = maze.getNext();
                }
            }
        }

        return rooms;
    }

    private void addBossRoom(Map<ChunkPos, RoomDescription> rooms) {
        // todo location
    }

    private void addSpawnRoom(Map<ChunkPos, RoomDescription> rooms, int x, int y, Direction... directions) {
        // todo location
        rooms.put(new ChunkPos(x, y), new RoomDescription(new ChunkPos(x, y), Arrays.asList(directions), new ResourceLocation("")));
    }
}
