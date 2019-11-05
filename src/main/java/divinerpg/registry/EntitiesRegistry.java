package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.projectiles.CorruptedBulletEntity;
import divinerpg.entities.projectiles.EdenSlicerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = DivineRPG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class EntitiesRegistry {

    @ObjectHolder("corrupted_bullet")
    public static final EntityType<CorruptedBulletEntity> corruptedBullet = null;
    @ObjectHolder("eden_slicer")
    public static final EntityType<EdenSlicerEntity> eden_slicer = null;

    private static final ArrayList<Class> bulletEntityClasses = new ArrayList<>();


    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<EntityType<?>> e) {
        IForgeRegistry<EntityType<?>> registry = e.getRegistry();

        registerBulletEntity(CorruptedBulletEntity.class, registry, CorruptedBulletEntity::new, w -> corruptedBullet.create(w), "corrupted_bullet");
        registerBulletEntity(EdenSlicerEntity.class, registry, EdenSlicerEntity::new, w -> eden_slicer.create(w), "eden_slicer");
    }


    @OnlyIn(Dist.CLIENT)
    public static void registerRender() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // Projectiles
        bulletEntityClasses.forEach(x -> RenderingRegistry.registerEntityRenderingHandler(x,
                factory -> new SpriteRenderer<>(factory, itemRenderer)));
        bulletEntityClasses.clear();
    }

    private static <T extends ProjectileItemEntity> void registerBulletEntity(Class<T> clazz, IForgeRegistry<EntityType<?>> registry, EntityType.IFactory<T> factoryIn,
                                                                              Function<World, T> createFunc, String name) {
        registry.register(EntityType.Builder.create(factoryIn, EntityClassification.MISC)
                .size(0.2F, 0.2F)
                .setUpdateInterval(1)
                .setTrackingRange(64)
                // Need to be set for client to see the entity
                .setCustomClientFactory((type, world) -> createFunc.apply(world))
                .build(name)
                .setRegistryName(DivineRPG.MODID, name));

        bulletEntityClasses.add(clazz);
    }
}
