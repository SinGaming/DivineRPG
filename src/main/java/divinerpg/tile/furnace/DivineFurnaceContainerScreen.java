package divinerpg.tile.furnace;

import divinerpg.DivineRPG;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DivineFurnaceContainerScreen extends AbstractFurnaceScreen<AbstractFurnaceContainer> {
    public DivineFurnaceContainerScreen(AbstractFurnaceContainer container, PlayerInventory playerInventory, ITextComponent name) {
        super(container, new FurnaceRecipeGui(), playerInventory, name, findGuiLayer(container));
    }

    protected static ResourceLocation findGuiLayer(AbstractFurnaceContainer container) {
        // todo implement
        return new ResourceLocation(DivineRPG.MODID, "textures/gui/coalstone_furnace.png");
    }
}
