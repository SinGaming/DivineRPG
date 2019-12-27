package divinerpg.entities.projectiles.Bullet;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.EntityHelper;
import divinerpg.utils.ITextured;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

/**
 * Textured bullet
 */
public class BulletEntity extends ThrowableEntity implements ITextured {
    protected static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BulletEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
    protected static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(BulletEntity.class, DataSerializers.FLOAT);
    protected static final DataParameter<String> NAME = EntityDataManager.createKey(BulletEntity.class, DataSerializers.STRING);
    protected static final DataParameter<IParticleData> PARTICLE = EntityDataManager.createKey(BulletEntity.class, DataSerializers.PARTICLE_DATA);

    public BulletEntity(World worldIn) {
        this(EntitiesRegistry.bullet_entity, worldIn);
    }

    public BulletEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public BulletEntity(World worldIn, LivingEntity thrower, float damage, String name) {
        this(worldIn, thrower, damage, name, null);
    }

    public BulletEntity(World worldIn, LivingEntity thrower, float damage, String name, IParticleData particleData) {
        super(EntitiesRegistry.bullet_entity, thrower, worldIn);

        EntityDataManager manager = getDataManager();

        manager.set(DAMAGE, damage);
        manager.set(NAME, name);
        manager.set(OWNER, Optional.of(thrower.getUniqueID()));
        if (particleData != null) {
            manager.set(PARTICLE, particleData);
        }
    }

    protected BulletEntity(EntityType<? extends ThrowableEntity> type, World worldIn, LivingEntity thrower, float damage, String name, IParticleData particleData) {
        super(type, thrower, worldIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        EntityHelper.handleImpact(this, result, getDataManager().get(DAMAGE));
    }

    @Override
    public void tick() {
        super.tick();
        EntityHelper.spawnParticle(this, getDataManager().get(PARTICLE));
    }

    @Override
    protected void registerData() {
        EntityDataManager manager = getDataManager();

        manager.register(DAMAGE, 0F);
        manager.register(OWNER, Optional.empty());
        manager.register(NAME, "halite_blitz");
        manager.register(PARTICLE, new BasicParticleType(true));
    }

    @Nullable
    @Override
    public LivingEntity getThrower() {
        LivingEntity thrower = super.getThrower();

        if (thrower == null) {
            Optional<UUID> uuid = getDataManager().get(OWNER);
            if (uuid.isPresent()) {
                return this.world.getPlayerByUuid(uuid.get());
            }
        }

        return thrower;
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        return CachedTexture.PROJECTILES.getTexture(getDataManager().get(NAME));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
