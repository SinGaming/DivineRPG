package divinerpg.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.*;

/**
 * Event posted to detect armor changes
 */
public class IsEquppedEvent extends PlayerEvent {
    /**
     * reference to armor
     */
    private final Map<EquipmentSlotType, net.minecraft.item.Item> currentArmor;
    private final List<ResourceLocation> currentlyEquipped = new ArrayList<>();

    public IsEquppedEvent(PlayerEntity player, Map<EquipmentSlotType, net.minecraft.item.Item> currentArmor) {
        super(player);
        this.currentArmor = currentArmor;
    }

    /**
     * Gets list of equipped armor sets
     *
     * @return
     */
    public Collection<ResourceLocation> getEquipped() {
        return Collections.unmodifiableCollection(currentlyEquipped);
    }

    /**
     * Adds list as confirmed equipment
     *
     * @param set
     */
    public void add(ResourceLocation set) {
        if (!currentlyEquipped.contains(set))
            currentlyEquipped.add(set);
    }

    /**
     * Remove list from confirmed equipment
     *
     * @param set
     */
    public void remove(ResourceLocation set) {
        currentlyEquipped.remove(set);
    }

    /**
     * Get current item in player inventory
     *
     * @param slot
     * @return
     */
    public net.minecraft.item.Item getItemInSlot(EquipmentSlotType slot) {
        return currentArmor.get(slot);
    }
}
