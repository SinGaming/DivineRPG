package divinerpg.entities.base.villager;

import divinerpg.registry.ContainerRegistry;
import net.minecraft.entity.NPCMerchant;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.MerchantContainer;
import net.minecraft.util.ResourceLocation;

public class DivineMerchantContainer extends MerchantContainer {
    private final ContainerType<?> type;
    private ResourceLocation gui;

    public DivineMerchantContainer(int windowId, PlayerInventory inventory, ResourceLocation gui) {
        this(windowId, inventory, new NPCMerchant(inventory.player), gui);

    }

    public DivineMerchantContainer(int windowId, PlayerInventory inventory, IMerchant merchant, ResourceLocation gui) {
        this(ContainerRegistry.divine_villager, windowId, inventory, merchant);
        this.gui = gui;
    }

    public DivineMerchantContainer(ContainerType<?> type, int windowId, PlayerInventory inventory, IMerchant merchant) {
        super(windowId, inventory, merchant);
        this.type = type;
    }

    @Override
    public ContainerType<?> getType() {
        return type;
    }

    public ResourceLocation getGui() {
        return gui;
    }
}
