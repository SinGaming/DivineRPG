package divinerpg.entities.bosses.scorcher;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import divinerpg.utils.projectile.Powers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class KingOfScorchers extends DivineBoss {
    int prevSuperAttack;

    public KingOfScorchers(World world) {
        super(EntitiesRegistry.king_of_scorchers, world, randomColor(), 2000);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1100, 22, 10);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(true, false);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        // ignore explosion damage
        return !source.isExplosion() && super.attackEntityFrom(source, amount);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        BulletEntity bullet = new BulletEntity(world, this, 14, "king_of_scorchers_shot", null, new Powers().withFire(8));
        launchArrow(target, bullet);

        if (ticksExisted - prevSuperAttack > 250) {
            superRangedAttack(target);
        }
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 1;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.KING_OF_SCORCHERS;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundRegistry.KING_OF_SCORCHERS_HURT;
    }

    private void superRangedAttack(@Nonnull LivingEntity target) {
        prevSuperAttack = ticksExisted;

        // single shot with large explosion
        BulletEntity bullet = new BulletEntity(world, this, 0, "king_of_scorchers_meteor", null,
                new Powers().withExplosion(5, Explosion.Mode.BREAK))
                .disableGravity()
                .setMaxLiveInTicks(200);

        BlockPos pos = target.getPosition().add(new Vec3i(rand.nextFloat() * 2 - 1, 10, rand.nextFloat() * 2 - 1));
        bullet.setPosition(pos.getX(), pos.getY(), pos.getZ());
        bullet.addVelocity(rand.nextFloat() - rand.nextFloat() / 5, -0.7, rand.nextFloat() - rand.nextFloat() / 5);

        world.addEntity(bullet);
    }
}
