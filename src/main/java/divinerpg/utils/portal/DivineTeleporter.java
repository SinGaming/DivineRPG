package divinerpg.utils.portal;

import divinerpg.utils.ReflectionHelper;
import divinerpg.utils.portal.description.IPortalDescription;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.util.ITeleporter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.minecraftforge.common.ForgeHooks.onTravelToDimension;

public class DivineTeleporter implements ITeleporter {

    public static DivineTeleporter Instance = new DivineTeleporter();

    private DivineTeleporter() {
    }

    public Entity placeEntity(Entity entityIn, ServerWorld destination, boolean canCreatePortal) {
        IPortalDescription description = PortalConstants.portalDimensionMap.get(destination.dimension.getType());
        if (description == null)
            return entityIn;

        BlockPos nearestPortal = findNearestPortal(destination.getWorld(), entityIn.getPosition(), 16, description);

        if (nearestPortal == null) {
            nearestPortal = entityIn.getPosition();

            if (canCreatePortal)
                description.createPortal(destination, nearestPortal);
        }

        if (entityIn instanceof PlayerEntity
                && !destination.isRemote()
                && entityIn instanceof ServerPlayerEntity) {
            return relocatePlayer((ServerPlayerEntity) entityIn, destination.dimension.getType());
        }

        return relocateEntity(entityIn, destination.dimension.getType());
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        return placeEntity(entity, destWorld, true);
    }

    /**
     * Own search implementation. Can be slow
     *
     * @param world       - world
     * @param center      - entity position
     * @param raduis      - raduis of search
     * @param description - portal description
     * @return - pos of nearest portal. Can be null
     */
    private BlockPos findNearestPortal(World world, BlockPos center, int raduis, IPortalDescription description) {
        double portalSizeSquared = Math.pow(description.maxSize(), 2);

        // search in raduis (XZ radius ignore Y)
        List<BlockPos> framePoses = BlockPos.getAllInBox(center.add(-raduis, 0, -raduis), center.add(raduis, world.getHeight(), raduis))
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

    /**
     * Taken from Entity.changeDimension
     *
     * @param entity
     * @param destination
     * @return
     */
    private Entity relocateEntity(Entity entity, DimensionType destination) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(entity, destination)) return null;
        if (!entity.world.isRemote && entity.isAlive()) {
            entity.world.getProfiler().startSection("changeDimension");
            MinecraftServer minecraftserver = entity.getServer();
            DimensionType dimensiontype = entity.dimension;
            ServerWorld current = minecraftserver.getWorld(dimensiontype);
            ServerWorld destinationWorld = minecraftserver.getWorld(destination);
            entity.dimension = destination;
            entity.detach();
            entity.world.getProfiler().startSection("reposition");

            Entity transportedEntity = placeEntity(entity, current, destinationWorld);

            entity.remove(false);
            entity.world.getProfiler().endSection();
            current.resetUpdateEntityTick();
            destinationWorld.resetUpdateEntityTick();
            entity.world.getProfiler().endSection();
            return transportedEntity;
        } else {
            return null;
        }
    }

    /**
     * Taken from ServerPlayerEntity.changeDimension
     *
     * @param entity
     * @param destination
     * @return
     */
    private Entity relocatePlayer(ServerPlayerEntity entity, DimensionType destination) {
        if (!onTravelToDimension(entity, destination)) return null;
        DimensionType dimensiontype = entity.dimension;
        ServerWorld currentWorld = entity.server.getWorld(dimensiontype);
        entity.dimension = destination;
        ServerWorld serverworld1 = entity.server.getWorld(destination);
        WorldInfo worldinfo = serverworld1.getWorldInfo();
        net.minecraftforge.fml.network.NetworkHooks.sendDimensionDataPacket(entity.connection.netManager, entity);
        entity.connection.sendPacket(new SRespawnPacket(destination, WorldInfo.func_227498_c_(worldinfo.getSeed()), worldinfo.getGenerator(), entity.interactionManager.getGameType()));
        entity.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = entity.server.getPlayerList();
        playerlist.updatePermissionLevel(entity);
        currentWorld.removeEntity(entity, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
        entity.revive();
        Entity e = placePlayer(entity, currentWorld, serverworld1);

        if (e != entity)
            throw new IllegalArgumentException(String.format("Teleporter %s returned not the player entity but instead %s, expected PlayerEntity %s", this, e, entity));
        entity.interactionManager.setWorld(serverworld1);
        entity.connection.sendPacket(new SPlayerAbilitiesPacket(entity.abilities));
        playerlist.sendWorldInfo(entity, serverworld1);
        playerlist.sendInventory(entity);

        for (EffectInstance effectinstance : entity.getActivePotionEffects()) {
            entity.connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
        }

        entity.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));

        ReflectionHelper.setValue(entity, "lastExperience", -1);
        ReflectionHelper.setValue(entity, "lastHealth", -1.0F);
        ReflectionHelper.setValue(entity, "lastFoodLevel", -1);

        net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerChangedDimensionEvent(entity, dimensiontype, destination);
        return entity;
    }

    private Entity placePlayer(ServerPlayerEntity entity, ServerWorld currentWorld, ServerWorld destination) {
        DimensionType dimensiontype = entity.dimension;
        entity.setWorld(destination);
        destination.func_217447_b(entity);
        CriteriaTriggers.CHANGED_DIMENSION.trigger(entity, dimensiontype, destination.dimension.getType());
        entity.connection.setPlayerLocation(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.rotationYaw, entity.rotationPitch);
        return entity;
    }

    private Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destination) {
        Vec3d vec3d = entity.getMotion();
        BlockPos blockpos = entity.getPosition();

        entity.world.getProfiler().endStartSection("reloading");
        entity = entity.getType().create(destination);

        if (entity != null) {
            entity.copyDataFromOld(entity);
            entity.moveToBlockPosAndAngles(blockpos, entity.rotationYaw, entity.rotationPitch);
            entity.setMotion(vec3d);
            destination.func_217460_e(entity);
        }

        return entity;
    }
}
