package divinerpg.entities.wildwood.wolf;

import divinerpg.utils.CachedTexture;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.ResourceLocation;

public class MoonWolfRender extends WolfRenderer {

    public MoonWolfRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public ResourceLocation getEntityTexture(WolfEntity entity) {
        return CachedTexture.ENTITIES.getTexture("moon_wolf");
    }
}
