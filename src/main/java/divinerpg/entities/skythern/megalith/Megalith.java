package divinerpg.entities.skythern.megalith;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class Megalith extends DivineMonster {
    public Megalith(World world) {
        super(EntitiesRegistry.megalith, world, SoundRegistry.MEGALITH_HURT, SoundRegistry.MEGALITH, 3.6F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(350, 20, 10);

        // A bit slowlier
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3 * 0.9);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
        this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(1.5F);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && entityIn instanceof LivingEntity) {
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 2, false, false));
        }

        return result;
    }
}
