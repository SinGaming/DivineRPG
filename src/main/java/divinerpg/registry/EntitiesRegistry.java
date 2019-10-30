package divinerpg.registry;

import divinerpg.DivineRPG;
import divinerpg.entities.projectiles.CorruptedBulletEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = DivineRPG.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(DivineRPG.MODID)
public class EntitiesRegistry {

    @ObjectHolder("corrupted_bullet")
    public static final EntityType<CorruptedBulletEntity> corruptedBullet = null;


    @SubscribeEvent
    public static void registerRenders(final RegistryEvent.Register<EntityType<?>> e) {
        IForgeRegistry<EntityType<?>> registry = e.getRegistry();

        registry.register(EntityType.Builder.<CorruptedBulletEntity>create(CorruptedBulletEntity::new, EntityClassification.MISC)
                .size(0.2F, 0.2F)
                .setUpdateInterval(1)
                .setTrackingRange(64)
                .setCustomClientFactory((type, world) -> corruptedBullet.create(world))
                .build("corrupted_bullet")
                .setRegistryName(DivineRPG.MODID, "corrupted_bullet"));
    }


    @OnlyIn(Dist.CLIENT)
    public static void registerRender() {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        // TODO no rendering on client. WHY!?!?!?

        // Projectiles
        RenderingRegistry.registerEntityRenderingHandler(CorruptedBulletEntity.class,
                factory -> new SpriteRenderer<>(factory, itemRenderer));
    }
}
