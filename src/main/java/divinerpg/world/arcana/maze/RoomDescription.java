package divinerpg.world.arcana.maze;

import divinerpg.DivineRPG;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoomDescription {
    private final ChunkPos pos;
    private final List<Direction> connections;
    private final List<Enter> enters = new ArrayList<>();
    private ResourceLocation location;


    public RoomDescription(ChunkPos pos, List<Direction> connections) {
        this(pos, connections, null);
    }

    public RoomDescription(ChunkPos pos, List<Direction> connections, ResourceLocation location) {
        this.pos = pos;
        this.connections = connections;

        this.location = location;
        if (location == null)
            findAndInitialize();
    }

    public boolean canConnect(RoomDescription room) {
        if (Math.abs(pos.x - room.pos.x) + Math.abs(pos.z - room.pos.z) != 0)
            return false;

        return enters.stream().anyMatch(x -> room.enters.stream().anyMatch(x::areConnected));
    }


    private void findAndInitialize() {
        // todo search for locations and anters

        // TODO debug
        location = new ResourceLocation(DivineRPG.MODID, "debug/" + connections.size());
        enters.addAll(connections.stream().map(x -> new Enter(x, new BlockPos(0, 0, 0))).collect(Collectors.toList()));
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public BlockPos getPos() {
        return pos.asBlockPos();
    }

    public class Enter {
        public final Direction direction;
        public final BlockPos topLeft;

        private final List<BlockPos> poses = new ArrayList<>();

        public Enter(Direction direction, BlockPos topLeft) {
            this.direction = direction;
            this.topLeft = topLeft;
        }

        public Enter withPoses(Stream<BlockPos> poses) {
            return withPoses(poses.map(BlockPos::toImmutable).toArray(BlockPos[]::new));
        }

        /**
         * Adds blockposes size
         *
         * @param poses
         * @return
         */
        public Enter withPoses(BlockPos... poses) {
            this.poses.clear();
            this.poses.addAll(Arrays.stream(poses).distinct().collect(Collectors.toList()));

            return this;
        }

        /**
         * Test if enter can be connected
         *
         * @param other
         * @return
         */
        public boolean areConnected(Enter other) {
            if (direction != other.direction.getOpposite() || !topLeft.equals(other.topLeft)) {
                return false;
            }

            return poses.size() == other.poses.size()
                    && poses.isEmpty()
                    || poses.containsAll(other.poses);
        }
    }
}
