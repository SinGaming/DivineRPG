package divinerpg.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class FoodItem extends Item {

    private final boolean isInstant;

    /**
     * @param isInstant - instant consuming
     */
    public FoodItem(Properties properties, boolean isInstant) {
        super(properties);
        this.isInstant = isInstant;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        if (isInstant)
            // should be greater than zero
            return 1;

        return super.getUseDuration(stack);
    }
}
