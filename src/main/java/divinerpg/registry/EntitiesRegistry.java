package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.base.DivineFireball;
import divinerpg.entities.fireball.FrostFireball;
import divinerpg.entities.fireball.ScorcherFireball;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.entities.projectiles.Bullet.BulletEntityRender;
import divinerpg.entities.projectiles.DivineArrow.DivineArrowEntity;
import divinerpg.entities.projectiles.DivineArrow.DivineEntityRender;
import divinerpg.entities.projectiles.ItemBulletEntity;
import divinerpg.entities.vanilla.crab.king.KingCrab;
import divinerpg.entities.vanilla.crab.king.KingCrabRender;
import divinerpg.entities.vanilla.crab.regular.Crab;
import divinerpg.entities.vanilla.crab.regular.CrabRender;
import divinerpg.entities.vanilla.dramcryx.enthralled.EnthralledDramcryx;
import divinerpg.entities.vanilla.dramcryx.enthralled.EnthralledDramcryxRender;
import divinerpg.entities.vanilla.dramcryx.jungle.JungleDramcryx;
import divinerpg.entities.vanilla.dramcryx.jungle.JungleDramcryxRender;
import divinerpg.entities.vanilla.frost.Frost;
import divinerpg.entities.vanilla.frost.FrostRender;
import divinerpg.entities.vanilla.glacon.Glacon;
import divinerpg.entities.vanilla.glacon.GlaconRender;
import divinerpg.entities.vanilla.grue.Grue;
import divinerpg.entities.vanilla.grue.GrueRender;
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
import divinerpg.entities.vanilla.triplets.EnderTriplets;
import divinerpg.entities.vanilla.triplets.EnderTripletsRender;
import divinerpg.entities.vanilla.watcher.ender.EnderWatcher;
import divinerpg.entities.vanilla.watcher.ender.EnderWatcherRender;
import divinerpg.entities.vanilla.wildfire.Wildfire;
import divinerpg.entities.vanilla.wildfire.WildfireRender;
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
    public static final EntityType<DivineArrowEntity> arrow_entity = null;

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

    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<EntityType<?>> e) {
        IForgeRegistry<EntityType<?>> registry = e.getRegistry();

        registerBulletEntity(registry, ItemBulletEntity::new, w -> bullet_item_entity.create(w), "bullet_item_entity");
        registerBulletEntity(registry, BulletEntity::new, w -> bullet_entity.create(w), "bullet_entity");
        registerBulletEntity(registry, DivineArrowEntity::new, w -> arrow_entity.create(w), "arrow_entity");
        registerBulletEntity(registry, FrostFireball::new, w -> frost_shot.create(w), "frost_fireball");
        registerBulletEntity(registry, ScorcherFireball::new, w -> scorcher_fireball.create(w), "scorcher_fireball");

        registry.register(EntityType.Builder.create(EnthralledDramcryx::new, EntityClassification.MONSTER)
                .size(1.35F, 1.75F)
                .setCustomClientFactory((spawnEntity, world) -> new EnthralledDramcryx(world))
                .build("entrhralled_dramcryx").setRegistryName(DivineRPG.MODID, "entrhralled_dramcryx"));
        registry.register(EntityType.Builder.create(Crab::new, EntityClassification.MONSTER)
                .size(1.1F, 0.8F)
                .setCustomClientFactory((spawnEntity, world) -> new Crab(world))
                .build("crab").setRegistryName(DivineRPG.MODID, "crab"));
        registry.register(EntityType.Builder.create(JungleDramcryx::new, EntityClassification.MONSTER)
                .size(1, 1.3F)
                .setCustomClientFactory((spawnEntity, world) -> new JungleDramcryx(world))
                .build("jungle_dramcryx").setRegistryName(DivineRPG.MODID, "jungle_dramcryx"));
        registry.register(EntityType.Builder.create(Frost::new, EntityClassification.MONSTER)
                .size(1, 1)
                .setCustomClientFactory((spawnEntity, world) -> new Frost(world))
                .build("frost").setRegistryName(DivineRPG.MODID, "frost"));
        registry.register(EntityType.Builder.create(Glacon::new, EntityClassification.MONSTER)
                .size(0.8F, 1.4f)
                .setCustomClientFactory((spawnEntity, world) -> new Glacon(world))
                .build("glacon").setRegistryName(DivineRPG.MODID, "glacon"));
        registry.register(EntityType.Builder.create(Rotatick::new, EntityClassification.MONSTER)
                .size(0.85F, 1)
                .setCustomClientFactory((spawnEntity, world) -> new Rotatick(world))
                .build("rotatick").setRegistryName(DivineRPG.MODID, "rotatick"));
        registry.register(EntityType.Builder.create(KingCrab::new, EntityClassification.MONSTER)
                .size(1.8F, 1.7F)
                .setCustomClientFactory((spawnEntity, world) -> new KingCrab(world))
                .build("king_crab").setRegistryName(DivineRPG.MODID, "king_crab"));

        registry.register(EntityType.Builder.<JungleSpider>create(JungleSpider::new, EntityClassification.MONSTER)
                .size(1.4F, 0.9F)
                .setCustomClientFactory((spawnEntity, world) -> new JungleSpider(world))
                .build("jungle_spider").setRegistryName(DivineRPG.MODID, "jungle_spider"));
        registry.register(EntityType.Builder.<HellSpider>create(HellSpider::new, EntityClassification.MONSTER)
                .size(1.4F, 0.9F)
                .immuneToFire()
                .setCustomClientFactory((spawnEntity, world) -> new HellSpider(world))
                .build("hell_spider").setRegistryName(DivineRPG.MODID, "hell_spider"));
        registry.register(EntityType.Builder.<EnderSpider>create(EnderSpider::new, EntityClassification.MONSTER)
                .size(0.5F, 0.55F)
                .setCustomClientFactory((spawnEntity, world) -> new EnderSpider(world))
                .build("ender_spider").setRegistryName(DivineRPG.MODID, "ender_spider"));
        registry.register(EntityType.Builder.<EnderWatcher>create(EnderWatcher::new, EntityClassification.MONSTER)
                .size(0.7F, 0.9F)
                .setCustomClientFactory((spawnEntity, world) -> new EnderWatcher(world))
                .build("ender_watcher").setRegistryName(DivineRPG.MODID, "ender_watcher"));
        registry.register(EntityType.Builder.<EnderTriplets>create(EnderTriplets::new, EntityClassification.MONSTER)
                .size(0.7F, 0.9F)
                .setCustomClientFactory((spawnEntity, world) -> new EnderTriplets(world))
                .build("ender_triplets").setRegistryName(DivineRPG.MODID, "ender_triplets"));
        registry.register(EntityType.Builder.<Scorcher>create(Scorcher::new, EntityClassification.MONSTER)
                .size(1.2F, 2F)
                .immuneToFire()
                .setCustomClientFactory((spawnEntity, world) -> new Scorcher(world))
                .build("scorcher").setRegistryName(DivineRPG.MODID, "scorcher"));
        registry.register(EntityType.Builder.<Wildfire>create(Wildfire::new, EntityClassification.MONSTER)
                .size(0.8F, 2.2F)
                .immuneToFire()
                .setCustomClientFactory((spawnEntity, world) -> new Wildfire(world))
                .build("wildfire").setRegistryName(DivineRPG.MODID, "wildfire"));
        registry.register(EntityType.Builder.create(Grue::new, EntityClassification.MONSTER)
                .size(0.8F, 1.9F)
                .setCustomClientFactory((spawnEntity, world) -> new Grue(world))
                .build("grue").setRegistryName(DivineRPG.MODID, "grue"));
    }


    @OnlyIn(Dist.CLIENT)
    public static void registerRender() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // projectiles
        RenderingRegistry.registerEntityRenderingHandler(BulletEntity.class, BulletEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(FrostFireball.class, BulletEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ScorcherFireball.class, BulletEntityRender::new);

        RenderingRegistry.registerEntityRenderingHandler(DivineArrowEntity.class, DivineEntityRender::new);
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
    }

    private static <T extends Entity> void registerBulletEntity(IForgeRegistry<EntityType<?>> registry, EntityType.IFactory<T> factoryIn,
                                                                Function<World, T> createFunc, String name) {
        registry.register(EntityType.Builder.create(factoryIn, EntityClassification.MISC)
                .size(0.2F, 0.2F)
                .setUpdateInterval(1)
                .setTrackingRange(64)
                // Need to be set for client to see the entity
                .setCustomClientFactory((type, world) -> createFunc.apply(world))
                .build(name)
                .setRegistryName(DivineRPG.MODID, name));
    }
}
