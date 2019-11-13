package divinerpg.events;

import divinerpg.api.DivineAPI;
import divinerpg.api.events.IsEquippedEvent;
import divinerpg.utils.ArmorObserver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Controls armor status of players
 */
@Mod.EventBusSubscriber()
public class FullArmorEventHandler {

    public static final HashMap<UUID, ArmorObserver> playerMap = new HashMap<>();

    /**
     * Removing leaving player from map
     */
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        // removing players
        PlayerEntity player = e.getPlayer();

        if (player != null)
            playerMap.remove(player.getUniqueID());
    }

    /**
     * Addind player to map
     */
    @SubscribeEvent
    public static void onPLayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        if (player != null) {
            putNewPLayer(player);
        }
    }

    /**
     * Detecting player's armor status changed
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void detectArmorChanges(TickEvent.PlayerTickEvent e) {
        UUID id = e.player.getUniqueID();

        if (playerMap.containsKey(id)) {
            playerMap.get(id).Update(e.player);
        } else {
            // Awkward situation, we should add player in logged in event
            putNewPLayer(e.player);
        }
    }

    /**
     * Detects if player wears that armor
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void detectIsEquipped(IsEquippedEvent event) {
        if (event.isEquipped())
            return;

        if (event.getArmorSet().getArmorSetDescriber().isEquipped(event.getPlayer())) {
            event.confirmEquipment();
        }
    }


    /**
     * Adding new player to list
     */
    private static void putNewPLayer(PlayerEntity playerEntity) {
        playerMap.put(playerEntity.getUniqueID(), new ArmorObserver(playerEntity, new ArrayList<>(DivineAPI.getPowerRegistry().getValues())));
    }
}
