package divinerpg.tile.statue;

import divinerpg.entities.bosses.ayeraco.AyeracoModel;
import divinerpg.entities.bosses.vamacheron.VamacheronModel;
import divinerpg.tile.statue.models.AncientEntityModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatueConstants {
    private static final HashMap<String, Model> MODELS;
    private static final HashMap<String, VoxelShape> SIZES;

    static {
        MODELS = new HashMap<>();
        SIZES = new HashMap<>();

        put("vamacheron_statue", new VamacheronModel(), Block.makeCuboidShape(3, 0, 3, 13, 11, 13));
        put("ayeraco_statue", new AyeracoModel(), Block.makeCuboidShape(3, 0, 3, 13, 6, 13));

        // TODO replace
        put("ancient_entity_statue", new AncientEntityModel());

        // TODO fill insert models
        put("the_watcher_statue", null, Block.makeCuboidShape(5, 0, 5, 11, 8, 11));
        put("twilight_demon_statue", null, Block.makeCuboidShape(3, 0, 3, 13, 16, 13));
        put("parasecta_statue", null, Block.makeCuboidShape(3, 3, 3, 13, 16, 13));
        put("soul_fiend_statue", null, Block.makeCuboidShape(3, 0, 3, 13, 16, 13));

        put("densos_statue", null);
        put("dramix_statue", null);
        put("eternal_archer_statue", null);
        put("karot_statue", null);
        put("king_of_scorchers_statue", null);
        put("reyvor_statue", null);
    }

    private static void put(String name, Model model, VoxelShape size) {
        MODELS.put(name, model);
        SIZES.put(name, size);
    }

    private static void put(String name, Model model) {
        put(name, model, VoxelShapes.fullCube());
    }

    /**
     * Returns all possible statue names
     */
    public static List<String> getStatueNames() {
        return new ArrayList<>(StatueConstants.MODELS.keySet());
    }

    /**
     * Gets model from name
     *
     * @param name - name of statue
     * @return Always not null
     */
    public static Model get(String name) {
        if (name != null && !name.isEmpty()) {
            Model result = MODELS.get(name);
            if (result != null)
                return result;
        }

        return MODELS.values().stream().findFirst().get();
    }

    /**
     * Gets size from name
     *
     * @param name- name of statue
     * @return Always not null
     */
    public static VoxelShape getShape(String name) {
        if (name != null && !name.isEmpty()) {
            VoxelShape result = SIZES.get(name);
            if (result != null)
                return result;
        }

        return SIZES.values().stream().findFirst().get();
    }
}
