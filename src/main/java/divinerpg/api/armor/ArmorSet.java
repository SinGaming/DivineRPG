package divinerpg.api.armor;

import divinerpg.api.armor.interfaces.IArmorSet;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import java.util.*;
import java.util.stream.Stream;

public class ArmorSet implements IArmorSet {
    private final Map<EquipmentSlotType, List<net.minecraft.item.Item>> map = new HashMap<>();

    @Override
    public List<net.minecraft.item.Item> getPossibleItems(EquipmentSlotType slot) {
        return map.computeIfAbsent(slot, x -> new ArrayList<>());
    }

    @Override
    public <T extends IArmorSet> T withVariant(Item helmet, Item chest, Item legs, Item boots, Item shield) {
        put(EquipmentSlotType.HEAD, helmet);
        put(EquipmentSlotType.CHEST, chest);
        put(EquipmentSlotType.LEGS, legs);
        put(EquipmentSlotType.FEET, boots);
        put(EquipmentSlotType.OFFHAND, shield);

        return (T) this;
    }

    @Override
    public <T extends IArmorSet> T withVariants(Stream<Item> helmets, Stream<Item> chests, Stream<Item> legs, Stream<Item> boots, Stream<Item> shields) {
        if (helmets != null) {
            helmets.forEach(x -> put(EquipmentSlotType.HEAD, x));
        }

        if (chests != null) {
            chests.forEach(x -> put(EquipmentSlotType.CHEST, x));
        }

        if (legs != null) {
            legs.forEach(x -> put(EquipmentSlotType.LEGS, x));
        }

        if (boots != null) {
            boots.forEach(x -> put(EquipmentSlotType.FEET, x));
        }

        if (shields != null) {
            shields.forEach(x -> put(EquipmentSlotType.OFFHAND, x));
        }

        return (T) this;
    }

    private void put(EquipmentSlotType slot, Item item) {
        if (item == null)
            return;

        List<Item> items = getPossibleItems(slot);
        if (items.contains(item))
            return;

        items.add(item);
    }
}
