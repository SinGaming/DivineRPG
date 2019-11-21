package divinerpg.entities.vanilla.frost;

import divinerpg.entities.base.DivineMonster;
import divinerpg.entities.goal.FrostAttackGoal;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Frost extends DivineMonster {
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;

    public Frost(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    public Frost(World world) {
        super(EntitiesRegistry.frost, world, SoundEvents.ENTITY_BLAZE_HURT, SoundRegistry.FROST, 0.6F);
        experienceValue = 20;

        this.setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);

    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));


        this.goalSelector.addGoal(1, new FrostAttackGoal(this));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        // ignored
        //super.fall(distance, damageMultiplier);
    }

    @Override
    public void livingTick() {
        // Taken from blaze
        if (!this.onGround && this.getMotion().y < 0.0D) {
            this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
        }

        super.livingTick();
    }

    // Is taken from Blaze
    @Override
    protected void updateAITasks() {
        if (this.isInWaterRainOrBubbleColumn()) {
            this.attackEntityFrom(DamageSource.DROWN, 1.0F);
        }

        --this.heightOffsetUpdateTime;
        if (this.heightOffsetUpdateTime <= 0) {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
        }

        LivingEntity livingentity = this.getAttackTarget();
        if (livingentity != null && livingentity.posY + (double) livingentity.getEyeHeight() > this.posY + (double) this.getEyeHeight() + (double) this.heightOffset && this.canAttack(livingentity)) {
            Vec3d vec3d = this.getMotion();
            this.setMotion(this.getMotion().add(0.0D, ((double) 0.3F - vec3d.y) * (double) 0.3F, 0.0D));
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }
}
