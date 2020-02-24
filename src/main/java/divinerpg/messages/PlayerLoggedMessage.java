package divinerpg.messages;

import divinerpg.events.ArmorMapEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerLoggedMessage implements IMessage {

    private boolean isLoggedIn;

    public PlayerLoggedMessage(){

    }

    public PlayerLoggedMessage(boolean isLoggedIn){

        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public void read(PacketBuffer buffer) {
        isLoggedIn = buffer.readBoolean();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeBoolean(isLoggedIn);
    }

    @Override
    public void handleClientSide(NetworkEvent.Context context) {
        if (isLoggedIn){
            ArmorMapEvents.onLogin(new PlayerEvent.PlayerLoggedInEvent(Minecraft.getInstance().player));
        } else {
            ArmorMapEvents.onPlayerLeave(new PlayerEvent.PlayerLoggedOutEvent(Minecraft.getInstance().player));
        }
    }

    @Override
    public void handleServerSide(NetworkEvent.Context context) {
        // ignored
    }
}
