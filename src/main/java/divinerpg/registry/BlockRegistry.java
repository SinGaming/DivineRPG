package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.blocks.base.*;
import divinerpg.blocks.twilight.DivinePortalBlock;
import divinerpg.utils.DivineParticleTypes;
import divinerpg.utils.DivinePlantType;
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
import net.minecraft.util.Tuple;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

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
    @ObjectHolder("eden_portal")
    public static DivinePortalBlock edenPortal;
    @ObjectHolder("eden_block")
    public static Block edenBlock;

    @ObjectHolder("wildwood_grass")
    public static GrassBlock wildwoodGrass;
    @ObjectHolder("wildwood_dirt")
    public static Block wildwoodDirt;
    @ObjectHolder("moon_bud")
    public static BushBlock moonBud;
    @ObjectHolder("moonlight_fern")
    public static BushBlock moonlightFern;
    @ObjectHolder("wildwood_tallgrass")
    public static DoublePlantBlock wildwoodTallgrass;
    @ObjectHolder("wildwood_leaves")
    public static LeavesBlock wildwoodLeaves;
    @ObjectHolder("wildwood_log")
    public static LogBlock wildwoodLog;
    @ObjectHolder("wildwood_planks")
    public static Block wildwoodPlanks;
    @ObjectHolder("wildwood_sapling")
    public static SaplingBlock wildwoodSapling;
    @ObjectHolder("wildwood_ore")
    public static Block wildwoodOre;
    @ObjectHolder("wildwood_portal")
    public static DivinePortalBlock wildwoodPortal;
    @ObjectHolder("wildwood_block")
    public static Block wildwoodBlock;


    private static int STONE = 1, IRON = 2, DIAMOND = 3;
    private static List<Tuple<Block, Item.Properties>> blockItems = new ArrayList<>();


    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        Item.Properties blockTabProperty = new Item.Properties().group(DivineRPGTabs.DivineBlocks);
        IExpDrop regularDrop = (r, f, s) -> MathHelper.nextInt(r, 2, 6);

        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(2.5F, 50, IRON, regularDrop)), "realmite_ore", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop)), "arlemite_ore", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop)), "rupee_ore", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND).onCollision(e -> e.attackEntityFrom(DamageSource.IN_FIRE, 1))),
                "netherite_ore", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop)), "bloodgem_ore", blockTabProperty);

        registerBlock(new BeaconBase(ExtendedBlockProperties.createForOre(4, 100, IRON, regularDrop).props), "realmite_block", blockTabProperty);
        registerBlock(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props), "arlemite_block", blockTabProperty);
        registerBlock(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props), "rupee_block", blockTabProperty);
        registerBlock(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props), "netherite_block", blockTabProperty);
        registerBlock(new BeaconBase(ExtendedBlockProperties.createForOre(5, 100, DIAMOND, regularDrop).props), "bloodgem_block", blockTabProperty);

        registerBlock(new Block(ExtendedBlockProperties.createForOre(1.5F, 10, STONE).props), "divine_rock", blockTabProperty);

        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_SPIDER_AMBIENT), "spider_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_ENDERMAN_SCREAM), "ender_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_CREEPER_PRIMED), "creeper_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_SKELETON_AMBIENT), "skeleton_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_BLAZE_AMBIENT), "blaze_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_ZOMBIE_AMBIENT), "zombie_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundRegistry.FROST), "frost_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundRegistry.CYCLOPS), "cyclops_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_GHAST_SCREAM), "ghast_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundRegistry.GLACIDE), "glacon_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundEvents.ENTITY_ENDERMAN_SCREAM), "ender_watcher_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundRegistry.HELL_SPIDER), "jungle_spider_pumpkin", blockTabProperty);
        registerBlock(new BlockMobPumpkin(SoundRegistry.HELL_SPIDER), "hell_spider_pumpkin", blockTabProperty);

        //registerBlock(new BasicTorch(ParticleTypes.FLAME), "aqua_torch"));
        //registerBlock(new BasicTorch(ParticleTypes.FLAME), "skeleton_torch"));

        registerBlock(new Block(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(0.8F)), "checker", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(0.8F)), "rainbow_wool", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F)), "crate", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.5F)), "plank_design", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(1.5F).lightValue(15)), "blue_stone", blockTabProperty);


        registerBlock(new Block(ExtendedBlockProperties.createForOre(2, 10, STONE, regularDrop).props), "blue_vane", blockTabProperty);
        registerBlock(new Block(ExtendedBlockProperties.createForOre(2, 10, STONE, regularDrop).props), "cyan_vane", blockTabProperty);
        registerBlock(new Block(ExtendedBlockProperties.createForOre(2, 10, STONE, regularDrop).props), "purple_vane", blockTabProperty);
        registerBlock(new Block(ExtendedBlockProperties.createForOre(2, 10, STONE, regularDrop).props), "red_vane", blockTabProperty);
        registerBlock(new Block(ExtendedBlockProperties.createForOre(2, 10, STONE, regularDrop).props), "yellow_vane", blockTabProperty);

        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE).hardnessAndResistance(6).harvestTool(ToolType.PICKAXE)), "twilight_stone", blockTabProperty);

        /////////////////////
        // EDEN
        ////////////////////
        registerBlock(new DivineGrassBlock(new ExtendedBlockProperties(Block.Properties.create(Material.ORGANIC, MaterialColor.LIGHT_BLUE)
                        .harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.5F, 1)).withSpreading(block -> block == edenDirt))
                , "eden_grass", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.EARTH, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.GROUND)
                .harvestTool(ToolType.SHOVEL)), "eden_dirt", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.YELLOW))
                        .withNonVanillaType(DivinePlantType.EDEN).withSize(8, 16))
                , "eden_brush", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.PLANTS, MaterialColor.YELLOW))
                        .withNonVanillaType(DivinePlantType.EDEN).withSize(8, 16))
                , "sun_blossom", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.PLANTS, MaterialColor.YELLOW))
                        .withNonVanillaType(DivinePlantType.EDEN).withSize(16, 8))
                , "sunbloom", blockTabProperty);
        registerBlock(new LeavesBlock(ExtendedBlockProperties.createForLeaves(MaterialColor.YELLOW).props)
                , "eden_leaves", blockTabProperty);
        registerBlock(new LogBlock(MaterialColor.YELLOW, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "eden_log", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "eden_planks", blockTabProperty);
        registerBlock(new DivineSaplingBlock(new DivineTree(new DivineTreeFeature(true, 7,
                        () -> edenSapling, () -> edenLog, () -> edenLeaves)), ExtendedBlockProperties.createForSapling(MaterialColor.YELLOW).withNonVanillaType(DivinePlantType.EDEN))
                , "eden_sapling", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                , "eden_ore", blockTabProperty);
        registerBlock(new DivinePortalBlock(
                Block.Properties.create(Material.PORTAL, MaterialColor.YELLOW),
                () -> DimensionType.byName(DimensionTypeRegistry.EDEN),
                () -> BlockRegistry.divineRock,
                // TODO right particle type
                DivineParticleTypes.EDEN), "eden_portal", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.YELLOW).hardnessAndResistance(2).sound(SoundType.STONE).harvestTool(ToolType.SHOVEL))
                , "eden_block", blockTabProperty);


        ////////////////////////
        // WILDWOOD
        ///////////////////////
        registerBlock(new DivineGrassBlock(new ExtendedBlockProperties(Block.Properties.create(Material.ORGANIC, MaterialColor.LIGHT_BLUE)
                        .hardnessAndResistance(0.5F, 1).harvestTool(ToolType.SHOVEL)).withSpreading(block -> block == wildwoodDirt))
                , "wildwood_grass", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.EARTH, MaterialColor.LIGHT_BLUE).hardnessAndResistance(0.5F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL))
                , "wildwood_dirt", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.LIGHT_BLUE))
                        .withNonVanillaType(DivinePlantType.WILDWOOD).withSize(12, 12))
                , "moonlight_fern", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.PLANTS, MaterialColor.LIGHT_BLUE))
                        .withNonVanillaType(DivinePlantType.WILDWOOD).withSize(12, 11))
                , "moon_bud", blockTabProperty);
        registerBlock(new DivineDoublePlantBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.LIGHT_BLUE))
                        .withNonVanillaType(DivinePlantType.WILDWOOD))
                , "wildwood_tallgrass", blockTabProperty);
        registerBlock(new LeavesBlock(ExtendedBlockProperties.createForLeaves(MaterialColor.LIGHT_BLUE).props)
                , "wildwood_leaves", blockTabProperty);
        registerBlock(new LogBlock(MaterialColor.YELLOW, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_BLUE).hardnessAndResistance(2.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "wildwood_log", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "wildwood_planks", blockTabProperty);
        registerBlock(new DivineSaplingBlock(new DivineTree(new DivineTreeFeature(true, 7,
                        () -> wildwoodSapling, () -> wildwoodLog, () -> wildwoodLeaves)), ExtendedBlockProperties.createForSapling(MaterialColor.LIGHT_BLUE).withNonVanillaType(DivinePlantType.WILDWOOD))
                , "wildwood_sapling", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                , "wildwood_ore", blockTabProperty);
        registerBlock(new DivinePortalBlock(
                Block.Properties.create(Material.PORTAL, MaterialColor.LIGHT_BLUE),
                () -> DimensionType.byName(DimensionTypeRegistry.WILDWOOD),
                () -> BlockRegistry.edenBlock,
                DivineParticleTypes.WILDWOOD), "wildwood_portal", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE).hardnessAndResistance(2).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE))
                , "wildwood_block", blockTabProperty);


        /////////////////////////
        // APALACHIA
        /////////////////////////
    }

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {

        blockItems.forEach(x -> {
            event.getRegistry().register(new BlockItem(x.getA(), x.getB()).setRegistryName(x.getA().getRegistryName()));
        });


        // clear memory
        blockItems.clear();
    }

    /**
     * Register block instance
     *
     * @param block - block to register
     * @param name  - his name
     * @param props - oprtional item block props. Pass null to not add to items
     */
    private static void registerBlock(Block block, String name, Item.Properties props) {
        ForgeRegistries.BLOCKS.register(block.setRegistryName(DivineRPG.MODID, name));

        if (props != null)
            blockItems.add(new Tuple<>(block, props));
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
