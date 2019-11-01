package divinerpg.world;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

/**
 * Simple wrapper for creating new dimensions
 */
public class DivineDimension extends ModDimension {

    private BiFunction<World, DimensionType, ? extends Dimension> factory;

    public DivineDimension(BiFunction<World, DimensionType, ? extends Dimension> factory) {

        this.factory = factory;
    }

    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return factory;
    }
}
