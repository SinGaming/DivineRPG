package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.blocks.TarFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class FluidRegistry {
    @ObjectHolder("flowing_tar")
    public static Fluid flowing_tar;
    @ObjectHolder("tar")
    public static Fluid tar;

    public static void register(RegistryEvent.Register<Fluid> e){
        IForgeRegistry<Fluid> registry = e.getRegistry();

        registry.register(new TarFluid(true).setRegistryName(DivineRPG.MODID, "tar"));
        registry.register(new TarFluid(false).setRegistryName(DivineRPG.MODID, "flowing_tar"));
    }
}
