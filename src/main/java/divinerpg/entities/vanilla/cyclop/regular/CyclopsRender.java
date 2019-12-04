package divinerpg.entities.vanilla.cyclop.regular;

import divinerpg.entities.base.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CyclopsRender extends DivineRender<Cyclops, BipedModel<Cyclops>> {
    private final ResourceLocation angry;

    public CyclopsRender(EntityRendererManager manager) {
        super(manager, new BipedModel<>(), 0, "cyclops", 2F);
        angry = createFromName("angry_cyclops");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Cyclops entity) {
        if (entity.isAggressive())
            return angry;

        return super.getEntityTexture(entity);
    }
}
