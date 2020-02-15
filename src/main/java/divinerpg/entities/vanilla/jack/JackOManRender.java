package divinerpg.entities.vanilla.jack;

import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class JackOManRender extends DivineRender<JackOMan, JackOManModel> {
    // todo redraw
    private static final ResourceLocation WITHER_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");

    public JackOManRender(EntityRendererManager manager) {
        super(manager, new JackOManModel(), 0, "");

        this.addLayer(new HeadLayer<>(this));
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(JackOMan entity) {
        return WITHER_SKELETON_TEXTURES;
    }
}
