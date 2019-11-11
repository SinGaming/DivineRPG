package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.DivineDimension;
import divinerpg.world.twilight.FloatingDimension;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
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
    public static DimensionType EDEN_TYPE;
    public static DimensionType WILDWOOD_TYPE;
    public static DimensionType APALACHIA_TYPE;
    public static DimensionType SKYTHERN_TYPE;


    @ObjectHolder("eden_dim")
    public static ModDimension EDEN_DIM;
    @ObjectHolder("wildwood_dim")
    public static ModDimension WILDWOOD_DIM;


    @SubscribeEvent
    public static void registerDimensions(RegistryEvent.Register<ModDimension> e) {
        IForgeRegistry<ModDimension> registry = e.getRegistry();

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, EDEN_TYPE, BlockRegistry.edenDirt.getDefaultState(), BiomeRegisty.EDEN,
                        // TODO stolen from nether
                        new Vec3d((double) 0.2F, (double) 0.03F, (double) 0.03F)))
                .setRegistryName(DivineRPG.MODID, "eden_dim"));

        registry.register(new DivineDimension((world, dimensionType) ->
                new FloatingDimension(world, WILDWOOD_TYPE, BlockRegistry.wildwoodDirt.getDefaultState(), BiomeRegisty.WILDWOOD,
                        // TODO stolen from nether
                        new Vec3d((double) 0.2F, (double) 0.03F, (double) 0.03F)))
                .setRegistryName(DivineRPG.MODID, "wildwood_dim"));
    }

    public static void registerDimTypes() {
        EDEN_TYPE = tryRegisterDimType(new ResourceLocation(DivineRPG.MODID, "eden_dim_type"), EDEN_DIM, null, true);
        WILDWOOD_TYPE = tryRegisterDimType(new ResourceLocation(DivineRPG.MODID, "wildwood_dim_type"), WILDWOOD_DIM, null, true);
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
