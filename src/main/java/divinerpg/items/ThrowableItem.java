package divinerpg.items;

import divinerpg.utils.properties.item.ExtendedItemProperties;
import divinerpg.utils.properties.item.IShootEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ThrowableItem extends Item {
    private final IShootEntity spawnBullet;
    private final SoundEvent soundEvent;

    public ThrowableItem(ExtendedItemProperties properties) {
        this(properties, SoundEvents.ENTITY_EGG_THROW);
    }

    public ThrowableItem(ExtendedItemProperties properties, net.minecraft.util.SoundEvent event) {
        super(properties);
        this.soundEvent = event;
        spawnBullet = properties.spawnBullet;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!player.abilities.isCreativeMode) {
            stack.shrink(1);
        }

        world.playSound(null, player.posX, player.posY, player.posZ, soundEvent, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote && spawnBullet != null) {
            spawnBullet.shoot(world, player, 100);
        }

        player.addStat(Stats.ITEM_USED.get(this));
        return new ActionResult(ActionResultType.SUCCESS, stack);
    }
}
