package divinerpg.entities.vanilla.crab;

import divinerpg.DivineRPG;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CrabRender extends MobRenderer<Crab, CrabModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(DivineRPG.MODID, "textures/entity/crab.png");

    public CrabRender(EntityRendererManager manager) {
        super(manager, new CrabModel(), 0);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Crab entity) {
        return TEXTURE;
    }
}
