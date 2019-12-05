package divinerpg.entities.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class DivineSpider extends SpiderEntity {
    private EntityType<? extends SpiderEntity> type;
    private SoundEvent hurt;
    private SoundEvent ambient;
    private float eyeHight;

    public DivineSpider(World world) {
        this(EntityType.SPIDER, world, SoundEvents.ENTITY_SPIDER_HURT, SoundEvents.ENTITY_SPIDER_AMBIENT, 0.65F);
    }

    public DivineSpider(EntityType<? extends SpiderEntity> type, World world, SoundEvent hurt, SoundEvent ambient, float eyeHight) {
        super(type, world);
        this.type = type;
        this.hurt = hurt;
        this.ambient = ambient;
        this.eyeHight = eyeHight;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // spiders are always attacking
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public EntityType<?> getType() {
        return type;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return hurt;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return hurt;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ambient;
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return eyeHight;
    }
}
