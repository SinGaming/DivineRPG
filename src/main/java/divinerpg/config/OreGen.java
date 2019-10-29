package divinerpg.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OreGen {
    public final ForgeConfigSpec.IntValue min;
    public final ForgeConfigSpec.IntValue max;
    public final ForgeConfigSpec.IntValue vein;
    public final ForgeConfigSpec.IntValue maxInChunk;

    public OreGen(ForgeConfigSpec.Builder builder, String oreName, int chunkMax, int vienSize, int minY, int maxY) {
        builder.comment(oreName);
        min = builder.comment("Mininum Y level to spawn ore")
                .translation("config.ore.min")
                .defineInRange(oreName + ".min", minY, 1, 256);

        max = builder.comment("Maximum Y level to spawn ore")
                .translation("config.ore.max")
                .defineInRange(oreName + ".max", maxY, 1, 256);

        vein = builder.comment("Ore will spawn in veins with current size")
                .translation("config.ore.max")
                .defineInRange(oreName + ".vein", vienSize, 1, 20);

        maxInChunk = builder.comment("Max ore blocks in a sigle chunk")
                .translation("config.chunkMax")
                .defineInRange(oreName + ".chunkMax", chunkMax, 1, 20);
    }
}
