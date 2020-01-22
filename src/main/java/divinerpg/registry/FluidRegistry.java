package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class FluidRegistry {
    @ObjectHolder("flowing_tar")
    public static FlowingFluid flowing_tar;
    @ObjectHolder("tar")
    public static FlowingFluid tar;

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Fluid> e) {
        IForgeRegistry<Fluid> registry = e.getRegistry();

        ForgeFlowingFluid.Properties props = new ForgeFlowingFluid.Properties(
                () -> tar,
                () -> flowing_tar,
                FluidAttributes.builder(
                        new ResourceLocation(DivineRPG.MODID, "blocks/liquid_tar_still"),
                        new ResourceLocation(DivineRPG.MODID, "blocks/liquid_tar_flow"))
                        .sound(SoundEvents.BLOCK_LAVA_POP, SoundEvents.BLOCK_LAVA_AMBIENT)
                        .luminosity(15).density(3000).viscosity(6000).temperature(1000));

        registry.register(new ForgeFlowingFluid.Flowing(props).setRegistryName(DivineRPG.MODID, "flowing_tar"));
        registry.register(new ForgeFlowingFluid.Source(props.bucket(() -> Items.LAVA_BUCKET)).setRegistryName(DivineRPG.MODID, "tar"));
    }
}
