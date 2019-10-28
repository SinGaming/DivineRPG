package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.messages.EquipmentChangedMessage;
import divinerpg.messages.IMessage;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageRegistry {

    public static void register() {
        int messageID = 0;

        registerMessage(messageID++, EquipmentChangedMessage.class);
    }


    private static <T extends IMessage> void registerMessage(int number, Class<T> clazz) {
        DivineRPG.CHANNEL.registerMessage(number, clazz, MessageRegistry::encode, buff -> decode(buff, clazz), MessageRegistry::consume);
    }

    private static <T extends IMessage> void encode(T message, PacketBuffer buffer) {
        message.write(buffer);
    }

    private static <T extends IMessage> T decode(PacketBuffer buffer, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            instance.read(buffer);
            return instance;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();

            throw new RuntimeException(e.getMessage());
        }
    }

    private static <T extends IMessage> void consume(T message, Supplier<NetworkEvent.Context> supplier) {
        message.consume(supplier);
        supplier.get().setPacketHandled(true);
    }
}
