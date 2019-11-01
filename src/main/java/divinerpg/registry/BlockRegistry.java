package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.blocks.base.*;
import divinerpg.utils.properties.block.ExtendedBlockProperties;
import divinerpg.utils.properties.block.IExpDrop;
import divinerpg.world.DivineTree;
import divinerpg.world.feature.DivineTreeFeature;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
// Event bus for receiving Registry Events)
@ObjectHolder(DivineRPG.MODID)
public class BlockRegistry {

    @ObjectHolder("realmite_ore")
    public static Block realmiteOre;
    @ObjectHolder("arlemite_ore")
    public static Block arlemiteOre;
    @ObjectHolder("rupee_ore")
    public static Block rupeeOre;
    @ObjectHolder("netherite_ore")
    public static Block netheriteOre;
    @ObjectHolder("bloodgem_ore")
    public static Block bloodgemOre;

    @ObjectHolder("realmite_block")
    public static Block realmiteBlock;
    @ObjectHolder("arlemite_block")
    public static Block arlemiteBlock;
    @ObjectHolder("rupee_block")
    public static Block rupeeBlock;
    @ObjectHolder("netherite_block")
    public static Block netheriteBlock;
    @ObjectHolder("bloodgem_block")
    public static Block bloodgemBlock;

    @ObjectHolder("divine_rock")
    public static Block divineRock;

    @ObjectHolder("spider_pumpkin")
    public static Block spiderPumpkin;
    @ObjectHolder("ender_pumpkin")
    public static Block enderPumpkin;
    @ObjectHolder("creeper_pumpkin")
    public static Block creeperPumpkin;
    @ObjectHolder("skeleton_pumpkin")
    public static Block skeletonPumpkin;
    @ObjectHolder("blaze_pumpkin")
    public static Block blazePumpkin;
    @ObjectHolder("zombie_pumpkin")
    public static Block zombiePumpkin;
    @ObjectHolder("frost_pumpkin")
    public static Block frostPumpkin;
    @ObjectHolder("cyclops_pumpkin")
    public static Block cyclopsPumpkin;
    @ObjectHolder("ghast_pumpkin")
    public static Block ghastPumpkin;
    @ObjectHolder("glacon_pumpkin")
    public static Block glaconPumpkin;
    @ObjectHolder("ender_watcher_pumpkin")
    public static Block enderWatcherPumpkin;
    @ObjectHolder("jungle_spider_pumpkin")
    public static Block jungleSpiderPumpkin;
    @ObjectHolder("hell_spider_pumpkin")
    public static Block hellSpiderPumpkin;

    //@ObjectHolder("divinerpg:aqua_torch") public static Block aquaTorch;
    //@ObjectHolder("divinerpg:skeleton_torch") public static Block skeletonTorch;

    @ObjectHolder("checker")
    public static Block checker;
    @ObjectHolder("rainbow_wool")
    public static Block rainbowWool;
    @ObjectHolder("crate")
    public static Block crate;
    @ObjectHolder("plank_design")
    public static Block plankDesign;
    @ObjectHolder("blue_stone")
    public static Block blueStone;

    @ObjectHolder("blue_vane")
    public static Block blueVane;
    @ObjectHolder("cyan_vane")
    public static Block cyanVane;
    @ObjectHolder("purple_vane")
    public static Block purpleVane;
    @ObjectHolder("red_vane")
    public static Block redVane;
    @ObjectHolder("yellow_vane")
    public static Block yellowVane;


    @ObjectHolder("eden_grass")
    public static GrassBlock edenGrass;
    @ObjectHolder("eden_dirt")
    public static Block edenDirt;
    @ObjectHolder("eden_brush")
    public static BushBlock edenBrush;
    @ObjectHolder("sun_blossom")
    public static BushBlock sunBlossom;
    @ObjectHolder("sunbloom")
    public static BushBlock sunbloom;
    @ObjectHolder("eden_leaves")
    public static LeavesBlock edenLeaves;
    @ObjectHolder("eden_log")
    public static LogBlock edenLog;
    @ObjectHolder("eden_planks")
    public static Block edenPlanks;
    @ObjectHolder("eden_sapling")
    public static SaplingBlock edenSapling;
    @ObjectHolder("twilight_stone")
    public static Block twilightStone;
    @ObjectHolder("eden_ore")
    public static Block edenOre;


    private static int IRON = 2, DIAMOND = 3;

    // TODO seems like Forge problem
    // BUG live here https://github.com/MinecraftForge/MinecraftForge/issues/6286
    public static PlantType EDEN_PLANT = PlantType.Nether;
    public static PlantType ARCANA_PLANT = PlantType.Nether;


    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        IExpDrop regularDrop = (r, f, s) -> MathHelper.nextInt(r, 2, 6);

        registry.register(new DivineOre(ExtendedBlockProperties.createForOre(2.5F, 50, IRON, regularDrop))
                .setRegistryName(DivineRPG.MODID, "realmite_ore"));
        registry.register(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                .setRegistryName(DivineRPG.MODID, "arlemite_ore"));
        registry.register(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                .setRegistryName(DivineRPG.MODID, "rupee_ore"));
        registry.register(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND).onCollision(e -> e.attackEntityFrom(DamageSource.IN_FIRE, 1)))
                .setRegistryName(DivineRPG.MODID, "netherite_ore"));
        registry.register(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                .setRegistryName(DivineRPG.MODID, "bloodgem_ore"));

        registry.register(new BeaconBase(ExtendedBlockProperties.createForOre(4, 100, IRON, regularDrop).props).setRegistryName(DivineRPG.MODID, "realmite_block"));
        registry.register(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props).setRegistryName(DivineRPG.MODID, "arlemite_block"));
        registry.register(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props).setRegistryName(DivineRPG.MODID, "rupee_block"));
        registry.register(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props).setRegistryName(DivineRPG.MODID, "netherite_block"));
        registry.register(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props).setRegistryName(DivineRPG.MODID, "bloodgem_block"));

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

        registry.register(new Block(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(0.8F)).setRegistryName(DivineRPG.MODID, "checker"));
        registry.register(new Block(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(0.8F)).setRegistryName(DivineRPG.MODID, "rainbow_wool"));
        registry.register(new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F)).setRegistryName(DivineRPG.MODID, "crate"));
        registry.register(new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F)).setRegistryName(DivineRPG.MODID, "plank_design"));
        registry.register(new Block(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(1.5F).lightValue(15)).setRegistryName(DivineRPG.MODID, "blue_stone"));

        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "blue_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "cyan_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "purple_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "red_vane"));
        registry.register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 10.0F)).setRegistryName(DivineRPG.MODID, "yellow_vane"));

        registry.register((edenGrass = (GrassBlock) new DivineGrassBlock(new ExtendedBlockProperties(Block.Properties.create(Material.ORGANIC, MaterialColor.DIRT)
                .hardnessAndResistance(0.5F, 1)).withSpreading(block -> block == edenDirt))
                .setRegistryName(DivineRPG.MODID, "eden_grass")));
        registry.register(new Block(Block.Properties.create(Material.EARTH, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.GROUND).lootFrom(edenGrass))
                .setRegistryName(DivineRPG.MODID, "eden_dirt"));
        registry.register(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.PLANTS, MaterialColor.YELLOW))
                .withGround((state, world, pos) -> state.getBlock() == edenGrass).withType(EDEN_PLANT).withSize(8, 16))
                .setRegistryName(DivineRPG.MODID, "eden_brush"));
        registry.register(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.PLANTS, MaterialColor.YELLOW))
                .withGround((state, world, pos) -> state.getBlock() == edenGrass).withType(EDEN_PLANT).withSize(8, 16))
                .setRegistryName(DivineRPG.MODID, "sun_blossom"));
        registry.register(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.PLANTS, MaterialColor.YELLOW))
                .withGround((state, world, pos) -> state.getBlock() == edenGrass).withType(EDEN_PLANT).withSize(16, 8))
                .setRegistryName(DivineRPG.MODID, "sunbloom"));
        registry.register(new LeavesBlock(ExtendedBlockProperties.createForLeaves(MaterialColor.YELLOW).props)
                .setRegistryName(DivineRPG.MODID, "eden_leaves"));
        registry.register(new LogBlock(MaterialColor.YELLOW, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F).sound(SoundType.WOOD))
                .setRegistryName(DivineRPG.MODID, "eden_log"));
        registry.register(new Block(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD))
                .setRegistryName(DivineRPG.MODID, "eden_planks"));
        registry.register(new DivineSaplingBlock(new DivineTree(new DivineTreeFeature(true, 7,
                () -> edenSapling, () -> edenLog, () -> edenLeaves)), ExtendedBlockProperties.createForSapling(MaterialColor.YELLOW).withType(EDEN_PLANT))
                .setRegistryName(DivineRPG.MODID, "eden_sapling"));
        registry.register(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                .setRegistryName(DivineRPG.MODID, "eden_ore"));

        registry.register(new Block(Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE).hardnessAndResistance(6))
                .setRegistryName(DivineRPG.MODID, "twilight_stone"));
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        Item.Properties blockTabProperty = new Item.Properties().group(DivineRPGTabs.DivineBlocks);

        registry.register(new BlockItem(BlockRegistry.realmiteOre, blockTabProperty).setRegistryName(DivineRPG.MODID, "realmite_ore"));
        registry.register(new BlockItem(BlockRegistry.arlemiteOre, blockTabProperty).setRegistryName(DivineRPG.MODID, "arlemite_ore"));
        registry.register(new BlockItem(BlockRegistry.rupeeOre, blockTabProperty).setRegistryName(DivineRPG.MODID, "rupee_ore"));
        registry.register(new BlockItem(BlockRegistry.netheriteOre, blockTabProperty).setRegistryName(DivineRPG.MODID, "netherite_ore"));
        registry.register(new BlockItem(BlockRegistry.bloodgemOre, blockTabProperty).setRegistryName(DivineRPG.MODID, "bloodgem_ore"));
        registry.register(new BlockItem(BlockRegistry.realmiteBlock, blockTabProperty).setRegistryName(DivineRPG.MODID, "realmite_block"));
        registry.register(new BlockItem(BlockRegistry.arlemiteBlock, blockTabProperty).setRegistryName(DivineRPG.MODID, "arlemite_block"));
        registry.register(new BlockItem(BlockRegistry.rupeeBlock, blockTabProperty).setRegistryName(DivineRPG.MODID, "rupee_block"));
        registry.register(new BlockItem(BlockRegistry.netheriteBlock, blockTabProperty).setRegistryName(DivineRPG.MODID, "netherite_block"));
        registry.register(new BlockItem(BlockRegistry.bloodgemBlock, blockTabProperty).setRegistryName(DivineRPG.MODID, "bloodgem_block"));
        registry.register(new BlockItem(BlockRegistry.divineRock, blockTabProperty).setRegistryName(DivineRPG.MODID, "divine_rock"));

        registry.register(new BlockItem(BlockRegistry.spiderPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "spider_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.enderPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "ender_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.creeperPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "creeper_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.skeletonPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "skeleton_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.blazePumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "blaze_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.zombiePumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "zombie_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.frostPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "frost_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.cyclopsPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "cyclops_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.ghastPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "ghast_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.glaconPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "glacon_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.enderWatcherPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "ender_watcher_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.jungleSpiderPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "jungle_spider_pumpkin"));
        registry.register(new BlockItem(BlockRegistry.hellSpiderPumpkin, blockTabProperty).setRegistryName(DivineRPG.MODID, "hell_spider_pumpkin"));

        //Torches don't quite work yet.
        //registry.register(new BlockItem(BlockRegistry.aquaTorch, blockTabProperty).setRegistryName(DivineRPG.MODID, "aqua_torch"));
        //registry.register(new BlockItem(BlockRegistry.skeletonTorch, blockTabProperty).setRegistryName(DivineRPG.MODID, "skeleton_torch"));

        registry.register(new BlockItem(BlockRegistry.checker, blockTabProperty).setRegistryName(DivineRPG.MODID, "checker"));
        registry.register(new BlockItem(BlockRegistry.rainbowWool, blockTabProperty).setRegistryName(DivineRPG.MODID, "rainbow_wool"));
        registry.register(new BlockItem(BlockRegistry.crate, blockTabProperty).setRegistryName(DivineRPG.MODID, "crate"));
        registry.register(new BlockItem(BlockRegistry.plankDesign, blockTabProperty).setRegistryName(DivineRPG.MODID, "plank_design"));
        registry.register(new BlockItem(BlockRegistry.blueStone, blockTabProperty).setRegistryName(DivineRPG.MODID, "blue_stone"));

        registry.register(new BlockItem(BlockRegistry.blueVane, blockTabProperty).setRegistryName(DivineRPG.MODID, "blue_vane"));
        registry.register(new BlockItem(BlockRegistry.cyanVane, blockTabProperty).setRegistryName(DivineRPG.MODID, "cyan_vane"));
        registry.register(new BlockItem(BlockRegistry.purpleVane, blockTabProperty).setRegistryName(DivineRPG.MODID, "purple_vane"));
        registry.register(new BlockItem(BlockRegistry.redVane, blockTabProperty).setRegistryName(DivineRPG.MODID, "red_vane"));
        registry.register(new BlockItem(BlockRegistry.yellowVane, blockTabProperty).setRegistryName(DivineRPG.MODID, "yellow_vane"));

        registry.register(new BlockItem(BlockRegistry.edenGrass, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_grass"));
        registry.register(new BlockItem(BlockRegistry.edenDirt, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_dirt"));
        registry.register(new BlockItem(BlockRegistry.edenBrush, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_brush"));
        registry.register(new BlockItem(BlockRegistry.sunBlossom, blockTabProperty).setRegistryName(DivineRPG.MODID, "sun_blossom"));
        registry.register(new BlockItem(BlockRegistry.sunbloom, blockTabProperty).setRegistryName(DivineRPG.MODID, "sunbloom"));
        registry.register(new BlockItem(BlockRegistry.edenLeaves, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_leaves"));
        registry.register(new BlockItem(BlockRegistry.edenLog, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_log"));
        registry.register(new BlockItem(BlockRegistry.edenPlanks, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_planks"));
        registry.register(new BlockItem(BlockRegistry.edenSapling, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_sapling"));
        registry.register(new BlockItem(BlockRegistry.twilightStone, blockTabProperty).setRegistryName(DivineRPG.MODID, "twilight_stone"));
        registry.register(new BlockItem(BlockRegistry.edenOre, blockTabProperty).setRegistryName(DivineRPG.MODID, "eden_ore"));
    }

    //solely for reference
    public static SoundType soundForMaterial(Material material) {
        if (material == Material.ROCK) {
            return SoundType.STONE;
        } else if (material == Material.EARTH) {
            return SoundType.GROUND;
        } else if (material == Material.IRON) {
            return SoundType.METAL;
        } else if (material == Material.GLASS || material == Material.PORTAL) {
            return SoundType.GLASS;
        } else if (material == Material.LEAVES || material == Material.ORGANIC) {
            return SoundType.PLANT;
        } else if (material == Material.SNOW) {
            return SoundType.SNOW;
        } else if (material == Material.WOOD) {
            return SoundType.WOOD;
        }
        return SoundType.ANVIL;
    }
}
