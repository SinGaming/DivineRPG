package divinerpg.entities.projectiles;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.utils.EntityHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ApalachiaSlicerEntity extends ProjectileItemEntity {
    ////////////////////
    // OVERRIDE THESE VALUES:
    // damage, render, getEntityType()
    ///////////////////
    private static LazyLoadBase<Item> render = new LazyLoadBase<>(() -> ItemRegistry.apalachiaSlicer);
    private final int damage = 12;
    private final IParticleData particle = new ItemParticleData(ParticleTypes.ITEM, new ItemStack(render.getValue()));


    protected ApalachiaSlicerEntity(World world) {
        super(getEntityType(), world);
    }

    public ApalachiaSlicerEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ApalachiaSlicerEntity(World world, LivingEntity thrower) {
        super(getEntityType(), thrower, world);
    }

    private static EntityType<? extends ProjectileItemEntity> getEntityType() {
        return EntitiesRegistry.apalachia_slicer;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        EntityHelper.handleImpact(this, result, damage);
    }

    @Override
    public void tick() {
        super.tick();
        EntityHelper.spawnParticle(this, particle);
    }

    @Override
    protected Item func_213885_i() {
        return render.getValue();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
