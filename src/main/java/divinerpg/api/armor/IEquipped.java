package divinerpg.api.armor;

import net.minecraft.entity.player.PlayerEntity;

@FunctionalInterface
public interface IEquipped {

    /**
     * Called when need to toggle powered armor ability
     *
     * @param player     - Player
     * @param isEquipped - is player full armored
     */
    void onEquppedChanged(PlayerEntity player, boolean isEquipped);
}
