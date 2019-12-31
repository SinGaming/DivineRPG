package divinerpg.registry;

import divinerpg.DivineRPG;
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
    @ObjectHolder("coalstone_furnace")
    public static ContainerType<? extends AbstractFurnaceContainer> infinite_furnace;

    @SubscribeEvent
    public static void register(final RegistryEvent.Register<ContainerType<?>> e) {
        IForgeRegistry<ContainerType<?>> registry = e.getRegistry();

        registry.register(new ContainerType<>(DivineFurnaceContainer::new).setRegistryName(DivineRPG.MODID, "coalstone_furnace"));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerGUI() {
        ScreenManager.registerFactory(infinite_furnace, DivineFurnaceContainerScreen::new);
    }
}
