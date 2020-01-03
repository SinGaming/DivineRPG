package divinerpg.messages;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Base message interface
 */
public interface IMessage {
    /**
     * Reads message from buffer
     */
    void read(PacketBuffer buffer);

    /**
     * Writes message to buffer
     */
    void write(PacketBuffer buffer);

    /**
     * Process message. Don't forget to use 'enqueueWork' method
     */
    default void consume(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                handleClientSide(ctx);
            }

            if (ctx.getDirection().getReceptionSide() == LogicalSide.SERVER) {
                handleServerSide(ctx);
            }
        });
    }

    /**
     * Handling message on client side. Already enqueued
     *
     * @param context
     */
    @OnlyIn(Dist.CLIENT)
    void handleClientSide(NetworkEvent.Context context);

    /**
     * Handling message on server side. Already enqueued
     *
     * @param context
     */
    void handleServerSide(NetworkEvent.Context context);
}
