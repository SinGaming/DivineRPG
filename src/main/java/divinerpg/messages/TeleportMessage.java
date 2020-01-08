package divinerpg.messages;

import divinerpg.utils.portal.relocate.NoPortalRelocator;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkEvent;

public class TeleportMessage implements IMessage {
    private DimensionType dim;
    private BlockPos pos;

    public TeleportMessage() {

    }

    public TeleportMessage(DimensionType dim, BlockPos pos) {
        this.dim = dim;
        this.pos = pos;
    }

    @Override
    public void read(PacketBuffer buffer) {
        dim = DimensionType.byName(new ResourceLocation(buffer.readString()));
        pos = BlockPos.fromLong(buffer.readLong());
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeString(dim.getRegistryName().toString());
        buffer.writeLong(pos.toLong());
    }

    @Override
    public void handleClientSide(NetworkEvent.Context context) {
        // ignored
    }

    @Override
    public void handleServerSide(NetworkEvent.Context context) {
        ServerPlayerEntity sender = context.getSender();

        if (dim != null && pos != null) {
            new NoPortalRelocator(sender, dim, pos).relocate();
        }
    }
}
