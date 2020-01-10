package divinerpg.world.twilight.feature.config;

import divinerpg.DivineRPG;
import divinerpg.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Extending ore gen with whis config. Can add new custom values
 */
public class CustomFillerBlockType {
    public static final CustomFillerBlockType TWILIGHT;
    public static final CustomFillerBlockType EDEN_DIRT;
    public static final CustomFillerBlockType WILDWOOD_DIRT;
    public static final CustomFillerBlockType APALACHIA_DIRT;
    public static final CustomFillerBlockType SKYTHERN_DIRT;
    public static final CustomFillerBlockType MORTUM_DIRT;
    private static final Map<String, CustomFillerBlockType> values = new HashMap<>();

    static {
        TWILIGHT = new CustomFillerBlockType(DivineRPG.MODID + "_twilight",
                state -> state.getBlock() == BlockRegistry.twilightStone);
        EDEN_DIRT = new CustomFillerBlockType(DivineRPG.MODID + "_eden_dirt",
                state -> state.getBlock() == BlockRegistry.edenDirt);
        WILDWOOD_DIRT = new CustomFillerBlockType(DivineRPG.MODID + "_wildwood_dirt",
                state -> state.getBlock() == BlockRegistry.wildwoodDirt);
        APALACHIA_DIRT = new CustomFillerBlockType(DivineRPG.MODID + "_apalachia_dirt",
                state -> state.getBlock() == BlockRegistry.apalachiaDirt);
        SKYTHERN_DIRT = new CustomFillerBlockType(DivineRPG.MODID + "skythern_dirt",
                state -> state.getBlock() == BlockRegistry.skythernDirt);
        MORTUM_DIRT = new CustomFillerBlockType(DivineRPG.MODID + "mortum_dirt",
                state -> state.getBlock() == BlockRegistry.mortumDirt);
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

    /**
     * Find predicate by it's name (deserialization)
     */
    public static CustomFillerBlockType find(String id) {
        return values.get(id);
    }
}
