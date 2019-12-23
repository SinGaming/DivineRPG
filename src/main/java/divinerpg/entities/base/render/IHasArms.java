package divinerpg.entities.base.render;

import net.minecraft.util.HandSide;

public interface IHasArms {
    /**
     * Gets arms amount
     *
     * @return
     */
    int size(HandSide side);

    /**
     * renders arm by index
     *
     * @param index
     * @param scale
     * @param side
     */
    void postRenderArm(int index, float scale, HandSide side);
}
