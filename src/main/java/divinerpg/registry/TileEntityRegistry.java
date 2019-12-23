package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.blocks.twilight.AyeracoBeamBlock;
import divinerpg.entities.bosses.ayeraco.manager.AyeracoManager;
import divinerpg.tile.ayeraco.beam.AyeracoBeam;
import divinerpg.tile.ayeraco.beam.AyeracoBeamRender;
import divinerpg.tile.ayeraco.spawn.AyeracoSpawn;
import divinerpg.tile.ayeraco.spawn.AyeracoSpawnRender;
import divinerpg.tile.statue.StatueConstants;
import divinerpg.tile.statue.StatueRender;
import divinerpg.tile.statue.TileEntityStatue;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = DivineRPG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class TileEntityRegistry {
    @ObjectHolder("statue")
    public static TileEntityType<?> statue;
    @ObjectHolder("ayeraco_spawner")
    public static TileEntityType<?> ayeraco_spawner;
    @ObjectHolder("ayeraco_beam")
    public static TileEntityType<?> ayeraco_beam;

    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<TileEntityType<?>> e) {
        IForgeRegistry<TileEntityType<?>> registry = e.getRegistry();

        singleRegister(registry, TileEntityStatue::new, "statue", byName(StatueConstants.getStatueNames()));
        singleRegister(registry, AyeracoBeam::new, "ayeraco_beam",
                byName(AyeracoManager.beamLocations.keySet().stream().map(AyeracoBeamBlock::getName).collect(Collectors.toList())));
        singleRegister(registry, AyeracoSpawn::new, "ayeraco_spawner", BlockRegistry.ayeraco_spawner);
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRender() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStatue.class, new StatueRender());
        ClientRegistry.bindTileEntitySpecialRenderer(AyeracoBeam.class, new AyeracoBeamRender());
        ClientRegistry.bindTileEntitySpecialRenderer(AyeracoSpawn.class, new AyeracoSpawnRender());
    }

    private static Block[] byName(List<String> names) {
        return names.stream().map(BlockRegistry::find).toArray(Block[]::new);
    }

    private static <T extends TileEntity> void singleRegister(IForgeRegistry<TileEntityType<?>> registry, Supplier<T> ctor, String name, Block... validBlocks) {
        registry.register(
                TileEntityType.Builder.create(ctor, validBlocks).build(null)
                        .setRegistryName(DivineRPG.MODID, name)
        );
    }
}
