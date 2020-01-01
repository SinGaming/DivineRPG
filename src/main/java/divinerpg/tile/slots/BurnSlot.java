package divinerpg.tile.slots;

import divinerpg.tile.furnace.DivineFurnaceContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.FurnaceFuelSlot;
import net.minecraft.item.ItemStack;

public class BurnSlot extends FurnaceFuelSlot {
    private DivineFurnaceContainer container;

    public BurnSlot(AbstractFurnaceContainer container, IInventory playerInventory, int index, int xPosition, int yPosition) {
        super(container, playerInventory, index, xPosition, yPosition);

        if (container instanceof DivineFurnaceContainer)
            this.container = ((DivineFurnaceContainer) container);
    }

    private boolean notNeedFuel() {
        if (container != null) {
            return container.isInfinite();
        }

        return false;
    }

    @Override
    public boolean isEnabled() {
        return !notNeedFuel() && super.isEnabled();
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return !notNeedFuel() && super.isItemValid(stack);
    }
}
