package divinerpg.entities.vanilla.koblin;

import divinerpg.entities.base.PeacefullDivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Kobblin extends PeacefullDivineMonster {
    public Kobblin(World world) {
        super(EntitiesRegistry.koblin, world, SoundRegistry.KOBBLIN, SoundRegistry.KOBBLIN, 0.9f);
    }

    public Kobblin(EntityType<? extends Entity> type, World world) {
        this(world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(4);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.GRASS_BLOCK
                && !worldIn.getBlockState(pos.down(2)).isAir(worldIn, pos.down(2))) {
            return super.getBlockPathWeight(pos, worldIn);
        }

        return -1;
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);

        if (entitylivingbaseIn != null) {
            jump();
        }
    }

    @Override
    public void setRevengeTarget(@Nullable LivingEntity livingBase) {
        super.setRevengeTarget(livingBase);

        if (livingBase != null) {
            jump();
        }
    }
}
