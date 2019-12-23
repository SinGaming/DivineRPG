package divinerpg.entities.base.render;

import divinerpg.entities.base.DivineBoss;

public abstract class DivineBossModel<T extends DivineBoss> extends DivineModel<T> implements IItemModel {
    /**
     * Render as item. Should looks the same as default render method
     *
     * @param scale
     */
    @Override
    public void render(float scale) {
        render(null, 0, 0, 0, 0, 0, scale);
    }
}
