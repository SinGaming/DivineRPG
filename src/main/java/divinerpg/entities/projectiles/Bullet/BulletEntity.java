package divinerpg.entities.projectiles.Bullet;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.EntityHelper;
import divinerpg.utils.ITextured;
import divinerpg.utils.projectile.Powers;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
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
    protected static final DataParameter<CompoundNBT> POWERS = EntityDataManager.createKey(BulletEntity.class, DataSerializers.COMPOUND_NBT);
    protected static final DataParameter<IParticleData> PARTICLE = EntityDataManager.createKey(BulletEntity.class, DataSerializers.PARTICLE_DATA);
    protected static final DataParameter<Float> GRAVITY = EntityDataManager.createKey(BulletEntity.class, DataSerializers.FLOAT);
    protected static final DataParameter<Integer> MAX_LIVE_TICKS = EntityDataManager.createKey(BulletEntity.class, DataSerializers.VARINT);

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
        this(worldIn, thrower, damage, name, particleData, null);
    }

    public BulletEntity(World worldIn, LivingEntity thrower, float damage, String name, IParticleData particleData, Powers power) {
        this(EntitiesRegistry.bullet_entity, worldIn, thrower, damage, name, particleData, power);
    }

    protected BulletEntity(EntityType<? extends ThrowableEntity> type, World worldIn, LivingEntity thrower, float damage, String name, IParticleData particleData, Powers power) {
        super(type, thrower, worldIn);

        EntityDataManager manager = getDataManager();

        manager.set(DAMAGE, damage);
        manager.set(NAME, name);
        manager.set(OWNER, Optional.of(thrower.getUniqueID()));

        if (particleData != null) {
            manager.set(PARTICLE, particleData);
        }

        if (power != null) {
            manager.set(POWERS, power.toTag());
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        EntityHelper.handleImpact(this, result, getDataManager().get(DAMAGE));

        if (result.getType() != RayTraceResult.Type.MISS) {
            Powers.handlePowers(this, result, getDataManager().get(POWERS));
        }
    }

    @Override
    public void tick() {
        super.tick();
        EntityHelper.spawnParticle(this, getDataManager().get(PARTICLE));

        Integer maxTicks = getDataManager().get(MAX_LIVE_TICKS);
        if (maxTicks > 0 && ticksExisted > maxTicks && !world.isRemote()) {
            remove();
        }
    }

    @Override
    protected void registerData() {
        EntityDataManager manager = getDataManager();

        manager.register(DAMAGE, 0F);
        manager.register(OWNER, Optional.empty());
        manager.register(NAME, "halite_blitz");
        manager.register(POWERS, new CompoundNBT());
        manager.register(PARTICLE, new BasicParticleType(true));
        manager.register(GRAVITY, super.getGravityVelocity());
        manager.register(MAX_LIVE_TICKS, -1);
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

    public ResourceLocation getTexture() {
        return CachedTexture.PROJECTILES.getTexture(getDataManager().get(NAME));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected float getGravityVelocity() {
        return getDataManager().get(GRAVITY);
    }

    /**
     * Disables gravity for bullet
     *
     * @param <T>
     * @return
     */
    public <T extends BulletEntity> T disableGravity() {
        getDataManager().set(GRAVITY, 0F);
        return (T) this;
    }

    /**
     * Set max live time for bullet
     *
     * @param ticks - ticks count
     * @param <T>
     * @return
     */
    public <T extends BulletEntity> T setMaxLiveInTicks(int ticks) {
        getDataManager().set(MAX_LIVE_TICKS, ticks);
        return (T) this;
    }
}
