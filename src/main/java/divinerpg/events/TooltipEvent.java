package divinerpg.events;

import divinerpg.utils.DivineArmorMaterial;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class TooltipEvent {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void addTooltip(ItemTooltipEvent event) {
        List<ITextComponent> toolTip = event.getToolTip();
        ItemStack stack = event.getItemStack();
        PlayerEntity player = event.getPlayer();

        if (stack.getItem() instanceof ArmorItem) {
            IArmorMaterial material = ((ArmorItem) stack.getItem()).getArmorMaterial();

            if (material instanceof DivineArmorMaterial) {
                ((DivineArmorMaterial) material).addTooltip(toolTip);
            }
        }
    }
}
