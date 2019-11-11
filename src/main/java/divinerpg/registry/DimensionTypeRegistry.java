package divinerpg.registry;

import divinerpg.DivineRPG;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DimensionTypeRegistry {
    public static final ResourceLocation EDEN = new ResourceLocation(DivineRPG.MODID, "eden_dim_type");
    public static final ResourceLocation WILDWOOD = new ResourceLocation(DivineRPG.MODID, "wildwood_dim_type");
    public static final ResourceLocation APALACHIA = new ResourceLocation(DivineRPG.MODID, "apalachia_dim_type");
    public static final ResourceLocation SKYTHERN = new ResourceLocation(DivineRPG.MODID, "skythern_dim_type");
    public static final ResourceLocation MORTUM = new ResourceLocation(DivineRPG.MODID, "mortum_dim_type");

    @SubscribeEvent
    public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
        if (DimensionType.byName(EDEN) == null) {
            DimensionManager.registerDimension(EDEN, DimensionRegistry.EDEN_DIM, null, true);
        }

        if (DimensionType.byName(WILDWOOD) == null) {
            DimensionManager.registerDimension(WILDWOOD, DimensionRegistry.WILDWOOD_DIM, null, true);
        }
    }
}
