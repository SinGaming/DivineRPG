package divinerpg;

import divinerpg.api.arcana.IArcana;
import divinerpg.api.armor.IPoweredArmorSet;
import divinerpg.arcana.Arcana;
import divinerpg.arcana.ArcanaStorage;
import divinerpg.arcana.client.ArcanaRender;
import divinerpg.config.DivineConfig;
import divinerpg.registry.*;
import divinerpg.utils.ReflectionHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should matchFull an entry in the META-INF/mods.toml file
@Mod(DivineRPG.MODID)
public class DivineRPG {
    public static final String MODID = "divinerpg";
    public static final DivineConfig CONFIG = new DivineConfig();

    private static final String protocol_version = Integer.toString(1);

    public static final SimpleChannel CHANNEL = NetworkRegistry
            .newSimpleChannel(new ResourceLocation(MODID, "main"),
                    () -> protocol_version,
                    protocol_version::equals,
                    protocol_version::equals);


    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public DivineRPG() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::createRegistries);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        MessageRegistry.register();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CONFIG.getCommonSpec());
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CONFIG.getClientSpec());

        // Fixing max health
        RangedAttribute maxHealth = (RangedAttribute) SharedMonsterAttributes.MAX_HEALTH;
        if (ReflectionHelper.setValue(maxHealth, "maximumValue", Double.MAX_VALUE)) {
            LOGGER.info("Maximum mob health increased to Double.MAX_VALUE (1.7976931348623157e+308)");
        } else {
            LOGGER.warn("Can't patch max mob health");
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        CapabilityManager.INSTANCE.register(IArcana.class, new ArcanaStorage(), Arcana::new);
        FeatureRegistry.registerWorldGen();

        SpawnRegistry.registerSpawn();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);

        EntitiesRegistry.registerRender();
        TileEntityRegistry.registerRender();
        ContainerRegistry.registerGUI();

        MinecraftForge.EVENT_BUS.register(new ArcanaRender());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    /**
     * Creating new registry here
     */
    private void createRegistries(RegistryEvent.NewRegistry event) {
        new RegistryBuilder<IPoweredArmorSet>()
                .setName(new ResourceLocation(DivineRPG.MODID, "powers"))
                .setType(IPoweredArmorSet.class)
                .create();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
