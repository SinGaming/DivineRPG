package divinerpg.utils.portal;

import divinerpg.utils.portal.description.IPortalDescription;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomTeleporter extends Teleporter {
    // cached pos
    protected final Map<BlockPos, BlockPattern.PatternHelper> portalCache = new HashMap<>();
    private final int distance = 64;
    private final IPortalDescription description;
    private final DimensionType current;
    private long lastUpdateTick;

    public CustomTeleporter(ServerWorld worldIn, ServerWorld current, IPortalDescription description) {
        super(worldIn);
        this.description = description;

        this.current = current.dimension.getType();

        worldIn.customTeleporters.add(this);
    }

    /**
     * Checks wherever it is the same teleport
     *
     * @param currentWorld
     * @param destinationWorld
     * @return
     */
    public boolean sameType(ServerWorld currentWorld, ServerWorld destinationWorld) {
        DimensionType currentTeleportType = currentWorld.dimension.getType();
        DimensionType destinationTeleportType = destinationWorld.dimension.getType();

        DimensionType destinationType = world.dimension.getType();

        return (current == currentTeleportType && destinationType == destinationTeleportType);
    }

    @Override
    public void tick(long worldTime) {
        if (portalCache.isEmpty())
            return;

        // once a minute
        if (worldTime - lastUpdateTick < 20)
            return;


        lastUpdateTick = worldTime;
        List<BlockPos> toRemove = portalCache.keySet().stream().filter(x -> description.matchFull(this.world, x) == null).collect(Collectors.toList());

        if (!toRemove.isEmpty())
            toRemove.forEach(portalCache::remove);
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        BlockPos pos = findFreePlace(entityIn.getPosition());

        description.createPortal(world, pos);
        portalCache.put(pos, description.matchFull(world, pos));
        return true;
    }

    @Override
    public final boolean func_222268_a(Entity p_222268_1_, float p_222268_2_) {
        return tryPlacePlayer(p_222268_1_, p_222268_2_);
    }

    public boolean tryPlacePlayer(Entity entity, float yaw) {
        BlockPos position = entity.getPosition();

        BlockPos nearest = portalCache.keySet().stream()
                // ignore height
                .filter(x -> isInRadius(x, position, distance))
                .findFirst().orElse(findNearestPortal(position));

        if (nearest == null)
            return false;

        BlockPattern.PatternHelper match = portalCache.get(nearest);

        // todo correct facing
        Direction direction = match.getForwards();

        nearest = description.getPlayerPosition(match);

        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) entity;

            playerEntity.connection.setPlayerLocation(nearest.getX(), nearest.getY(), nearest.getZ(), direction.getHorizontalAngle(), direction.getHorizontalAngle());
            playerEntity.connection.captureCurrentPosition();
        } else {
            entity.setLocationAndAngles(nearest.getX(), nearest.getY(), nearest.getZ(), direction.getHorizontalAngle(), direction.getHorizontalAngle());
        }

        return true;
    }

    private BlockPos findNearestPortal(BlockPos center) {
        double portalSizeSquared = Math.pow(description.maxSize(), 2);

        // search in raduis (XZ radius ignore Y)
        List<BlockPos> framePoses = BlockPos.getAllInBox(center.add(-distance / 2, 0, -distance / 2), center.add(distance / 2, world.getHeight(), distance / 2))
                .map(BlockPos::toImmutable)
                // find all frames
                .filter(x -> world.getBlockState(x).getBlock() == description.getFrame())
                .collect(Collectors.toList());

        while (!framePoses.isEmpty()) {
            BlockPos pos = framePoses.get(0);
            // remove itself
            framePoses.remove(0);

            // detecting is it portal
            BlockPattern.PatternHelper match = description.matchFull(world, pos);

            // math was founded
            if (match != null) {
                // get placement player position
                BlockPos portalPos = description.getPlayerPosition(match);
                // should cache it
                portalCache.put(portalPos, match);
                return portalPos;
            }


            // deleting blocks near because it was already checked
            List<BlockPos> nearestPoses = framePoses.stream().filter(x -> x.distanceSq(pos) <= portalSizeSquared).collect(Collectors.toList());
            if (!nearestPoses.isEmpty()) {
                framePoses.removeAll(nearestPoses);
            }
        }

        return null;
    }

    private BlockPos findFreePlace(BlockPos entityPos) {
        int y = world.getHeight(Heightmap.Type.WORLD_SURFACE, entityPos.getX(), entityPos.getZ());
        return entityPos.add(0, y - entityPos.getY(), 0);
    }

    private boolean isInRadius(BlockPos left, BlockPos right, int radius) {
        return Math.sqrt(
                Math.pow(left.getX() - right.getX(), 2)
                        +
                Math.pow(left.getZ() - right.getZ(), 2)
        ) <= radius;
    }
}
