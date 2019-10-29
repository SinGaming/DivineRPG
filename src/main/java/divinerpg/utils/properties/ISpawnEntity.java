package divinerpg.utils.properties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

@FunctionalInterface
public interface ISpawnEntity {

    /**
     * Spawns entity
     *
     * @param world           - Current world
     * @param thrower         - Entity thrower
     * @param percentagePower - loaded power
     * @return - bullet entity
     */
    ThrowableEntity createEntity(World world, Entity thrower, int percentagePower);
}
