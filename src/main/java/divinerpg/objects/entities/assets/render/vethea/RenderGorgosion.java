package divinerpg.objects.entities.assets.render.vethea;

import javax.annotation.Nullable;

import divinerpg.objects.entities.assets.model.vethea.ModelGorgosion;
import divinerpg.objects.entities.entity.vethea.EntityGorgosion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGorgosion extends RenderLiving<EntityGorgosion> {
	
	public static final IRenderFactory FACTORY = new Factory();
	ResourceLocation texture = new ResourceLocation("divinerpg:textures/entity/gorgosion.png");
	private final ModelGorgosion modelEntity;
    
	public RenderGorgosion(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
        super(rendermanagerIn, new ModelGorgosion(), 1F);
        modelEntity = (ModelGorgosion) super.mainModel;

    }


	@Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGorgosion entity) {
        return texture;
    }

	 public static class Factory implements IRenderFactory<EntityGorgosion> {

	        @Override
	        public Render<? super EntityGorgosion> createRenderFor(RenderManager manager) {
	            return new RenderGorgosion(manager, new ModelGorgosion(), 1F);
	        }
	    }

	}