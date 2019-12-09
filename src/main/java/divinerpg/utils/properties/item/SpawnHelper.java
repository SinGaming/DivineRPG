package divinerpg.utils.properties.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
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

        float playerVelocity = (float) player.getDistanceSq(player.posX, player.posY, player.posZ);

        bullet.shoot(player, player.rotationPitch, player.rotationYaw, 0, 1.5F + playerVelocity, 1F);
        world.addEntity(bullet);
    }

    /**
     * ONLY SERVERSIDE ACTION
     * Spawn single entity
     */
    public static void singleSpawn(World world, LivingEntity player, AbstractArrowEntity bullet) {
        if (world.isRemote())
            return;

        float playerVelocity = (float) player.getDistanceSq(player.posX, player.posY, player.posZ);

        bullet.shoot(player, player.rotationPitch, player.rotationYaw, 0, 1.5F + playerVelocity, 1F);
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
