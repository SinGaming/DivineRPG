package divinerpg.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class WorldGenSection {
    public final ForgeConfigSpec.BooleanValue genHats;
    public final ForgeConfigSpec.BooleanValue genTrees;
    public final ForgeConfigSpec.BooleanValue genTar;

    public final OreGen nethers;
    public final OreGen arlemit;
    public final OreGen rupee;
    public final OreGen realmit;
    public final OreGen twilights;


    public WorldGenSection(ForgeConfigSpec.Builder builder) {
        builder.push("WorldGen");

        genHats = builder.comment("Enables native generation of livestock merchant huts in the overworld")
                .translation("config.genHats")
                .define("genHats", true);

        genTrees = builder.comment("Enables native generation of divine trees in overworld")
                .translation("config.genTrees")
                .define("genTrees", true);

        genTar = builder.comment("Enables native generation of TarFluid lakes")
                .translation("config.genTar")
                .define("genTar", true);

        builder.pop();
        builder.push("OreGen");

        realmit = new OreGen(builder, "realmit", 12, 4, 1, 48);
        arlemit = new OreGen(builder, "arlemit", 4, 2, 1, 16);
        rupee = new OreGen(builder, "rupee", 2, 2, 1, 16);
        nethers = new OreGen(builder, "nethers", 20, 4, 1, 256);
        twilights = new OreGen(builder, "twilights", 50, 4, 15, 150);


        builder.pop();
    }
}
