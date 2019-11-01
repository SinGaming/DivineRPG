package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.world.DivineDimension;
import divinerpg.world.eden.EdenDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionRegistry {

    public static DimensionType EDEN;

    public static void register() {
        EDEN = DimensionManager.registerDimension(new ResourceLocation(DivineRPG.MODID, "twilight"),
                new DivineDimension((world, dimensionType) -> new EdenDimension(world)), null, true);
    }
}
