package divinerpg.tile.statue;

import divinerpg.entities.base.render.DivineBossModel;
import divinerpg.entities.base.render.IItemModel;
import divinerpg.entities.bosses.ancient.AncientEntityModel;
import divinerpg.entities.bosses.ayeraco.AyeracoModel;
import divinerpg.entities.bosses.deamon.TwilightDemonModel;
import divinerpg.entities.bosses.densos.DensosModel;
import divinerpg.entities.bosses.etheral.EternalArcherModel;
import divinerpg.entities.bosses.fiend.SoulFiendModel;
import divinerpg.entities.bosses.karot.KarotModel;
import divinerpg.entities.bosses.scorcher.KingScorcherModel;
import divinerpg.entities.bosses.vamacheron.VamacheronModel;
import divinerpg.entities.bosses.watcher.WatcherModel;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class StatueConstants {
    private static final HashMap<String, IItemModel> MODELS;
    private static final HashMap<String, VoxelShape> SIZES;

    static {
        MODELS = new HashMap<>();
        SIZES = new HashMap<>();

        put("ancient_entity_statue", AncientEntityModel::new, VoxelShapes.fullCube());
        put("ayeraco_statue", AyeracoModel::new, Block.makeCuboidShape(3, 0, 3, 13, 6, 13));
        put("twilight_demon_statue", TwilightDemonModel::new, Block.makeCuboidShape(3, 0, 3, 13, 16, 13));
        put("densos_statue", DensosModel::new, VoxelShapes.fullCube());
        put("reyvor_statue", DensosModel::new, VoxelShapes.fullCube());
        put("eternal_archer_statue", EternalArcherModel::new);
        put("soul_fiend_statue", SoulFiendModel::new, Block.makeCuboidShape(3, 0, 3, 13, 16, 13));
        put("karot_statue", KarotModel::new, Block.makeCuboidShape(5, 0, 5, 11, 8, 11));
        put("vamacheron_statue", VamacheronModel::new, Block.makeCuboidShape(3, 0, 3, 13, 11, 13));
        put("the_watcher_statue", WatcherModel::new, Block.makeCuboidShape(5, 0, 5, 11, 8, 11));
        put("king_of_scorchers_statue", KingScorcherModel::new, VoxelShapes.fullCube());


        // TODO fill insert models
        //put("parasecta_statue", null, Block.makeCuboidShape(3, 3, 3, 13, 16, 13));
        //put("dramix_statue", null);
    }

    /**
     * Should call on init, assosiate name, model and size of block
     *
     * @param name  - name of statue
     * @param model - entity model
     * @param size  - block size
     */
    private static void put(String name, Supplier<DivineBossModel> model, VoxelShape size) {
        SIZES.put(name, size);
        put(name, model);
    }

    /**
     * Saves model only on client
     *
     * @param name  - statue name
     * @param model - entity model
     */
    @OnlyIn(Dist.CLIENT)
    private static void put(String name, Supplier<DivineBossModel> model) {
        MODELS.put(name, model.get());
    }

    /**
     * Returns all possible statue names
     */
    public static List<String> getStatueNames() {
        return new ArrayList<>(StatueConstants.SIZES.keySet());
    }

    /**
     * Gets model from name
     *
     * @param name - name of statue
     * @return Always not null
     */
    public static IItemModel get(String name) {
        if (name != null && !name.isEmpty()) {
            IItemModel result = MODELS.get(name);
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
