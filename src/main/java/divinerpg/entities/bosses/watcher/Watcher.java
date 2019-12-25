package divinerpg.entities.bosses.watcher;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.DivineFireball;
import divinerpg.entities.goal.GhastAttackGoal;
import divinerpg.entities.goal.RandomTeleportGoal;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import divinerpg.utils.properties.item.ICreateFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

public class Watcher extends DivineBoss implements ICreateFireball {
    public Watcher(World world) {
        super(EntitiesRegistry.the_watcher, world, BossInfo.Color.RED, 5000);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(950, 0, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new GhastAttackGoal(this, this, SoundEvents.ENTITY_GHAST_SHOOT));

        this.goalSelector.addGoal(3, new RandomTeleportGoal(this, 100));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
    }

    @Override
    public void tick() {
        super.tick();

        Vec3d motion = this.getMotion();
        // TODO Some magical number?..
        Vec3d newMotion = new Vec3d(motion.x, motion.y * 0.6000000238418579D, motion.z);
        setMotion(newMotion);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        // ignored
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        // ignored
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundRegistry.ROAR;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.ROAR;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 2.6F;
    }

    @Override
    public Entity createFireball(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        DivineFireball result = new DivineFireball(EntitiesRegistry.divine_fireball, worldIn, shooter, accelX, accelY, accelZ, null, "watcher_shot");
        result.explosionPower = 10;
        return result;
    }
}
