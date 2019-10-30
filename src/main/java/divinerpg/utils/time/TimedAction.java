package divinerpg.utils.time;

/**
 * Using server ticks to check for timed actions
 */
public class TimedAction {
    /**
     * Contains current ticks ammoConsumeCount
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
     * Changing ticks ammoConsumeCount
     *
     * @param newTicksCount - new ticks ammoConsumeCount
     */
    public void updateTicksCount(int newTicksCount) {
        ticksCount = newTicksCount;
    }
}
