package divinerpg.entities.bosses.fiend;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.projectiles.SoulFiendShot;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class SoulFiend extends DivineBoss {
    public SoulFiend(World world) {
        super(EntitiesRegistry.soul_fiend, world, randomColor(), 2000);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(false, true);

        initArcher(150);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1100, 24, 10);

        this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(2.5);
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        for (int i = 0; i < 4; i++) {
            launchArrow(target, new SoulFiendShot(world, this));
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && entityIn instanceof LivingEntity) {
            // apply two potion effects
            Stream.of(Effects.NAUSEA, Effects.BLINDNESS)
                    // 12 sec duration
                    .map(x -> new EffectInstance(x, 12 * 20))
                    .forEach(((LivingEntity) entityIn)::addPotionEffect);
        }

        return result;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 1.725F;
    }
}
