package divinerpg.entities.base.villager;

import divinerpg.utils.CachedTexture;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.MerchantOffers;
import net.minecraft.network.IPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Arrays;
import java.util.List;

// todo natural gen don't saving own profession
public class DivineVillager extends VillagerEntity {
    private IVillagerType type;
    private VillagerProfession profession;

    private DivineVillager(World p_i50182_2_) {
        super(EntityType.VILLAGER, p_i50182_2_);
    }

    public DivineVillager(EntityType<? extends VillagerEntity> entityType, World world, IVillagerType type, VillagerProfession profession) {
        super(entityType, world, type);
        this.type = type;
        this.profession = profession;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void populateTradeData() {
        VillagerData data = getVillagerData();
        Int2ObjectMap<VillagerTrades.ITrade[]> allTrades = VillagerTrades.field_221239_a.get(data.getProfession());
        if (allTrades != null && !allTrades.isEmpty()) {
            List<VillagerTrades.ITrade> trades = Arrays.asList(allTrades.get(data.getLevel()));
            MerchantOffers offers = this.getOffers();
            trades.stream().map(x -> x.getOffer(this, rand)).forEach(offers::add);
        }
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1000);
    }

    /**
     * Returns GUI texture location
     *
     * @return
     */
    protected ResourceLocation getGUI() {
        // using same name for GUI image
        return CachedTexture.GUI.getTexture(getType().getRegistryName().getPath());
    }

    @Override
    public void func_213707_a(PlayerEntity player, ITextComponent name, int var1) {
        if (player instanceof ServerPlayerEntity) {
            ResourceLocation gui = getGUI();

            NetworkHooks.openGui(((ServerPlayerEntity) player),
                    new SimpleNamedContainerProvider((windowId, inventory, playerEntity)
                            -> new DivineMerchantContainer(windowId, inventory, this, gui), name),
                    packetBuffer -> packetBuffer.writeString(gui.toString()));

            MerchantOffers merchantoffers = this.getOffers();
            if (!merchantoffers.isEmpty()) {
                player.func_213818_a(((ServerPlayerEntity) player).currentWindowId, merchantoffers, var1, this.getXp(), this.func_213705_dZ(), this.func_223340_ej());
            }
        }
    }

    /**
     * Never cahgne type/profession
     *
     * @param data
     */
    @Override
    public void setVillagerData(VillagerData data) {

        if (type != null && data.getType() != type) {
            data = data.withType(type);
        }

        if (profession != null && profession != data.getProfession()) {
            data = data.withProfession(profession);
        }

        super.setVillagerData(data);
    }
}
