package divinerpg.utils;

import net.minecraftforge.common.PlantType;

import java.util.HashMap;

/**
 * Created to extend base PlantType functionality
 * TODO seems like Forge problem
 * BUG live here https://github.com/MinecraftForge/MinecraftForge/issues/6286
 */
public class DivinePlantType {
    public static final DivinePlantType EDEN = register("eden");
    public static final DivinePlantType WILDWOOD = register("wildwood");
    private static final HashMap<String, DivinePlantType> values = new HashMap<>();
    public final PlantType type;
    public final String id;

    private DivinePlantType(String id, PlantType type) {
        this.type = type;
        this.id = id;

        if (!values.containsKey(id)) {
            values.put(id, this);
        }
    }

    public DivinePlantType(PlantType type) {
        this(type.name(), type);
    }

    public static DivinePlantType findById(String id) {
        return values.get(id);
    }

    public static DivinePlantType register(String id) {
        return new DivinePlantType(id, PlantType.Nether);
    }
}
