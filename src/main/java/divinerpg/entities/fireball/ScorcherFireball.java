package divinerpg.entities.fireball;

import divinerpg.entities.base.DivineFireball;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ScorcherFireball extends DivineFireball {

    public ScorcherFireball(EntityType<? extends FireballEntity> type, World world) {
        super(type, world);
    }

    public ScorcherFireball(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(EntitiesRegistry.scorcher_fireball, worldIn, shooter, accelX, accelY, accelZ,
                ParticleTypes.SMOKE, "scorcher_shot");
    }

    @Override
    protected boolean onEntityHit(@Nonnull Entity entity) {
        entity.setFire(5);
        return super.onEntityHit(entity);
    }
}
