package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.blocks.base.BeaconBase;
import divinerpg.blocks.base.DivineOre;
import divinerpg.blocks.nether.NetheriteOre;
import divinerpg.blocks.vanilla.BasicTorch;
import divinerpg.blocks.vanilla.BlockMobPumpkin;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)

// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
// Event bus for receiving Registry Events)
public class BlockRegistry {

    @ObjectHolder("divinerpg:realmite_ore") public static Block realmiteOre;
    @ObjectHolder("divinerpg:arlemite_ore") public static Block arlemiteOre;
    @ObjectHolder("divinerpg:rupee_ore") public static Block rupeeOre;
    @ObjectHolder("divinerpg:netherite_ore") public static Block netheriteOre;
    @ObjectHolder("divinerpg:bloodgem_ore") public static Block bloodgemOre;

    @ObjectHolder("divinerpg:realmite_block") public static Block realmiteBlock;
    @ObjectHolder("divinerpg:arlemite_block") public static Block arlemiteBlock;
    @ObjectHolder("divinerpg:rupee_block") public static Block rupeeBlock;
    @ObjectHolder("divinerpg:netherite_block") public static Block netheriteBlock;
    @ObjectHolder("divinerpg:bloodgem_block") public static Block bloodgemBlock;

    @ObjectHolder("divinerpg:divine_rock") public static Block divineRock;

    @ObjectHolder("divinerpg:spider_pumpkin") public static Block spiderPumpkin;
    @ObjectHolder("divinerpg:ender_pumpkin") public static Block enderPumpkin;
    @ObjectHolder("divinerpg:creeper_pumpkin") public static Block creeperPumpkin;
    @ObjectHolder("divinerpg:skeleton_pumpkin") public static Block skeletonPumpkin;
    @ObjectHolder("divinerpg:blaze_pumpkin") public static Block blazePumpkin;
    @ObjectHolder("divinerpg:zombie_pumpkin") public static Block zombiePumpkin;
    @ObjectHolder("divinerpg:frost_pumpkin") public static Block frostPumpkin;
    @ObjectHolder("divinerpg:cyclops_pumpkin") public static Block cyclopsPumpkin;
    @ObjectHolder("divinerpg:ghast_pumpkin") public static Block ghastPumpkin;
    @ObjectHolder("divinerpg:glacon_pumpkin") public static Block glaconPumpkin;
    @ObjectHolder("divinerpg:ender_watcher_pumpkin") public static Block enderWatcherPumpkin;
    @ObjectHolder("divinerpg:jungle_spider_pumpkin") public static Block jungleSpiderPumpkin;
    @ObjectHolder("divinerpg:hell_spider_pumpkin") public static Block hellSpiderPumpkin;

    //@ObjectHolder("divinerpg:aqua_torch") public static Block aquaTorch;
    //@ObjectHolder("divinerpg:skeleton_torch") public static Block skeletonTorch;

    @ObjectHolder("divinerpg:checker") public static Block checker;
    @ObjectHolder("divinerpg:rainbow_wool") public static Block rainbowWool;
    @ObjectHolder("divinerpg:crate") public static Block crate;
    @ObjectHolder("divinerpg:plank_design") public static Block plankDesign;
    @ObjectHolder("divinerpg:blue_stone") public static Block blueStone;

    @ObjectHolder("divinerpg:blue_vane") public static Block blueVane;
    @ObjectHolder("divinerpg:cyan_vane") public static Block cyanVane;
    @ObjectHolder("divinerpg:purple_vane") public static Block purpleVane;
    @ObjectHolder("divinerpg:red_vane") public static Block redVane;
    @ObjectHolder("divinerpg:yellow_vane") public static Block yellowVane;

    private static int IRON = 2, DIAMOND = 3;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(new DivineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5F, 50F).harvestLevel(IRON).harvestTool(ToolType.PICKAXE), true).setRegistryName(DivineRPG.MODID, "realmite_ore"));
        registry.register(new DivineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5F, 50F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE), true).setRegistryName(DivineRPG.MODID, "arlemite_ore"));
        registry.register(new DivineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 50F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE), true).setRegistryName(DivineRPG.MODID, "rupee_ore"));
        registry.register(new NetheriteOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 50F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE), false).setRegistryName(DivineRPG.MODID, "netherite_ore"));
        registry.register(new DivineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 50F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE), false).setRegistryName(DivineRPG.MODID, "bloodgem_ore"));

        registry.register(new BeaconBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(4.0F, 100F).harvestLevel(IRON).harvestTool(ToolType.PICKAXE)).setRegistryName(DivineRPG.MODID, "realmite_block"));
        registry.register(new BeaconBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 100F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE)).setRegistryName(DivineRPG.MODID, "arlemite_block"));
        registry.register(new BeaconBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 100F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE)).setRegistryName(DivineRPG.MODID, "rupee_block"));
        registry.register(new BeaconBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 100F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE)).setRegistryName(DivineRPG.MODID, "netherite_block"));
        registry.register(new BeaconBase(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 100F).harvestLevel(DIAMOND).harvestTool(ToolType.PICKAXE)).setRegistryName(DivineRPG.MODID, "bloodgem_block"));

        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 10F)).setRegistryName(DivineRPG.MODID, "divine_rock"));

        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_SPIDER_AMBIENT).setRegistryName(DivineRPG.MODID, "spider_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_ENDERMAN_SCREAM).setRegistryName(DivineRPG.MODID, "ender_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_CREEPER_PRIMED).setRegistryName(DivineRPG.MODID, "creeper_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_SKELETON_AMBIENT).setRegistryName(DivineRPG.MODID, "skeleton_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_BLAZE_AMBIENT).setRegistryName(DivineRPG.MODID, "blaze_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_ZOMBIE_AMBIENT).setRegistryName(DivineRPG.MODID, "zombie_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundRegistry.FROST).setRegistryName(DivineRPG.MODID, "frost_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundRegistry.CYCLOPS).setRegistryName(DivineRPG.MODID, "cyclops_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_GHAST_SCREAM).setRegistryName(DivineRPG.MODID, "ghast_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundRegistry.GLACIDE).setRegistryName(DivineRPG.MODID, "glacon_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundEvents.ENTITY_ENDERMAN_SCREAM).setRegistryName(DivineRPG.MODID, "ender_watcher_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundRegistry.HELL_SPIDER).setRegistryName(DivineRPG.MODID, "jungle_spider_pumpkin"));
        registry.register(new BlockMobPumpkin(SoundRegistry.HELL_SPIDER).setRegistryName(DivineRPG.MODID, "hell_spider_pumpkin"));

        //registry.register(new BasicTorch(ParticleTypes.FLAME).setRegistryName(DivineRPG.MODID, "aqua_torch"));
        //registry.register(new BasicTorch(ParticleTypes.FLAME).setRegistryName(DivineRPG.MODID, "skeleton_torch"));

        registry.register(new Block(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(0.8F)).setRegistryName("checker"));
        registry.register(new Block(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(0.8F)).setRegistryName("rainbow_wool"));
        registry.register(new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F)).setRegistryName("crate"));
        registry.register(new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F)).setRegistryName("plank_design"));
        registry.register(new Block(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(1.5F).lightValue(15)).setRegistryName("blue_stone"));

        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "blue_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "cyan_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "purple_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "red_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "yellow_vane"));
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Item.Properties blockTabProperty = new Item.Properties().group(DivineRPGTabs.DivineBlocks);

        registry.register(new BlockItem(BlockRegistry.realmiteOre, blockTabProperty).setRegistryName("realmite_ore"));
        registry.register(new BlockItem(BlockRegistry.arlemiteOre, blockTabProperty).setRegistryName("arlemite_ore"));
        registry.register(new BlockItem(BlockRegistry.rupeeOre, blockTabProperty).setRegistryName("rupee_ore"));
        registry.register(new BlockItem(BlockRegistry.netheriteOre, blockTabProperty).setRegistryName("netherite_ore"));
        registry.register(new BlockItem(BlockRegistry.bloodgemOre, blockTabProperty).setRegistryName("bloodgem_ore"));
        registry.register(new BlockItem(BlockRegistry.realmiteBlock, blockTabProperty).setRegistryName("realmite_block"));
        registry.register(new BlockItem(BlockRegistry.arlemiteBlock, blockTabProperty).setRegistryName("arlemite_block"));
        registry.register(new BlockItem(BlockRegistry.rupeeBlock, blockTabProperty).setRegistryName("rupee_block"));
        registry.register(new BlockItem(BlockRegistry.netheriteBlock, blockTabProperty).setRegistryName("netherite_block"));
        registry.register(new BlockItem(BlockRegistry.bloodgemBlock, blockTabProperty).setRegistryName("bloodgem_block"));
        registry.register(new BlockItem(BlockRegistry.divineRock, blockTabProperty).setRegistryName("divine_rock"));

        registry.register(new BlockItem(BlockRegistry.spiderPumpkin, blockTabProperty).setRegistryName("spider_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.enderPumpkin, blockTabProperty).setRegistryName("ender_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.creeperPumpkin, blockTabProperty).setRegistryName("creeper_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.skeletonPumpkin, blockTabProperty).setRegistryName("skeleton_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.blazePumpkin, blockTabProperty).setRegistryName("blaze_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.zombiePumpkin, blockTabProperty).setRegistryName("zombie_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.frostPumpkin, blockTabProperty).setRegistryName("frost_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.cyclopsPumpkin, blockTabProperty).setRegistryName("cyclops_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.ghastPumpkin, blockTabProperty).setRegistryName("ghast_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.glaconPumpkin, blockTabProperty).setRegistryName("glacon_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.enderWatcherPumpkin, blockTabProperty).setRegistryName("ender_watcher_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.jungleSpiderPumpkin, blockTabProperty).setRegistryName("jungle_spider_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.hellSpiderPumpkin, blockTabProperty).setRegistryName("hell_spider_pumpkin"));

        //Torches don't quite work yet.
        //registry.register(new BlockItem(BlockRegistry.aquaTorch, blockTabProperty).setRegistryName("aqua_torch"));
        //registry.register(new BlockItem(BlockRegistry.skeletonTorch, blockTabProperty).setRegistryName("skeleton_torch"));

        registry.register(new BlockItem(BlockRegistry.checker, blockTabProperty).setRegistryName("checker"));
        registry.register(new BlockItem(BlockRegistry.rainbowWool, blockTabProperty).setRegistryName("rainbow_wool"));
        registry.register(new BlockItem(BlockRegistry.crate, blockTabProperty).setRegistryName("crate"));
        registry.register(new BlockItem(BlockRegistry.plankDesign, blockTabProperty).setRegistryName("plank_design"));
        registry.register(new BlockItem(BlockRegistry.blueStone, blockTabProperty).setRegistryName("blue_stone"));

        registry.register(new BlockItem(BlockRegistry.blueVane, blockTabProperty).setRegistryName("blue_vane"));
        registry.register(new BlockItem(BlockRegistry.cyanVane, blockTabProperty).setRegistryName("cyan_vane"));
        registry.register(new BlockItem(BlockRegistry.purpleVane, blockTabProperty).setRegistryName("purple_vane"));
        registry.register(new BlockItem(BlockRegistry.redVane, blockTabProperty).setRegistryName("red_vane"));
        registry.register(new BlockItem(BlockRegistry.yellowVane, blockTabProperty).setRegistryName("yellow_vane"));
    }

    //solely for reference
    public static SoundType soundForMaterial(Material material) {
        if(material == Material.ROCK) {
            return SoundType.STONE;
        }
        else if(material == Material.EARTH) {
            return SoundType.GROUND;
        }
        else if(material == Material.IRON) {
            return SoundType.METAL;
        }
        else if(material == Material.GLASS || material == Material.PORTAL) {
            return SoundType.GLASS;
        }
        else if(material == Material.LEAVES || material == Material.ORGANIC) {
            return SoundType.PLANT;
        }
        else if(material == Material.SNOW) {
            return SoundType.SNOW;
        }
        else if(material == Material.WOOD) {
            return SoundType.WOOD;
        }
        return SoundType.ANVIL;
    }
}
