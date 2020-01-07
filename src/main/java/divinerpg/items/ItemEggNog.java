package divinerpg.items;

import net.minecraft.item.*;

public class ItemEggNog extends FoodItem {
    /**
     * @param group - item group
     */
    public ItemEggNog(ItemGroup group) {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(group)
                .containerItem(Items.BUCKET)
                .food(new Food.Builder().saturation(5).hunger(4).build()), false);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
