package divinerpg.utils.properties.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

public interface ICreateBullet {
    /**
     * Shoul only createBullet instance of entity
     *
     * @param world           - Current world
     * @param thrower         - Entity thrower
     * @param percentagePower - loaded power
     * @return - bullet entity
     */
    ThrowableEntity createBullet(World world, LivingEntity thrower, int percentagePower);
}
