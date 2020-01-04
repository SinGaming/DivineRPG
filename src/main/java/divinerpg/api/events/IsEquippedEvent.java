package divinerpg.api.events;

import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Calls when need to detect is player full armored
 */
public class IsEquippedEvent extends PlayerEvent {

    private final ItemStack ring;
    private final IPoweredArmorSet armorSet;
    private boolean isEquipped = false;

    public IsEquippedEvent(PlayerEntity player, IPoweredArmorSet armorSet) {
        super(player);
        this.armorSet = armorSet;

        ring = Stream.of(player.inventory.mainInventory, player.inventory.armorInventory, player.inventory.offHandInventory).flatMap(Collection::stream)
                .filter(x -> x.getItem() == ItemRegistry.armor_ring).findFirst().orElse(ItemStack.EMPTY);
    }

    /**
     * Get current armor set description
     */
    public IPoweredArmorSet getArmorSet() {
        return armorSet;
    }

    /**
     * Is current set equipped on player
     */
    public boolean isEquipped() {
        return isEquipped;
    }

    /**
     * Call method when player is equipped in current Armor set
     */
    public void confirmEquipment() {
        isEquipped = true;
    }

    /**
     * Scanning player inventory for ring item
     *
     * @return - ItemStack.EMPTY if not founded
     */
    public ItemStack getRing() {
        return ring;
    }
}
