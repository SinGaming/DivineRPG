package divinerpg.entities.base.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public interface IEntityRenderDeobf<T extends Entity> {

    /**
     * Renders object
     */
    void render(MatrixStack stack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
                float red, float green, float blue, float alpha);

    void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);


    Iterable<ModelRenderer> getBodyParts();

    Iterable<ModelRenderer> getHeadParts();

    default void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
