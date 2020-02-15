package divinerpg.entities.vanilla.pig;

import divinerpg.entities.base.render.DivineRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.PigModel;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class HellPigRender extends DivineRender<HellPig, PigModel<HellPig>> {
    public HellPigRender(EntityRendererManager manager) {
        super(manager, new PigModel<>(), 0, "");
    }


    @Nullable
    @Override
    public ResourceLocation getEntityTexture(HellPig entity) {
        if (entity.isTamed())
            return CachedTexture.ENTITIES.getTexture("tame_hell_pig");

        return entity.isAngry()
                ? CachedTexture.ENTITIES.getTexture("mad_hell_pig")
                : CachedTexture.ENTITIES.getTexture("hell_pig");
    }
}
