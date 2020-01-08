package divinerpg.events;

import divinerpg.api.DivineAPI;
import divinerpg.api.events.IsEquippedEvent;
import divinerpg.items.IArmorRing;
import divinerpg.utils.ArmorObserver;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Controls armor status of players
 */
@Mod.EventBusSubscriber()
public class FullArmorEventHandler {

    // TODO check on phisycal client/server model, not in a singleplayer
    private static final HashMap<LogicalSide, HashMap<UUID, ArmorObserver>> playerMap = new HashMap<>();

    /**
     * Returns actual player map based on dist
     *
     * @return
     */
    public static final HashMap<UUID, ArmorObserver> getPlayerMap() {
        return playerMap.computeIfAbsent(EffectiveSide.get(), key -> new HashMap<>());
    }


    /**
     * Removing leaving player from map
     */
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        // removing players
        PlayerEntity player = e.getPlayer();

        if (player != null)
            getPlayerMap().remove(player.getUniqueID());
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
    public static void detectArmorChanges(TickEvent.PlayerTickEvent e) {
        UUID id = e.player.getUniqueID();

        if (!getPlayerMap().containsKey(id)) {
            // PlayerLoggedInEvent server only as I understood, so it's possible situation
            putNewPLayer(e.player);
        }

        getPlayerMap().get(id).Update(e.player);
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

        ItemStack ring = event.getRing();
        if (!ring.isEmpty() && ring.getItem() instanceof IArmorRing) {
            if (((IArmorRing) ring.getItem()).isOn(ring, event.getArmorSet().getRegistryName())) {
                event.confirmEquipment();
            }
        }
    }


    /**
     * Adding new player to list
     */
    private static void putNewPLayer(PlayerEntity playerEntity) {
        getPlayerMap().put(playerEntity.getUniqueID(), new ArmorObserver(playerEntity, new ArrayList<>(DivineAPI.getPowerRegistry().getValues())));
    }
}
