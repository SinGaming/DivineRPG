package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.projectiles.Bullet.BulletEntity;
import divinerpg.entities.projectiles.Bullet.BulletEntityRender;
import divinerpg.entities.projectiles.DivineArrow.DivineArrowEntity;
import divinerpg.entities.projectiles.DivineArrow.DivineEntityRender;
import divinerpg.entities.projectiles.ItemBulletEntity;
import divinerpg.entities.vanilla.crab.Crab;
import divinerpg.entities.vanilla.crab.CrabRender;
import divinerpg.entities.vanilla.dramcryx.enthralled.EnthralledDramcryx;
import divinerpg.entities.vanilla.dramcryx.enthralled.EnthralledDramcryxRender;
import divinerpg.entities.vanilla.dramcryx.jungle.JungleDramcryx;
import divinerpg.entities.vanilla.dramcryx.jungle.JungleDramcryxRender;
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
    @ObjectHolder("entrhralled_dramcryx")
    public static EntityType<EnthralledDramcryx> entrhralled_dramcryx = null;
    @ObjectHolder("crab")
    public static EntityType<Crab> crab = null;
    @ObjectHolder("jungle_dramcryx")
    public static EntityType<JungleDramcryx> jungle_dramcryx = null;

    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<EntityType<?>> e) {
        IForgeRegistry<EntityType<?>> registry = e.getRegistry();

        registerBulletEntity(registry, ItemBulletEntity::new, w -> bullet_item_entity.create(w), "bullet_item_entity");
        registerBulletEntity(registry, BulletEntity::new, w -> bullet_entity.create(w), "bullet_entity");
        registerBulletEntity(registry, DivineArrowEntity::new, w -> arrow_entity.create(w), "arrow_entity");

        registry.register(EntityType.Builder.<EnthralledDramcryx>create(EnthralledDramcryx::new, EntityClassification.MONSTER)
                .size(1.35F, 1.75F)
                .setCustomClientFactory((spawnEntity, world) -> new EnthralledDramcryx(world))
                .build("entrhralled_dramcryx").setRegistryName(DivineRPG.MODID, "entrhralled_dramcryx"));
        registry.register(EntityType.Builder.<Crab>create(Crab::new, EntityClassification.MONSTER)
                .size(1.1F, 0.8F)
                .setCustomClientFactory((spawnEntity, world) -> new Crab(world))
                .build("crab").setRegistryName(DivineRPG.MODID, "crab"));
        registry.register(EntityType.Builder.<JungleDramcryx>create(JungleDramcryx::new, EntityClassification.MONSTER)
                .size(1, 1.3F)
                .setCustomClientFactory((spawnEntity, world) -> new JungleDramcryx(jungle_dramcryx, world))
                .build("jungle_dramcryx").setRegistryName(DivineRPG.MODID, "jungle_dramcryx"));
    }


    @OnlyIn(Dist.CLIENT)
    public static void registerRender() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // projectiles
        RenderingRegistry.registerEntityRenderingHandler(BulletEntity.class, BulletEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(DivineArrowEntity.class, DivineEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(ItemBulletEntity.class, factory -> new SpriteRenderer<>(factory, itemRenderer));
        RenderingRegistry.registerEntityRenderingHandler(EnthralledDramcryx.class, EnthralledDramcryxRender::new);
        RenderingRegistry.registerEntityRenderingHandler(JungleDramcryx.class, JungleDramcryxRender::new);
        RenderingRegistry.registerEntityRenderingHandler(Crab.class, CrabRender::new);
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
