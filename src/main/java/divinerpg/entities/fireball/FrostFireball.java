package divinerpg.entities.fireball;

import divinerpg.entities.base.DivineFireball;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class FrostFireball extends DivineFireball {

    public FrostFireball(World worldIn) {
        super(EntitiesRegistry.frost_shot, worldIn);
    }

    public FrostFireball(EntityType<? extends FireballEntity> type, World world) {
        this(world);
    }

    public FrostFireball(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntitiesRegistry.frost_shot, worldIn, shooter, accelX, accelY, accelZ, ParticleTypes.END_ROD, "frost_shot");
    }

    @Override
    protected boolean onBlockHit(@Nonnull BlockPos pos) {
        if (!world.isRemote()) {
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

            remove();
        }

        return true;
    }

    @Override
    protected boolean onEntityHit(@Nonnull Entity entity) {
        return onBlockHit(entity.getPosition());
    }
}
