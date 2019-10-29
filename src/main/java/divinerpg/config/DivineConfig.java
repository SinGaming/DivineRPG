package divinerpg.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Main config
 */
public class DivineConfig {
    public final GeneralSection general;
    public final WorldGenSection worlgen;
    public final ArcanaBarSection arcanaBar;
    private final ForgeConfigSpec commonSpec;
    private final ForgeConfigSpec clientSpec;

    public DivineConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        general = new GeneralSection(builder);
        worlgen = new WorldGenSection(builder);

        commonSpec = builder.build();

        builder = new ForgeConfigSpec.Builder();
        arcanaBar = new ArcanaBarSection(builder);

        clientSpec = builder.build();
    }

    /**
     * @return Client config specification
     */
    public ForgeConfigSpec getClientSpec() {
        return clientSpec;
    }

    /**
     * @return - Common config specification
     */
    public ForgeConfigSpec getCommonSpec() {
        return commonSpec;
    }
}
