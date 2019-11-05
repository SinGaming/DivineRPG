package divinerpg.items;

import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.IHitEntity;
import divinerpg.utils.properties.item.IRightClick;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SpecialSwordItem extends SwordItem {

    private final IRightClick rightClick;
    private final IHitEntity onHit;
    private final boolean disableSword;

    public SpecialSwordItem(IItemTier tier, int attackBonus, float attackSpeed, ExtendedItemProperties properties) {
        super(tier, attackBonus, attackSpeed, properties);
        rightClick = properties.rightClick;
        onHit = properties.onHit;
        disableSword = properties.disableSword;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand hand) {
        if (rightClick != null
                && rightClick.onItemRightClick(world, playerEntity, hand)
                && !playerEntity.isCreative()) {
            playerEntity.getHeldItem(hand).damageItem(1, playerEntity, player -> player.sendBreakAnimation(Hand.MAIN_HAND));
        }

        return super.onItemRightClick(world, playerEntity, hand);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (onHit != null) {
            onHit.onLeftClickEntity(stack, player, entity);
        }

        return disableSword;
    }
}
