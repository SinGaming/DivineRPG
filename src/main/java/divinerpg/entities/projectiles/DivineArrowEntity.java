package divinerpg.entities.projectiles;

import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DivineArrowEntity extends ArrowEntity implements ITextured {
    private static final DataParameter<String> NAME = EntityDataManager.createKey(DivineArrowEntity.class, DataSerializers.STRING);
    private CachedTexture texture;
    private final List<Consumer<RayTraceResult>> onHit = new ArrayList<>();

    protected DivineArrowEntity(World world) {
        this(EntitiesRegistry.arrow_entity, world);
    }

    public DivineArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
    }

    public DivineArrowEntity(World worldIn, LivingEntity shooter, String name, int damage) {
        super(EntitiesRegistry.arrow_entity, worldIn);

        setDamage(damage);
        getDataManager().set(NAME, name);

        this.setShooter(shooter);
        setPositionAndRotation(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

        texture = CachedTexture.createForProjectiles();
    }

    public DivineArrowEntity withExposion(float power) {
        onHit.add(rayTraceResult ->
                this.getEntityWorld().createExplosion(getShooter(), this.posX, this.posY, this.posZ, power, false, Explosion.Mode.NONE)
        );

        return this;
    }

    @Override
    protected void registerData() {
        super.registerData();

        getDataManager().register(NAME, "hunter_arrow");
    }

    @Override
    protected void onHit(RayTraceResult raytrace) {
        super.onHit(raytrace);
        onHit.forEach(x -> x.accept(raytrace));
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        return texture.getTexture(getDataManager().get(NAME));
    }
}
