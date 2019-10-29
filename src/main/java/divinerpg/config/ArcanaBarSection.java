package divinerpg.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ArcanaBarSection {
    public final ForgeConfigSpec.BooleanValue showArcanaBar;

//    public final ForgeConfigSpec.ConfigValue<Integer> x;
//    public final ForgeConfigSpec.ConfigValue<Integer> y;

    public final ForgeConfigSpec.ConfigValue<Integer> width;
    public final ForgeConfigSpec.ConfigValue<Integer> height;
    public final ForgeConfigSpec.IntValue opacity;


    public ArcanaBarSection(ForgeConfigSpec.Builder builder) {
        builder.push("Arcana bar config");

        showArcanaBar = builder.comment("Show overlaying Arcana bar")
                .translation("config.arcana.show")
                .define("arcana.show", true);

        width = builder.comment("Arcana bar width")
                .translation("config.arcana.width")
                .define("arcana.width", 111);

        height = builder.comment("Arcana bar height")
                .translation("config.arcana.height")
                .define("arcana.height", 18);

//        x = builder.comment("Arcana bar X position")
//                .translation("config.arcana.x")
//                .define("arcana.x", 111);
//
//        y = builder.comment("Arcana bar Y position")
//                .translation("config.arcana.y")
//                .define("arcana.y", 18);

        opacity = builder.comment("Arcana bar opacity")
                .translation("config.arcana.opacity")
                .defineInRange("arcana.opacity", 100, 1, 100);

        builder.pop();
    }
}
