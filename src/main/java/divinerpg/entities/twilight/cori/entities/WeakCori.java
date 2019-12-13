package divinerpg.entities.twilight.cori.entities;

import divinerpg.entities.base.DivineGhast;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class WeakCori extends DivineGhast {
    private final float attack;
    private final String bulletName;

    public WeakCori(World world) {
        this(EntitiesRegistry.weak_cori, world, 30, "cori_shot");
    }

    public WeakCori(EntityType<? extends GhastEntity> type, World world, float attack, String bulletName) {
        super(type, world, SoundRegistry.CORI_HURT, SoundRegistry.CORI_IDLE, 0.8F);
        this.attack = attack;
        this.bulletName = bulletName;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getCoriHealth());
    }

    @Override
    public Entity createFireball(World worldIn, LivingEntity shooter, double x, double y, double z) {
        BulletEntity bullet = new BulletEntity(worldIn, shooter, attack, bulletName);
        double speed = (double) MathHelper.sqrt(x * x + z * z);
        bullet.shoot(x, y + speed * (double) 0.2F, z, 1.6F, 1);
        return bullet;
    }

    @Override
    protected SoundEvent shootSound() {
        return SoundRegistry.CORI_SHOOT;
    }

    protected float getCoriHealth() {
        return 10;
    }
}
