package divinerpg.entities.base.villager;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.IContainerFactory;

public class DivineContainerProviderFactory implements IContainerFactory<DivineMerchantContainer> {
    @Override
    public DivineMerchantContainer create(int windowId, PlayerInventory inv, PacketBuffer data) {
        return new DivineMerchantContainer(windowId, inv, new ResourceLocation(data.readString()));
    }
}
