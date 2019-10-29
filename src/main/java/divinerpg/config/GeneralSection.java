package divinerpg.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralSection {

    public final ForgeConfigSpec.BooleanValue checkUpdates;
    public final ForgeConfigSpec.BooleanValue debug;
    public final ForgeConfigSpec.IntValue mobSpawnMultiplier;
    public final ForgeConfigSpec.BooleanValue isBeaconBase;


    public GeneralSection(ForgeConfigSpec.Builder builder) {
        builder.push("General");

        checkUpdates = builder.comment("Enable update checker")
                .translation("config.updateCheck")
                .define("updateCheck", true);

        debug = builder.comment("Don't enable this unless instructed by a developer")
                .translation("config.debug")
                .define("debug", false);

        mobSpawnMultiplier = builder.comment("If you are experiencing lag I would recommend lowering this number")
                .translation("config.spawnMultiplier")
                .defineInRange("mobSpawnMultiplier", 5, 1, 32);

        isBeaconBase = builder.comment("Make DivineRPG blocks as beacon objects")
                .translation("config.beacon")
                .define("beacon", true);


        builder.pop();
    }
}
