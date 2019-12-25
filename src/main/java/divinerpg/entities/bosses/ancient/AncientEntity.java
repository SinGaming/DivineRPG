package divinerpg.entities.bosses.ancient;

import divinerpg.entities.base.DivineBoss;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AncientEntity extends DivineBoss {

    public AncientEntity(World world) {
        super(EntitiesRegistry.ancient_entity, world, randomColor(), 2000);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        initAI(false, true);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(800, 12, 0);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && entityIn instanceof LivingEntity) {
            Vec3d motion = this.getMotion();
            ((LivingEntity) entityIn).knockBack(this, 10, motion.x, motion.z);
            playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1, 1);
        }

        return result;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }
}
