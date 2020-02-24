package divinerpg.api.armor;

import divinerpg.api.events.IsEquppedEvent;
import divinerpg.events.ArmorMapEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerArmorObserver {
    private final Map<EquipmentSlotType, net.minecraft.item.Item> currentArmor = new HashMap<>();
    private PlayerEntity player;
    private Set<ResourceLocation> equipped = new HashSet<>();

    public PlayerArmorObserver(PlayerEntity player) {
        this.player = player;

        for (EquipmentSlotType id : EquipmentSlotType.values()) {
            currentArmor.put(id, player.getItemStackFromSlot(id).getItem());
        }

        recheckEquipment();

        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Check any armor equipment changes
     */
    @SubscribeEvent
    public void handleArmorChanges(LivingEquipmentChangeEvent e) {
        if (this.player != e.getEntity())
            return;

        currentArmor.put(e.getSlot(), e.getTo().getItem());

        recheckEquipment();
    }

    public void recheckEquipment() {
        // checks the statuses of

        IsEquppedEvent event = new IsEquppedEvent(player, currentArmor);
        MinecraftForge.EVENT_BUS.post(event);

        Collection<ResourceLocation> curentlyEquped = event.getEquipped();


        List<ResourceLocation> takenOff = this.equipped.stream().filter(x -> !curentlyEquped.contains(x)).collect(Collectors.toList());
        List<ResourceLocation> takenUp = curentlyEquped.stream().filter(x -> !equipped.contains(x)).collect(Collectors.toList());

        equipped.removeAll(takenOff);
        equipped.addAll(takenUp);

        for (ResourceLocation id : takenOff) {
            ArmorMapEvents.findArmorSetManager(id).removePlayer(player);
        }

        for (ResourceLocation id : takenUp) {
            ArmorMapEvents.findArmorSetManager(id).addPlayer(player);
        }
    }

    /**
     * Detects wherever the armor is on
     *
     * @param id - registry name of armor set
     * @return
     */
    public boolean isOn(ResourceLocation id) {
        return equipped.contains(id);
    }
}
