package divinerpg.entities.mortum.stealer;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class SoulStealer extends DivineMonster {
    public SoulStealer(World world) {
        super(EntitiesRegistry.soul_stealer, world, SoundRegistry.INSECT, SoundRegistry.INSECT, 1.75F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(142, 20, 10);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && entityIn instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entityIn;
            int duration = 12 * 20;

            livingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA, duration, 0, false, false));
            livingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, duration, 0, false, false));
        }

        return result;
    }
}
