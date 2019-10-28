package divinerpg.api.armor;

import net.minecraftforge.eventbus.api.Event;

@FunctionalInterface
public interface IPowerAbility<T extends Event> {
    /**
     * Called on event when player is full equipped.
     *
     * @param event - Forge event
     */
    void handleAbility(T event);
}
