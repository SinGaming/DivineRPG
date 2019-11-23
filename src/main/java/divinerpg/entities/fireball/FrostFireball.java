package divinerpg.entities.fireball;

import divinerpg.entities.base.DivineFireball;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.utils.CachedTexture;
import divinerpg.utils.ITextured;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class FrostFireball extends DivineFireball {

    public FrostFireball(World worldIn) {
        super(worldIn);
    }

    public FrostFireball(EntityType<? extends FireballEntity> type, World world) {
        super(type, world);
    }

    public FrostFireball(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntitiesRegistry.divine_fireball, worldIn, shooter, accelX, accelY, accelZ, ParticleTypes.SMOKE, "frost_shot");
    }
//
    @Override
    protected void onImpact(RayTraceResult result) {
        if (world.isRemote() || result.getType() == RayTraceResult.Type.MISS)
            return;

        BlockPos pos = null;

        if (result instanceof EntityRayTraceResult) {
            pos = new BlockPos(((EntityRayTraceResult) result).getEntity());
        }
        if (result instanceof BlockRayTraceResult) {
            pos = ((BlockRayTraceResult) result).getPos();
        }

        if (pos != null) {
            AreaEffectCloudEntity frostCloud = new AreaEffectCloudEntity(this.world, pos.getX(), pos.getY(), pos.getZ());
            frostCloud.setOwner(this.shootingEntity);
            frostCloud.setDuration(50);
            frostCloud.setRadiusPerTick((0.0F - frostCloud.getRadius()) / frostCloud.getDuration());
            frostCloud.setParticleData(getParticle());

            // Currently poison is MAGIC damage, exacly what we need
            frostCloud.addEffect(new EffectInstance(Effects.POISON, 1, 0));
            this.world.playSound(null, getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4,
                    (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
            this.world.addEntity(frostCloud);
        }

        this.remove();
    }
}
