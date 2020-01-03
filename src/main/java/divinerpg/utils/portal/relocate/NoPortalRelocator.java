package divinerpg.utils.portal.relocate;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

/**
 * Teleports player without portal
 */
public class NoPortalRelocator extends PortalRelocator {
    private final BlockPos pos;

    public NoPortalRelocator(PlayerEntity traveler, DimensionType destination, BlockPos pos) {
        super(traveler, destination, null);
        this.pos = pos;
    }

    @Override
    public boolean relocate() {
        return super.relocate();
    }

    @Override
    protected void placeInPortal(ServerWorld currentWorld, ServerWorld destinationWorld, float yaw) {
        if (player instanceof ServerPlayerEntity) {
            ServerPlayerEntity playerEntity = (ServerPlayerEntity) player;

            playerEntity.connection.setPlayerLocation(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
            playerEntity.connection.captureCurrentPosition();
        } else {
            player.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), player.rotationYaw, player.rotationPitch);
        }
    }
}
