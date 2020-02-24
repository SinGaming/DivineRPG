package divinerpg.api.armor.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface IEquipped {
    /**
     * Calls when player take off or take up armor
     *
     * @param player
     * @param isEquipped
     */
    void onEquppedChanged(PlayerEntity player, boolean isEquipped);
}
