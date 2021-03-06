package divinerpg.registry;

import static divinerpg.utils.SpawnEggColors.Dimension.APALACHIA;
import static divinerpg.utils.SpawnEggColors.Dimension.ARCANA;
import static divinerpg.utils.SpawnEggColors.Dimension.BOSS;
import static divinerpg.utils.SpawnEggColors.Dimension.EDEN;
import static divinerpg.utils.SpawnEggColors.Dimension.END;
import static divinerpg.utils.SpawnEggColors.Dimension.ICEIKA;
import static divinerpg.utils.SpawnEggColors.Dimension.MORTUM;
import static divinerpg.utils.SpawnEggColors.Dimension.NETHER;
import static divinerpg.utils.SpawnEggColors.Dimension.OVERWORLD;
import static divinerpg.utils.SpawnEggColors.Dimension.SKYTHERN;
import static divinerpg.utils.SpawnEggColors.Dimension.VETHEA;
import static divinerpg.utils.SpawnEggColors.Dimension.WILDWOOD;

import divinerpg.DivineRPG;
import divinerpg.api.java.divinerpg.api.Reference;
import divinerpg.config.Config;
import divinerpg.objects.entities.assets.render.arcana.RenderCaptainMerik;
import divinerpg.objects.entities.assets.render.arcana.RenderDatticon;
import divinerpg.objects.entities.assets.render.arcana.RenderDeathHound;
import divinerpg.objects.entities.assets.render.arcana.RenderDeathcryx;
import divinerpg.objects.entities.assets.render.arcana.RenderDramix;
import divinerpg.objects.entities.assets.render.arcana.RenderDungeonConstructor;
import divinerpg.objects.entities.assets.render.arcana.RenderDungeonDemon;
import divinerpg.objects.entities.assets.render.arcana.RenderDungeonPrisoner;
import divinerpg.objects.entities.assets.render.arcana.RenderFyracryx;
import divinerpg.objects.entities.assets.render.arcana.RenderGolemOfRejuvenation;
import divinerpg.objects.entities.assets.render.arcana.RenderLeorna;
import divinerpg.objects.entities.assets.render.arcana.RenderLivingStatue;
import divinerpg.objects.entities.assets.render.arcana.RenderLordVatticus;
import divinerpg.objects.entities.assets.render.arcana.RenderParasecta;
import divinerpg.objects.entities.assets.render.arcana.RenderParatiku;
import divinerpg.objects.entities.assets.render.arcana.RenderRazorback;
import divinerpg.objects.entities.assets.render.arcana.RenderRoamer;
import divinerpg.objects.entities.assets.render.arcana.RenderSeimer;
import divinerpg.objects.entities.assets.render.arcana.RenderWarGeneral;
import divinerpg.objects.entities.assets.render.arcana.RenderWraith;
import divinerpg.objects.entities.assets.render.arcana.RenderZelus;
import divinerpg.objects.entities.assets.render.iceika.RenderAlicanto;
import divinerpg.objects.entities.assets.render.iceika.RenderFractite;
import divinerpg.objects.entities.assets.render.iceika.RenderFrostArcher;
import divinerpg.objects.entities.assets.render.iceika.RenderFrostCloud;
import divinerpg.objects.entities.assets.render.iceika.RenderFrosty;
import divinerpg.objects.entities.assets.render.iceika.RenderGlacide;
import divinerpg.objects.entities.assets.render.iceika.RenderHastreus;
import divinerpg.objects.entities.assets.render.iceika.RenderRollum;
import divinerpg.objects.entities.assets.render.iceika.RenderWorkshopMerchant;
import divinerpg.objects.entities.assets.render.iceika.RenderWorkshopTinkerer;
import divinerpg.objects.entities.assets.render.projectiles.*;
import divinerpg.objects.entities.assets.render.twilight.RenderAdvancedCori;
import divinerpg.objects.entities.assets.render.twilight.RenderAngryBunny;
import divinerpg.objects.entities.assets.render.twilight.RenderApalachiaCadillion;
import divinerpg.objects.entities.assets.render.twilight.RenderApalachiaGolem;
import divinerpg.objects.entities.assets.render.twilight.RenderApalachiaTomo;
import divinerpg.objects.entities.assets.render.twilight.RenderBasilisk;
import divinerpg.objects.entities.assets.render.twilight.RenderBehemoth;
import divinerpg.objects.entities.assets.render.twilight.RenderBunny;
import divinerpg.objects.entities.assets.render.twilight.RenderDemonOfDarkness;
import divinerpg.objects.entities.assets.render.twilight.RenderDensos;
import divinerpg.objects.entities.assets.render.twilight.RenderEdenCadillion;
import divinerpg.objects.entities.assets.render.twilight.RenderEdenTomo;
import divinerpg.objects.entities.assets.render.twilight.RenderEnchantedArcher;
import divinerpg.objects.entities.assets.render.twilight.RenderEnchantedWarrior;
import divinerpg.objects.entities.assets.render.twilight.RenderEpiphite;
import divinerpg.objects.entities.assets.render.twilight.RenderEternalArcher;
import divinerpg.objects.entities.assets.render.twilight.RenderGreenfeet;
import divinerpg.objects.entities.assets.render.twilight.RenderKarot;
import divinerpg.objects.entities.assets.render.twilight.RenderMadivel;
import divinerpg.objects.entities.assets.render.twilight.RenderMage;
import divinerpg.objects.entities.assets.render.twilight.RenderMegalith;
import divinerpg.objects.entities.assets.render.twilight.RenderMoonWolf;
import divinerpg.objects.entities.assets.render.twilight.RenderMortumCadillion;
import divinerpg.objects.entities.assets.render.twilight.RenderMystic;
import divinerpg.objects.entities.assets.render.twilight.RenderReyvor;
import divinerpg.objects.entities.assets.render.twilight.RenderSamek;
import divinerpg.objects.entities.assets.render.twilight.RenderSkythernArcher;
import divinerpg.objects.entities.assets.render.twilight.RenderSkythernFiend;
import divinerpg.objects.entities.assets.render.twilight.RenderSkythernGolem;
import divinerpg.objects.entities.assets.render.twilight.RenderSorcerer;
import divinerpg.objects.entities.assets.render.twilight.RenderSoulFiend;
import divinerpg.objects.entities.assets.render.twilight.RenderSoulSpider;
import divinerpg.objects.entities.assets.render.twilight.RenderSoulStealer;
import divinerpg.objects.entities.assets.render.twilight.RenderSpellbinder;
import divinerpg.objects.entities.assets.render.twilight.RenderSunArcher;
import divinerpg.objects.entities.assets.render.twilight.RenderTwilightArcher;
import divinerpg.objects.entities.assets.render.twilight.RenderTwilightDemon;
import divinerpg.objects.entities.assets.render.twilight.RenderVamacheron;
import divinerpg.objects.entities.assets.render.twilight.RenderVerek;
import divinerpg.objects.entities.assets.render.twilight.RenderWeakCori;
import divinerpg.objects.entities.assets.render.twilight.RenderWildwoodCadillion;
import divinerpg.objects.entities.assets.render.twilight.RenderWildwoodGolem;
import divinerpg.objects.entities.assets.render.twilight.RenderWildwoodTomo;
import divinerpg.objects.entities.assets.render.vanilla.RenderAncientEntity;
import divinerpg.objects.entities.assets.render.vanilla.RenderAridWarrior;
import divinerpg.objects.entities.assets.render.vanilla.RenderAyeracoBlue;
import divinerpg.objects.entities.assets.render.vanilla.RenderAyeracoGreen;
import divinerpg.objects.entities.assets.render.vanilla.RenderAyeracoPurple;
import divinerpg.objects.entities.assets.render.vanilla.RenderAyeracoYellow;
import divinerpg.objects.entities.assets.render.vanilla.RenderAyerecoRed;
import divinerpg.objects.entities.assets.render.vanilla.RenderBrownGrizzle;
import divinerpg.objects.entities.assets.render.vanilla.RenderCaveCrawler;
import divinerpg.objects.entities.assets.render.vanilla.RenderCaveclops;
import divinerpg.objects.entities.assets.render.vanilla.RenderCrab;
import divinerpg.objects.entities.assets.render.vanilla.RenderCyclops;
import divinerpg.objects.entities.assets.render.vanilla.RenderDesertCrawler;
import divinerpg.objects.entities.assets.render.vanilla.RenderEhu;
import divinerpg.objects.entities.assets.render.vanilla.RenderEnderSpider;
import divinerpg.objects.entities.assets.render.vanilla.RenderEnderTriplets;
import divinerpg.objects.entities.assets.render.vanilla.RenderEnderWatcher;
import divinerpg.objects.entities.assets.render.vanilla.RenderEnthralledDramcryx;
import divinerpg.objects.entities.assets.render.vanilla.RenderFrost;
import divinerpg.objects.entities.assets.render.vanilla.RenderGlacon;
import divinerpg.objects.entities.assets.render.vanilla.RenderHellPig;
import divinerpg.objects.entities.assets.render.vanilla.RenderHellSpider;
import divinerpg.objects.entities.assets.render.vanilla.RenderHusk;
import divinerpg.objects.entities.assets.render.vanilla.RenderJackOMan;
import divinerpg.objects.entities.assets.render.vanilla.RenderJungleBat;
import divinerpg.objects.entities.assets.render.vanilla.RenderJungleDramcryx;
import divinerpg.objects.entities.assets.render.vanilla.RenderJungleSpider;
import divinerpg.objects.entities.assets.render.vanilla.RenderKingCrab;
import divinerpg.objects.entities.assets.render.vanilla.RenderKingOfScorchers;
import divinerpg.objects.entities.assets.render.vanilla.RenderKobblin;
import divinerpg.objects.entities.assets.render.vanilla.RenderLiopleurodon;
import divinerpg.objects.entities.assets.render.vanilla.RenderLivestockMerchant;
import divinerpg.objects.entities.assets.render.vanilla.RenderMiner;
import divinerpg.objects.entities.assets.render.vanilla.RenderPumpkinSpider;
import divinerpg.objects.entities.assets.render.vanilla.RenderRainbour;
import divinerpg.objects.entities.assets.render.vanilla.RenderRotatick;
import divinerpg.objects.entities.assets.render.vanilla.RenderSaguaroWorm;
import divinerpg.objects.entities.assets.render.vanilla.RenderScorcher;
import divinerpg.objects.entities.assets.render.vanilla.RenderShark;
import divinerpg.objects.entities.assets.render.vanilla.RenderSmelter;
import divinerpg.objects.entities.assets.render.vanilla.RenderSnapper;
import divinerpg.objects.entities.assets.render.vanilla.RenderStoneGolem;
import divinerpg.objects.entities.assets.render.vanilla.RenderTheEye;
import divinerpg.objects.entities.assets.render.vanilla.RenderTheGrue;
import divinerpg.objects.entities.assets.render.vanilla.RenderTheWatcher;
import divinerpg.objects.entities.assets.render.vanilla.RenderWhale;
import divinerpg.objects.entities.assets.render.vanilla.RenderWhiteGrizzle;
import divinerpg.objects.entities.assets.render.vanilla.RenderWildfire;
import divinerpg.objects.entities.assets.render.vethea.*;
import divinerpg.objects.entities.entity.EntityFrostCloud;
import divinerpg.objects.entities.entity.arcana.EntityCaptainMerik;
import divinerpg.objects.entities.entity.arcana.EntityDatticon;
import divinerpg.objects.entities.entity.arcana.EntityDeathHound;
import divinerpg.objects.entities.entity.arcana.EntityDeathcryx;
import divinerpg.objects.entities.entity.arcana.EntityDramix;
import divinerpg.objects.entities.entity.arcana.EntityDungeonConstructor;
import divinerpg.objects.entities.entity.arcana.EntityDungeonDemon;
import divinerpg.objects.entities.entity.arcana.EntityDungeonPrisoner;
import divinerpg.objects.entities.entity.arcana.EntityFyracryx;
import divinerpg.objects.entities.entity.arcana.EntityGolemOfRejuvenation;
import divinerpg.objects.entities.entity.arcana.EntityLeorna;
import divinerpg.objects.entities.entity.arcana.EntityLivingStatue;
import divinerpg.objects.entities.entity.arcana.EntityLordVatticus;
import divinerpg.objects.entities.entity.arcana.EntityParasecta;
import divinerpg.objects.entities.entity.arcana.EntityParatiku;
import divinerpg.objects.entities.entity.arcana.EntityRazorback;
import divinerpg.objects.entities.entity.arcana.EntityRoamer;
import divinerpg.objects.entities.entity.arcana.EntitySeimer;
import divinerpg.objects.entities.entity.arcana.EntityWarGeneral;
import divinerpg.objects.entities.entity.arcana.EntityWraith;
import divinerpg.objects.entities.entity.arcana.EntityZelus;
import divinerpg.objects.entities.entity.iceika.EntityAlicanto;
import divinerpg.objects.entities.entity.iceika.EntityFractite;
import divinerpg.objects.entities.entity.iceika.EntityFrostArcher;
import divinerpg.objects.entities.entity.iceika.EntityFrosty;
import divinerpg.objects.entities.entity.iceika.EntityGlacide;
import divinerpg.objects.entities.entity.iceika.EntityHastreus;
import divinerpg.objects.entities.entity.iceika.EntityRollum;
import divinerpg.objects.entities.entity.iceika.EntityWorkshopMerchant;
import divinerpg.objects.entities.entity.iceika.EntityWorkshopTinkerer;
import divinerpg.objects.entities.entity.projectiles.*;
import divinerpg.objects.entities.entity.twilight.EntityAdvancedCori;
import divinerpg.objects.entities.entity.twilight.EntityAngryBunny;
import divinerpg.objects.entities.entity.twilight.EntityApalachiaCadillion;
import divinerpg.objects.entities.entity.twilight.EntityApalachiaGolem;
import divinerpg.objects.entities.entity.twilight.EntityApalachiaTomo;
import divinerpg.objects.entities.entity.twilight.EntityBasilisk;
import divinerpg.objects.entities.entity.twilight.EntityBehemoth;
import divinerpg.objects.entities.entity.twilight.EntityBunny;
import divinerpg.objects.entities.entity.twilight.EntityDemonOfDarkness;
import divinerpg.objects.entities.entity.twilight.EntityDensos;
import divinerpg.objects.entities.entity.twilight.EntityEdenCadillion;
import divinerpg.objects.entities.entity.twilight.EntityEdenTomo;
import divinerpg.objects.entities.entity.twilight.EntityEnchantedArcher;
import divinerpg.objects.entities.entity.twilight.EntityEnchantedWarrior;
import divinerpg.objects.entities.entity.twilight.EntityEpiphite;
import divinerpg.objects.entities.entity.twilight.EntityEternalArcher;
import divinerpg.objects.entities.entity.twilight.EntityGreenfeet;
import divinerpg.objects.entities.entity.twilight.EntityKarot;
import divinerpg.objects.entities.entity.twilight.EntityMadivel;
import divinerpg.objects.entities.entity.twilight.EntityMage;
import divinerpg.objects.entities.entity.twilight.EntityMegalith;
import divinerpg.objects.entities.entity.twilight.EntityMoonWolf;
import divinerpg.objects.entities.entity.twilight.EntityMortumCadillion;
import divinerpg.objects.entities.entity.twilight.EntityMystic;
import divinerpg.objects.entities.entity.twilight.EntityReyvor;
import divinerpg.objects.entities.entity.twilight.EntitySamek;
import divinerpg.objects.entities.entity.twilight.EntitySkythernArcher;
import divinerpg.objects.entities.entity.twilight.EntitySkythernFiend;
import divinerpg.objects.entities.entity.twilight.EntitySkythernGolem;
import divinerpg.objects.entities.entity.twilight.EntitySorcerer;
import divinerpg.objects.entities.entity.twilight.EntitySoulFiend;
import divinerpg.objects.entities.entity.twilight.EntitySoulSpider;
import divinerpg.objects.entities.entity.twilight.EntitySoulStealer;
import divinerpg.objects.entities.entity.twilight.EntitySpellbinder;
import divinerpg.objects.entities.entity.twilight.EntitySunArcher;
import divinerpg.objects.entities.entity.twilight.EntityTwilightArcher;
import divinerpg.objects.entities.entity.twilight.EntityTwilightDemon;
import divinerpg.objects.entities.entity.twilight.EntityVamacheron;
import divinerpg.objects.entities.entity.twilight.EntityVerek;
import divinerpg.objects.entities.entity.twilight.EntityWeakCori;
import divinerpg.objects.entities.entity.twilight.EntityWildwoodCadillion;
import divinerpg.objects.entities.entity.twilight.EntityWildwoodGolem;
import divinerpg.objects.entities.entity.twilight.EntityWildwoodTomo;
import divinerpg.objects.entities.entity.vanilla.EntityAncientEntity;
import divinerpg.objects.entities.entity.vanilla.EntityAridWarrior;
import divinerpg.objects.entities.entity.vanilla.EntityBrownGrizzle;
import divinerpg.objects.entities.entity.vanilla.EntityCaveCrawler;
import divinerpg.objects.entities.entity.vanilla.EntityCaveclops;
import divinerpg.objects.entities.entity.vanilla.EntityCrab;
import divinerpg.objects.entities.entity.vanilla.EntityCyclops;
import divinerpg.objects.entities.entity.vanilla.EntityDesertCrawler;
import divinerpg.objects.entities.entity.vanilla.EntityEhu;
import divinerpg.objects.entities.entity.vanilla.EntityEnderSpider;
import divinerpg.objects.entities.entity.vanilla.EntityEnderTriplets;
import divinerpg.objects.entities.entity.vanilla.EntityEnderWatcher;
import divinerpg.objects.entities.entity.vanilla.EntityEnthralledDramcryx;
import divinerpg.objects.entities.entity.vanilla.EntityFrost;
import divinerpg.objects.entities.entity.vanilla.EntityGlacon;
import divinerpg.objects.entities.entity.vanilla.EntityHellPig;
import divinerpg.objects.entities.entity.vanilla.EntityHellSpider;
import divinerpg.objects.entities.entity.vanilla.EntityHusk;
import divinerpg.objects.entities.entity.vanilla.EntityJackOMan;
import divinerpg.objects.entities.entity.vanilla.EntityJungleBat;
import divinerpg.objects.entities.entity.vanilla.EntityJungleDramcryx;
import divinerpg.objects.entities.entity.vanilla.EntityJungleSpider;
import divinerpg.objects.entities.entity.vanilla.EntityKingCrab;
import divinerpg.objects.entities.entity.vanilla.EntityKingOfScorchers;
import divinerpg.objects.entities.entity.vanilla.EntityKobblin;
import divinerpg.objects.entities.entity.vanilla.EntityLiopleurodon;
import divinerpg.objects.entities.entity.vanilla.EntityLivestockMerchant;
import divinerpg.objects.entities.entity.vanilla.EntityMiner;
import divinerpg.objects.entities.entity.vanilla.EntityPumpkinSpider;
import divinerpg.objects.entities.entity.vanilla.EntityRainbour;
import divinerpg.objects.entities.entity.vanilla.EntityRotatick;
import divinerpg.objects.entities.entity.vanilla.EntitySaguaroWorm;
import divinerpg.objects.entities.entity.vanilla.EntityScorcher;
import divinerpg.objects.entities.entity.vanilla.EntityShark;
import divinerpg.objects.entities.entity.vanilla.EntitySmelter;
import divinerpg.objects.entities.entity.vanilla.EntitySnapper;
import divinerpg.objects.entities.entity.vanilla.EntityStoneGolem;
import divinerpg.objects.entities.entity.vanilla.EntityTheEye;
import divinerpg.objects.entities.entity.vanilla.EntityTheGrue;
import divinerpg.objects.entities.entity.vanilla.EntityTheWatcher;
import divinerpg.objects.entities.entity.vanilla.EntityWhale;
import divinerpg.objects.entities.entity.vanilla.EntityWhiteGrizzle;
import divinerpg.objects.entities.entity.vanilla.EntityWildfire;
import divinerpg.objects.entities.entity.vanilla.ayeraco.EntityAyeracoBlue;
import divinerpg.objects.entities.entity.vanilla.ayeraco.EntityAyeracoGreen;
import divinerpg.objects.entities.entity.vanilla.ayeraco.EntityAyeracoPurple;
import divinerpg.objects.entities.entity.vanilla.ayeraco.EntityAyeracoRed;
import divinerpg.objects.entities.entity.vanilla.ayeraco.EntityAyeracoYellow;
import divinerpg.objects.entities.entity.vethea.*;
import divinerpg.utils.SpawnEggColors;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModEntities {
	private static int mobID = 500;
	private static int projectileID = 0;

    @SubscribeEvent
    public static void registerEnities(RegistryEvent.Register<EntityEntry> event) {
        IForgeRegistry<EntityEntry> registry = event.getRegistry();

        registry.registerAll(initProjectiles());
        registry.registerAll(initMobs());
    }

    public static EntityEntry buildEntityEntry(Class entityClass, String entityID, SpawnEggColors.Dimension dimension) { 
        SpawnEggColors colors = SpawnEggColors.getColorsForDimension(dimension);
        return EntityEntryBuilder.create().entity(entityClass)
                .id(new ResourceLocation(Reference.MODID, entityID), mobID++).name(Reference.MODID + "." + entityID).tracker(128, 3, true)
                .egg(colors.getPrimaryColor(), colors.getSecondaryColor()).build();
    }

    public static EntityEntry buildProjectileEntry(Class entityClass, String entityID) {
        return EntityEntryBuilder.create().entity(entityClass)
                .id(new ResourceLocation(Reference.MODID, entityID), projectileID++).name(Reference.MODID + "." + entityID)
                .tracker(250, 5, true).build();
    }

    public static EntityEntry[] initMobs() {
        EntityEntry[] divineMobs;
        divineMobs = new EntityEntry[]{
                // Overworld
                buildEntityEntry(EntityAridWarrior.class, "arid_warrior", OVERWORLD),
                buildEntityEntry(EntityCaveCrawler.class, "cave_crawler", OVERWORLD),
                buildEntityEntry(EntityCaveclops.class, "caveclops", OVERWORLD),
                buildEntityEntry(EntityCrab.class, "crab", OVERWORLD), buildEntityEntry(EntityCyclops.class, "cyclops", OVERWORLD),
                buildEntityEntry(EntityDesertCrawler.class, "desert_crawler", OVERWORLD),
                buildEntityEntry(EntityEhu.class, "ehu", OVERWORLD),
                buildEntityEntry(EntityEnthralledDramcryx.class, "enthralled_dramcryx", OVERWORLD),
                buildEntityEntry(EntityFrost.class, "frost", OVERWORLD), buildEntityEntry(EntityGlacon.class, "glacon", OVERWORLD),
                buildEntityEntry(EntityBrownGrizzle.class, "brown_grizzle", OVERWORLD),
                buildEntityEntry(EntityWhiteGrizzle.class, "white_grizzle", OVERWORLD),
                buildEntityEntry(EntityHusk.class, "husk", OVERWORLD),
                buildEntityEntry(EntityJackOMan.class, "jack_o_man", OVERWORLD),
                buildEntityEntry(EntityJungleBat.class, "jungle_bat", OVERWORLD),
                buildEntityEntry(EntityJungleDramcryx.class, "jungle_dramcryx", OVERWORLD),
                buildEntityEntry(EntityJungleSpider.class, "jungle_spider", OVERWORLD),
                buildEntityEntry(EntityKingCrab.class, "king_crab", OVERWORLD),
                buildEntityEntry(EntityKobblin.class, "kobblin", OVERWORLD),
                buildEntityEntry(EntityLiopleurodon.class, "liopleurodon", OVERWORLD),
                buildEntityEntry(EntityLivestockMerchant.class, "livestock_merchant", OVERWORLD),
                buildEntityEntry(EntityMiner.class, "miner", OVERWORLD),
                buildEntityEntry(EntityPumpkinSpider.class, "pumpkin_spider", OVERWORLD),
                buildEntityEntry(EntityRainbour.class, "rainbour", OVERWORLD),
                buildEntityEntry(EntityRotatick.class, "rotatick", OVERWORLD),
                buildEntityEntry(EntitySaguaroWorm.class, "saguaro_worm", OVERWORLD),
                buildEntityEntry(EntityShark.class, "shark", OVERWORLD),
                buildEntityEntry(EntitySmelter.class, "smelter", OVERWORLD),
                buildEntityEntry(EntitySnapper.class, "snapper", OVERWORLD),
                buildEntityEntry(EntityStoneGolem.class, "stone_golem", OVERWORLD),
                buildEntityEntry(EntityTheEye.class, "the_eye", OVERWORLD),
                buildEntityEntry(EntityTheGrue.class, "the_grue", OVERWORLD),
                buildEntityEntry(EntityWhale.class, "whale", OVERWORLD),

                // Nether
                buildEntityEntry(EntityHellPig.class, "hell_pig", NETHER),
                buildEntityEntry(EntityHellSpider.class, "hell_spider", NETHER),
                buildEntityEntry(EntityScorcher.class, "scorcher", NETHER),
                buildEntityEntry(EntityWildfire.class, "wildfire", NETHER),

                // End
                buildEntityEntry(EntityEnderSpider.class, "ender_spider", END),
                buildEntityEntry(EntityEnderTriplets.class, "ender_triplets", END),
                buildEntityEntry(EntityEnderWatcher.class, "ender_watcher", END),

                // Iceika
                buildEntityEntry(EntityAlicanto.class, "alicanto", ICEIKA),
                buildEntityEntry(EntityFractite.class, "fractite", ICEIKA),
                buildEntityEntry(EntityFrostArcher.class, "frost_archer", ICEIKA),
                buildEntityEntry(EntityFrosty.class, "frosty", ICEIKA), buildEntityEntry(EntityGlacide.class, "glacide", ICEIKA),
                buildEntityEntry(EntityHastreus.class, "hastreus", ICEIKA), buildEntityEntry(EntityRollum.class, "rollum", ICEIKA),
                buildEntityEntry(EntityWorkshopMerchant.class, "workshop_merchant", ICEIKA),
                buildEntityEntry(EntityWorkshopTinkerer.class, "workshop_tinkerer", ICEIKA),

                // Eden
                buildEntityEntry(EntityBunny.class, "bunny", EDEN),
                buildEntityEntry(EntityEdenCadillion.class, "eden_cadillion", EDEN),
                buildEntityEntry(EntityEdenTomo.class, "eden_tomo", EDEN),
                buildEntityEntry(EntityGreenfeet.class, "greenfeet", EDEN), buildEntityEntry(EntityMadivel.class, "madivel", EDEN),
                buildEntityEntry(EntitySunArcher.class, "sun_archer", EDEN),
                buildEntityEntry(EntityWeakCori.class, "weak_cori", EDEN),

                // Wildwood
                buildEntityEntry(EntityBehemoth.class, "behemoth", WILDWOOD),
                buildEntityEntry(EntityEpiphite.class, "epiphite", WILDWOOD), buildEntityEntry(EntityMage.class, "mage", WILDWOOD),
                buildEntityEntry(EntityMoonWolf.class, "moon_wolf", WILDWOOD),
                buildEntityEntry(EntityVerek.class, "verek", WILDWOOD),
                buildEntityEntry(EntityWildwoodCadillion.class, "wildwood_cadillion", WILDWOOD),
                buildEntityEntry(EntityWildwoodGolem.class, "wildwood_golem", WILDWOOD),
                buildEntityEntry(EntityWildwoodTomo.class, "wildwood_tomo", WILDWOOD),

                // Apalachia
                buildEntityEntry(EntityApalachiaCadillion.class, "apalachia_cadillion", APALACHIA),
                buildEntityEntry(EntityApalachiaGolem.class, "apalachia_golem", APALACHIA),
                buildEntityEntry(EntityApalachiaTomo.class, "apalachia_tomo", APALACHIA),
                buildEntityEntry(EntityEnchantedArcher.class, "enchanted_archer", APALACHIA),
                buildEntityEntry(EntityEnchantedWarrior.class, "enchanted_warrior", APALACHIA),
                buildEntityEntry(EntitySpellbinder.class, "spellbinder", APALACHIA),

                // Skythern
                buildEntityEntry(EntityAdvancedCori.class, "advanced_cori", SKYTHERN),
                buildEntityEntry(EntityMegalith.class, "megalith", SKYTHERN),
                buildEntityEntry(EntityMystic.class, "mystic", SKYTHERN), buildEntityEntry(EntitySamek.class, "samek", SKYTHERN),
                buildEntityEntry(EntitySkythernArcher.class, "skythern_archer", SKYTHERN),
                buildEntityEntry(EntitySkythernFiend.class, "skythern_fiend", SKYTHERN),
                buildEntityEntry(EntitySkythernGolem.class, "skythern_golem", SKYTHERN),

                // Mortum
                buildEntityEntry(EntityAngryBunny.class, "angry_bunny", MORTUM),
                buildEntityEntry(EntityBasilisk.class, "basilisk", MORTUM),
                buildEntityEntry(EntityDemonOfDarkness.class, "demon_of_darkness", MORTUM),
                buildEntityEntry(EntityMortumCadillion.class, "mortum_cadillion", MORTUM),
                buildEntityEntry(EntitySorcerer.class, "sorcerer", MORTUM),
                buildEntityEntry(EntitySoulStealer.class, "soul_stealer", MORTUM),
                buildEntityEntry(EntitySoulSpider.class, "soul_spider", MORTUM),
                buildEntityEntry(EntityTwilightArcher.class, "twilight_archer", MORTUM),

                // Arcana
                buildEntityEntry(EntityCaptainMerik.class, "captain_merik", ARCANA),
                buildEntityEntry(EntityDatticon.class, "datticon", ARCANA),
                buildEntityEntry(EntityDeathcryx.class, "deathcryx", ARCANA),
                buildEntityEntry(EntityDeathHound.class, "death_hound", ARCANA),
                buildEntityEntry(EntityDungeonConstructor.class, "dungeon_constructor", ARCANA),
                buildEntityEntry(EntityDungeonDemon.class, "dungeon_demon", ARCANA),
                buildEntityEntry(EntityDungeonPrisoner.class, "dungeon_prisoner", ARCANA),
                buildEntityEntry(EntityFyracryx.class, "fyracryx", ARCANA), buildEntityEntry(EntityLeorna.class, "leorna", ARCANA),
                buildEntityEntry(EntityLivingStatue.class, "living_statue", ARCANA),
                buildEntityEntry(EntityParatiku.class, "paratiku", ARCANA),
                buildEntityEntry(EntityRazorback.class, "razorback", ARCANA),
                buildEntityEntry(EntityGolemOfRejuvenation.class, "golem_of_rejuvenation", ARCANA),
                buildEntityEntry(EntityRoamer.class, "roamer", ARCANA), buildEntityEntry(EntitySeimer.class, "seimer", ARCANA),
                buildEntityEntry(EntityLordVatticus.class, "lord_vatticus", ARCANA),
                buildEntityEntry(EntityWarGeneral.class, "war_general", ARCANA),
                buildEntityEntry(EntityWraith.class, "wraith", ARCANA), buildEntityEntry(EntityZelus.class, "zelus", ARCANA),

                // Vethea
                buildEntityEntry(EntityAcidHag.class, "acid_hag", VETHEA),
                buildEntityEntry(EntityBiphron.class, "biphron", VETHEA),
                buildEntityEntry(EntityBohemite.class, "bohemite", VETHEA),
                buildEntityEntry(EntityCryptKeeper.class, "crypt_keeper", VETHEA),
                buildEntityEntry(EntityCymesoid.class, "cymesoid", VETHEA),
                buildEntityEntry(EntityDissiment.class, "dissiment", VETHEA),
                buildEntityEntry(EntityDreamwrecker.class, "dreamwrecker", VETHEA),
                buildEntityEntry(EntityDuo.class, "duo", VETHEA),
                buildEntityEntry(EntityEnt.class, "ent", VETHEA),
                buildEntityEntry(EntityFakeVhraak.class, "fake_vhraak", VETHEA),
                buildEntityEntry(EntityGalroid.class, "galroid", VETHEA),
                buildEntityEntry(EntityGorgosion.class, "gorgosion", VETHEA),
                buildEntityEntry(EntityHelio.class, "helio", VETHEA),
                buildEntityEntry(EntityHerbomancer.class, "herbomancer", VETHEA),
                buildEntityEntry(EntityHerbomancerMinion.class, "herbomancer_minion", VETHEA),
                buildEntityEntry(EntityHiveSoldier.class, "hive_soldier", VETHEA),
                buildEntityEntry(EntityHoverStinger.class, "hover_stinger", VETHEA),
                buildEntityEntry(EntityKazrotic.class, "kazrotic", VETHEA),
                buildEntityEntry(EntityLheiva.class, "lheiva", VETHEA),
                buildEntityEntry(EntityLorga.class, "lorga", VETHEA),
                buildEntityEntry(EntityLorgaflight.class, "lorga_flight", VETHEA),
                buildEntityEntry(EntityMandragora.class, "mandragora", VETHEA),
                buildEntityEntry(EntityMysteriousManLayer1.class, "mysterious_man_layer_1", VETHEA),
                buildEntityEntry(EntityMysteriousManLayer2.class, "mysterious_man_layer_2", VETHEA),
                buildEntityEntry(EntityMysteriousManLayer3.class, "mysterious_man_layer_3", VETHEA),
                buildEntityEntry(EntityShadahier.class, "shadahier", VETHEA),
                buildEntityEntry(EntityTheHunger.class, "the_hunger", VETHEA),
                buildEntityEntry(EntityTempleGuardian.class, "temple_guardian", VETHEA),
                buildEntityEntry(EntityTocaxin.class, "tocaxin", VETHEA),
                buildEntityEntry(EntityTwins.class, "twins", VETHEA),
                buildEntityEntry(EntityVermenous.class, "vermenous", VETHEA),
                buildEntityEntry(EntityVhraak.class, "vhraak", VETHEA),
                buildEntityEntry(EntityZone.class, "zone", VETHEA),
                buildEntityEntry(EntityZoragon.class, "zoragon", VETHEA),

                // Boss
                buildEntityEntry(EntityAncientEntity.class, "ancient_entity", BOSS),
                buildEntityEntry(EntityAyeracoBlue.class, "ayeraco_blue", BOSS),
                buildEntityEntry(EntityAyeracoGreen.class, "ayeraco_green", BOSS),
                buildEntityEntry(EntityAyeracoPurple.class, "ayeraco_purple", BOSS),
                buildEntityEntry(EntityAyeracoRed.class, "ayeraco_red", BOSS),
                buildEntityEntry(EntityAyeracoYellow.class, "ayeraco_yellow", BOSS),
                buildEntityEntry(EntityDensos.class, "densos", BOSS), buildEntityEntry(EntityDramix.class, "dramix", BOSS),
                buildEntityEntry(EntityEternalArcher.class, "eternal_archer", BOSS),
                buildEntityEntry(EntityHiveQueen.class, "hive_queen", BOSS),
                buildEntityEntry(EntityKarot.class, "karot", BOSS),
                buildEntityEntry(EntityKaros.class, "karos", BOSS),
                buildEntityEntry(EntityKingOfScorchers.class, "king_of_scorchers", BOSS),
                buildEntityEntry(EntityLadyLuna.class, "lady_luna", BOSS),
                buildEntityEntry(EntityParasecta.class, "parasecta", BOSS),
                buildEntityEntry(EntityQuadro.class, "quadro", BOSS),
                buildEntityEntry(EntityRaglok.class, "raglok", BOSS),
                buildEntityEntry(EntityReyvor.class, "reyvor", BOSS), buildEntityEntry(EntitySoulFiend.class, "soul_fiend", BOSS),
                buildEntityEntry(EntityTheWatcher.class, "the_watcher", BOSS),
                buildEntityEntry(EntityTwilightDemon.class, "twilight_demon", BOSS),
                buildEntityEntry(EntityVamacheron.class, "vamacheron", BOSS),
                buildEntityEntry(EntityWreck.class, "wreck", BOSS)
        };

        return divineMobs;
    }

    public static EntityEntry[] initProjectiles() {
        // Projectiles
        EntityEntry[] divineProjectiles = {buildProjectileEntry(EntityCaveRock.class, "cave_rock"),
                buildProjectileEntry(EntitySerenadeOfDeath.class, "serenade_of_death"),
                buildProjectileEntry(EntitySerenadeOfIce.class, "serenade_of_ice"),
                buildProjectileEntry(EntityEnderTripletsFireball.class, "ender_triplets_fireball"),
                buildProjectileEntry(EntityFractiteShot.class, "fractite_shot"),
                buildProjectileEntry(EntityFrostShot.class, "frost_shot"),
                buildProjectileEntry(EntityFrostCloud.class, "frost_cloud"),
                buildProjectileEntry(EntityKingOfScorchersMeteor.class, "king_of_scorchers_meteor"),
                buildProjectileEntry(EntityKingOfScorchersShot.class, "king_of_scorchers_shot"),
                buildProjectileEntry(EntitySaguaroWormShot.class, "saguaro_worm_shot"),
                buildProjectileEntry(EntityScorcherShot.class, "scorcher_shot"),
                buildProjectileEntry(EntityShuriken.class, "shuriken"),
                buildProjectileEntry(EntitySparkler.class, "sparkler"),
                buildProjectileEntry(EntityWatcherShot.class, "watcher_shot"),
                buildProjectileEntry(EntityVileStorm.class, "vile_storm"),
                buildProjectileEntry(EntitySnowflakeShuriken.class, "snowflake_shuriken"),
                buildProjectileEntry(EntityShooterBullet.class, "shooter_bullet"),
                buildProjectileEntry(EntityParticleBullet.class, "particle_bullet"),
                buildProjectileEntry(EntityColoredBullet.class, "colored_bullet"),
                buildProjectileEntry(EntityCorruptedBullet.class, "corrupted_bullet"),
                buildProjectileEntry(EntitySoundOfMusic.class, "sound_of_music"),
                buildProjectileEntry(EntitySoundOfCarols.class, "sound_of_carols"),
                buildProjectileEntry(EntityDivineArrow.class, "arrow_shot"),
                buildProjectileEntry(EntityCoriShot.class, "cori_shot"),
                buildProjectileEntry(EntityTwilightMageShot.class, "twilight_mage_shot"),
                buildProjectileEntry(EntitySoulFiendShot.class, "soul_fiend_shot"),
                buildProjectileEntry(EntityDisk.class, "disk"),
                buildProjectileEntry(EntityGrenade.class, "grenade"),
                buildProjectileEntry(EntityAttractor.class, "attractor"),
                buildProjectileEntry(EntityGeneralsStaff.class, "generals_staff"),
                buildProjectileEntry(EntityMeteor.class, "meteor"), buildProjectileEntry(EntityStar.class, "star"),
                buildProjectileEntry(EntityLamona.class, "lamona"),
                buildProjectileEntry(EntityFirefly.class, "firefly"),
                buildProjectileEntry(EntityReflector.class, "reflector"),
                buildProjectileEntry(EntityFyracryxFireball.class, "fyracryx_fireball"),
                buildProjectileEntry(EntityTwilightDemonShot.class, "twilight_demon_shot"),
                buildProjectileEntry(EntityMerikMissile.class, "meriks_missile"),
                buildProjectileEntry(EntityBouncingProjectile.class, "bouncing_projectile"),
                buildProjectileEntry(EntityMandragoraProjectile.class, "mandragora_projectile"),
                buildProjectileEntry(EntityKazroticShot.class, "kazrotic_shot"),
                buildProjectileEntry(EntityZoragonBomb.class, "zoragon_bomb"),
                buildProjectileEntry(EntityDissimentShot.class, "dissiment_shot"),
                buildProjectileEntry(EntityLadyLunaSparkler.class, "lady_luna_sparkler"),
                buildProjectileEntry(EntityRaglokBomb.class, "raglok_bomb"),
                buildProjectileEntry(EntityWreckBouncingProjectile.class, "wreck_bouncing_projectile"),
                buildProjectileEntry(EntityWreckExplosiveShot.class, "wreck_explosive_shot"),
                buildProjectileEntry(EntityWreckShot.class, "wreck_shot")};

        if (Config.debug) {
            DivineRPG.logger.info(Reference.MODID + " entities have been loaded");
        }

        return divineProjectiles;
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {

        // Projectiles
        registerRender(EntityAttractor.class, manager -> new RenderAttractor(manager));
        registerRender(EntityBouncingProjectile.class, manager -> new divinerpg.objects.entities.assets.render.projectiles.RenderBouncingProjectile(manager));
        registerRender(EntityCaveRock.class, manager -> new RenderCaveRock(manager, 1f));
        registerRender(EntityCoriShot.class, manager -> new RenderCoriShot(manager, 1f));
        registerRender(EntityCorruptedBullet.class, manager -> new RenderCorruptedBullet(manager, 1f));
        registerRender(EntityDisk.class, manager -> new RenderDisk(manager));
        registerRender(EntityDissimentShot.class, manager -> new RenderDissimentShot(manager, 1f));
        registerRender(EntityDivineArrow.class, manager -> new RenderDivineArrow(manager));
        registerRender(EntityEnderTripletsFireball.class, manager -> new RenderEnderTripletsFireball(manager, 1f));
        registerRender(EntityFirefly.class, manager -> new RenderFirefly(manager));
        registerRender(EntityFractiteShot.class, manager -> new RenderFractiteShot(manager, 1.0f));
        registerRender(EntityFrostShot.class, manager -> new RenderFrostShot(manager, 0.75f));
        registerRender(EntityFyracryxFireball.class, manager -> new RenderFyracryxFireball(manager));
        registerRender(EntityGeneralsStaff.class, RenderStaff::new);
        registerRender(EntityGrenade.class, manager -> new RenderGrenade(manager));
        registerRender(EntityKazroticShot.class, manager -> new RenderKazroticShot(manager, 1f));
        registerRender(EntityKingOfScorchersMeteor.class, manager -> new RenderKingofScorchersMeteor(manager, 1f));
        registerRender(EntityKingOfScorchersShot.class, manager -> new RenderKingofScorchersShot(manager, 1f));
        registerRender(EntityLadyLunaSparkler.class, manager -> new RenderShooterBullet(manager));
        registerRender(EntityLamona.class, manager -> new RenderLamona(manager));
        registerRender(EntityMandragoraProjectile.class, manager -> new RenderMandragoraProjectile(manager, 1f));
        registerRender(EntityMerikMissile.class, manager -> new RenderMerikMissile(manager));
        registerRender(EntityMeteor.class, manager -> new RenderMeteor(manager));
        registerRender(EntityRaglokBomb.class, manager -> new RenderRaglokBomb(manager, 1f));
        registerRender(EntityReflector.class, manager -> new RenderReflector(manager));
        registerRender(EntitySaguaroWormShot.class, manager -> new RenderSaguaroWormShot(manager));
        registerRender(EntityScorcherShot.class, manager -> new RenderScorcherShot(manager, 0.5f));
        registerRender(EntitySerenadeOfDeath.class, manager -> new RenderSerenadeOfDeath(manager, 1f));
        registerRender(EntitySerenadeOfIce.class, manager -> new RenderSerenadeOfIce(manager, 1f));
        registerRender(EntityShooterBullet.class, manager -> new RenderShooterBullet(manager));
        registerRender(EntitySnowflakeShuriken.class, manager -> new RenderSnowShuriken(manager, 1f));
        registerRender(EntitySoundOfCarols.class, manager -> new RenderSoundOfCarols(manager, 1f));
        registerRender(EntitySoundOfMusic.class, manager -> new RenderSoundOfMusic(manager, 1f));
        registerRender(EntitySparkler.class, manager -> new RenderSparkler(manager));
        registerRender(EntityStar.class, manager -> new RenderStar(manager));
        registerRender(EntityVileStorm.class, manager -> new RenderVileStorm(manager, 1f));
        registerRender(EntityWatcherShot.class, manager -> new RenderWatcherShot(manager, 1f));
        registerRender(EntityWreckBouncingProjectile.class, manager -> new RenderWreckBouncingProjectile(manager, 1f));
        registerRender(EntityWreckExplosiveShot.class, manager -> new RenderWreckExplosiveShot(manager, 1f));
        registerRender(EntityWreckShot.class, manager -> new RenderWreckShot(manager, 1f));
        registerRender(EntityZoragonBomb.class, manager -> new RenderZoragonBomb(manager, 1f));

        registerRender(EntityFrostCloud.class, manager -> new RenderFrostCloud(manager));

        // Vanilla
        registerRender(EntityAncientEntity.class, RenderAncientEntity.FACTORY);
        registerRender(EntityAridWarrior.class, RenderAridWarrior.FACTORY);
        registerRender(EntityAyeracoBlue.class, RenderAyeracoBlue.FACTORY);
        registerRender(EntityAyeracoGreen.class, RenderAyeracoGreen.FACTORY);
        registerRender(EntityAyeracoPurple.class, RenderAyeracoPurple.FACTORY);
        registerRender(EntityAyeracoRed.class, RenderAyerecoRed.FACTORY);
        registerRender(EntityAyeracoYellow.class, RenderAyeracoYellow.FACTORY);
        registerRender(EntityBrownGrizzle.class, RenderBrownGrizzle.FACTORY);
        registerRender(EntityCaveclops.class, RenderCaveclops.FACTORY);
        registerRender(EntityCaveCrawler.class, RenderCaveCrawler.FACTORY);
        registerRender(EntityCrab.class, RenderCrab.FACTORY);
        registerRender(EntityCyclops.class, RenderCyclops.FACTORY);
        registerRender(EntityDesertCrawler.class, RenderDesertCrawler.FACTORY);
        registerRender(EntityEhu.class, RenderEhu.FACTORY);
        registerRender(EntityEnderSpider.class, RenderEnderSpider.FACTORY);
        registerRender(EntityEnderTriplets.class, RenderEnderTriplets.FACTORY);
        registerRender(EntityEnderWatcher.class, RenderEnderWatcher.FACTORY);
        registerRender(EntityEnthralledDramcryx.class, RenderEnthralledDramcryx.FACTORY);
        registerRender(EntityFrost.class, RenderFrost.FACTORY);
        registerRender(EntityGlacon.class, RenderGlacon.FACTORY);
        registerRender(EntityHellPig.class, RenderHellPig.FACTORY);
        registerRender(EntityHellSpider.class, RenderHellSpider.FACTORY);
        registerRender(EntityHusk.class, RenderHusk.FACTORY);
        registerRender(EntityJackOMan.class, RenderJackOMan.FACTORY);
        registerRender(EntityJungleBat.class, RenderJungleBat.FACTORY);
        registerRender(EntityJungleDramcryx.class, RenderJungleDramcryx.FACTORY);
        registerRender(EntityJungleSpider.class, RenderJungleSpider.FACTORY);
        registerRender(EntityKingCrab.class, RenderKingCrab.FACTORY);
        registerRender(EntityKingOfScorchers.class, RenderKingOfScorchers.FACTORY);
        registerRender(EntityKobblin.class, RenderKobblin.FACTORY);
        registerRender(EntityLiopleurodon.class, RenderLiopleurodon.FACTORY);
        registerRender(EntityLivestockMerchant.class, RenderLivestockMerchant.FACTORY);
        registerRender(EntityMiner.class, RenderMiner.FACTORY);
        registerRender(EntityPumpkinSpider.class, RenderPumpkinSpider.FACTORY);
        registerRender(EntityRainbour.class, RenderRainbour.FACTORY);
        registerRender(EntityRotatick.class, RenderRotatick.FACTORY);
        registerRender(EntitySaguaroWorm.class, RenderSaguaroWorm.FACTORY);
        registerRender(EntityScorcher.class, RenderScorcher.FACTORY);
        registerRender(EntityShark.class, RenderShark.FACTORY);
        registerRender(EntitySmelter.class, RenderSmelter.FACTORY);
        registerRender(EntitySnapper.class, RenderSnapper.FACTORY);
        registerRender(EntityStoneGolem.class, RenderStoneGolem.FACTORY);
        registerRender(EntityTheEye.class, RenderTheEye.FACTORY);
        registerRender(EntityTheGrue.class, RenderTheGrue.FACTORY);
        registerRender(EntityTheWatcher.class, RenderTheWatcher.FACTORY);
        registerRender(EntityWhale.class, RenderWhale.FACTORY);
        registerRender(EntityWhiteGrizzle.class, RenderWhiteGrizzle.FACTORY);
        registerRender(EntityWildfire.class, RenderWildfire.FACTORY);

        // Iceika
        registerRender(EntityAlicanto.class, RenderAlicanto.FACTORY);
        registerRender(EntityFrostArcher.class, RenderFrostArcher.FACTORY);
        registerRender(EntityFractite.class, RenderFractite.FACTORY);
        registerRender(EntityFrosty.class, RenderFrosty.FACTORY);
        registerRender(EntityGlacide.class, RenderGlacide.FACTORY);
        registerRender(EntityHastreus.class, RenderHastreus.FACTORY);
        registerRender(EntityRollum.class, RenderRollum.FACTORY);
        registerRender(EntityWorkshopTinkerer.class, RenderWorkshopTinkerer.FACTORY);
        registerRender(EntityWorkshopMerchant.class, RenderWorkshopMerchant.FACTORY);

        // Twilight
        registerRender(EntityAdvancedCori.class, RenderAdvancedCori.FACTORY);
        registerRender(EntityAngryBunny.class, RenderAngryBunny.FACTORY);
        registerRender(EntityApalachiaCadillion.class, RenderApalachiaCadillion.FACTORY);
        registerRender(EntityApalachiaGolem.class, RenderApalachiaGolem.FACTORY);
        registerRender(EntityApalachiaTomo.class, RenderApalachiaTomo.FACTORY);
        registerRender(EntityBasilisk.class, RenderBasilisk.FACTORY);
        registerRender(EntityBehemoth.class, RenderBehemoth.FACTORY);
        registerRender(EntityBunny.class, RenderBunny.FACTORY);
        registerRender(EntityDemonOfDarkness.class, RenderDemonOfDarkness.FACTORY);
        registerRender(EntityDensos.class, RenderDensos.FACTORY);
        registerRender(EntityEdenCadillion.class, RenderEdenCadillion.FACTORY);
        registerRender(EntityEdenTomo.class, RenderEdenTomo.FACTORY);
        registerRender(EntityEnchantedArcher.class, RenderEnchantedArcher.FACTORY);
        registerRender(EntityEnchantedWarrior.class, RenderEnchantedWarrior.FACTORY);
        registerRender(EntityEpiphite.class, RenderEpiphite.FACTORY);
        registerRender(EntityEternalArcher.class, RenderEternalArcher.FACTORY);
        registerRender(EntityGreenfeet.class, RenderGreenfeet.FACTORY);
        registerRender(EntityKarot.class, RenderKarot.FACTORY);
        registerRender(EntityMadivel.class, RenderMadivel.FACTORY);
        registerRender(EntityMage.class, RenderMage.FACTORY);
        registerRender(EntityMegalith.class, RenderMegalith.FACTORY);
        registerRender(EntityMoonWolf.class, RenderMoonWolf.FACTORY);
        registerRender(EntityMortumCadillion.class, RenderMortumCadillion.FACTORY);
        registerRender(EntityMystic.class, RenderMystic.FACTORY);
        registerRender(EntityReyvor.class, RenderReyvor.FACTORY);
        registerRender(EntitySamek.class, RenderSamek.FACTORY);
        registerRender(EntitySkythernArcher.class, RenderSkythernArcher.FACTORY);
        registerRender(EntitySkythernFiend.class, RenderSkythernFiend.FACTORY);
        registerRender(EntitySkythernGolem.class, RenderSkythernGolem.FACTORY);
        registerRender(EntitySorcerer.class, RenderSorcerer.FACTORY);
        registerRender(EntitySoulFiend.class, RenderSoulFiend.FACTORY);
        registerRender(EntitySoulSpider.class, RenderSoulSpider.FACTORY);
        registerRender(EntitySoulStealer.class, RenderSoulStealer.FACTORY);
        registerRender(EntitySpellbinder.class, RenderSpellbinder.FACTORY);
        registerRender(EntitySunArcher.class, RenderSunArcher.FACTORY);
        registerRender(EntityTwilightArcher.class, RenderTwilightArcher.FACTORY);
        registerRender(EntityTwilightDemon.class, RenderTwilightDemon.FACTORY);
        registerRender(EntityVamacheron.class, RenderVamacheron.FACTORY);
        registerRender(EntityVerek.class, RenderVerek.FACTORY);
        registerRender(EntityWeakCori.class, RenderWeakCori.FACTORY);
        registerRender(EntityWildwoodCadillion.class, RenderWildwoodCadillion.FACTORY);
        registerRender(EntityWildwoodGolem.class, RenderWildwoodGolem.FACTORY);
        registerRender(EntityWildwoodTomo.class, RenderWildwoodTomo.FACTORY);

        // Arcana
        registerRender(EntityCaptainMerik.class, RenderCaptainMerik.FACTORY);
        registerRender(EntityDatticon.class, RenderDatticon.FACTORY);
        registerRender(EntityDeathcryx.class, RenderDeathcryx.FACTORY);
        registerRender(EntityDeathHound.class, RenderDeathHound.FACTORY);
        registerRender(EntityDramix.class, RenderDramix.FACTORY);
        registerRender(EntityDungeonConstructor.class, RenderDungeonConstructor.FACTORY);
        registerRender(EntityDungeonDemon.class, RenderDungeonDemon.FACTORY);
        registerRender(EntityDungeonPrisoner.class, RenderDungeonPrisoner.FACTORY);
        registerRender(EntityFyracryx.class, RenderFyracryx.FACTORY);
        registerRender(EntityGolemOfRejuvenation.class, RenderGolemOfRejuvenation.FACTORY);
        registerRender(EntityLeorna.class, RenderLeorna.FACTORY);
        registerRender(EntityLivingStatue.class, RenderLivingStatue.FACTORY);
        registerRender(EntityLordVatticus.class, RenderLordVatticus.FACTORY);
        registerRender(EntityParasecta.class, RenderParasecta.FACTORY);
        registerRender(EntityParatiku.class, RenderParatiku.FACTORY);
        registerRender(EntityRazorback.class, RenderRazorback.FACTORY);
        registerRender(EntityRoamer.class, RenderRoamer.FACTORY);
        registerRender(EntitySeimer.class, RenderSeimer.FACTORY);
        registerRender(EntityWarGeneral.class, RenderWarGeneral.FACTORY);
        registerRender(EntityWraith.class, RenderWraith.FACTORY);
        registerRender(EntityZelus.class, RenderZelus.FACTORY);

        //Vethea
        registerRender(EntityAcidHag.class, RenderAcidHag.FACTORY);
        registerRender(EntityBiphron.class, RenderBiphron.FACTORY);
        registerRender(EntityBohemite.class, RenderBohemite.FACTORY);
        registerRender(EntityCryptKeeper.class, RenderCryptKeeper.FACTORY);
        registerRender(EntityCymesoid.class, RenderCymesoid.FACTORY);
        registerRender(EntityDissiment.class, RenderDissiment.FACTORY);
        registerRender(EntityDreamwrecker.class, RenderDreamwrecker.FACTORY);
        registerRender(EntityDuo.class, RenderDuo.FACTORY);
        registerRender(EntityEnt.class, RenderEnt.FACTORY);
        registerRender(EntityFakeVhraak.class, RenderFakeVhraak.FACTORY);
        registerRender(EntityGalroid.class, RenderGalroid.FACTORY);
        registerRender(EntityGorgosion.class, RenderGorgosion.FACTORY);
        registerRender(EntityHelio.class, RenderHelio.FACTORY);
        registerRender(EntityHerbomancer.class, RenderHerbomancer.FACTORY);
        registerRender(EntityHerbomancerMinion.class, RenderHerbomancerMinion.FACTORY);
        registerRender(EntityHiveQueen.class, RenderHiveQueen.FACTORY);
        registerRender(EntityHiveSoldier.class, RenderHiveSoldier.FACTORY);
        registerRender(EntityHoverStinger.class, RenderHoverStinger.FACTORY);
        registerRender(EntityKaros.class, RenderKaros.FACTORY);
        registerRender(EntityKazrotic.class, RenderKazrotic.FACTORY);
        registerRender(EntityLadyLuna.class, RenderLadyLuna.FACTORY);
        registerRender(EntityLheiva.class, RenderLheiva.FACTORY);
        registerRender(EntityLorga.class, RenderLorga.FACTORY);
        registerRender(EntityLorgaflight.class, RenderLorgaFlight.FACTORY);
        registerRender(EntityMandragora.class, RenderMandragora.FACTORY);
        registerRender(EntityMysteriousManLayer1.class, RenderMysteriousManLayer1.FACTORY);
        registerRender(EntityMysteriousManLayer2.class, RenderMysteriousManLayer2.FACTORY);
        registerRender(EntityMysteriousManLayer3.class, RenderMysteriousManLayer3.FACTORY);
        registerRender(EntityQuadro.class, RenderQuadro.FACTORY);
        registerRender(EntityRaglok.class, RenderRaglok.FACTORY);
        registerRender(EntityShadahier.class, RenderShadahier.FACTORY);
        registerRender(EntitySpinarus.class, RenderSpinarus.FACTORY);
        registerRender(EntityTempleGuardian.class, RenderTempleGuardian.FACTORY);
        registerRender(EntityTheHunger.class, RenderTheHunger.FACTORY);
        registerRender(EntityTocaxin.class, RenderTocaxin.FACTORY);
        registerRender(EntityTwins.class, RenderTwins.FACTORY);
        registerRender(EntityVermenous.class, RenderVermenous.FACTORY);
        registerRender(EntityVhraak.class, RenderVhraak.FACTORY);
        registerRender(EntityWreck.class, RenderWreck.FACTORY);
        registerRender(EntityZone.class, RenderZone.FACTORY);
        registerRender(EntityZoragon.class, RenderZoragon.FACTORY);
    }

    /**
     * Private helper method that simply calls the rendering registry method.
     * No functional benefit, exists solely to make rendering registry code section less of an eyesore.
     *
     * @param entityClass   the entity class of type T
     * @param renderFactory a render factory for the entity
     * @param <T>           generic class to ensure it extends entity
     */
    private static <T extends Entity> void registerRender(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
}