package divinerpg.entities.bosses.deamon;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import divinerpg.utils.RGBHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

public class TwilightDemon extends DivineBoss {
    public TwilightDemon(World world) {
        super(EntitiesRegistry.twilight_demon, world, BossInfo.Color.RED, 2000);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1600, 30, 10);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(true, true);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        // launches two shots
        for (int i = 0; i < 2; i++) {
            IParticleData particleData = rand.nextInt(50) == 0
                    ? RGBHelper.particlefromRGB(0, 0, 0)
                    : RGBHelper.particlefromRGB(255, 0, 0);

            launchArrow(target, new BulletEntity(world, this, 16, "twilight_demon_shot", particleData));
        }
    }

    // perfect archer always
    @Override
    protected float getInaccuracy() {
        return 0;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 3.5F;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.INSECT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundRegistry.INSECT;
    }
}
