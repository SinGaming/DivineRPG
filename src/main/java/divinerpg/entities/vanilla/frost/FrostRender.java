package divinerpg.entities.vanilla.frost;

import divinerpg.DivineRPG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class FrostRender extends MobRenderer<Frost, FrostModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DivineRPG.MODID, "textures/entity/frost.png");

    public FrostRender(EntityRendererManager manager) {
        super(manager, new FrostModel(), 0);
    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Frost entity) {
        return TEXTURE;
    }
}
