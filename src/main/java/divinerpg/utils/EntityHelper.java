package divinerpg.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

import java.util.Random;
import java.util.function.Supplier;

public class EntityHelper {
    /**
     * Extracted code
     *
     * @param bullet - bullet entity
     * @param result - ray trace result
     * @param damage - damage amount
     */
    public static void handleImpact(ThrowableEntity bullet, RayTraceResult result, float damage) {
        if (result instanceof EntityRayTraceResult) {
            Entity target = ((EntityRayTraceResult) result).getEntity();
            // ignore other bullets
            if (!(target instanceof IProjectile) && bullet.getThrower() != target) {
                target.attackEntityFrom(DamageSource.causeThrownDamage(bullet, target), damage);
            } else {
                // ignore if we impact with bullets or thrower
                return;
            }
        }

        if (!bullet.getEntityWorld().isRemote()) {
            bullet.remove();
        }
    }

    public static void spawnParticle(ThrowableEntity bullet, IParticleData data) {
        double x = bullet.posX;
        double y = bullet.posY;
        double z = bullet.posZ;

        Random rand = bullet.getEntityWorld().rand;
        Supplier<Integer> randPos = () -> rand.nextInt(2) - 1;

        bullet.getEntityWorld().addParticle(data, x, y, z, randPos.get(), randPos.get(), randPos.get());
    }
}
