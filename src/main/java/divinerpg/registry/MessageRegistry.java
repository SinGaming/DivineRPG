package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.messages.EquipmentChangedMessage;
import divinerpg.messages.IMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageRegistry {

    /**
     * Main registering method
     */
    public static void register() {
        int messageID = 0;

        registerMessage(messageID++, EquipmentChangedMessage.class);
    }


    /**
     * Helping mthod
     *
     * @param number - number of message, use increment for it
     * @param clazz  - concrete class of the message. Currently works with IMessage implementations
     * @param <T>    - Implementations of {@link IMessage}
     */
    private static <T extends IMessage> void registerMessage(int number, Class<T> clazz) {
        DivineRPG.CHANNEL.registerMessage(number, clazz, MessageRegistry::encode, buff -> decode(buff, clazz), MessageRegistry::consume);
    }

    /**
     * Static method encoding (writing to buffer) message
     */
    private static <T extends IMessage> void encode(T message, PacketBuffer buffer) {
        message.write(buffer);
    }

    /**
     * Static method to create instance of message from buffer.
     * Currently message class must have empty ctor.
     * After creating message is reading from buffer
     */
    private static <T extends IMessage> T decode(PacketBuffer buffer, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            instance.read(buffer);
            return instance;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    /**
     * Handling message. Set handled to true by default
     */
    private static <T extends IMessage> void consume(T message, Supplier<NetworkEvent.Context> supplier) {
        message.consume(supplier);
        supplier.get().setPacketHandled(true);
    }
}
