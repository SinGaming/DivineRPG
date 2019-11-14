package divinerpg.utils.properties.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@FunctionalInterface
public interface IBlockHit {
    /**
     * callback on block hit
     *
     * @param world  - current world
     * @param entity - clicker (player) entity
     * @param weapon - item stack with weapon
     * @param hit    - hit result. hit.getType() == BLOCK ALWAYS!!!
     */
    void onHit(World world, LivingEntity entity, ItemStack weapon, BlockRayTraceResult hit);
}
