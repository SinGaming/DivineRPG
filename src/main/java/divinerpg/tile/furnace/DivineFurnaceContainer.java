package divinerpg.tile.furnace;

import com.google.common.collect.Lists;
import divinerpg.registry.ContainerRegistry;
import divinerpg.tile.slots.FakeSlot;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

import java.util.List;

public class DivineFurnaceContainer extends AbstractFurnaceContainer {
    public DivineFurnaceContainer(int id, PlayerInventory playerInventoryIn) {
        this(id, playerInventoryIn, new Inventory(3), new IntArray(4));
    }

    // main
    public DivineFurnaceContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50104_6_) {
        super(ContainerRegistry.infinite_furnace, IRecipeType.SMELTING, id, playerInventoryIn, furnaceInventoryIn, p_i50104_6_);


        inventorySlots.remove(1);

        // replace slot (maybe for upgrades)
        inventorySlots.add(1, new FakeSlot(furnaceInventoryIn, 1, 56, 53));
    }

    @Override
    public List<RecipeBookCategories> getRecipeBookCategories() {
        return Lists.newArrayList(RecipeBookCategories.FURNACE_SEARCH, RecipeBookCategories.FURNACE_FOOD, RecipeBookCategories.FURNACE_BLOCKS, RecipeBookCategories.FURNACE_MISC);
    }
}
