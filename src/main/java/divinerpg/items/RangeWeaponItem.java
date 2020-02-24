package divinerpg.items;

import divinerpg.api.DivineAPI;
import divinerpg.api.arcana.IArcana;
import divinerpg.utils.TooltipUtil;
import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.IShootEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class RangeWeaponItem extends ShootableItem {
    private final int duration;
    private final IShootEntity spawnBullet;
    private final int consumeCount;
    private final Supplier<Item> ammo;
    private final int arcana;
    private final int delay;
    private final UseAction useAction;
    private final float damage;

    public RangeWeaponItem(ExtendedItemProperties properties) {
        super(properties);

        duration = Math.max(0, properties.bowLikeDuration);
        spawnBullet = properties.spawnBullet;
        consumeCount = properties.ammoConsumeCount;
        ammo = properties.ammo;
        arcana = properties.arcana;
        delay = properties.cooldown;
        useAction = duration > 0 ? UseAction.BOW : UseAction.NONE;
        damage = properties.bulletDamage;
    }

    /**
     * Only perform shot, no checks
     *
     * @param world       - world
     * @param player      - actor
     * @param ammoStack   - stack with ammo
     * @param weaponStack - stack with weapon
     * @param hand        - main hand
     * @param percentage  - loaded power in percentage
     */
    private void performShoot(World world, PlayerEntity player, ItemStack ammoStack, ItemStack weaponStack, Hand hand, int percentage) {
        // spawn entity only on server side
        if (spawnBullet != null && !world.isRemote) {
            spawnBullet.shoot(world, player, percentage, damage);
        }

        if (player.isCreative())
            return;

        // shrink ammo
        if (consumeCount > 0 && ammoStack != null) {
            ammoStack.shrink(consumeCount);
            if (ammoStack.isEmpty()) {
                player.inventory.deleteStack(ammoStack);
            }
        }

        // damaging weapon
        if (weaponStack.isDamageable()) {
            weaponStack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(hand));
        }

        // consuming arcana
        if (arcana > 0) {
            IArcana arcana = DivineAPI.getPlayerArcana(player);
            arcana.consume(this.arcana);
        }

        // set cooldown
        if (delay > 0 && !weaponStack.isEmpty()) {
            player.getCooldownTracker().setCooldown(this, delay);
        }
    }

    /**
     * Perform all checks if we can shoot
     *
     * @param player - player
     * @param weapon - itemstack with weapon
     */
    private boolean canShoot(PlayerEntity player, ItemStack weapon) {
        if (player.isCreative())
            return true;

        // arcana check
        if (arcana > 0) {
            IArcana arcana = DivineAPI.getPlayerArcana(player);
            if (arcana.getArcana() < this.arcana)
                return false;
        }

        // check ammo
        if (ammo != null) {
            ItemStack ammo = player.findAmmo(weapon);
            return !ammo.isEmpty() && ammo.getCount() >= this.consumeCount;
        }

        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return duration;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return useAction;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack weaponStack = playerIn.getHeldItem(handIn);

        if (useAction == UseAction.BOW) {

            if (canShoot(playerIn, weaponStack)) {
                ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(weaponStack, worldIn, playerIn, handIn, true);
                if (ret != null) return ret;

                playerIn.setActiveHand(handIn);
                return new ActionResult<>(ActionResultType.SUCCESS, weaponStack);
            }

            return new ActionResult<>(ActionResultType.FAIL, weaponStack);
        } else {
            ActionResult<ItemStack> result = super.onItemRightClick(worldIn, playerIn, handIn);

            if (result.getType() != ActionResultType.FAIL) {
                if (canShoot(playerIn, result.getResult())) {
                    performShoot(worldIn, playerIn, playerIn.findAmmo(weaponStack), weaponStack, handIn, 100);
                    result = new ActionResult<>(ActionResultType.SUCCESS, weaponStack);
                }
            }

            return result;
        }
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        if (ammo == null || ammo.get() == null)
            return o -> true;

        Item item = ammo.get();
        return stack -> item == stack.getItem();
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        PlayerEntity player = (PlayerEntity) entityLiving;
        if (player == null)
            return;

        float speed = BowItem.getArrowVelocity(this.getUseDuration(stack) - timeLeft);
        if (speed > 0.1 && canShoot(player, stack)) {
            float percantage = (float) timeLeft / getUseDuration(stack) * 100;
            performShoot(worldIn, player, player.findAmmo(stack), stack, Hand.MAIN_HAND, (int) percantage);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        if (worldIn == null)
            return;

        tooltip.add(new TranslationTextComponent("tooltip.damage.ranged", damage));

        tooltip.add(TooltipUtil.withRangedAmmo(stack, ammo));

        tooltip.add(TooltipUtil.uses(stack));

        if (arcana > 0) {
            tooltip.add(TooltipUtil.arcana(arcana));
        }
    }
}
