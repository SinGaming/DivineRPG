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
    public static final ResourceLocation EDEN = new ResourceLocation(DivineRPG.MODID, "eden");
    public static final ResourceLocation WILDWOOD = new ResourceLocation(DivineRPG.MODID, "wildwood");
    public static final ResourceLocation APALACHIA = new ResourceLocation(DivineRPG.MODID, "apalachia");
    public static final ResourceLocation SKYTHERN = new ResourceLocation(DivineRPG.MODID, "skythern");
    public static final ResourceLocation MORTUM = new ResourceLocation(DivineRPG.MODID, "mortum");
    public static final ResourceLocation ICEIKA = new ResourceLocation(DivineRPG.MODID, "iceika");
    public static final ResourceLocation ARCANA = new ResourceLocation(DivineRPG.MODID, "arcana");

    @SubscribeEvent
    public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
        if (DimensionType.byName(EDEN) == null) {
            DimensionManager.registerDimension(EDEN, DimensionRegistry.EDEN_DIM, null, true);
        }

        if (DimensionType.byName(WILDWOOD) == null) {
            DimensionManager.registerDimension(WILDWOOD, DimensionRegistry.WILDWOOD_DIM, null, true);
        }

        if (DimensionType.byName(APALACHIA) == null) {
            DimensionManager.registerDimension(APALACHIA, DimensionRegistry.APALACHIA_DIM, null, true);
        }

        if (DimensionType.byName(SKYTHERN) == null) {
            DimensionManager.registerDimension(SKYTHERN, DimensionRegistry.SKYTHERN_DIM, null, true);
        }

        if (DimensionType.byName(MORTUM) == null) {
            DimensionManager.registerDimension(MORTUM, DimensionRegistry.MORTUM_DIM, null, true);
        }

        if (DimensionType.byName(ICEIKA) == null) {
            DimensionManager.registerDimension(ICEIKA, DimensionRegistry.ICEIKA, null, true);
        }

        if (DimensionType.byName(ARCANA) == null) {
            DimensionManager.registerDimension(ARCANA, DimensionRegistry.ARCANA, null, true);
        }
    }
}
