package divinerpg.entities.base.villager;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;

public class DivineContainerProvider implements INamedContainerProvider {
    private final ITextComponent name;
    private final IContainerProvider inner;

    public DivineContainerProvider(IContainerProvider provider, ITextComponent textComponent) {
        this.inner = provider;
        this.name = textComponent;
    }

    public ITextComponent getDisplayName() {
        return this.name;
    }

    public Container createMenu(int windowID, PlayerInventory inventory, PlayerEntity player) {
        return this.inner.createMenu(windowID, inventory, player);
    }
}
