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
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
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

    public static Tag<Block> DIVINE_GRASS = new BlockTags.Wrapper(new ResourceLocation(DivineRPG.MODID, "grass"));

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

    @ObjectHolder("divine_rock")
    public static Block divineRock;//

    @ObjectHolder("white_mushroom_plant")
    public static DivineCropsBlock white_mushroom_plant;
    @ObjectHolder("tomato_plant")
    public static DivineCropsBlock tomato_plant;

    //@ObjectHolder("divinerpg:aqua_torch") public static Block aquaTorch;
    //@ObjectHolder("divinerpg:skeleton_torch") public static Block skeletonTorch;

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

    @ObjectHolder("apalachia_grass")
    public static GrassBlock apalachiaGrass;
    @ObjectHolder("apalachia_dirt")
    public static Block apalachiaDirt;
    @ObjectHolder("dusk_flower")
    public static DoublePlantBlock dusk_flower;
    @ObjectHolder("dusk_bloom")
    public static BushBlock dusk_bloom;
    @ObjectHolder("apalachia_tallgrass")
    public static BushBlock apalachiaTallgrass;
    @ObjectHolder("apalachia_leaves")
    public static LeavesBlock apalachiaLeaves;
    @ObjectHolder("apalachia_log")
    public static LogBlock apalachiaLog;
    @ObjectHolder("apalachia_planks")
    public static Block apalachiaPlanks;
    @ObjectHolder("apalachia_sapling")
    public static SaplingBlock apalachiaSapling;
    @ObjectHolder("apalachia_ore")
    public static Block apalachiaOre;
    @ObjectHolder("apalachia_portal")
    public static DivinePortalBlock apalachiaPortal;
    @ObjectHolder("apalachia_block")
    public static Block apalachiaBlock;

    @ObjectHolder("skythern_grass")
    public static GrassBlock skythernGrass;
    @ObjectHolder("skythern_dirt")
    public static Block skythernDirt;
    @ObjectHolder("skythern_brush")
    public static BushBlock skythern_brush;
    @ObjectHolder("dust_lily")
    public static BushBlock dust_lily;
    @ObjectHolder("dust_brambles")
    public static BushBlock dust_brambles;
    @ObjectHolder("skythern_leaves")
    public static LeavesBlock skythernLeaves;
    @ObjectHolder("skythern_log")
    public static LogBlock skythernLog;
    @ObjectHolder("skythern_planks")
    public static Block skythernPlanks;
    @ObjectHolder("skythern_sapling")
    public static SaplingBlock skythernSapling;
    @ObjectHolder("skythern_ore")
    public static Block skythernOre;
    @ObjectHolder("skythern_portal")
    public static DivinePortalBlock skythernPortal;
    @ObjectHolder("skythern_block")
    public static Block skythernBlock;

    @ObjectHolder("mortum_grass")
    public static GrassBlock mortumGrass;
    @ObjectHolder("mortum_dirt")
    public static Block mortumDirt;
    @ObjectHolder("mortum_brush")
    public static BushBlock mortum_brush;
    @ObjectHolder("demon_brambles")
    public static BushBlock demon_brambles;
    @ObjectHolder("eye_plant")
    public static BushBlock eye_plant;
    @ObjectHolder("mortum_leaves")
    public static LeavesBlock mortumLeaves;
    @ObjectHolder("mortum_log")
    public static LogBlock mortumLog;
    @ObjectHolder("mortum_planks")
    public static Block mortumPlanks;
    @ObjectHolder("mortum_sapling")
    public static SaplingBlock mortumSapling;
    @ObjectHolder("mortum_ore")
    public static Block mortumOre;
    @ObjectHolder("mortum_portal")
    public static DivinePortalBlock mortumPortal;
    @ObjectHolder("mortum_block")
    public static Block mortumBlock;


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

        // Plants
        Block.Properties plantProps = Block.Properties.create(Material.PLANTS);

        registerBlock(new DivineCropsBlock(new ExtendedBlockProperties(plantProps)
                .withAge(2)
                .withSeed(() -> ItemRegistry.white_mushroom_seeds)), "white_mushroom_plant", null);
        registerBlock(new DivineCropsBlock(new ExtendedBlockProperties(plantProps)
                .withSeed(() -> ItemRegistry.tomato_seeds)), "tomato_plant", null);

        // TODO double crop with seeds
//        registerBlock(new DivineCropsBlock(new ExtendedBlockProperties(plantProps)
//                .withGround(Blocks.GRASS_BLOCK).withSeed(() -> ItemRegistry.purple_glowbone_seeds)), "purple_glowbone_plant", null);
//        registerBlock(new DivineCropsBlock(new ExtendedBlockProperties(plantProps)
//                .withGround(Blocks.GRASS_BLOCK).withSeed(() -> ItemRegistry.pink_glowbone_seeds)), "pink_glowbone_plant", null);
//        registerBlock(new DivineCropsBlock(new ExtendedBlockProperties(plantProps)
//                .withGround(Blocks.GRASS_BLOCK).withSeed(() -> ItemRegistry.sky_plant_seeds)), "sky_plant_plant", null);

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
                        .harvestTool(ToolType.SHOVEL).hardnessAndResistance(3)).mapDirt(() -> edenDirt))
                , "eden_grass", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.EARTH, MaterialColor.YELLOW).hardnessAndResistance(3).sound(SoundType.GROUND)
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
        registerBlock(new LogBlock(MaterialColor.YELLOW, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(3).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
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
                DivineParticleTypes.EDEN), "eden_portal", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.YELLOW).hardnessAndResistance(3).sound(SoundType.STONE).harvestTool(ToolType.SHOVEL))
                , "eden_block", blockTabProperty);


        ////////////////////////
        // WILDWOOD
        ///////////////////////
        registerBlock(new DivineGrassBlock(new ExtendedBlockProperties(Block.Properties.create(Material.ORGANIC, MaterialColor.LIGHT_BLUE)
                        .hardnessAndResistance(5).harvestTool(ToolType.SHOVEL)).mapDirt(() -> wildwoodDirt))
                , "wildwood_grass", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.EARTH, MaterialColor.LIGHT_BLUE).hardnessAndResistance(5).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL))
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
        registerBlock(new LogBlock(MaterialColor.LIGHT_BLUE, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_BLUE).hardnessAndResistance(5).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
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
        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.LIGHT_BLUE).hardnessAndResistance(5).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE))
                , "wildwood_block", blockTabProperty);


        /////////////////////////
        // APALACHIA
        /////////////////////////
        registerBlock(new DivineGrassBlock(new ExtendedBlockProperties(Block.Properties.create(Material.ORGANIC, MaterialColor.PURPLE)
                        .hardnessAndResistance(7).harvestTool(ToolType.SHOVEL)).mapDirt(() -> apalachiaDirt))
                , "apalachia_grass", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.EARTH, MaterialColor.PURPLE).hardnessAndResistance(7).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL))
                , "apalachia_dirt", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.PURPLE))
                        .withNonVanillaType(DivinePlantType.APALACHIA).withSize(8, 8))
                , "dusk_bloom", blockTabProperty);
        registerBlock(new DivineDoublePlantBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.PURPLE))
                        .withNonVanillaType(DivinePlantType.APALACHIA))
                , "dusk_flower", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.PURPLE))
                        .withNonVanillaType(DivinePlantType.APALACHIA))
                , "apalachia_tallgrass", blockTabProperty);
        registerBlock(new LeavesBlock(ExtendedBlockProperties.createForLeaves(MaterialColor.PURPLE).props)
                , "apalachia_leaves", blockTabProperty);
        registerBlock(new LogBlock(MaterialColor.PURPLE, Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(7).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "apalachia_log", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "apalachia_planks", blockTabProperty);
        registerBlock(new DivineSaplingBlock(new DivineTree(new DivineTreeFeature(true, 7,
                        () -> apalachiaSapling, () -> apalachiaLog, () -> apalachiaLeaves)), ExtendedBlockProperties.createForSapling(MaterialColor.PURPLE).withNonVanillaType(DivinePlantType.APALACHIA))
                , "apalachia_sapling", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                , "apalachia_ore", blockTabProperty);
        registerBlock(new DivinePortalBlock(
                Block.Properties.create(Material.PORTAL, MaterialColor.PURPLE),
                () -> DimensionType.byName(DimensionTypeRegistry.APALACHIA),
                () -> BlockRegistry.wildwoodBlock,
                DivineParticleTypes.APALACHIA), "apalachia_portal", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE).hardnessAndResistance(7).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE))
                , "apalachia_block", blockTabProperty);

        ///////////////////////
        // Skythern
        //////////////////////
        registerBlock(new DivineGrassBlock(new ExtendedBlockProperties(Block.Properties.create(Material.ORGANIC, MaterialColor.GRAY)
                        .hardnessAndResistance(9).harvestTool(ToolType.SHOVEL)).mapDirt(() -> skythernDirt))
                , "skythern_grass", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.EARTH, MaterialColor.GRAY).hardnessAndResistance(9).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL))
                , "skythern_dirt", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.GRAY))
                        .withNonVanillaType(DivinePlantType.SKYTHERN))
                , "skythern_brush", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.GRAY))
                        .withNonVanillaType(DivinePlantType.SKYTHERN))
                , "dust_lily", blockTabProperty);
        registerBlock(new DivineDoublePlantBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.GRAY))
                        .withNonVanillaType(DivinePlantType.SKYTHERN)
                        .onCollision(e -> e.attackEntityFrom(DamageSource.CACTUS, 6))
                        .onHarvest(e -> e.attackEntityFrom(DamageSource.CACTUS, 1)))
                , "dust_brambles", blockTabProperty);
        registerBlock(new LeavesBlock(ExtendedBlockProperties.createForLeaves(MaterialColor.GRAY).props)
                , "skythern_leaves", blockTabProperty);
        registerBlock(new LogBlock(MaterialColor.GRAY, Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(9).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "skythern_log", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "skythern_planks", blockTabProperty);
        registerBlock(new DivineSaplingBlock(new DivineTree(new DivineTreeFeature(true, 7,
                        () -> skythernSapling, () -> skythernLog, () -> skythernLeaves)), ExtendedBlockProperties.createForSapling(MaterialColor.GRAY).withNonVanillaType(DivinePlantType.SKYTHERN))
                , "skythern_sapling", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                , "skythern_ore", blockTabProperty);
        registerBlock(new DivinePortalBlock(
                Block.Properties.create(Material.PORTAL, MaterialColor.GRAY),
                () -> DimensionType.byName(DimensionTypeRegistry.SKYTHERN),
                () -> BlockRegistry.apalachiaBlock,
                DivineParticleTypes.SKYTHERN), "skythern_portal", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(9).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE))
                , "skythern_block", blockTabProperty);

        ///////////////////////
        // Mortum
        //////////////////////
        registerBlock(new DivineGrassBlock(new ExtendedBlockProperties(Block.Properties.create(Material.ORGANIC, MaterialColor.BLACK)
                        .hardnessAndResistance(12).harvestTool(ToolType.SHOVEL)).mapDirt(() -> mortumDirt))
                , "mortum_grass", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.EARTH, MaterialColor.BLACK).hardnessAndResistance(12).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL))
                , "mortum_dirt", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.BLACK))
                        .withNonVanillaType(DivinePlantType.MORTUM))
                , "mortum_brush", blockTabProperty);
        registerBlock(new DivineBushBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.BLACK))
                        .withNonVanillaType(DivinePlantType.MORTUM).withSize(8, 8))
                , "eye_plant", blockTabProperty);
        registerBlock(new DivineDoublePlantBlock(new ExtendedBlockProperties(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.BLACK))
                        .withNonVanillaType(DivinePlantType.MORTUM)
                        .onCollision(e -> e.attackEntityFrom(DamageSource.CACTUS, 6))
                        .onHarvest(e -> e.attackEntityFrom(DamageSource.CACTUS, 1)))
                , "demon_brambles", blockTabProperty);
        registerBlock(new LeavesBlock(ExtendedBlockProperties.createForLeaves(MaterialColor.BLACK).props)
                , "mortum_leaves", blockTabProperty);
        registerBlock(new LogBlock(MaterialColor.BLACK, Block.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(12).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "mortum_log", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.BLACK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE))
                , "mortum_planks", blockTabProperty);
        registerBlock(new DivineSaplingBlock(new DivineTree(new DivineTreeFeature(true, 7,
                        () -> mortumSapling, () -> mortumLog, () -> mortumLeaves)), ExtendedBlockProperties.createForSapling(MaterialColor.BLACK)
                        .withNonVanillaType(DivinePlantType.MORTUM))
                , "mortum_sapling", blockTabProperty);
        registerBlock(new DivineOre(ExtendedBlockProperties.createForOre(3, 50, DIAMOND, regularDrop))
                , "mortum_ore", blockTabProperty);
        registerBlock(new DivinePortalBlock(
                Block.Properties.create(Material.PORTAL, MaterialColor.BLACK),
                () -> DimensionType.byName(DimensionTypeRegistry.MORTUM),
                () -> BlockRegistry.skythernBlock,
                DivineParticleTypes.MORTUM), "mortum_portal", blockTabProperty);
        registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(12).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE))
                , "mortum_block", blockTabProperty);

        ///////////////////////
        // TODO Arcana
        //////////////////////
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
