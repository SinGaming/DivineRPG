package divinerpg.entities.wildwood.wolf;

import net.minecraft.client.renderer.entity.model.WolfModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// todo fix
public class MoonWolfModel extends WolfModel<MoonWolf> {
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
    ModelRenderer Ear4;
    ModelRenderer Ear3;
    ModelRenderer spike1;
    ModelRenderer spike2;
    ModelRenderer spike3;
    ModelRenderer spike4;
    ModelRenderer Shape1;
    ModelRenderer Shape2;

    // Just a ref
    ModelRenderer Body;

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void func_225597_a_(MoonWolf entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.func_225597_a_(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        float yaw = netHeadYaw / (180F / (float) Math.PI);
        float pitch = headPitch / (180F / (float) Math.PI);

        this.Nose.rotateAngleY = yaw;
        this.Ear1.rotateAngleX = pitch;
        this.Ear1.rotateAngleY = yaw;
        this.Ear2.rotateAngleX = pitch;
        this.Ear2.rotateAngleY = yaw;
        this.Ear3.rotateAngleX = pitch;
        this.Ear3.rotateAngleY = yaw;
        this.Ear4.rotateAngleX = pitch;
        this.Ear4.rotateAngleY = yaw;
    }
}
