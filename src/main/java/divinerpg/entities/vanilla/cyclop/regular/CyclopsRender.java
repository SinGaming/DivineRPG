package divinerpg.entities.vanilla.cyclop.regular;

import divinerpg.entities.base.render.DivineBipedModel;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CyclopsRender extends DivineRender<Cyclops, DivineBipedModel<Cyclops>> {
    private final ResourceLocation angry;

    public CyclopsRender(EntityRendererManager manager) {
        super(manager, new DivineBipedModel<>(), 0, "cyclops", 2F);
        angry = createFromName("angry_cyclops");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(Cyclops entity) {
        if (entity.isAggressive())
            return angry;

        return super.getEntityTexture(entity);
    }
}
