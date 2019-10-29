package divinerpg.messages;

import divinerpg.api.arcana.IArcana;
import divinerpg.arcana.client.ArcanaRender;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ArcanaMessage implements IMessage {
    public int percantage;

    public ArcanaMessage() {
    }

    public ArcanaMessage(IArcana arcana) {
        percantage = (int) MathHelper.clamp(100 * arcana.getArcana() / arcana.getMaxArcana(), 0, 100);
    }

    @Override
    public void read(PacketBuffer buffer) {
        percantage = buffer.readInt();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeInt(percantage);
    }

    @Override
    public void consume(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                ArcanaRender.percentage = percantage;
            }
        });
    }
}
