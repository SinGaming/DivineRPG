package divinerpg.entities.bosses.etheral;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.projectiles.DivineArrow.DivineArrow;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.utils.projectile.Powers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EternalArcher extends DivineBoss {
    public EternalArcher(World world) {
        super(EntitiesRegistry.eternal_archer, world, randomColor(), 250);

        setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemRegistry.halite_bow));
        setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ItemRegistry.halite_bow));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        Powers power = new Powers();
        List<EffectInstance> effects = new ArrayList<>();

        // Every 15-th arrow is killing
        if (rand.nextInt(15) == 0) {

            if (canApplyAbility()) {
                power.withFire(12);
            }

            if (canApplyAbility()) {
                power.withExplosion(3, Explosion.Mode.BREAK);
            }

            if (canApplyAbility()) {
                effects.add(new EffectInstance(Effects.WITHER, 100, 2));
            }

            if (canApplyAbility()) {
                effects.add(new EffectInstance(Effects.SLOWNESS, 100, 2));
            }

            if (canApplyAbility()) {
                effects.add(new EffectInstance(Effects.BLINDNESS, 100, 0));
            }

            if (canApplyAbility()) {
                effects.add(new EffectInstance(Effects.NAUSEA, 100, 0));
            }
        }


        DivineArrow arrow = new DivineArrow(world, this, "fury_arrow", 26, power);

        if (!effects.isEmpty()) {
            effects.forEach(arrow::addEffect);
        }

        launchArrow(target, arrow);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1500, 0, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        initAI(true, false);
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 4.5F;
    }

    private boolean canApplyAbility() {
        // 6 arms and can choose beetween 6 of them
        return rand.nextInt(6) == 0;
    }
}
