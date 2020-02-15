package divinerpg.items;

import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.IShootEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ThrowableItem extends Item {
    private final IShootEntity spawnBullet;
    private final SoundEvent soundEvent;
    private final float damage;

    public ThrowableItem(ExtendedItemProperties properties) {
        this(properties, SoundEvents.ENTITY_EGG_THROW);
    }

    public ThrowableItem(ExtendedItemProperties properties, net.minecraft.util.SoundEvent event) {
        super(properties);
        this.soundEvent = event;
        spawnBullet = properties.spawnBullet;
        damage = properties.bulletDamage;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!player.abilities.isCreativeMode) {
            stack.shrink(1);
        }

        world.playSound(null, player.serverPosX, player.serverPosY, player.serverPosZ, soundEvent, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote && spawnBullet != null) {
            spawnBullet.shoot(world, player, 100, damage);
        }

        player.addStat(Stats.ITEM_USED.get(this));
        return new ActionResult(ActionResultType.SUCCESS, stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        if (worldIn == null)
            return;

        tooltip.add(new TranslationTextComponent("tooltip.damage.ranged", damage));
    }
}
