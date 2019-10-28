package divinerpg.events;

import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.api.events.IsEquippedEvent;
import divinerpg.utils.ArmorObserver;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

/**
 * Controls armor status of players
 */
@Mod.EventBusSubscriber()
public class FullArmorEventHandler {

    public static final HashMap<UUID, ArmorObserver> playerMap = new HashMap<>();
    /**
     * Contains all possible power sets data
     */
    private static final List<IPoweredArmorSet> powerSets = new ArrayList<>();

    /**
     * Single point for adding new powerd armor handlers
     *
     * @param sets - not null list of powered armor sets
     */
    public static void addPowerHandlers(IPoweredArmorSet... sets) {
        Arrays.stream(sets).filter(x -> !powerSets.contains(x)).forEach(powerSets::add);
    }

    /**
     * Removing leaving player from map
     */
    @SubscribeEvent
    public static void onPlayerLeave(ClientPlayerNetworkEvent.LoggedOutEvent e) {
        // removing players
        ClientPlayerEntity player = e.getPlayer();

        if (player != null)
            playerMap.remove(player.getUniqueID());
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
            playerMap.put(id, new ArmorObserver(e.player, powerSets));
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
}
