package divinerpg.utils.time;

/**
 * Using server ticks to check for timed actions
 */
public class TimedAction {
    /**
     * Contains current ticks count
     */
    private int ticksCount;

    /**
     * Ticks counter
     */
    private int completedTicks = 0;

    public TimedAction(int ticks) {
        this.ticksCount = ticks;
    }

    /**
     * Detect if we can execute timed action
     */
    public boolean tryExecute() {
        completedTicks++;

        if (completedTicks < ticksCount) {
            return false;
        }

        completedTicks = 0;
        return true;
    }

    /**
     * Changing ticks count
     *
     * @param newTicksCount - new ticks count
     */
    public void updateTicksCount(int newTicksCount) {
        ticksCount = newTicksCount;
    }
}
