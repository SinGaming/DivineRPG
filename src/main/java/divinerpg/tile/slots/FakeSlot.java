package divinerpg.tile.slots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;

public class FakeSlot extends Slot {
    private boolean enabled = false;

    public FakeSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    @Override
    public boolean canTakeStack(PlayerEntity playerIn) {
        return enabled && super.canTakeStack(playerIn);
    }
}
