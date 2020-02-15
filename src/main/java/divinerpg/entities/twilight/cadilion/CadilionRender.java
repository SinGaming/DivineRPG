package divinerpg.entities.twilight.cadilion;

import divinerpg.entities.base.DivineMonster;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class CadilionRender extends DivineRender<DivineMonster, CadilionModel> {
    public CadilionRender(EntityRendererManager manager) {
        super(manager, new CadilionModel(), 0, "");
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(DivineMonster entity) {
        return textureBasedOnType(entity);
    }
}
