package divinerpg.entities.projectiles;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.EntityHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ItemBulletEntity extends ThrowableEntity implements IRendersAsItem {
    protected static final DataParameter<ItemStack> ITEMSTACK_DATA = EntityDataManager.createKey(ItemBulletEntity.class, DataSerializers.ITEMSTACK);
    protected static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(ItemBulletEntity.class, DataSerializers.FLOAT);
    protected static final DataParameter<IParticleData> PARTICLE = EntityDataManager.createKey(ItemBulletEntity.class, DataSerializers.PARTICLE_DATA);

    /**
     * Needed for creation factory
     */
    protected ItemBulletEntity(World world) {
        this(EntitiesRegistry.bullet_item_entity, world);
    }

    public ItemBulletEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    /**
     * Client ctor
     *
     * @param world   - current world
     * @param thrower - current thrower
     * @param render  - rendering item
     * @param damage  - damage amount
     */
    public ItemBulletEntity(World world, LivingEntity thrower, Item render, float damage) {
        super(EntitiesRegistry.bullet_item_entity, thrower, world);

        EntityDataManager dataManager = getDataManager();

        dataManager.set(ITEMSTACK_DATA, new ItemStack(render));
        dataManager.set(DAMAGE, damage);
        dataManager.set(PARTICLE, new ItemParticleData(ParticleTypes.ITEM, getItem()));
    }

    @Override
    public void tick() {
        super.tick();

        EntityHelper.spawnParticle(this, getDataManager().get(PARTICLE));
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        EntityHelper.handleImpact(this, result, getDataManager().get(DAMAGE));
    }

    @Override
    protected void registerData() {
        EntityDataManager manager = getDataManager();

        manager.register(ITEMSTACK_DATA, new ItemStack(Items.EGG));
        manager.register(DAMAGE, 0F);
        manager.register(PARTICLE, new ItemParticleData(ParticleTypes.ITEM, getItem()));
    }

    @Override
    public ItemStack getItem() {
        return getDataManager().get(ITEMSTACK_DATA);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
