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
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Consumer;

public class DivineArrowEntity extends ArrowEntity implements ITextured {
    private static final DataParameter<String> NAME = EntityDataManager.createKey(DivineArrowEntity.class, DataSerializers.STRING);
    private Consumer<RayTraceResult> onHit;

    protected DivineArrowEntity(World world) {
        this(EntitiesRegistry.arrow_entity, world);
    }

    public DivineArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
    }

    public DivineArrowEntity(World worldIn, LivingEntity shooter, String name, int damage, Consumer<RayTraceResult> onHit) {
        super(EntitiesRegistry.arrow_entity, worldIn);

        setDamage(damage);
        getDataManager().set(NAME, name);

        this.setShooter(shooter);
        setPositionAndRotation(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);

        this.onHit = onHit;
    }

    @Override
    protected void registerData() {
        super.registerData();

        getDataManager().register(NAME, "hunter_arrow");
    }

    @Override
    protected void onHit(RayTraceResult raytrace) {
        super.onHit(raytrace);

        if (onHit != null) {
            onHit(raytrace);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getTexture() {
        return CachedTexture.PROJECTILES.getTexture(getDataManager().get(NAME));
    }
}
