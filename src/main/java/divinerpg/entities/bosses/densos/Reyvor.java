package divinerpg.entities.bosses.densos;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.projectiles.DivineArrow.DivineArrow;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Reyvor extends DivineBoss {
    public Reyvor(World world) {
        super(EntitiesRegistry.reyvor, world, BossInfo.Color.YELLOW, 2000);

        setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemRegistry.twilight_bow));
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        DivineArrow arrow = new DivineArrow(world, this, "fury_arrow", 15, "");
        launchArrow(this, arrow);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(true, false);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1000, 20, 0);
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 2;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundRegistry.REYVOR_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.REYVOR;
    }
}
