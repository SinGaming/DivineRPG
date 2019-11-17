package divinerpg.entities.projectiles.DivineArrow;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DivineArrowEntity extends ArrowEntity implements ITextured {
    private static final DataParameter<String> NAME = EntityDataManager.createKey(DivineArrowEntity.class, DataSerializers.STRING);
    private static final DataParameter<String> POWER = EntityDataManager.createKey(DivineArrowEntity.class, DataSerializers.STRING);

    protected DivineArrowEntity(World world) {
        this(EntitiesRegistry.arrow_entity, world);
    }

    public DivineArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
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
    public DivineArrowEntity(World worldIn, LivingEntity shooter, String name, int damage, String powers) {
        super(EntitiesRegistry.arrow_entity, worldIn);

        setDamage(damage);
        getDataManager().set(NAME, name);
        getDataManager().set(POWER, powers);


        this.setShooter(shooter);
        setPositionAndRotation(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

        if (shooter instanceof PlayerEntity) {
            this.pickupStatus = PickupStatus.ALLOWED;
        }
    }

    @Override
    protected void registerData() {
        super.registerData();
        getDataManager().register(NAME, "hunter_arrow");
        getDataManager().register(POWER, "none");
    }

    @Override
    protected void onHit(RayTraceResult raytrace) {
        super.onHit(raytrace);

        if (raytrace.getType() == RayTraceResult.Type.MISS)
            return;

        Entity victim = null;
        if (raytrace instanceof EntityRayTraceResult) {
            victim = ((EntityRayTraceResult) raytrace).getEntity();
        }

        String powerRaw = getDataManager().get(POWER);
        if (powerRaw != null && powerRaw != "") {
            String[] allPowers = powerRaw.split(";");

            for (String power : allPowers) {
                switch (power) {
                    case "explosion":
                        this.world.createExplosion(getShooter(), this.posX, this.posY, this.posZ, 3.0F, Explosion.Mode.NONE);
                        break;

                    case "fire":
                        if (victim != null) {
                            victim.setFire(12);
                        }
                        break;
                }
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        return CachedTexture.PROJECTILES.getTexture(getDataManager().get(NAME));
    }
}
