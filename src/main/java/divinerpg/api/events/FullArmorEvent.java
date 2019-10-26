package divinerpg.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Calls when armor inventory was changed
 */
public class FullArmorEvent extends PlayerEvent {

    private final boolean emptyOffhand;
    private final boolean fullArmored;

    public FullArmorEvent(PlayerEntity player) {
        super(player);

        emptyOffhand = player.getHeldItemOffhand().isEmpty();
        fullArmored = player.inventory.armorInventory.stream().noneMatch(ItemStack::isEmpty);
    }

    /**
     * Detect if all 4 parts of armor is equipped
     */
    public boolean isFullArmored() {
        return fullArmored;
    }

    /**
     * Detects if player hold smth in other hand
     */
    public boolean isEmptyOffhand() {
        return emptyOffhand;
    }
}
