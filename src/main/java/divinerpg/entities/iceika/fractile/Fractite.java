package divinerpg.entities.iceika.fractile;

import divinerpg.entities.base.DivineFireball;
import divinerpg.entities.base.DivineGhast;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class Fractite extends DivineGhast {
    public Fractite(World world) {
        super(EntitiesRegistry.fractite, world, SoundRegistry.FRACTITE_HURT, SoundRegistry.FRACTITE, 0.7F);
        setPathPriority(PathNodeType.WATER, -1);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
    }

    @Override
    protected SoundEvent shootSound() {
        return SoundRegistry.FRACTITE_CANNON;
    }

    @Override
    public Entity createFireball(World worldIn, LivingEntity shooter, double x, double y, double z) {
        DivineFireball bullet = new DivineFireball(EntitiesRegistry.divine_fireball, world, shooter, x, y, z, ParticleTypes.END_ROD, "fractite_shot");
        bullet.explosionPower = 3;
        bullet.damage = 12;
        return bullet;
    }
}
