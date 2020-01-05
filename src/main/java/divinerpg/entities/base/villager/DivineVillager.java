package divinerpg.entities.base.villager;

import divinerpg.utils.CachedTexture;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.MerchantOffers;
import net.minecraft.network.IPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DivineVillager extends VillagerEntity {
    private DivineVillager(World p_i50182_2_) {
        super(EntityType.VILLAGER, p_i50182_2_);
    }

    public DivineVillager(EntityType<? extends VillagerEntity> entityType, World world, IVillagerType type) {
        super(entityType, world, type);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void populateTradeData() {
        super.populateTradeData();
    }

    @Override
    public void func_213707_a(PlayerEntity player, ITextComponent name, int var1) {
        if (player instanceof ServerPlayerEntity) {
            // using same name for GUI image
            String guiPath = getType().getRegistryName().getPath();

            NetworkHooks.openGui(((ServerPlayerEntity) player),
                    new SimpleNamedContainerProvider((windowId, inventory, playerEntity)
                            -> new DivineMerchantContainer(windowId, inventory, this, CachedTexture.GUI.getTexture(guiPath)), name),
                    packetBuffer -> packetBuffer.writeString(guiPath));

            MerchantOffers merchantoffers = this.getOffers();
            if (!merchantoffers.isEmpty()) {
                player.func_213818_a(((ServerPlayerEntity) player).currentWindowId, merchantoffers, var1, this.getXp(), this.func_213705_dZ(), this.func_223340_ej());
            }
        }
    }
}
