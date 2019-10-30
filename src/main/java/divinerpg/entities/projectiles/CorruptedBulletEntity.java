package divinerpg.entities.projectiles;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CorruptedBulletEntity extends ProjectileItemEntity {

    protected CorruptedBulletEntity(World world) {
        super(EntitiesRegistry.corruptedBullet, world);
    }

    public CorruptedBulletEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public CorruptedBulletEntity(World world, LivingEntity thrower) {
        super(EntitiesRegistry.corruptedBullet, thrower, world);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        if (result instanceof EntityRayTraceResult) {
            Entity target = ((EntityRayTraceResult) result).getEntity();
            // ignore other bullets
            if (!(target instanceof IProjectile) && getThrower() != target) {
                target.attackEntityFrom(DamageSource.causeThrownDamage(this, target), 10);
            } else {
                // ignore if we impact with bullets or thrower
                return;
            }
        }

        if (!this.getEntityWorld().isRemote()) {
            this.remove();
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected Item func_213885_i() {
        return ItemRegistry.corruptedBullet;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
