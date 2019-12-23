package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.tile.ayeraco.beam.AyeracoBeam;
import divinerpg.tile.ayeraco.beam.AyeracoBeamRender;
import divinerpg.tile.statue.StatueConstants;
import divinerpg.tile.statue.StatueRender;
import divinerpg.tile.statue.TileEntityStatue;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DivineRPG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class TileEntityRegistry {
    @ObjectHolder("statue")
    public static TileEntityType<?> statue;
    @ObjectHolder("ayeraco_spawn")
    public static TileEntityType<?> ayeraco_spawn;
    @ObjectHolder("ayeraco_beam")
    public static TileEntityType<?> ayeraco_beam;

    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<TileEntityType<?>> e) {
        IForgeRegistry<TileEntityType<?>> registry = e.getRegistry();

        singleRegister(registry, TileEntityStatue::new, "statue", byName(StatueConstants.getStatueNames()));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRender() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStatue.class, new StatueRender());
        ClientRegistry.bindTileEntitySpecialRenderer(AyeracoBeam.class, new AyeracoBeamRender());
    }

    private static Block[] byName(List<String> names) {
        return names.stream().map(x -> new ResourceLocation(DivineRPG.MODID, x)).map(ForgeRegistries.BLOCKS::getValue).toArray(Block[]::new);
    }

    private static <T extends TileEntity> void singleRegister(IForgeRegistry<TileEntityType<?>> registry, Supplier<T> ctor, String name, Block... validBlocks) {
        registry.register(
                TileEntityType.Builder.create(ctor, validBlocks).build(null)
                        .setRegistryName(DivineRPG.MODID, name)
        );
    }
}
