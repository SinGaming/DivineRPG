package divinerpg.tile.statue;

import divinerpg.entities.base.render.DivineBossModel;
import divinerpg.entities.base.render.IItemModel;
import divinerpg.entities.bosses.ancient.AncientEntityModel;
import divinerpg.entities.bosses.ayeraco.AyeracoModel;
import divinerpg.entities.bosses.ayeraco.manager.AyeracoManager;
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
import net.minecraftforge.fml.DistExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class StatueConstants {
    // Filled on client side only
    private static final HashMap<String, IItemModel> MODELS;
    private static final HashMap<String, VoxelShape> SIZES;

    static {
        MODELS = new HashMap<>();
        SIZES = new HashMap<>();

        initSizes();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> StatueConstants::initModels);
    }

    private static void initSizes() {
        SIZES.put("ancient_entity_statue", VoxelShapes.fullCube());
        SIZES.put("twilight_demon_statue", Block.makeCuboidShape(3, 0, 3, 13, 16, 13));
        SIZES.put("densos_statue", VoxelShapes.fullCube());
        SIZES.put("reyvor_statue", VoxelShapes.fullCube());
        SIZES.put("eternal_archer_statue", VoxelShapes.fullCube());
        SIZES.put("soul_fiend_statue", Block.makeCuboidShape(3, 0, 3, 13, 16, 13));
        SIZES.put("karot_statue", Block.makeCuboidShape(5, 0, 5, 11, 8, 11));
        SIZES.put("vamacheron_statue", Block.makeCuboidShape(3, 0, 3, 13, 11, 13));
        SIZES.put("the_watcher_statue", Block.makeCuboidShape(5, 0, 5, 11, 8, 11));
        SIZES.put("king_of_scorchers_statue", VoxelShapes.fullCube());

        // todo add sizes
//        SIZES.put("parasecta_statue", VoxelShapes.fullCube());
//        SIZES.put("dramix_statue", VoxelShapes.fullCube());

        AyeracoManager.beamLocations.keySet().forEach(x -> SIZES.put(String.format("ayeraco_%s_statue", x.getName()),
                Block.makeCuboidShape(3, 0, 3, 13, 6, 13)));
    }

    @OnlyIn(Dist.CLIENT)
    private static void initModels() {
        MODELS.put("ancient_entity_statue", new AncientEntityModel());
        MODELS.put("twilight_demon_statue", new TwilightDemonModel());
        MODELS.put("densos_statue", new DensosModel());
        MODELS.put("reyvor_statue", new DensosModel());
        MODELS.put("eternal_archer_statue", new EternalArcherModel());
        MODELS.put("soul_fiend_statue", new SoulFiendModel());
        MODELS.put("karot_statue", new KarotModel());
        MODELS.put("vamacheron_statue", new VamacheronModel());
        MODELS.put("the_watcher_statue", new WatcherModel());
        MODELS.put("king_of_scorchers_statue", new KingScorcherModel());

        // todo add models
//        MODELS.put("dramix_statue", null);
//        MODELS.put("parasecta_statue", null);

        AyeracoManager.beamLocations.keySet().forEach(x -> MODELS.put(String.format("ayeraco_%s_statue", x.getName()), new AyeracoModel()));
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
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> MODELS.put(name, model.get()));
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
