package divinerpg.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class RayTraceHelper {
    public static Entity rayTrace(Entity player, float range) {
        // Some magic number
        double fix = 0.3;

        Vec3d start = player.getPositionVec().add(0, fix, 0);
        Vec3d end = start.add(player.getLookVec().scale(range)).add(0, fix, 0);

        AxisAlignedBB cube = new AxisAlignedBB(start, end);

        for (Entity entity : player.getEntityWorld().getEntitiesWithinAABBExcludingEntity(player, cube)) {
            Optional<Vec3d> optional = entity.getBoundingBox().grow(1).rayTrace(start, end);
            if (optional.isPresent() && start.distanceTo(optional.get()) <= range) {
                return entity;
            }
        }

        return null;
    }
}
