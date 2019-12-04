package divinerpg.entities.vanilla.miner;

import net.minecraft.client.renderer.entity.model.AbstractZombieModel;
import net.minecraft.entity.monster.MonsterEntity;

public class MinerModel<T extends MonsterEntity> extends AbstractZombieModel<T> {
    public MinerModel() {
        this(0.0F, false);
    }

    public MinerModel(float modelSize, boolean isSmall) {
        super(modelSize, 0.0F, 64, isSmall ? 32 : 64);
    }

    protected MinerModel(float size, float shadow, int textureWidthIn, int textureHeightIn) {
        super(size, shadow, textureWidthIn, textureHeightIn);
    }

    public boolean func_212850_a_(T entity) {
        return entity.isAggressive();
    }
}
