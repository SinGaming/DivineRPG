package divinerpg.entities.vanilla.triplets;

import divinerpg.entities.base.DivineFireball;
import divinerpg.entities.base.DivineGhast;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EnderTriplets extends DivineGhast {

    public EnderTriplets(World world) {
        super(EntitiesRegistry.ender_triplets, world, SoundEvents.ENTITY_GHAST_SCREAM, SoundEvents.ENTITY_GHAST_AMBIENT, 1);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D);
    }

    @Override
    public AbstractFireballEntity createFireball(World worldIn, LivingEntity shooter, double x, double y, double z) {
        return new DivineFireball(EntitiesRegistry.divine_fireball, worldIn, shooter, x, y, z, ParticleTypes.PORTAL,
                "ender_triplets_fireball");
    }
}
