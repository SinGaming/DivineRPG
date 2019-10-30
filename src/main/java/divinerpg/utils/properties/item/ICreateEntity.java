package divinerpg.utils.properties.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

public interface ICreateEntity {
    /**
     * Shoul only create instance of entity
     *
     * @param world           - Current world
     * @param thrower         - Entity thrower
     * @param percentagePower - loaded power
     * @return - bullet entity
     */
    ThrowableEntity create(World world, LivingEntity thrower, int percentagePower);
}
