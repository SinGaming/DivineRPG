package divinerpg.messages;

import divinerpg.api.DivineAPI;
import divinerpg.api.arcana.ArcanaProvider;
import divinerpg.api.arcana.IArcana;
import divinerpg.arcana.ArcanaStorage;
import divinerpg.arcana.client.ArcanaRender;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkEvent;

public class ArcanaMessage implements IMessage {
    private static final Capability.IStorage<IArcana> storage = ArcanaProvider.ARCANA_CAPABILITY.getStorage();
    private CompoundNBT nbt;

    public ArcanaMessage() {
    }

    public ArcanaMessage(IArcana arcana) {
        nbt = (CompoundNBT) storage.writeNBT(ArcanaProvider.ARCANA_CAPABILITY, arcana, Direction.UP);
    }

    @Override
    public void read(PacketBuffer buffer) {
        nbt = buffer.readCompoundTag();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeCompoundTag(nbt);
    }

    @Override
    public void handleClientSide(NetworkEvent.Context context) {
        ArcanaRender.percentage = ArcanaStorage.percantage(nbt);
    }

    @Override
    public void handleServerSide(NetworkEvent.Context context) {
        IArcana arcana = DivineAPI.getPlayerArcana(context.getSender());
        storage.readNBT(ArcanaProvider.ARCANA_CAPABILITY, arcana, Direction.UP, nbt);
    }
}
