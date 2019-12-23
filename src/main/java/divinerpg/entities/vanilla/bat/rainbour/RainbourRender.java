package divinerpg.entities.vanilla.bat.rainbour;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RainbourRender extends DivineRender<Rainbour, RainbourModel> {
    private final ResourceLocation angry;

    public RainbourRender(EntityRendererManager manager) {
        super(manager, new RainbourModel(), 0, "rainbour");

        angry = createFromName("angry_rainbour");
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Rainbour entity) {
        return entity.isAggressive()
                ? angry
                : super.getEntityTexture(entity);
    }
}
