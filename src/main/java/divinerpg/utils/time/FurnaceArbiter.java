package divinerpg.utils.time;

public class FurnaceArbiter {
    private int ticks;
    private int totalTicks;
    private boolean isBurning;

    public boolean isBurning() {
        return isBurning;
    }

    public void setReceipe(int total) {
        totalTicks = total;
        isBurning = true;
    }

    public boolean heat() {
        isBurning = true;

        ticks++;
        if (ticks >= totalTicks) {
            ticks = 0;
            return true;
        }

        return false;
    }

    public boolean cooldown() {
        isBurning = false;
        int newVal = Math.max(0, ticks - 1);
        if (newVal == ticks)
            return false;

        ticks = newVal;
        return true;
    }
}
