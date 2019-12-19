package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.apalachia.warrior.EnchantedWarrior;
import divinerpg.entities.apalachia.warrior.EnchantedWarriorRender;
import divinerpg.entities.base.DivineFireball;
import divinerpg.entities.bosses.ayeraco.Ayeraco;
import divinerpg.entities.bosses.vamacheron.Vamacheron;
import divinerpg.entities.bosses.vamacheron.VamacheronRender;
import divinerpg.entities.eden.archer.SunArcher;
import divinerpg.entities.eden.archer.SunArcherRender;
import divinerpg.entities.eden.bunny.angry.AngryBunny;
import divinerpg.entities.eden.bunny.angry.AngryBunnyRender;
import divinerpg.entities.eden.bunny.pet.Bunny;
import divinerpg.entities.eden.bunny.pet.BunnyRender;
import divinerpg.entities.eden.feet.Greenfeet;
import divinerpg.entities.eden.feet.GreenfeetRender;
import divinerpg.entities.eden.madivel.Madivel;
import divinerpg.entities.eden.madivel.MadivelRender;
import divinerpg.entities.fireball.FrostFireball;
import divinerpg.entities.fireball.ScorcherFireball;
import divinerpg.entities.mortum.basilisk.Basilisk;
import divinerpg.entities.mortum.basilisk.BasiliskRender;
import divinerpg.entities.mortum.deamon.DemonOfDarkness;
import divinerpg.entities.mortum.deamon.DemonOfDarknessRender;
import divinerpg.entities.mortum.sorcerer.Sorcerer;
import divinerpg.entities.mortum.sorcerer.SorcererRender;
import divinerpg.entities.mortum.stealer.SoulStealer;
import divinerpg.entities.mortum.stealer.SoulStealerRender;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.entities.projectiles.Bullet.BulletEntityRender;
import divinerpg.entities.projectiles.DivineArrow.DivineArrow;
import divinerpg.entities.projectiles.DivineArrow.DivineArrowRender;
import divinerpg.entities.projectiles.ItemBulletEntity;
import divinerpg.entities.skythern.fiend.SkythernFiend;
import divinerpg.entities.skythern.fiend.SkythernFiendRender;
import divinerpg.entities.skythern.megalith.Megalith;
import divinerpg.entities.skythern.megalith.MegalithRender;
import divinerpg.entities.twilight.archer.ArcherRender;
import divinerpg.entities.twilight.archer.entiites.EnchantedArcher;
import divinerpg.entities.twilight.archer.entiites.SkythernArcher;
import divinerpg.entities.twilight.archer.twilight.TwilightArcher;
import divinerpg.entities.twilight.archer.twilight.TwilightArcherRender;
import divinerpg.entities.twilight.cadilion.CadilionRender;
import divinerpg.entities.twilight.cadilion.enitities.ApalachiaCadilion;
import divinerpg.entities.twilight.cadilion.enitities.EdenCadilion;
import divinerpg.entities.twilight.cadilion.enitities.MortumCadilion;
import divinerpg.entities.twilight.cadilion.enitities.WildwoodCadilion;
import divinerpg.entities.twilight.cori.CoriRender;
import divinerpg.entities.twilight.cori.entities.AdvancedCori;
import divinerpg.entities.twilight.cori.entities.WeakCori;
import divinerpg.entities.twilight.golem.GolemRender;
import divinerpg.entities.twilight.golem.entities.ApalachiaGolem;
import divinerpg.entities.twilight.golem.entities.SkythernGolem;
import divinerpg.entities.twilight.golem.entities.WildwoodGolem;
import divinerpg.entities.twilight.mystic.MysticRender;
import divinerpg.entities.twilight.mystic.entities.Mystic;
import divinerpg.entities.twilight.mystic.entities.Spellbinder;
import divinerpg.entities.twilight.samek.SamekRender;
import divinerpg.entities.twilight.samek.entities.Samek;
import divinerpg.entities.twilight.samek.entities.Verek;
import divinerpg.entities.twilight.tomo.TomoRender;
import divinerpg.entities.twilight.tomo.entities.ApalachiaTomo;
import divinerpg.entities.twilight.tomo.entities.EdenTomo;
import divinerpg.entities.twilight.tomo.entities.WildwoodTomo;
import divinerpg.entities.vanilla.arid.AridWarrior;
import divinerpg.entities.vanilla.arid.AridWarriorRender;
import divinerpg.entities.vanilla.bat.DivineBatRender;
import divinerpg.entities.vanilla.bat.HellBat;
import divinerpg.entities.vanilla.bat.JungleBat;
import divinerpg.entities.vanilla.bat.rainbour.Rainbour;
import divinerpg.entities.vanilla.bat.rainbour.RainbourRender;
import divinerpg.entities.vanilla.crab.king.KingCrab;
import divinerpg.entities.vanilla.crab.king.KingCrabRender;
import divinerpg.entities.vanilla.crab.regular.Crab;
import divinerpg.entities.vanilla.crab.regular.CrabRender;
import divinerpg.entities.vanilla.crawler.CrawlerRender;
import divinerpg.entities.vanilla.crawler.cave.CaveCrawler;
import divinerpg.entities.vanilla.crawler.desert.DesertCrawler;
import divinerpg.entities.vanilla.cyclop.cave.Cavelops;
import divinerpg.entities.vanilla.cyclop.cave.CavelopsRender;
import divinerpg.entities.vanilla.cyclop.regular.Cyclops;
import divinerpg.entities.vanilla.cyclop.regular.CyclopsRender;
import divinerpg.entities.vanilla.dramcryx.enthralled.EnthralledDramcryx;
import divinerpg.entities.vanilla.dramcryx.enthralled.EnthralledDramcryxRender;
import divinerpg.entities.vanilla.dramcryx.jungle.JungleDramcryx;
import divinerpg.entities.vanilla.dramcryx.jungle.JungleDramcryxRender;
import divinerpg.entities.vanilla.eye.Eye;
import divinerpg.entities.vanilla.eye.EyeRender;
import divinerpg.entities.vanilla.frost.Frost;
import divinerpg.entities.vanilla.frost.FrostRender;
import divinerpg.entities.vanilla.glacon.Glacon;
import divinerpg.entities.vanilla.glacon.GlaconRender;
import divinerpg.entities.vanilla.grue.Grue;
import divinerpg.entities.vanilla.grue.GrueRender;
import divinerpg.entities.vanilla.koblin.Kobblin;
import divinerpg.entities.vanilla.koblin.KobblinRender;
import divinerpg.entities.vanilla.miner.Miner;
import divinerpg.entities.vanilla.miner.MinerRender;
import divinerpg.entities.vanilla.pig.HellPig;
import divinerpg.entities.vanilla.pig.HellPigRender;
import divinerpg.entities.vanilla.rotatick.Rotatick;
import divinerpg.entities.vanilla.rotatick.RotatickRender;
import divinerpg.entities.vanilla.scorcher.Scorcher;
import divinerpg.entities.vanilla.scorcher.ScorcherRender;
import divinerpg.entities.vanilla.spider.ender.EnderSpider;
import divinerpg.entities.vanilla.spider.ender.EnderSpiderRender;
import divinerpg.entities.vanilla.spider.hell.HellSpider;
import divinerpg.entities.vanilla.spider.hell.HellSpiderRender;
import divinerpg.entities.vanilla.spider.jungle.JungleSpider;
import divinerpg.entities.vanilla.spider.jungle.JungleSpiderRender;
import divinerpg.entities.vanilla.spider.pumpkin.PumpkinSpider;
import divinerpg.entities.vanilla.spider.pumpkin.PumpkinSpiderRender;
import divinerpg.entities.vanilla.triplets.EnderTriplets;
import divinerpg.entities.vanilla.triplets.EnderTripletsRender;
import divinerpg.entities.vanilla.watcher.ender.EnderWatcher;
import divinerpg.entities.vanilla.watcher.ender.EnderWatcherRender;
import divinerpg.entities.vanilla.wildfire.Wildfire;
import divinerpg.entities.vanilla.wildfire.WildfireRender;
import divinerpg.entities.vanilla.worm.SaguaroWorm;
import divinerpg.entities.vanilla.worm.SaguaroWormRender;
import divinerpg.entities.wildwood.epiphite.Epiphite;
import divinerpg.entities.wildwood.epiphite.EpiphiteRender;
import divinerpg.entities.wildwood.mage.Mage;
import divinerpg.entities.wildwood.mage.MageRender;
import divinerpg.entities.wildwood.wolf.MoonWolf;
import divinerpg.entities.wildwood.wolf.MoonWolfRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = DivineRPG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class EntitiesRegistry {

    @ObjectHolder("bullet_item_entity")
    public static final EntityType<ItemBulletEntity> bullet_item_entity = null;
    @ObjectHolder("bullet_entity")
    public static final EntityType<BulletEntity> bullet_entity = null;
    @ObjectHolder("arrow_entity")
    public static final EntityType<DivineArrow> arrow_entity = null;

    @ObjectHolder("divine_fireball")
    public static final EntityType<DivineFireball> divine_fireball = null;
    @ObjectHolder("frost_shot")
    public static final EntityType<FrostFireball> frost_shot = null;

    @ObjectHolder("entrhralled_dramcryx")
    public static EntityType<EnthralledDramcryx> entrhralled_dramcryx = null;
    @ObjectHolder("crab")
    public static EntityType<Crab> crab = null;
    @ObjectHolder("jungle_dramcryx")
    public static EntityType<JungleDramcryx> jungle_dramcryx = null;
    @ObjectHolder("frost")
    public static EntityType<Frost> frost = null;
    @ObjectHolder("glacon")
    public static EntityType<Glacon> glacon = null;
    @ObjectHolder("rotatick")
    public static EntityType<Rotatick> rotatick = null;
    @ObjectHolder("king_crab")
    public static EntityType<KingCrab> king_crab = null;
    @ObjectHolder("jungle_spider")
    public static EntityType<JungleSpider> jungle_spider = null;
    @ObjectHolder("hell_spider")
    public static EntityType<HellSpider> hell_spider = null;
    @ObjectHolder("ender_spider")
    public static EntityType<EnderSpider> ender_spider = null;
    @ObjectHolder("ender_watcher")
    public static EntityType<EnderWatcher> ender_watcher = null;
    @ObjectHolder("ender_triplets")
    public static EntityType<EnderTriplets> ender_triplets = null;
    @ObjectHolder("scorcher")
    public static EntityType<Scorcher> scorcher = null;
    @ObjectHolder("scorcher_fireball")
    public static EntityType<ScorcherFireball> scorcher_fireball = null;
    @ObjectHolder("wildfire")
    public static EntityType<Wildfire> wildfire = null;
    @ObjectHolder("grue")
    public static EntityType<Grue> grue = null;
    @ObjectHolder("cave_crawler")
    public static EntityType<CaveCrawler> cave_crawler = null;
    @ObjectHolder("desert_crawler")
    public static EntityType<DesertCrawler> desert_crawler = null;
    @ObjectHolder("cavelops")
    public static EntityType<Cavelops> cavelops = null;
    @ObjectHolder("cyclops")
    public static EntityType<Cyclops> cyclops = null;
    @ObjectHolder("miner")
    public static EntityType<Miner> miner = null;
    @ObjectHolder("jungle_bat")
    public static EntityType<JungleBat> jungle_bat = null;
    @ObjectHolder("hell_bat")
    public static EntityType<HellBat> hell_bat = null;
    @ObjectHolder("eye")
    public static EntityType<Eye> eye = null;
    @ObjectHolder("kobblin")
    public static EntityType<Kobblin> koblin = null;
    @ObjectHolder("rainbour")
    public static EntityType<Rainbour> rainbour = null;
    @ObjectHolder("saguaro_worm")
    public static EntityType<SaguaroWorm> saguaro_worm = null;
    @ObjectHolder("arid_warrior")
    public static EntityType<AridWarrior> arid_warrior = null;
    @ObjectHolder("pumpkin_spider")
    public static EntityType<PumpkinSpider> pumpkin_spider = null;
    @ObjectHolder("eden_tomo")
    public static EntityType<EdenTomo> eden_tomo = null;
    @ObjectHolder("wildwood_tomo")
    public static EntityType<WildwoodTomo> wildwood_tomo = null;
    @ObjectHolder("apalachia_tomo")
    public static EntityType<ApalachiaTomo> apalachia_tomo = null;
    @ObjectHolder("eden_cadillion")
    public static EntityType<EdenCadilion> eden_cadillion = null;
    @ObjectHolder("wildwood_cadillion")
    public static EntityType<WildwoodCadilion> wildwood_cadillion = null;
    @ObjectHolder("apalachia_cadillion")
    public static EntityType<ApalachiaCadilion> apalachia_cadillion = null;
    @ObjectHolder("mortum_cadillion")
    public static EntityType<MortumCadilion> mortum_cadillion = null;
    @ObjectHolder("weak_cori")
    public static EntityType<WeakCori> weak_cori;
    @ObjectHolder("advanced_cori")
    public static EntityType<AdvancedCori> advanced_cori;
    @ObjectHolder("moon_wolf")
    public static EntityType<MoonWolf> moon_wolf;
    @ObjectHolder("hell_pig")
    public static EntityType<HellPig> hell_pig;
    @ObjectHolder("bunny")
    public static EntityType<Bunny> bunny;
    @ObjectHolder("angry_bunny")
    public static EntityType<AngryBunny> angry_bunny;
    @ObjectHolder("greenfeet")
    public static EntityType<Greenfeet> greenfeet;
    @ObjectHolder("madivel")
    public static EntityType<Madivel> madivel;
    @ObjectHolder("sun_archer")
    public static EntityType<SunArcher> sun_archer;
    @ObjectHolder("epiphite")
    public static EntityType<Epiphite> epiphite;
    @ObjectHolder("wildwood_golem")
    public static EntityType<WildwoodGolem> wildwood_golem;
    @ObjectHolder("apalachia_golem")
    public static EntityType<ApalachiaGolem> apalachia_golem;
    @ObjectHolder("skythern_golem")
    public static EntityType<SkythernGolem> skythern_golem;
    @ObjectHolder("enchanted_archer")
    public static EntityType<EnchantedArcher> enchanted_archer;
    @ObjectHolder("enchanted_warrior")
    public static EntityType<EnchantedWarrior> enchanted_warrior;
    @ObjectHolder("spellbinder")
    public static EntityType<Spellbinder> spellbinder;
    @ObjectHolder("mystic")
    public static EntityType<Mystic> mystic;
    @ObjectHolder("samek")
    public static EntityType<Samek> samek;
    @ObjectHolder("skythern_fiend")
    public static EntityType<SkythernFiend> skythern_fiend;
    @ObjectHolder("verek")
    public static EntityType<Verek> verek;
    @ObjectHolder("mage")
    public static EntityType<Mage> mage;
    @ObjectHolder("skythern_archer")
    public static EntityType<SkythernArcher> skythern_archer;
    @ObjectHolder("twilight_archer")
    public static EntityType<TwilightArcher> twilight_archer;
    @ObjectHolder("megalith")
    public static EntityType<Megalith> megalith;
    @ObjectHolder("demon_of_darkness")
    public static EntityType<DemonOfDarkness> demon_of_darkness;
    @ObjectHolder("basilisk")
    public static EntityType<Basilisk> basilisk;
    @ObjectHolder("sorcerer")
    public static EntityType<Sorcerer> sorcerer;
    @ObjectHolder("soul_stealer")
    public static EntityType<SoulStealer> soul_stealer;
    @ObjectHolder("vamacheron")
    public static EntityType<Vamacheron> vamacheron;
    @ObjectHolder("ayeraco")
    public static EntityType<Ayeraco> ayeraco;

    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<EntityType<?>> e) {
        IForgeRegistry<EntityType<?>> registry = e.getRegistry();

        registerBulletEntity(registry, ItemBulletEntity::new, w -> bullet_item_entity.create(w), "bullet_item_entity");
        registerBulletEntity(registry, BulletEntity::new, w -> bullet_entity.create(w), "bullet_entity");
        registerBulletEntity(registry, DivineArrow::new, w -> arrow_entity.create(w), "arrow_entity");
        registerBulletEntity(registry, FrostFireball::new, w -> frost_shot.create(w), "frost_fireball");
        registerBulletEntity(registry, ScorcherFireball::new, w -> scorcher_fireball.create(w), "scorcher_fireball");

        registerSingle(registry, EnthralledDramcryx::new, "entrhralled_dramcryx", 1.35F, 1.75F);
        registerSingle(registry, Crab::new, "crab", 1.1F, 0.8F);
        registerSingle(registry, JungleDramcryx::new, "jungle_dramcryx", 1, 1.3F);
        registerSingle(registry, Frost::new, "frost", 1, 1);
        registerSingle(registry, Glacon::new, "glacon", 0.8F, 1.4f);
        registerSingle(registry, Rotatick::new, "rotatick", 0.85F, 1);
        registerSingle(registry, KingCrab::new, "king_crab", 1.8F, 1.7F);
        registerSingle(registry, JungleSpider::new, "jungle_spider", 1.4F, 0.9F);
        registerSingle(registry, HellSpider::new, "hell_spider", 1.4F, 0.9F);
        registerSingle(registry, EnderSpider::new, "ender_spider", 0.5F, 0.55F);
        registerSingle(registry, EnderWatcher::new, "ender_watcher", 0.7F, 0.9F);
        registerSingle(registry, EnderTriplets::new, "ender_triplets", 0.7F, 0.9F);
        registerSingle(registry, Scorcher::new, "scorcher", 1.2F, 2);
        registerSingle(registry, Wildfire::new, "wildfire", 0.8F, 2.2F);
        registerSingle(registry, Grue::new, "grue", 0.8F, 1.9F);
        registerSingle(registry, CaveCrawler::new, "cave_crawler", 1, 1.5F);
        registerSingle(registry, DesertCrawler::new, "desert_crawler", 1, 1.5F);
        registerSingle(registry, Cavelops::new, "cavelops", 1.2F, 4);
        registerSingle(registry, Cyclops::new, "cyclops", 1.2F, 4);
        registerSingle(registry, Miner::new, "miner", 0.6F, 2);
        registerSingle(registry, JungleBat::new, "jungle_bat", 0.7F, 1);
        registerSingle(registry, HellBat::new, "hell_bat", 0.7F, 1);
        registerSingle(registry, Eye::new, "eye", 1.3F, 2);
        registerSingle(registry, Kobblin::new, "kobblin", 0.75F, 1);
        registerSingle(registry, Rainbour::new, "rainbour", 1, 1);
        registerSingle(registry, SaguaroWorm::new, "saguaro_worm", 1, 3);
        registerSingle(registry, AridWarrior::new, "arid_warrior", 1.4F, 2.8f);
        registerSingle(registry, PumpkinSpider::new, "pumpkin_spider", 0.6F, 0.85F);

        // Twilight
        registerSingle(registry, EdenTomo::new, "eden_tomo", 1, 1);
        registerSingle(registry, WildwoodTomo::new, "wildwood_tomo", 1, 1);
        registerSingle(registry, ApalachiaTomo::new, "apalachia_tomo", 1, 1);

        registerSingle(registry, EdenCadilion::new, "eden_cadillion", 1, 1.5F);
        registerSingle(registry, WildwoodCadilion::new, "wildwood_cadillion", 1, 1.5F);
        registerSingle(registry, ApalachiaCadilion::new, "apalachia_cadillion", 1, 1.5F);
        registerSingle(registry, MortumCadilion::new, "mortum_cadillion", 1, 1.5F);

        registerSingle(registry, WeakCori::new, "weak_cori", 0.6F, 1.5F);
        registerSingle(registry, AdvancedCori::new, "advanced_cori", 0.6F, 1.5F);

        registerSingle(registry, WildwoodGolem::new, "wildwood_golem", 1.3F, 2.9F);
        registerSingle(registry, ApalachiaGolem::new, "apalachia_golem", 1.3F, 2.9F);
        registerSingle(registry, SkythernGolem::new, "skythern_golem", 1.3F, 2.9F);

        registerSingle(registry, Spellbinder::new, "spellbinder", 0.5F, 2.2F);
        registerSingle(registry, Mystic::new, "mystic", 0.5F, 2.2F);

        registerSingle(registry, EnchantedWarrior::new, "enchanted_warrior", 0.6F, 2);
        registerSingle(registry, Samek::new, "samek", 0.6F, 2);
        registerSingle(registry, SkythernFiend::new, "skythern_fiend", 0.6F, 2);
        registerSingle(registry, Verek::new, "verek", 0.6F, 2);

        registerSingle(registry, EnchantedArcher::new, "enchanted_archer", 1.8F, 3.0F);
        registerSingle(registry, SkythernArcher::new, "skythern_archer", 1.8F, 3.0F);
        registerSingle(registry, TwilightArcher::new, "twilight_archer", 1.8F, 3.0F);

        registerSingle(registry, MoonWolf::new, EntityClassification.CREATURE, "moon_wolf", 1.25F, 1);
        registerSingle(registry, HellPig::new, EntityClassification.CREATURE, "hell_pig", 1, 0.9F);
        registerSingle(registry, Bunny::new, EntityClassification.CREATURE, "bunny", 0.5F, 0.7F);

        registerSingle(registry, AngryBunny::new, "angry_bunny", 1.1F, 1.8F);
        registerSingle(registry, Greenfeet::new, "greenfeet", 1, 2);
        registerSingle(registry, Madivel::new, "madivel", 0.6F, 2.9F);
        registerSingle(registry, SunArcher::new, "sun_archer", 0.8F, 2.2F);

        registry.register(EntityType.Builder.create(Epiphite::new, EntityClassification.MONSTER)
                .size(0.9F, 1.3F)
                .immuneToFire()
                .setCustomClientFactory((spawnEntity, world) -> new Epiphite(world))
                .build("epiphite").setRegistryName(DivineRPG.MODID, ""));

        registerSingle(registry, Mage::new, "mage", 0.5F, 2.2F);
        registerSingle(registry, Megalith::new, "megalith", 1.2F, 4);
        registerSingle(registry, DemonOfDarkness::new, "demon_of_darkness", 0.8F, 1.6F);
        registerSingle(registry, Basilisk::new, "basilisk", 0.7F, 0.8F);
        registerSingle(registry, Sorcerer::new, "sorcerer", 0.5F, 2.2F);
        registerSingle(registry, SoulStealer::new, "soul_stealer", 0.8F, 2);
        registerSingle(registry, Vamacheron::new, "vamacheron", 1.45F, 2.2F);

    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRender() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // projectiles
        RenderingRegistry.registerEntityRenderingHandler(BulletEntity.class, BulletEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FrostFireball.class, BulletEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ScorcherFireball.class, BulletEntityRender::new);

        RenderingRegistry.registerEntityRenderingHandler(DivineArrow.class, DivineArrowRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ItemBulletEntity.class, factory -> new SpriteRenderer<>(factory, itemRenderer));

        RenderingRegistry.registerEntityRenderingHandler(EnthralledDramcryx.class, EnthralledDramcryxRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JungleDramcryx.class, JungleDramcryxRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Crab.class, CrabRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Frost.class, FrostRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Glacon.class, GlaconRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Rotatick.class, RotatickRender::new);
        RenderingRegistry.registerEntityRenderingHandler(KingCrab.class, KingCrabRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JungleSpider.class, JungleSpiderRender::new);
        RenderingRegistry.registerEntityRenderingHandler(HellSpider.class, HellSpiderRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EnderSpider.class, EnderSpiderRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EnderWatcher.class, EnderWatcherRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EnderTriplets.class, EnderTripletsRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Scorcher.class, ScorcherRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Wildfire.class, WildfireRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Grue.class, GrueRender::new);
        RenderingRegistry.registerEntityRenderingHandler(CaveCrawler.class, f -> new CrawlerRender<>(f, "cave_crawler"));
        RenderingRegistry.registerEntityRenderingHandler(DesertCrawler.class, f -> new CrawlerRender<>(f, "desert_crawler"));
        RenderingRegistry.registerEntityRenderingHandler(Cavelops.class, CavelopsRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Cyclops.class, CyclopsRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Miner.class, MinerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JungleBat.class, DivineBatRender::new);
        RenderingRegistry.registerEntityRenderingHandler(HellBat.class, DivineBatRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Eye.class, EyeRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Kobblin.class, KobblinRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Rainbour.class, RainbourRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SaguaroWorm.class, SaguaroWormRender::new);
        RenderingRegistry.registerEntityRenderingHandler(AridWarrior.class, AridWarriorRender::new);
        RenderingRegistry.registerEntityRenderingHandler(PumpkinSpider.class, PumpkinSpiderRender::new);

        registerForOneRender(TomoRender::new, EdenTomo.class, WildwoodTomo.class, ApalachiaTomo.class);
        registerForOneRender(CadilionRender::new, EdenCadilion.class, WildwoodCadilion.class, ApalachiaCadilion.class,
                MortumCadilion.class);

        registerForOneRender(CoriRender::new, WeakCori.class, AdvancedCori.class);
        registerForOneRender(GolemRender::new, WildwoodGolem.class, ApalachiaGolem.class, SkythernGolem.class);
        registerForOneRender(MysticRender::new, Spellbinder.class, Mystic.class);
        registerForOneRender(SamekRender::new, Samek.class, Verek.class);
        registerForOneRender(ArcherRender::new, EnchantedArcher.class, SkythernArcher.class);

        RenderingRegistry.registerEntityRenderingHandler(MoonWolf.class, MoonWolfRender::new);
        RenderingRegistry.registerEntityRenderingHandler(HellPig.class, HellPigRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Bunny.class, BunnyRender::new);
        RenderingRegistry.registerEntityRenderingHandler(AngryBunny.class, AngryBunnyRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Greenfeet.class, GreenfeetRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Madivel.class, MadivelRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SunArcher.class, SunArcherRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Epiphite.class, EpiphiteRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EnchantedArcher.class, ArcherRender::new);
        RenderingRegistry.registerEntityRenderingHandler(EnchantedWarrior.class, EnchantedWarriorRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SkythernFiend.class, SkythernFiendRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Mage.class, MageRender::new);
        RenderingRegistry.registerEntityRenderingHandler(TwilightArcher.class, TwilightArcherRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Megalith.class, MegalithRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DemonOfDarkness.class, DemonOfDarknessRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Basilisk.class, BasiliskRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Sorcerer.class, SorcererRender::new);
        RenderingRegistry.registerEntityRenderingHandler(SoulStealer.class, SoulStealerRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Vamacheron.class, VamacheronRender::new);
    }


    private static <T extends Entity> void registerSingle(IForgeRegistry<EntityType<?>> registry, Function<World, T> createFunc, String name, float width, float height) {
        registerSingle(registry, createFunc, EntityClassification.MONSTER, name, width, height);
    }

    private static <T extends Entity> void registerSingle(IForgeRegistry<EntityType<?>> registry, Function<World, T> createFunc, EntityClassification classification,
                                                          String name, float width, float height) {
        registry.register(
                EntityType.Builder.<T>create((type, world) -> createFunc.apply(world), classification)
                        .setCustomClientFactory((spawnEntity, world) -> createFunc.apply(world))
                        .size(width, height)
                        .build(name).setRegistryName(DivineRPG.MODID, name)
        );
    }

    private static <T extends Entity> void registerForOneRender(IRenderFactory<T> render, Class<? extends T>... classes) {
        for (Class clazz : classes) {
            RenderingRegistry.registerEntityRenderingHandler(clazz, render);
        }
    }


    private static <T extends Entity> void registerBulletEntity(IForgeRegistry<EntityType<?>> registry, EntityType.IFactory<T> factoryIn,
                                                                Function<World, T> createFunc, String name) {
        registry.register(EntityType.Builder.create(factoryIn, EntityClassification.MISC)
                .size(0.2F, 0.2F)
                .setUpdateInterval(5)
                .setTrackingRange(256)
                .setShouldReceiveVelocityUpdates(true)
                // Need to be set for client to see the entity
                .setCustomClientFactory((type, world) -> createFunc.apply(world))
                .build(name)
                .setRegistryName(DivineRPG.MODID, name));
    }
}
