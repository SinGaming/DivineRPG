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

    private static HashMap<String, DivinePlantType> values;
    public final PlantType type;
    public final String id;

    private DivinePlantType(String id, PlantType type) {
        this.type = type;
        this.id = id;
    }


    /**
     * registry Call can be eralier static ctor, so we need to init by ourself
     */
    private static HashMap<String, DivinePlantType> getValues() {
        if (values == null) {
            values = new HashMap<>();
        }

        return values;
    }

    /**
     * Registering helping method
     */
    private static DivinePlantType tryRegistrate(String id, PlantType type) {
        HashMap<String, DivinePlantType> map = getValues();

        if (!map.containsKey(id)) {
            map.put(id, new DivinePlantType(id, type));
        }

        return map.get(id);
    }

    public static DivinePlantType findById(String id) {
        return getValues().get(id);
    }

    public static DivinePlantType fromPlantType(PlantType type) {
        return tryRegistrate(type.name(), type);
    }

    public static DivinePlantType register(String id) {
        return tryRegistrate(id, PlantType.Nether);
    }
}
