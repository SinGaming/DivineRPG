package divinerpg.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * Calls when armor inventory was changed
 */
public class FullArmorEvent extends PlayerEvent {
    public FullArmorEvent(PlayerEntity player) {
        super(player);
    }
}
