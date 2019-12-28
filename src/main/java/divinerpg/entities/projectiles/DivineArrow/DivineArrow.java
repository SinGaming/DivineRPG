package divinerpg.entities.projectiles.DivineArrow;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import divinerpg.utils.projectile.Powers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DivineArrow extends ArrowEntity implements ITextured {
    private static final DataParameter<String> NAME = EntityDataManager.createKey(DivineArrow.class, DataSerializers.STRING);
    private static final DataParameter<CompoundNBT> POWER = EntityDataManager.createKey(DivineArrow.class, DataSerializers.COMPOUND_NBT);

    protected DivineArrow(World world) {
        super(EntitiesRegistry.arrow_entity, world);
    }

    public DivineArrow(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    /**
     * Creates arrow
     *
     * @param worldIn - world
     * @param shooter - thrower
     * @param name    - name of arrow. Used for textures
     * @param damage  - damage amount
     */
    public DivineArrow(World worldIn, LivingEntity shooter, String name, double damage) {
        this(worldIn, shooter, name, damage, null);
    }

    /**
     * Creates arrow
     *
     * @param worldIn - world
     * @param shooter - thrower
     * @param name    - name of arrow. Used for textures
     * @param damage  - damage amount
     * @param powers  - name of possible powers, separated by ';'. Currently only 'explosion', use potion Effects for others
     */
    public DivineArrow(World worldIn, LivingEntity shooter, String name, double damage, Powers powers) {
        this(worldIn);

        setDamage(damage);
        getDataManager().set(NAME, name);
        getDataManager().set(POWER, powers == null ? new CompoundNBT() : powers.toTag());


        this.setShooter(shooter);
        setPositionAndRotation(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

        if (shooter instanceof PlayerEntity && !((PlayerEntity) shooter).isCreative()) {
            this.pickupStatus = PickupStatus.ALLOWED;
        }

        // todo add particle if superpowered
    }

    @Override
    protected void registerData() {
        super.registerData();
        getDataManager().register(NAME, "hunter_arrow");
        getDataManager().register(POWER, new CompoundNBT());
    }

    @Override
    protected void onHit(RayTraceResult raytrace) {
        super.onHit(raytrace);

        if (raytrace.getType() == RayTraceResult.Type.MISS)
            return;

        Powers.handlePowers(this, raytrace, getDataManager().get(POWER));
    }

    public ResourceLocation getTexture() {
        return CachedTexture.PROJECTILES.getTexture(getDataManager().get(NAME));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
