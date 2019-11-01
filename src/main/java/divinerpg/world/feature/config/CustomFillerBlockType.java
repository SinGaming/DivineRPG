package divinerpg.world.feature.config;

import divinerpg.DivineRPG;
import divinerpg.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class CustomFillerBlockType {
    public static final CustomFillerBlockType TWILIGHT;
    private static final Map<String, CustomFillerBlockType> values = new HashMap<>();

    static {
        TWILIGHT = new CustomFillerBlockType(DivineRPG.MODID + "_twilight",
                state -> state.getBlock() == BlockRegistry.twilightStone);
    }

    public final String id;
    public final Predicate<BlockState> canReplace;

    /**
     * Instantiate filler type and register it
     *
     * @param id         - id. Register if it is unique
     * @param canReplace - predicat
     */
    public CustomFillerBlockType(String id, Predicate<BlockState> canReplace) {
        this.id = id;
        this.canReplace = canReplace;

        if (!values.containsKey(id)) {
            values.put(id, this);
        }
    }

    public CustomFillerBlockType(OreFeatureConfig.FillerBlockType type) {
        this(type.func_214737_a(), type.func_214738_b());
    }

    public static CustomFillerBlockType find(String id) {
        return values.get(id);
    }
}
