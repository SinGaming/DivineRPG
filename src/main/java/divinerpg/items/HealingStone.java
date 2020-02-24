package divinerpg.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HealingStone extends Item {
    public HealingStone(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ActionResult<ItemStack> result = super.onItemRightClick(worldIn, playerIn, handIn);

        if (result.getType() != ActionResultType.FAIL) {
            if (playerIn.getHealth() >= playerIn.getMaxHealth())
                return new ActionResult<>(ActionResultType.FAIL, result.getResult());

            playerIn.heal(playerIn.getMaxHealth() - playerIn.getHealth());

            if (!playerIn.isCreative())
                result.getResult().shrink(1);
        }

        return result;
    }
}
