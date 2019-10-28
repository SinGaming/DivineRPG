package divinerpg.api;

import divinerpg.api.arcana.ArcanaProvider;
import divinerpg.api.arcana.IArcana;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.arcana.Arcana;
import divinerpg.events.FullArmorEventHandler;
import net.minecraft.entity.Entity;

public class DivineAPI {

    /**
     * Gets player's arcana ability
     */
    public static IArcana getPLayerArcana(Entity player) {
        return new Arcana(player.getCapability(ArcanaProvider.ARCANA_CAPABILITY)
                .orElse(new Arcana(player)), player);
    }

    public static void addPowerHandlers(IPoweredArmorSet... sets) {
        FullArmorEventHandler.addPowerHandlers(sets);
    }
}
