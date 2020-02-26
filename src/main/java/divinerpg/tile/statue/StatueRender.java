package divinerpg.tile.statue;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import divinerpg.tile.base.DivineTileEntityRender;
import divinerpg.utils.CachedTexture;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class StatueRender extends DivineTileEntityRender<TileEntityStatue> {
    public StatueRender(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(TileEntityStatue entity, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        super.render(entity, partialTicks, stack, buffer, combinedLightIn, combinedOverlayIn);

        float rotation = 0;

        // in case when render item
        if (entity.hasWorld()) {
            BlockState state = entity.getWorld().getBlockState(entity.getPos());
            if (state.has(BlockStateProperties.FACING)) {
                Direction direction = state.get(BlockStateProperties.FACING);
                rotation = direction.getHorizontalAngle() * -1;
            }
        }

        IVertexBuilder builder = buffer.getBuffer(RenderType.func_228640_c_(findResource(entity)));

        // Todo check correct texture binding, check correct translation & offset

        StatueConstants.get(entity.getName()).render(stack, builder, combinedLightIn, combinedOverlayIn,
                1, 1, 1, 1);
    }

    //    @Override
//    public void render(TileEntityStatue te, double x, double y, double z, float partialTicks, int destroyStage) {
//        super.render(te, x, y, z, partialTicks, destroyStage);
//        float rotation = 0;
//

    //
//        this.bindTexture(findResource(te));
//
//        GL11.glPushMatrix();
//        GL11.glDisable(GL11.GL_LIGHTING);
//        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.7F, (float) z + 0.5F);
//        GL11.glScaled(0.5, 0.5, 0.5);
//
//        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
//
//        renderModel(te, 0.0625F);
//
//        GL11.glEnable(GL11.GL_LIGHTING);
//        GL11.glPopMatrix();
//    }
//
    private ResourceLocation findResource(TileEntityStatue te) {
        String resourceName = te.getName().replace("_statue", "");
        return CachedTexture.ENTITIES.getTexture(resourceName);
    }
}
