package divinerpg.utils.properties;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;

import java.util.Random;

public class SpawnHelper {

    /**
     * ONLY SERVERSIDE ACTION
     * Spawn single entity
     */
    public static void singleSpawn(World world, LivingEntity player, ThrowableEntity bullet) {
        if (world.isRemote())
            return;

        bullet.shoot(player, player.prevRotationPitch, player.prevRotationYaw, 0, 1.5F, 1F);
        world.addEntity(bullet);
    }

    /**
     * ONLY SERVERSIDE ACTION
     * Spawns amount in range
     *
     * @param count      - bullets count
     * @param power      - power in percentage 1 ~ 100
     * @param createFunc - createFunc func
     */
    public static void spawnRange(World world, LivingEntity player, int power, int count, ICreateEntity createFunc) {
        if (world.isRemote())
            return;

        Random rand = world.rand;

        for (int i = 0; i < count; i++) {
            ThrowableEntity bullet = createFunc.create(world, player, power);

            bullet.posX += (rand.nextDouble() - rand.nextDouble()) * 1.5;
            bullet.posY += (rand.nextDouble() - rand.nextDouble()) * 1.5;
            bullet.posZ += (rand.nextDouble() - rand.nextDouble()) * 1.5;

            singleSpawn(world, player, bullet);
        }
    }
}
