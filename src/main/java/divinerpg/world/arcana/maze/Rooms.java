package divinerpg.world.arcana.maze;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Rooms {
    private static final HashMap<List<RoomDescription.Enter>, ResourceLocation> map;

    static {
        map = new HashMap<>();

    }


    public static ResourceLocation findRooms(List<RoomDescription.Enter> enters) {
        Optional<Map.Entry<List<RoomDescription.Enter>, ResourceLocation>> find = map.entrySet().stream()
                .filter(x -> x.getKey().size() == enters.size())
                .filter(x -> enters.containsAll(x.getKey()))
                .findFirst();

        return find.map(Map.Entry::getValue).orElse(null);
    }
}
