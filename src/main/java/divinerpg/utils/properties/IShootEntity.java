package divinerpg.utils.properties;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

@FunctionalInterface
public interface IShootEntity {

    /**
     * Perform shot. Should create bullet, shot it and spawn in the world,
     * {@link SpawnHelper}
     *
     * @param world           - Current world
     * @param thrower         - Entity thrower
     * @param percentagePower - loaded power
     */
    void shoot(World world, LivingEntity thrower, int percentagePower);
}
