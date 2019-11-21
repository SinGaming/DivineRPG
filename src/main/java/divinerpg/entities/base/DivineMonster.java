package divinerpg.entities.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public abstract class DivineMonster extends PeacefullDivineMonster {

    protected DivineMonster(World world) {
        super(world);
    }

    protected DivineMonster(EntityType<? extends MonsterEntity> type, World world, SoundEvent hurt, SoundEvent ambient, float eyeHight) {
        super(type, world, hurt, ambient, eyeHight);
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        // Same as in MonsterEntity
        return 0.5F - worldIn.getBrightness(pos);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
    }
}
