package divinerpg.objects.entities.assets.render.vethea;

import javax.annotation.Nullable;

import divinerpg.objects.entities.assets.model.vethea.ModelQuadro;
import divinerpg.objects.entities.entity.vethea.EntityQuadro;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderQuadro extends RenderLiving<EntityQuadro> {
	
	public static final IRenderFactory FACTORY = new Factory();
	ResourceLocation texture = new ResourceLocation("divinerpg:textures/entity/quadro.png");
	private final ModelQuadro modelEntity;
    
	public RenderQuadro(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, new ModelQuadro(), 1F);
        modelEntity = (ModelQuadro) super.mainModel;

    }


	@Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityQuadro entity) {
        return texture;
    }

	 public static class Factory implements IRenderFactory<EntityQuadro> {

	        @Override
	        public Render<? super EntityQuadro> createRenderFor(RenderManager manager) {
	            return new RenderQuadro(manager, new ModelQuadro(), 1F);
	        }
	    }

	}