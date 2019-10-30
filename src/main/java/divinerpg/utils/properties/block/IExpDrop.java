package divinerpg.utils.properties.block;

import java.util.Random;

@FunctionalInterface
public interface IExpDrop {

    /**
     * Calculates amount of exp drop
     *
     * @param random  - current random var
     * @param fortune - applied fortune
     * @param silk    - applied silk
     * @return amount od dropped experience
     */
    int getExp(Random random, int fortune, int silk);
}
