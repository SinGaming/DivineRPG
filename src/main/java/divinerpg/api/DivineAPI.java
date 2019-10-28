package divinerpg.api;

import divinerpg.api.arcana.ArcanaProvider;
import divinerpg.api.arcana.IArcana;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.arcana.Arcana;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DivineAPI {

    /**
     * Contains all possible power sets data
     */
    private static final List<IPoweredArmorSet> powerSets = new ArrayList<>();

    /**
     * Gets player's arcana ability
     */
    public static IArcana getPLayerArcana(Entity player) {
        return new Arcana(player.getCapability(ArcanaProvider.ARCANA_CAPABILITY)
                .orElse(new Arcana(player)), player);
    }

    public static void addPowerHandlers(IPoweredArmorSet... sets) {
        powerSets.addAll(Arrays.stream(sets).collect(Collectors.toList()));
    }

    /**
     * @return Unmodiriable collection of all power sets
     */
    public static List<IPoweredArmorSet> getPowerSets() {
        return Collections.unmodifiableList(powerSets);
    }
}
