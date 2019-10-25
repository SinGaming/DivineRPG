package divinerpg.api.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public interface IArmorSet {

    /**
     * Adds variant of armor set. Null-items is ignored
     */
    IArmorSet withVariant(Item helmet, Item chest, Item legs, Item boots, Item shield);

    /**
     * Adds variants of armor set. Null-items is ignored
     */
    IArmorSet withVariants(Item[] helmet, Item[] chest, Item[] legs, Item[] boots, Item[] shields);

    /**
     * Is current set applied on player
     *
     * @param entity - player
     */
    boolean isEquipped(PlayerEntity entity);
}
