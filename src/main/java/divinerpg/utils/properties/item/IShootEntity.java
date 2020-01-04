package divinerpg.utils.properties.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

@FunctionalInterface
public interface IShootEntity {

    /**
     * Perform shot. Should createFireball bullet, shot it and spawn in the world,
     * {@link SpawnHelper}
     *
     * @param world           - Current world
     * @param thrower         - Entity thrower
     * @param percentagePower - loaded power
     * @param damage          - bullet damage
     */
    void shoot(World world, LivingEntity thrower, int percentagePower, float damage);
}
