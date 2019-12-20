package divinerpg.entities.goal;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.Comparator;
import java.util.EnumSet;

public class EatWoodGoal extends Goal {
    private BlockPos nearestWoodPos;
    private PathNavigator navigator;
    private MobEntity entity;

    public EatWoodGoal(MobEntity entity) {
        this.entity = entity;
        navigator = entity.getNavigator();
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean shouldExecute() {
        if (!isAngry())
            return false;

        // search in 12 blocks area
        BlockPos nearestWood = BlockPos.getAllInBox(entity.getPosition().add(-12, -12, -12), entity.getPosition().add(12, 12, 12))
                // ordered by distane of entity
                .sorted(Comparator.comparingDouble(o -> entity.getDistanceSq(o.getX(), o.getY(), o.getZ())))
                // find first we can eat
                .filter(x -> canEat(entity.world, x)).findFirst().orElse(null);

        // don't find any
        if (nearestWood == null)
            return false;

        // remember location
        nearestWoodPos = nearestWood;
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        // null cehcks
        if (!isAngry() || nearestWoodPos == null || !canEat(entity.world, nearestWoodPos)) {
            resetTask();
            return;
        }

        // in a rich distance
        if (entity.getDistanceSq(nearestWoodPos.getX(), nearestWoodPos.getY(), nearestWoodPos.getZ()) <= 2) {
            // check if we can destroy it
            if (ForgeHooks.canEntityDestroy(entity.world, nearestWoodPos, entity)) {
                entity.world.destroyBlock(nearestWoodPos, false);
                entity.heal(10);

                resetTask();
            }
            // Or set another path
        } else {
            navigator.setPath(navigator.getPathToPos(nearestWoodPos, 0), entity.getAIMoveSpeed());
        }


    }

    @Override
    public void resetTask() {
        super.resetTask();

        nearestWoodPos = null;
        navigator.clearPath();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return nearestWoodPos != null;
    }

    private boolean isAngry() {
        return entity != null && entity.isAlive() && entity.getHealth() / entity.getMaxHealth() < 0.5F;
    }

    private boolean canEat(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.getMaterial() == Material.WOOD && state.getBlockHardness(entity.world, pos) > 0;
    }
}
