package divinerpg.entities.bosses.etheral;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.projectiles.DivineArrow.DivineArrow;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class EternalArcher extends DivineBoss {
    public EternalArcher(World world) {
        super(EntitiesRegistry.eternal_archer, world, randomColor(), 250);

        setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemRegistry.halite_bow));
        setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(ItemRegistry.halite_bow));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        String power = "";
        EffectInstance effect = null;

        switch (rand.nextInt(100)) {
            case 0:
                power = "fire";
                break;

            case 1:
                power = "explosion";
                break;

            case 2:
                effect = new EffectInstance(Effects.WITHER, 100, 2);
                break;

            case 3:
                effect = new EffectInstance(Effects.SLOWNESS, 100, 2);
                break;

            case 4:
                effect = new EffectInstance(Effects.BLINDNESS, 100, 0);
                break;

            case 5:
                effect = new EffectInstance(Effects.NAUSEA, 100, 0);
        }

        DivineArrow arrow = new DivineArrow(world, this, "fury_arrow", 18, power);

        if (effect != null) {
            arrow.addEffect(effect);
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

        initAI(true, false);
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 4.5F;
    }
}
