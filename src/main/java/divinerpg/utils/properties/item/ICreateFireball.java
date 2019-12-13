package divinerpg.utils.properties.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

@FunctionalInterface
public interface ICreateFireball {

    /**
     * Creates fireball entity
     *
     * @param worldIn - world
     * @param shooter - shooter
     * @param accelX  - x speed
     * @param accelY  - y speed
     * @param accelZ  - z speed
     * @return new instance of fireball entity
     */
    Entity createFireball(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ);
}
