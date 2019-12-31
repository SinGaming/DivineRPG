package divinerpg.tile.furnace;

import divinerpg.registry.ContainerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class DivineFurnaceContainer extends AbstractFurnaceContainer {
    public DivineFurnaceContainer(int id, PlayerInventory playerInventoryIn) {
        this(ContainerRegistry.infinite_furnace, IRecipeType.SMELTING, id, playerInventoryIn, new Inventory(3), new IntArray(4));
    }

    public DivineFurnaceContainer(ContainerType<?> containerTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn, int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50104_6_) {
        super(containerTypeIn, recipeTypeIn, id, playerInventoryIn, furnaceInventoryIn, p_i50104_6_);

        //inventorySlots.remove(1);

        // replace slot (maybe for upgrades)
        //inventorySlots.add(1, new FakeSlot(furnaceInventoryIn, 1, 56, 53));
    }
}
