package divinerpg.entities.vanilla.bat.jungle;

import divinerpg.entities.base.DivineBat;
import divinerpg.registry.EntitiesRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class JungleBat extends DivineBat {

    public JungleBat(World world) {
        super(EntitiesRegistry.jungle_bat, world, SoundEvents.ENTITY_BAT_HURT, SoundEvents.ENTITY_BAT_AMBIENT);
    }

    public JungleBat(EntityType<? extends BatEntity> type, World world) {
        super(type, world, SoundEvents.ENTITY_BAT_HURT, SoundEvents.ENTITY_BAT_AMBIENT);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && entityIn instanceof LivingEntity) {
            ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 20 * 2, 1));
        }

        return result;
    }
}
