package divinerpg.messages;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;

public class EquipmentChangedMessage implements IMessage {

    private UUID id;

    public EquipmentChangedMessage() {

    }

    public EquipmentChangedMessage(UUID id) {
        this.id = id;
    }

    @Override
    public void read(PacketBuffer buffer) {
        id = new UUID(buffer.readVarLong(), buffer.readVarLong());
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeVarLong(id.getMostSignificantBits());
        buffer.writeVarLong(id.getLeastSignificantBits());
    }

    @Override
    public void handleClientSide(NetworkEvent.Context context) {
        // ignored
    }

    @Override
    public void handleServerSide(NetworkEvent.Context context) {
        ServerPlayerEntity playerEntity = context.getSender();
        if (playerEntity != null) {
            // get world
            World world = playerEntity.getEntityWorld();
            // find caller
            PlayerEntity player = world.getPlayerByUuid(id);
            if (player != null) {
                // find his powers ability record
                ArmorObserver observer = FullArmorEventHandler.getPlayerMap().get(id);
                if (observer != null) {
                    // request update
                    observer.Update(player);
                    return;
                }
            }
        }

        throw new RuntimeException("Can't access to player world!");
    }
}
