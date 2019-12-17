package divinerpg.entities.vanilla.grue;

import divinerpg.entities.base.DivineMonster;
import divinerpg.registry.EntitiesRegistry;
import divinerpg.registry.SoundRegistry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Grue extends DivineMonster {

    public Grue(World world) {
        super(EntitiesRegistry.grue, world, SoundRegistry.DEATHCRYX_HURT, SoundRegistry.DEATHCRYX, 1.4F);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27D * 1.3D);

        initAttr(30, 10);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new LeapAtTargetGoal(this, 0.6F));
    }

    @Override
    public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
        if (pos.getY() > 16)
            return -1;

        return super.getBlockPathWeight(pos, worldIn);
    }
}
