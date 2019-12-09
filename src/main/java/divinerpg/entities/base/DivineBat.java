package divinerpg.entities.base;

import divinerpg.entities.goal.MeleeGoal;
import divinerpg.entities.goal.RevengeGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class DivineBat extends BatEntity {
    private final SoundEvent hurt;
    private final SoundEvent ambient;

    @Deprecated
    protected DivineBat(World world) {
        this(EntityType.BAT, world, SoundEvents.ENTITY_BAT_HURT, SoundEvents.ENTITY_BAT_AMBIENT);
    }

    public DivineBat(EntityType<? extends BatEntity> type, World world, SoundEvent hurt, SoundEvent ambient) {
        super(type, world);
        this.hurt = hurt;
        this.ambient = ambient;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.5F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        targetSelector.addGoal(1, new RevengeGoal(this));
        goalSelector.addGoal(1, new MeleeGoal(this, 1, true));
    }

    @Override
    protected SoundEvent getDeathSound() {
        return hurt;
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return ambient;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return hurt;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        boolean result = super.attackEntityAsMob(entityIn);

        if (result && entityIn instanceof LivingEntity) {
            onHit(((LivingEntity) entityIn));
        }

        return result;
    }

    protected void onHit(@Nonnull LivingEntity victim) {

    }
}
