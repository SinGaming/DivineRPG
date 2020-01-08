package divinerpg.api;

import divinerpg.api.arcana.ArcanaProvider;
import divinerpg.api.arcana.IArcana;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.arcana.Arcana;
import divinerpg.events.FullArmorEventHandler;
import divinerpg.utils.ArmorObserver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class DivineAPI {

    /**
     * Contains all possible power sets data
     */
    public static IForgeRegistry<IPoweredArmorSet> getPowerRegistry() {
        return RegistryManager.ACTIVE.getRegistry(IPoweredArmorSet.class);
    }

    /**
     * Gets player's arcana ability
     */
    public static IArcana getPlayerArcana(Entity player) {
        IArcana arcana = player
                .getCapability(ArcanaProvider.ARCANA_CAPABILITY)
                .orElse(new Arcana());

        if (arcana instanceof Arcana) {
            ((Arcana) arcana).withPlayer(player);
        }

        return arcana;
    }

    /**
     * Checks if player wears that type of armor
     * @param entity - player
     * @param id - ID of powered armor set
     */
    public static boolean isOn(Entity entity, ResourceLocation id) {
        if (id == null || !(entity instanceof PlayerEntity))
            return false;

        ArmorObserver observer = FullArmorEventHandler.getPlayerMap().get(entity.getUniqueID());
        if (observer == null)
            return false;

        return observer.isOn(id);
    }
}
