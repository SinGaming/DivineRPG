package divinerpg.objects.entities.assets.render.vethea;

import javax.annotation.Nullable;

import divinerpg.objects.entities.assets.model.vethea.ModelAcidHag;
import divinerpg.objects.entities.entity.vethea.EntityAcidHag;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderAcidHag extends RenderLiving<EntityAcidHag> {
	
	public static final IRenderFactory FACTORY = new Factory();
	ResourceLocation texture = new ResourceLocation("divinerpg:textures/entity/acid_hag.png");
	private final ModelAcidHag modelEntity;
    
	public RenderAcidHag(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, new ModelAcidHag(), 1F);
        modelEntity = (ModelAcidHag) super.mainModel;

    }


	@Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAcidHag entity) {
        return texture;
    }

	 public static class Factory implements IRenderFactory<EntityAcidHag> {

	        @Override
	        public Render<? super EntityAcidHag> createRenderFor(RenderManager manager) {
	            return new RenderAcidHag(manager, new ModelAcidHag(), 1F);
	        }
	    }

	}