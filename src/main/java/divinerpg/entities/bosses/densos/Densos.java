package divinerpg.entities.bosses.densos;

import divinerpg.entities.base.DivineBoss;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.ItemRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Densos extends DivineBoss {
    public Densos(World world) {
        super(EntitiesRegistry.densos, world, BossInfo.Color.RED, 2000);

        putItem(EquipmentSlotType.MAINHAND, ItemRegistry.haliteBlade);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        initAI(false, true);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        initAttr(1000, 0, 10);
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return 2;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundRegistry.DENSOS_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.DENSOS;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    protected float getDropChance(EquipmentSlotType slotIn) {
        return super.getDropChance(slotIn);
    }
}
