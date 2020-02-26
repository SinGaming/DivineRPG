package divinerpg.tile.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.LazyValue;

import java.util.List;

public class DivineTileEntityRender<T extends TileEntity> extends TileEntityRenderer<T> {

    protected LazyValue<List<ModelRenderer>> parts;

    public DivineTileEntityRender(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    public void render(T entity, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int combinedLightIn,
                       int combinedOverlayIn) {

    }

    @Override
    public final void func_225616_a_(T p_225616_1_, float p_225616_2_, MatrixStack p_225616_3_,
                                     IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {

    }
}
