package divinerpg.messages;

import net.minecraft.network.PacketBuffer;
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
    void consume(Supplier<NetworkEvent.Context> context);
}
