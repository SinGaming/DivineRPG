package divinerpg.entities.base.villager;

import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.gui.screen.inventory.MerchantScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.MerchantContainer;
import net.minecraft.util.text.ITextComponent;

public class DivineMerchantScreen extends MerchantScreen {
    public DivineMerchantScreen(MerchantContainer container, PlayerInventory player, ITextComponent name) {
        super(container, player, name);

        if (container instanceof DivineMerchantContainer)
            ReflectionHelper.setValue(this, "MERCHANT_GUI_TEXTURE", ((DivineMerchantContainer) container).getGui());
    }
}
