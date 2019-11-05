package divinerpg.utils.properties.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IHitEntity {
    /**
     * On hit entity
     *
     * @param stack  - sword
     * @param player - player
     * @param entity - hitting entity
     */
    void onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity);
}
