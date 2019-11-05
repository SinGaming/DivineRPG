package divinerpg.items;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;

public class Shickaxe extends ToolItem {

    public Shickaxe(float attackSpeedIn, IItemTier tier, Properties builder) {
        super(tier.getAttackDamage(), attackSpeedIn, tier, ImmutableSet.of(),
                builder.addToolType(ToolType.PICKAXE, tier.getHarvestLevel())
                        .addToolType(ToolType.AXE, tier.getHarvestLevel())
                        .addToolType(ToolType.SHOVEL, tier.getHarvestLevel()));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return canHarvestBlock(state)
                ? efficiency
                : 1;
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return canHarvestBlock(state);
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return getTier().getHarvestLevel() >= blockIn.getHarvestLevel();
    }
}
