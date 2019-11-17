package divinerpg.items;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic implementation if stored powersets
 */
public interface IArmorRing extends IItemProvider {

    /**
     * NBT tag key containing all loaded power sets
     */
    default String getKey() {
        return "power_armors";
    }

    /**
     * Load all powersets containing in NBT
     *
     * @param ring - current item
     */
    @Nonnull
    default Map<ResourceLocation, NonNullList<ItemStack>> loadAll(ItemStack ring) {
        HashMap<ResourceLocation, NonNullList<ItemStack>> result = new HashMap<>();

        if (ring.hasTag()) {
            CompoundNBT tag = ring.getTag();
            // Taken from ItemStackHelper
            ListNBT armors = tag.getList(getKey(), 10);
            if (!armors.isEmpty()) {

                for (int i = 0; i < armors.size(); i++) {
                    CompoundNBT armor = armors.getCompound(i);
                    if (!armor.isEmpty()) {
                        NonNullList<ItemStack> items = NonNullList.create();
                        ItemStackHelper.loadAllItems(armor, items);

                        result.put(new ResourceLocation(armor.getString("id")), items);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Stores all data in current item
     *
     * @param ring   - ring item
     * @param armors - powersets map
     */
    default void saveAll(ItemStack ring, Map<ResourceLocation, NonNullList<ItemStack>> armors) {
        if (!ring.hasTag()) {
            ring.setTag(new CompoundNBT());
        }

        CompoundNBT tag = ring.getTag();
        ListNBT listNBT = new ListNBT();

        for (Map.Entry<ResourceLocation, NonNullList<ItemStack>> entry : armors.entrySet()) {

            CompoundNBT armorEntry = ItemStackHelper.saveAllItems(new CompoundNBT(), entry.getValue());
            armorEntry.putString("id", entry.getKey().toString());

            listNBT.add(armorEntry);
        }

        String id = getKey();

        tag.remove(id);
        tag.put(id, listNBT);
    }
}
