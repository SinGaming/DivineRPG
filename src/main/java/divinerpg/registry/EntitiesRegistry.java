package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.projectiles.*;
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
    @ObjectHolder("wildwood_slicer")
    public static final EntityType<WildwoodSlicerEntity> wildwood_slicer = null;
    @ObjectHolder("apalachia_slicer")
    public static final EntityType<ApalachiaSlicerEntity> apalachia_slicer = null;
    @ObjectHolder("skythern_slicer")
    public static final EntityType<SkythernSlicerEntity> skythern_slicer = null;
    @ObjectHolder("mortum_slicer")
    public static final EntityType<MortumSlicerEntity> mortum_slicer = null;
    @ObjectHolder("halite_slicer")
    public static final EntityType<HaliteSlicerEntity> halite_slicer = null;


    @ObjectHolder("eden_dust")
    public static final EntityType<EdenDustEntity> eden_dust = null;
    @ObjectHolder("wildwood_dust")
    public static final EntityType<WildwoodDustEntity> wildwood_dust = null;
    @ObjectHolder("mortum_dust")
    public static final EntityType<MortumDustEntity> mortum_dust = null;
    @ObjectHolder("apalachia_dust")
    public static EntityType<ApalachiaDustEntity> apalachia_dust = null;
    @ObjectHolder("skythern_dust")
    public static EntityType<SkythernDustEntity> skythern_dust = null;


    private static final ArrayList<Class> bulletEntityClasses = new ArrayList<>();

    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<EntityType<?>> e) {
        IForgeRegistry<EntityType<?>> registry = e.getRegistry();

        registerBulletEntity(CorruptedBulletEntity.class, registry, CorruptedBulletEntity::new, w -> corruptedBullet.create(w), "corrupted_bullet");

        registerBulletEntity(EdenSlicerEntity.class, registry, EdenSlicerEntity::new, w -> eden_slicer.create(w), "eden_slicer");
        registerBulletEntity(WildwoodSlicerEntity.class, registry, WildwoodSlicerEntity::new, w -> wildwood_slicer.create(w), "wildwood_slicer");
        registerBulletEntity(ApalachiaSlicerEntity.class, registry, ApalachiaSlicerEntity::new, w -> apalachia_slicer.create(w), "apalachia_slicer");
        registerBulletEntity(SkythernSlicerEntity.class, registry, SkythernSlicerEntity::new, w -> skythern_slicer.create(w), "skythern_slicer");
        registerBulletEntity(MortumSlicerEntity.class, registry, MortumSlicerEntity::new, w -> mortum_slicer.create(w), "mortum_slicer");
        registerBulletEntity(HaliteSlicerEntity.class, registry, HaliteSlicerEntity::new, w -> halite_slicer.create(w), "halite_slicer");

        registerBulletEntity(EdenDustEntity.class, registry, EdenDustEntity::new, w -> eden_dust.create(w), "eden_dust");
        registerBulletEntity(WildwoodDustEntity.class, registry, WildwoodDustEntity::new, w -> wildwood_dust.create(w), "wildwood_dust");
        registerBulletEntity(ApalachiaDustEntity.class, registry, ApalachiaDustEntity::new, w -> apalachia_dust.create(w), "apalachia_dust");
        registerBulletEntity(SkythernDustEntity.class, registry, SkythernDustEntity::new, w -> skythern_dust.create(w), "skythern_dust");
        registerBulletEntity(MortumDustEntity.class, registry, MortumDustEntity::new, w -> mortum_dust.create(w), "mortum_dust");

        //registerBulletEntity(HaliteSlicerEntity.class, registry, HaliteSlicerEntity::new, w -> halite_dust.create(w), "halite_dust");
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
