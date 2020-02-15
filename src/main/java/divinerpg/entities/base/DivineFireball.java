package divinerpg.entities.base;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class DivineFireball extends FireballEntity implements ITextured {
    protected static final DataParameter<String> NAME = EntityDataManager.createKey(DivineFireball.class, DataSerializers.STRING);
    protected static final DataParameter<IParticleData> PARTICLE = EntityDataManager.createKey(DivineFireball.class, DataSerializers.PARTICLE_DATA);
    private EntityType<?> type;
    public float damage = 6;

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
        IParticleData result = getDataManager().get(PARTICLE);

        return result == null
                ? super.getParticle()
                : result;
    }

    @Override
    protected void registerData() {
        super.registerData();

        EntityDataManager manager = getDataManager();

        manager.register(NAME, "halite_blitz");
        manager.register(PARTICLE, ParticleTypes.SMOKE);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result instanceof EntityRayTraceResult && onEntityHit(((EntityRayTraceResult) result).getEntity())) {
            return;
        }

        if (result instanceof BlockRayTraceResult && onBlockHit(((BlockRayTraceResult) result).getPos())) {
            return;
        }

        if (!this.world.isRemote) {
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult) result).getEntity();
                entity.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 6.0F);
                this.applyEnchantments(this.shootingEntity, entity);
            }

            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity);
            this.world.createExplosion(shootingEntity, this.serverPosX, this.serverPosY, this.serverPosZ, (float) this.explosionPower, flag, flag ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            this.remove();
        }
    }

    /**
     * Called when fireball hits entity
     *
     * @param entity
     * @return Should handle execution onImpact
     */
    protected boolean onEntityHit(@Nonnull Entity entity) {
        return false;
    }

    /**
     * Called when fireball hits block
     *
     * @param pos
     * @return Should handle execution onImpact
     */
    protected boolean onBlockHit(@Nonnull BlockPos pos) {
        return false;
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("Damage"))
            damage = compound.getFloat("Damage");
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putFloat("Damage", damage);
    }
}
