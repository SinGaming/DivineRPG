package divinerpg.world.feature.config;

import divinerpg.config.OreGen;
import net.minecraft.world.gen.placement.CountRangeConfig;

public class DivineCountRangeConfig extends CountRangeConfig {
    public DivineCountRangeConfig(OreGen config) {
        super(config.vein.get(), config.min.get(), 256 - config.max.get(), config.maxInChunk.get());
    }
}
