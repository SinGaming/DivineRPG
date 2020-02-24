package divinerpg.api.armor.interfaces;

import net.minecraft.entity.player.PlayerEntity;

/**
 * Managing players wearing that armor set
 */
public interface IPoweredArmorManage {
    /**
     * Gets current armor set
     *
     * @return
     */
    IPoweredArmor getArmorSet();

    /**
     * Adds player to handlers list
     *
     * @param player
     */
    void addPlayer(PlayerEntity player);

    /**
     * Removes player from handler list
     *
     * @param player
     */
    void removePlayer(PlayerEntity player);
}
