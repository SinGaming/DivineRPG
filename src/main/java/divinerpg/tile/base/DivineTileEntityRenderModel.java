package divinerpg.tile.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import divinerpg.utils.ReflectionHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.function.Function;

public class DivineTileEntityRenderModel<T extends TileEntity> extends Model {

    protected LazyValue<List<ModelRenderer>> parts;

    public DivineTileEntityRenderModel(Function<ResourceLocation, RenderType> renderTypeIn) {
        super(renderTypeIn);

        parts = new LazyValue<>(() -> ReflectionHelper.getDeclaredFieldsValues(this, ModelRenderer.class));
    }

    protected void render(MatrixStack stack, IVertexBuilder builder, int packedLightIn,
                          int packedOverlayIn, float red, float blue, float green,
                          float alpha) {
        parts.getValue().forEach(x -> x.func_228309_a_(stack, builder, packedLightIn, packedOverlayIn, red, blue, green, alpha));
    }

    @Override
    public final void func_225598_a_(MatrixStack stack, IVertexBuilder builder, int packedLightIn,
                                     int packedOverlayIn, float red, float blue, float green,
                                     float alpha) {
        render(stack, builder, packedLightIn, packedOverlayIn, red, blue, green, alpha);
    }


    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
