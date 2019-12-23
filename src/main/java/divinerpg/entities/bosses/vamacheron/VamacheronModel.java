package divinerpg.entities.bosses.vamacheron;

import divinerpg.entities.base.render.DivineBossModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

public class VamacheronModel extends DivineBossModel<Vamacheron> {
    RendererModel head;
    RendererModel body;
    RendererModel leg1;
    RendererModel leg2;
    RendererModel leg3;
    RendererModel leg4;
    RendererModel horn1;
    RendererModel horn2;
    RendererModel Horn;
    RendererModel Shape1;
    RendererModel head1;
    RendererModel horn3;
    RendererModel horn4;
    RendererModel Horn1;
    RendererModel Shape2;

    public VamacheronModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new RendererModel(this, 0, 0);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6);
        this.head.setRotationPoint(-6.0F, 4.0F, -8.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new RendererModel(this, 18, 4);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 8, 18, 10);
        this.body.setRotationPoint(2.0F, 5.0F, 2.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, ((float) Math.PI / 2F), 0.0F, 0.0F);
        this.leg1 = new RendererModel(this, 0, 16);
        this.leg1.addBox(-3.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg1.setRotationPoint(-2.0F, 12.0F, 7.0F);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
        this.leg2 = new RendererModel(this, 0, 16);
        this.leg2.addBox(-1.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leg2.setRotationPoint(2.0F, 12.0F, 7.0F);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
        this.leg3 = new RendererModel(this, 0, 16);
        this.leg3.addBox(-3.0F, 0.0F, -3.0F, 4, 12, 4);
        this.leg3.setRotationPoint(-2.0F, 12.0F, -5.0F);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
        this.leg4 = new RendererModel(this, 0, 16);
        this.leg4.addBox(-1.0F, 0.0F, -3.0F, 4, 12, 4);
        this.leg4.setRotationPoint(2.0F, 12.0F, -5.0F);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
        this.horn1 = new RendererModel(this, 54, 0);
        this.horn1.addBox(-4.0F, -5.0F, -4.0F, 1, 3, 3);
        this.horn1.setRotationPoint(-6.0F, 2.0F, -8.0F);
        this.horn1.setTextureSize(64, 32);
        this.horn1.mirror = true;
        this.setRotation(this.horn1, 0.0F, 0.0F, 0.0F);
        this.horn2 = new RendererModel(this, 55, 0);
        this.horn2.addBox(3.0F, -5.0F, -4.0F, 1, 3, 3);
        this.horn2.setRotationPoint(-6.0F, 2.0F, -8.0F);
        this.horn2.setTextureSize(64, 32);
        this.horn2.mirror = true;
        this.setRotation(this.horn2, 0.0F, 0.0F, 0.0F);
        this.Horn = new RendererModel(this, 55, 7);
        this.Horn.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2);
        this.Horn.setRotationPoint(-7.0F, 0.0F, -20.0F);
        this.Horn.setTextureSize(64, 32);
        this.Horn.mirror = true;
        this.setRotation(this.Horn, 1.22173F, 0.0F, 0.0F);
        this.Shape1 = new RendererModel(this, 9, 0);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
        this.Shape1.setRotationPoint(-7.0F, 3.0F, -15.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.head1 = new RendererModel(this, 0, 0);
        this.head1.addBox(0.0F, 0.0F, 0.0F, 8, 8, 6);
        this.head1.setRotationPoint(2.0F, 0.0F, -14.0F);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.horn3 = new RendererModel(this, 55, 0);
        this.horn3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3);
        this.horn3.setRotationPoint(2.0F, -3.0F, -12.0F);
        this.horn3.setTextureSize(64, 32);
        this.horn3.mirror = true;
        this.setRotation(this.horn3, 0.0F, 0.0F, 0.0F);
        this.horn4 = new RendererModel(this, 55, 0);
        this.horn4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3);
        this.horn4.setRotationPoint(9.0F, -3.0F, -12.0F);
        this.horn4.setTextureSize(64, 32);
        this.horn4.mirror = true;
        this.setRotation(this.horn4, 0.0F, 0.0F, 0.0F);
        this.Horn1 = new RendererModel(this, 55, 7);
        this.Horn1.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2);
        this.Horn1.setRotationPoint(5.0F, 0.0F, -20.0F);
        this.Horn1.setTextureSize(64, 32);
        this.Horn1.mirror = true;
        this.setRotation(this.Horn1, 1.22173F, 0.0F, 0.0F);
        this.Shape2 = new RendererModel(this, 9, 0);
        this.Shape2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
        this.Shape2.setRotationPoint(5.0F, 3.0F, -15.0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void setRotationAngles(Vamacheron entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.body.rotateAngleX = ((float) Math.PI / 2F);
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }
}
