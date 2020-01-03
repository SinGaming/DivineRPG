package divinerpg.utils;

import divinerpg.DivineRPG;
import divinerpg.api.armor.IEquipped;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.api.events.IsEquippedEvent;
import divinerpg.items.IArmorRing;
import divinerpg.messages.EquipmentChangedMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Observing armor changing. Works only with Item, not ItemStack
 */
public class ArmorObserver {
    private final List<IPoweredArmorSet> allPossible;

    // storing all in NBT tag
    private final CompoundNBT stored = new CompoundNBT();

    private final List<IPoweredArmorSet> current = new ArrayList<>();

    public ArmorObserver(PlayerEntity player, List<IPoweredArmorSet> allPossible) {
        this.allPossible = allPossible;

        Update(player);
    }

    /**
     * Should calls every tick to detect changes
     */
    public void Update(PlayerEntity e) {
        if (!checkAndStore(e.inventory))
            return;

        if (e.getEntityWorld().isRemote())
            DivineRPG.CHANNEL.sendToServer(new EquipmentChangedMessage(e.getUniqueID()));

        for (IPoweredArmorSet armorSet : allPossible) {
            IsEquippedEvent event = new IsEquippedEvent(e, armorSet);
            MinecraftForge.EVENT_BUS.post(event);

            boolean isActive = current.contains(armorSet);

            // status wasn't changed
            if (isActive == event.isEquipped())
                continue;

            if (event.isEquipped()) {
                current.add(armorSet);
            } else {
                current.remove(armorSet);
            }

            // Should work on both sides
            IEquipped equippedHandler = armorSet.getEquippedHandler();

            if (equippedHandler != null)
                equippedHandler.onEquppedChanged(e, event.isEquipped());
        }
    }

    /**
     * Detect and save player invertory changes
     *
     * @param inventory - player inventory
     * @return - was inventory changed
     */
    private boolean checkAndStore(PlayerInventory inventory) {
        NonNullList<ItemStack> armor = load("armor", 4);
        NonNullList<ItemStack> off = load("off", 1);
        NonNullList<ItemStack> ring = load("ring", 1);

        boolean changed = false;

        if (!stacksEquals(armor, inventory.armorInventory)) {
            armor = inventory.armorInventory;
            changed = true;
        }

        if (!stacksEquals(off, inventory.offHandInventory)) {
            off = inventory.offHandInventory;
            changed = true;
        }

        ItemStack[] rings = Stream.of(inventory.mainInventory, inventory.armorInventory, inventory.offHandInventory).flatMap(Collection::stream)
                .filter(x -> x.getItem() instanceof IArmorRing).limit(1).toArray(ItemStack[]::new);

        if (!stacksEquals(ring, Arrays.asList(rings))) {
            ring = NonNullList.from(ItemStack.EMPTY, rings);
            changed = true;
        }

        if (changed) {
            stored.remove("armor");
            stored.remove("off");
            stored.remove("ring");

            stored.put("armor", ItemStackHelper.saveAllItems(new CompoundNBT(), armor));
            stored.put("off", ItemStackHelper.saveAllItems(new CompoundNBT(), off));
            stored.put("ring", ItemStackHelper.saveAllItems(new CompoundNBT(), ring));
        }


        return changed;
    }

    /**
     * Load items from tag name
     *
     * @param key - tag name
     * @return - loaded items or empty list
     */
    private NonNullList<ItemStack> load(String key, int size) {
        INBT inbt = stored.get(key);
        if (inbt instanceof CompoundNBT) {
            NonNullList<ItemStack> result = NonNullList.withSize(size, ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(((CompoundNBT) inbt), result);
            return result;
        }

        return NonNullList.create();
    }

    /**
     * Comparing items in stacks
     *
     * @param fromTag - first stacks collection
     * @param right   - second stacks collection
     * @return - equalituy status
     */
    private boolean stacksEquals(List<ItemStack> fromTag, List<ItemStack> right) {

        if (fromTag.size() != right.size())
            return false;

        for (int i = fromTag.size() - 1; i >= 0; i--) {
            ItemStack x = fromTag.get(i);
            ItemStack y = right.get(i);

            if (x.isEmpty() && y.isEmpty())
                continue;

            if (!x.isItemEqual(y))
                return false;
        }

        return true;
    }

    /**
     * Find stask with current item
     *
     * @param inventory - player inventory
     * @param stack     - searching item
     * @return matching stacks
     */
    private NonNullList<ItemStack> find(PlayerInventory inventory, ItemStack stack) {
        NonNullList<ItemStack> result = NonNullList.create();

        Stream<ItemStack> finded = Stream.of(inventory.armorInventory, inventory.mainInventory, inventory.offHandInventory).flatMap(Collection::stream)
                .filter(x -> x.isItemEqual(stack));

        result.addAll(finded.collect(Collectors.toList()));

        return result;
    }

    public <T extends Event> void Handle(T event) {
        current.forEach(x -> x.handleAbility(event));
    }

    public boolean isOn(ResourceLocation armorSetID) {
        return current.stream().anyMatch(x -> armorSetID.equals(x.getRegistryName()));
    }
}
