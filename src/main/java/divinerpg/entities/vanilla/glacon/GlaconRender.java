package divinerpg.entities.vanilla.glacon;

import divinerpg.DivineRPG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class GlaconRender extends MobRenderer<Glacon, GlaconModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DivineRPG.MODID, "textures/entity/glacon.png");

    public GlaconRender(EntityRendererManager manager) {
        super(manager, new GlaconModel(), 0);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Glacon entity) {
        return TEXTURE;
    }
}
