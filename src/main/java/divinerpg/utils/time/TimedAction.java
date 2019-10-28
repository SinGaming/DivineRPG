package divinerpg.utils.time;

import net.minecraft.world.World;

/**
 * Using server ticks to check for timed actions
 */
public class TimedAction {
    private final World world;
    private final int ticksCount;

    // furute ticks count, when action can be executed
    private long future;

    public TimedAction(World world, int ticks) {
        this.ticksCount = ticks;
        this.world = world;

        future = world.getGameTime() + ticksCount;
    }

    /**
     * Detect if we can execute timed action
     */
    public boolean tryExecute() {
        long serverTime = world.getGameTime();
        if (future > serverTime)
            return false;

        future = serverTime + ticksCount;
        return true;
    }
}
