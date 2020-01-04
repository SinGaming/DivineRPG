package divinerpg.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Supplier;

public class TooltipUtil {
    public static ITextComponent uses(ItemStack stack) {
        if (stack.getMaxDamage() <= 0)
            return i18n("tooltip.uses.infinite");

        return new TranslationTextComponent("tooltip.uses", stack.getMaxDamage() - stack.getDamage());
    }

    public static ITextComponent i18n(String key) {
        return new TranslationTextComponent(key);
    }

    public static ITextComponent i18n(String key, Object... params) {
        return new TranslationTextComponent(key, params);
    }

    @OnlyIn(Dist.CLIENT)
    public static ITextComponent withRangedAmmo(ItemStack stack, Supplier<Item> ammo) {
        if (Minecraft.getInstance().player == null)
            return new StringTextComponent("");

        if (ammo == null || ammo.get() == null)
            return i18n("tooltip.ammo.infinite");

        boolean empty = Minecraft.getInstance().player.findAmmo(stack).isEmpty();
        return i18n("tooltip.ammo").appendSibling(
                new TranslationTextComponent(ammo.get().getTranslationKey()).applyTextStyle(empty ? TextFormatting.RED : TextFormatting.GREEN)
        );
    }

    public static ITextComponent arcana(double arcana) {
        return new TranslationTextComponent("tooltip.arcana", arcana);
    }

    /**
     * Stolen from potionUtils
     *
     * @param list
     * @param tooltip
     */
    public static void potionEffects(List<EffectInstance> list, List<ITextComponent> tooltip) {
        ItemStack copy = ItemStack.EMPTY.copy();
        PotionUtils.appendEffects(copy, list);

        PotionUtils.addPotionTooltip(copy, tooltip, 1);
    }
}
