package divinerpg.api.armor.interfaces;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Stream;

/**
 * Describes how armorset should lok like
 */
public interface IArmorSet {
    /**
     * Returns list of possible values
     *
     * @param slot - entity slot
     */
    @Nonnull
    java.util.List<net.minecraft.item.Item> getPossibleItems(EquipmentSlotType slot);

    /**
     * Adds variant of armor
     */
    default <T extends IArmorSet> T withVariant(net.minecraft.item.Item helmet, Item chest, Item legs, Item boots) {
        return withVariant(helmet, chest, legs, boots, null);
    }

    <T extends IArmorSet> T withVariant(Item helmet, Item chest, Item legs, Item boots, Item shield);


    <T extends IArmorSet> T withVariants(Stream<Item> helmets, Stream<Item> chests, Stream<Item> legs, Stream<Item> boots, Stream<Item> shields);

    /**
     * Adds variant of armor
     */
    default<T extends IArmorSet> T withVariants(Stream<Item> helmets, Stream<Item> chests, Stream<Item> legs, Stream<Item> boots) {
        return withVariants(helmets, chests, legs, boots, null);
    }
}
