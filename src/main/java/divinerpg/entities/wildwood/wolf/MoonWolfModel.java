package divinerpg.entities.wildwood.wolf;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.WolfModel;

public class MoonWolfModel extends WolfModel<MoonWolf> {
    RendererModel Ear1;
    RendererModel Ear2;
    RendererModel Nose;
    RendererModel Ear4;
    RendererModel Ear3;
    RendererModel spike1;
    RendererModel spike2;
    RendererModel spike3;
    RendererModel spike4;
    RendererModel Shape1;
    RendererModel Shape2;

    // Just a ref
    RendererModel Body;

    protected void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(MoonWolf entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

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
