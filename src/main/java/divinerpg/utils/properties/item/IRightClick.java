package divinerpg.utils.properties.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@FunctionalInterface
public interface IRightClick {
    /**
     * Perform action on right click
     *
     * @param world        - world
     * @param playerEntity - player
     * @param hand         - current hand
     * @return need to damage sword
     */
    boolean onItemRightClick(World world, PlayerEntity playerEntity, Hand hand);
}
