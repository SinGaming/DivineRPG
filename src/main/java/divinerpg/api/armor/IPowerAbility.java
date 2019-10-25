package divinerpg.api.armor;

import net.minecraftforge.eventbus.api.Event;

@FunctionalInterface
public interface IPowerAbility {
    /**
     * Called on event when player is full equipped.
     *
     * @param event - Forge event
     */
    <T extends Event> void handleAbility(T event);
}
