package divinerpg.world.twilight.feature.config;

import divinerpg.config.OreGen;
import net.minecraft.world.gen.placement.CountRangeConfig;

/**
 * Using just for simple ctor
 */
public class DivineCountRangeConfig extends CountRangeConfig {
    public DivineCountRangeConfig(OreGen config) {
        super(config.vein.get(), config.min.get(), 0, config.max.get() - config.min.get());
    }
}
