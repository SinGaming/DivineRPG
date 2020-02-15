package divinerpg.utils.portal.relocate;

import divinerpg.utils.portal.CustomTeleporter;
import divinerpg.utils.portal.description.IPortalDescription;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;
import net.minecraftforge.fml.loading.FMLEnvironment;

import javax.annotation.Nonnull;

/**
 * Using to place player in portal
 */
public class PortalRelocator {
    protected final PlayerEntity player;

    /**
     * Destination woirld
     */
    protected final DimensionType destination;

    /**
     * portal description
     */
    protected final IPortalDescription description;

    public PortalRelocator(PlayerEntity player, DimensionType destination, IPortalDescription description) {
        this.player = player;
        this.destination = destination;
        this.description = description;
    }

    /**
     * Relocaes player with possible portal creation
     *
     * @return was player relocated
     */
    public boolean relocate() {
        if (player instanceof ServerPlayerEntity)
            return relocateServerPlayer(((ServerPlayerEntity) player));

        if (FMLEnvironment.dist == Dist.CLIENT) {
            if (player instanceof ClientPlayerEntity) {
                return relocateClient(((ClientPlayerEntity) player));
            }
        }

        return false;
    }

    /**
     * Relocation for server player
     *
     * @param entity - server player
     * @return
     */
    protected boolean relocateServerPlayer(@Nonnull ServerPlayerEntity entity) {
        // block traveling
        if (!ForgeHooks.onTravelToDimension(entity, destination))
            return false;

        DimensionType currentDimention = entity.dimension;
        ServerWorld currentWorld = entity.server.getWorld(currentDimention);

        entity.dimension = destination;
        ServerWorld destinationWorld = entity.server.getWorld(destination);
        WorldInfo worldinfo = entity.world.getWorldInfo();
        entity.connection.sendPacket(new SRespawnPacket(destination, worldinfo.getGenerator(), entity.interactionManager.getGameType()));
        entity.connection.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        PlayerList playerlist = entity.server.getPlayerList();
        playerlist.updatePermissionLevel(entity);
        currentWorld.removeEntity(entity, true); //Forge: the player entity is moved to the new world, NOT cloned. So keep the data alive with no matching invalidate call.
        entity.revive();
        double d0 = entity.serverPosX;
        double d1 = entity.serverPosY;
        double d2 = entity.serverPosZ;
        float f = entity.rotationPitch;
        float f1 = entity.rotationYaw;
        double d3 = 8.0D;
        float f2 = f1;
        currentWorld.getProfiler().startSection("moving");
        double moveFactor = currentWorld.getDimension().getMovementFactor() / destinationWorld.getDimension().getMovementFactor();
        d0 *= moveFactor;
        d2 *= moveFactor;

        entity.setLocationAndAngles(d0, d1, d2, f1, f);
        currentWorld.getProfiler().endSection();
        currentWorld.getProfiler().startSection("placing");
        double d7 = Math.min(-2.9999872E7D, destinationWorld.getWorldBorder().minX() + 16.0D);
        double d4 = Math.min(-2.9999872E7D, destinationWorld.getWorldBorder().minZ() + 16.0D);
        double d5 = Math.min(2.9999872E7D, destinationWorld.getWorldBorder().maxX() - 16.0D);
        double d6 = Math.min(2.9999872E7D, destinationWorld.getWorldBorder().maxZ() - 16.0D);
        d0 = MathHelper.clamp(d0, d7, d5);
        d2 = MathHelper.clamp(d2, d4, d6);
        entity.setLocationAndAngles(d0, d1, d2, f1, f);

        placeInPortal(currentWorld, destinationWorld, f2);

        currentWorld.getProfiler().endSection();
        entity.setWorld(destinationWorld);
        destinationWorld.func_217447_b(entity);
        entity.connection.setPlayerLocation(entity.serverPosX, entity.serverPosY, entity.serverPosZ, f1, f);
        entity.interactionManager.setWorld(destinationWorld);
        entity.connection.sendPacket(new SPlayerAbilitiesPacket(entity.abilities));
        playerlist.sendWorldInfo(entity, destinationWorld);
        playerlist.sendInventory(entity);

        for (EffectInstance effectinstance : entity.getActivePotionEffects()) {
            entity.connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), effectinstance));
        }

        entity.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
        // todo make smth
        //serverTraveler.lastHealth = -1.0F;
        //serverTraveler.lastFoodLevel = -1;
        //serverTraveler.lastExperience = -1;
        BasicEventHooks.firePlayerChangedDimensionEvent(entity, currentDimention, destination);
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    protected boolean relocateClient(@Nonnull ClientPlayerEntity entity) {
        if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(entity, destination)) return false;
        if (entity.world.isRemote() || !entity.isAlive()) return false;

        entity.world.getProfiler().startSection("changeDimension");
        MinecraftServer minecraftserver = entity.getServer();
        DimensionType dimensiontype = entity.dimension;
        ServerWorld currentWorld = minecraftserver.getWorld(dimensiontype);
        ServerWorld destinationWorld = minecraftserver.getWorld(destination);
        entity.dimension = destination;
        entity.detach();
        entity.world.getProfiler().startSection("reposition");
        Vec3d vec3d = entity.getMotion();
        float f = 0.0F;
        BlockPos blockpos;
        double movementFactor = currentWorld.getDimension().getMovementFactor() / destinationWorld.getDimension().getMovementFactor();
        double d0 = entity.serverPosX * movementFactor;
        double d1 = entity.serverPosZ * movementFactor;

        double d3 = Math.min(-2.9999872E7D, destinationWorld.getWorldBorder().minX() + 16.0D);
        double d4 = Math.min(-2.9999872E7D, destinationWorld.getWorldBorder().minZ() + 16.0D);
        double d5 = Math.min(2.9999872E7D, destinationWorld.getWorldBorder().maxX() - 16.0D);
        double d6 = Math.min(2.9999872E7D, destinationWorld.getWorldBorder().maxZ() - 16.0D);

        d0 = MathHelper.clamp(d0, d3, d5);
        d1 = MathHelper.clamp(d1, d4, d6);
        Vec3d vec3d1 = entity.getLastPortalVec();
        blockpos = new BlockPos(d0, entity.serverPosY, d1);

        placeInPortal(currentWorld, destinationWorld, entity.rotationYaw);
        blockpos = entity.getPosition();
        vec3d = Vec3d.ZERO;
        f = 0;


        entity.world.getProfiler().endStartSection("reloading");

        Entity copy = entity.getType().create(destinationWorld);

        if (copy != null) {
            copy.copyDataFromOld(copy);
            copy.moveToBlockPosAndAngles(blockpos, copy.rotationYaw + f, copy.rotationPitch);
            copy.setMotion(vec3d);
            destinationWorld.func_217460_e(copy);
        }

        entity.remove(false);
        entity.world.getProfiler().endSection();
        currentWorld.resetUpdateEntityTick();
        destinationWorld.resetUpdateEntityTick();
        entity.world.getProfiler().endSection();
        return true;
    }

    /**
     * Trying to place player in nearest portal
     *
     * @param currentWorld     - where player is standing
     * @param destinationWorld - where he wants to transfer
     * @param yaw              - rotation yaw
     */
    protected void placeInPortal(ServerWorld currentWorld, ServerWorld destinationWorld, float yaw) {
        CustomTeleporter teleporter = destinationWorld.customTeleporters.stream()
                .filter(x -> x instanceof CustomTeleporter)
                .map(x -> ((CustomTeleporter) x))
                // check correct world
                .filter(x -> x.sameType(currentWorld, destinationWorld))
                .findFirst().orElse(new CustomTeleporter(destinationWorld, currentWorld, description));

        if (!teleporter.func_222268_a(player, yaw)) {
            teleporter.makePortal(player);
            teleporter.func_222268_a(player, yaw);
        }
    }
}
