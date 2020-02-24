package divinerpg.events;


import divinerpg.DivineRPG;
import divinerpg.api.DivineAPI;
import divinerpg.api.armor.PlayerArmorObserver;
import divinerpg.api.armor.PoweredArmorManager;
import divinerpg.api.armor.interfaces.IArmorSet;
import divinerpg.api.armor.interfaces.IPoweredArmor;
import divinerpg.api.armor.interfaces.IPoweredArmorManage;
import divinerpg.api.events.IsEquppedEvent;
import divinerpg.messages.PlayerLoggedMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber()
public class ArmorMapEvents {
    //
    // We need to store map for each side for singleplayer
    // Because we are running both side on one physical machine
    //

    private static final java.util.Map<LogicalSide, java.util.Map<PlayerEntity, PlayerArmorObserver>> playerMap = new HashMap<>();

    private static final java.util.Map<LogicalSide, java.util.Map<ResourceLocation, IPoweredArmorManage>> armorManagerMap = new HashMap<>();

    /**
     * Gets Armor set manager from registry name of PowerArmor
     *
     * @param location
     * @return
     */
    public static IPoweredArmorManage findArmorSetManager(ResourceLocation location) {
        return armorManagerMap.computeIfAbsent(EffectiveSide.get(), ArmorMapEvents::createPowerMap).get(location);
    }

    /**
     * Gets armor status player info
     *
     * @param player
     * @return
     */
    @Nonnull
    public static PlayerArmorObserver findPlayerArmorObserver(PlayerEntity player) {
        java.util.Map<PlayerEntity, PlayerArmorObserver> map = getPlayersMap();
        return map.computeIfAbsent(player, PlayerArmorObserver::new);
    }

    /**
     * Creating map of armor managers
     *
     * @param side
     * @return
     */
    private static java.util.Map<ResourceLocation, IPoweredArmorManage> createPowerMap(LogicalSide side) {
        return DivineAPI.getPowerRegistry().getValues().stream()
                .collect(Collectors.toMap(IForgeRegistryEntry::getRegistryName, PoweredArmorManager::new));
    }

    private static java.util.Map<PlayerEntity, PlayerArmorObserver> getPlayersMap() {
        return playerMap.computeIfAbsent(EffectiveSide.get(), x -> new HashMap<>());
    }


    /**
     * When new player is logged, create its own player observer
     *
     * @param event
     */
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        java.util.Map<PlayerEntity, PlayerArmorObserver> map = getPlayersMap();
        map.computeIfAbsent(event.getPlayer(), PlayerArmorObserver::new);

        if (event.getPlayer() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();

            DivineRPG.CHANNEL.sendTo(new PlayerLoggedMessage(true), player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    /**
     * Removing leaving player from map
     */
    @SubscribeEvent
    public static void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        getPlayersMap().remove(e.getPlayer());

        if (e.getPlayer() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) e.getPlayer();

            DivineRPG.CHANNEL.sendTo(new PlayerLoggedMessage(false), player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    @SubscribeEvent
    public static void checkEquipment(IsEquppedEvent event) {
        for (IPoweredArmor armor : DivineAPI.getPowerRegistry()) {
            if (armor != null) {
                // returns description of armor
                IArmorSet description = armor.getArmorDescription();

                boolean isOn = Arrays.stream(EquipmentSlotType.values())
                        .allMatch(x -> {
                            // getting possible items of armor set
                            List<net.minecraft.item.Item> items = description.getPossibleItems(x);
                            // checks if list is empty or armor description contains
                            // equipped item
                            return items.isEmpty() || items.contains(event.getItemInSlot(x));
                        });

                if (isOn) {
                    event.add(armor.getRegistryName());
                }
            }
        }
    }
}
