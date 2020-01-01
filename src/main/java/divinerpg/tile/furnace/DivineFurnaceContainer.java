package divinerpg.tile.furnace;

import com.google.common.collect.Lists;
import divinerpg.registry.ContainerRegistry;
import divinerpg.tile.slots.BurnSlot;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.block.Block;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.IContainerFactory;

import java.util.List;
import java.util.function.Consumer;

public class DivineFurnaceContainer extends AbstractFurnaceContainer implements ITextured {
    private String name = "coastone_furnace";
    private boolean isInfinite;

    public DivineFurnaceContainer(int id, PlayerInventory playerInventoryIn) {
        this(id, playerInventoryIn, new Inventory(3), new IntArray(4));
    }

    // main
    public DivineFurnaceContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray p_i50104_6_) {
        super(ContainerRegistry.infinite_furnace, IRecipeType.SMELTING, id, playerInventoryIn, furnaceInventoryIn, p_i50104_6_);

        inventorySlots.remove(1);

        // replace slot (maybe for upgrades)
        inventorySlots.add(1, createBurnSlot(furnaceInventoryIn, 1, 56, 53));
    }

    /**
     * Replaces base fuel slot
     *
     * @param playerInventory -player inventory
     * @param index           - slot index (always 1)
     * @param xPosition       - x pos (always 56)
     * @param yPosition       - y pos (always 53)
     * @return
     */
    protected Slot createBurnSlot(IInventory playerInventory, int index, int xPosition, int yPosition) {
        return new BurnSlot(this, playerInventory, index, xPosition, yPosition);
    }

    @Override
    public List<RecipeBookCategories> getRecipeBookCategories() {
        return Lists.newArrayList(RecipeBookCategories.FURNACE_SEARCH, RecipeBookCategories.FURNACE_FOOD, RecipeBookCategories.FURNACE_BLOCKS, RecipeBookCategories.FURNACE_MISC);
    }

    @Override
    public ResourceLocation getTexture() {
        return CachedTexture.GUI.getTexture(name);
    }

    public boolean isInfinite() {
        return isInfinite;
    }

    /**
     * Supporting server-client message
     */
    public static class DivineFurnaceFactory implements IContainerFactory<DivineFurnaceContainer> {
        public static Consumer<PacketBuffer> create(Block block, DivineFurnaceTileEntity entity) {
            return buff -> {
                buff.writeString(block.getRegistryName().getPath());
                buff.writeBoolean(!entity.needFuel());
            };
        }

        @Override
        public DivineFurnaceContainer create(int windowId, PlayerInventory inv, PacketBuffer data) {
            DivineFurnaceContainer container = new DivineFurnaceContainer(windowId, inv);
            if (data != null) {
                container.name = data.readString();
                container.isInfinite = data.readBoolean();
            }

            return container;
        }
    }
}
