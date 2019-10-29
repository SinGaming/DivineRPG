package divinerpg.utils;

import divinerpg.DivineRPG;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.api.events.IsEquippedEvent;
import divinerpg.messages.EquipmentChangedMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Observing armor changing. Works only with Item, not ItemStack
 */
public class ArmorObserver {
    private final List<IPoweredArmorSet> allPossible;
    private final List<IPoweredArmorSet> current = new ArrayList<>();
    private List<Item> armor;
    private Item offhand;

    public ArmorObserver(PlayerEntity player, List<IPoweredArmorSet> allPossible) {
        this.allPossible = allPossible;

        Update(player);
    }

    /**
     * Should calls every tick to detect changes
     */
    public void Update(PlayerEntity e) {
        boolean wasChanged = false;
        List<Item> armor = e.inventory.armorInventory.stream().map(ItemStack::getItem).collect(Collectors.toList());

        if (!armor.equals(this.armor)) {
            this.armor = armor;
            wasChanged = true;
        }

        Item offhand = e.getHeldItemOffhand().getItem();
        if (offhand != this.offhand) {
            this.offhand = offhand;
            wasChanged = true;
        }

        if (!wasChanged)
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
            armorSet.getEquippedHandler().onEquppedChanged(e, event.isEquipped());
        }
    }

    public <T extends Event> void Handle(T event) {
        current.forEach(x -> x.handleAbility(event));
    }
}
