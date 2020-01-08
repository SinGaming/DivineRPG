package divinerpg.events;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Manages with special armor abilities
 */
@Mod.EventBusSubscriber()
public class ArmorAbilityEventHandler {

    private static <T extends Event> void handleForAll(T event) {
        FullArmorEventHandler.getPlayerMap().values().forEach(x -> x.Handle(event));
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        handleForAll(e);
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent e) {
        handleForAll(e);
    }

    @SubscribeEvent
    public static void onPLayerJump(LivingEvent.LivingJumpEvent event) {
        handleForAll(event);
    }
}
