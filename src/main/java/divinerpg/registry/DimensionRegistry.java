package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.DivineDimension;
import divinerpg.world.eden.EdenDimension;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = DivineRPG.MODID)
@ObjectHolder(DivineRPG.MODID)
public class DimensionRegistry {

    public static ModDimension EDEN_DIM = new DivineDimension((world, dimensionType) -> new EdenDimension(world));
    public static DimensionType EDEN_TYPE;


    @SubscribeEvent
    public static void registerDimensions(RegistryEvent.Register<ModDimension> e) {
        IForgeRegistry<ModDimension> registry = e.getRegistry();

        registry.register(EDEN_DIM.setRegistryName(DivineRPG.MODID, "eden_dim"));
    }

    public static void registerDimTypes() {
        EDEN_TYPE = tryRegisterDimType(new ResourceLocation(DivineRPG.MODID, "eden_dim_type"), EDEN_DIM, null, true);
    }

    private static DimensionType tryRegisterDimType(ResourceLocation name, ModDimension type, PacketBuffer data, boolean hasSkyLight) {
        DimensionType result = DimensionType.byName(name);

        if (result == null) {
            // Null handling
            if (data == null) {
                data = new PacketBuffer(Unpooled.buffer());
            }

            result = DimensionManager.registerDimension(name, type, data, hasSkyLight);
        }

        return result;
    }
}
