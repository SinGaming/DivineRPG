package divinerpg.events;

import divinerpg.DivineRPG;
import divinerpg.api.DivineAPI;
import divinerpg.api.arcana.ArcanaProvider;
import divinerpg.api.arcana.IArcana;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class ArcanaEvents {

    /**
     * Attaching arcana capability
     */
    @SubscribeEvent
    public static void attachCaps(final AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof PlayerEntity)
            e.addCapability(new ResourceLocation(DivineRPG.MODID, "acrana_cap"), new ArcanaProvider());
    }

    /**
     * Clear arcana. Nedded because of static IArcana field,
     * Creating new world in a singleplayer will use same instance object
     */
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedOutEvent e) {
        IArcana arcana = DivineAPI.getPlayerArcana(e.getPlayer());
        arcana.setArcana(0, true);
    }

    /**
     * Refilling 1 point every tick
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        IArcana arcana = DivineAPI.getPlayerArcana(e.player);
        arcana.fill(1);
    }
}
