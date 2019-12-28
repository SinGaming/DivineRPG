package divinerpg.entities.projectiles;

import divinerpg.entities.bosses.fiend.pet.SoulSpider;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.EntityHelper;
import divinerpg.utils.RGBHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class SoulFiendShot extends BulletEntity {
    private final IParticleData blackParticle = RGBHelper.particlefromRGB(0, 0, 0);

    public SoulFiendShot(World worldIn) {
        super(EntitiesRegistry.soul_fiend_shot, worldIn);
    }

    public SoulFiendShot(EntityType<? extends ThrowableEntity> type, World worldIn) {
        this(worldIn);
    }

    public SoulFiendShot(World worldIn, LivingEntity thrower) {
        super(EntitiesRegistry.soul_fiend_shot, worldIn, thrower, 0, "blank", RGBHelper.particlefromRGB(255, 0, 0), null);
    }

    @Override
    public void tick() {
        super.tick();

        // in super method spawn paricle one time
        for (int i = 0; i < 7; i++) {
            EntityHelper.spawnParticle(this, getDataManager().get(PARTICLE));
            EntityHelper.spawnParticle(this, blackParticle);
        }

        if (!world.isRemote() && ticksExisted > 20) {
            remove();
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);

        if (result instanceof EntityRayTraceResult && !world.isRemote()) {
            for (int i = 0; i < 3; i++) {
                Vec3i vec = new Vec3i(rand.nextFloat() - 1, rand.nextFloat() - 1, rand.nextFloat() - 1);
                summonEntity(this.getPosition().add(vec));
            }
        }
    }

    private void summonEntity(BlockPos pos) {
        SoulSpider soulSpider = new SoulSpider(world);
        soulSpider.setMaxLiveInTicks(600);
        soulSpider.setPosition(pos.getX(), pos.getY(), pos.getZ());
        world.addEntity(soulSpider);
    }
}
