package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.base.villager.DivineContainerProviderFactory;
import divinerpg.entities.base.villager.DivineMerchantContainer;
import divinerpg.entities.base.villager.DivineMerchantScreen;
import divinerpg.tile.furnace.DivineFurnaceContainer;
import divinerpg.tile.furnace.DivineFurnaceContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = DivineRPG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class ContainerRegistry {
    @ObjectHolder("infinite_furnace")
    public static ContainerType<? extends AbstractFurnaceContainer> infinite_furnace;
    @ObjectHolder("divine_villager")
    public static ContainerType<DivineMerchantContainer> divine_villager;

    @SubscribeEvent
    public static void register(final RegistryEvent.Register<ContainerType<?>> e) {
        IForgeRegistry<ContainerType<?>> registry = e.getRegistry();

        registry.register(new ContainerType<>(new DivineFurnaceContainer.DivineFurnaceFactory()).setRegistryName(DivineRPG.MODID, "infinite_furnace"));
        registry.register(new ContainerType<>(new DivineContainerProviderFactory()).setRegistryName(DivineRPG.MODID, "divine_villager"));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerGUI() {
        ScreenManager.registerFactory(infinite_furnace, DivineFurnaceContainerScreen::new);
        ScreenManager.registerFactory(divine_villager, DivineMerchantScreen::new);
    }
}
