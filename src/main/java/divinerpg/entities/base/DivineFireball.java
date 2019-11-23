package divinerpg.entities.base;

import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DivineFireball extends FireballEntity implements ITextured {
    protected static final DataParameter<String> NAME = EntityDataManager.createKey(DivineFireball.class, DataSerializers.STRING);
    protected static final DataParameter<IParticleData> PARTICLE = EntityDataManager.createKey(DivineFireball.class, DataSerializers.PARTICLE_DATA);
    private EntityType<?> type;

    public DivineFireball(World worldIn) {
        this(EntitiesRegistry.divine_fireball, worldIn);
    }

    public DivineFireball(EntityType<? extends FireballEntity> type, World world) {
        super(type, world);
        this.type = type;
    }

    public DivineFireball(EntityType<? extends FireballEntity> type, World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ, IParticleData particleData, String name) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.type = type;

        getDataManager().set(NAME, name);
        getDataManager().set(PARTICLE, particleData);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ResourceLocation getTexture() {
        return CachedTexture.PROJECTILES.getTexture(getDataManager().get(NAME));
    }

    @Override
    public EntityType<?> getType() {
        return type;
    }

    @Override
    protected IParticleData getParticle() {
        return getDataManager().get(PARTICLE);
    }

    @Override
    protected void registerData() {
        super.registerData();

        EntityDataManager manager = getDataManager();

        manager.register(NAME, "halite_blitz");
        manager.register(PARTICLE, ParticleTypes.SMOKE);
    }
}
