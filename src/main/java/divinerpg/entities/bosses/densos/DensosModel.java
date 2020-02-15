package divinerpg.entities.bosses.densos;

import com.mojang.blaze3d.platform.GlStateManager;
import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineBossModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

// todo check model eyes
public class DensosModel extends DivineBossModel<DivineBoss> implements IHasArm {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer head4;

    public DensosModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-11.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 0.0F, 0.0F, 0.0F);
        this.rightarm = new ModelRenderer(this, 40, 4);
        this.rightarm.addBox(-3.0F, -2.0F, -2.0F, 4, 18, 4);
        this.rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightarm.setTextureSize(64, 32);
        this.rightarm.mirror = true;
        this.setRotation(this.rightarm, 0.0F, 0.0F, 0.0F);
        this.leftarm = new ModelRenderer(this, 40, 1);
        this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 18, 4);
        this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftarm.setTextureSize(64, 32);
        this.leftarm.mirror = true;
        this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
        this.rightleg = new ModelRenderer(this, 0, 16);
        this.rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.rightleg.setTextureSize(64, 32);
        this.rightleg.mirror = true;
        this.setRotation(this.rightleg, 0.0F, 0.0F, 0.0F);
        this.leftleg = new ModelRenderer(this, 0, 16);
        this.leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
        this.leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.leftleg.setTextureSize(64, 32);
        this.leftleg.mirror = true;
        this.setRotation(this.leftleg, 0.0F, 0.0F, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.head1.setRotationPoint(1.466667F, -8.0F, -4.0F);
        this.head1.setTextureSize(64, 32);
        this.head1.mirror = true;
        this.setRotation(this.head1, 0.0F, 0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.head2.setRotationPoint(6.0F, -16.0F, -3.0F);
        this.head2.setTextureSize(64, 32);
        this.head2.mirror = true;
        this.setRotation(this.head2, 0.0F, 0.0F, 0.0F);
        this.head3 = new ModelRenderer(this, 0, 0);
        this.head3.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.head3.setRotationPoint(-15.0F, -16.0F, -3.0F);
        this.head3.setTextureSize(64, 32);
        this.head3.mirror = true;
        this.setRotation(this.head3, 0.0F, 0.0F, 0.0F);
        this.head4 = new ModelRenderer(this, 0, 0);
        this.head4.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.head4.setRotationPoint(-4.0F, -16.0F, -3.0F);
        this.head4.setTextureSize(64, 32);
        this.head4.mirror = true;
        this.setRotation(this.head4, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void setRotationAngles(DivineBoss entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.head.rotateAngleY =
                this.head1.rotateAngleY =
                        this.head2.rotateAngleY =
                                this.head3.rotateAngleY =
                                        this.head4.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

        this.head.rotateAngleX =
                this.head1.rotateAngleX =
                        this.head2.rotateAngleX =
                                this.head3.rotateAngleX =
                                        this.head4.rotateAngleX = headPitch / (180F / (float) Math.PI);

        this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightarm.rotateAngleZ =
                this.leftarm.rotateAngleZ =
                        this.rightleg.rotateAngleY =
                                this.leftleg.rotateAngleY = 0.0F;

        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
    }

    @Override
    public void postRenderArm(float scale, HandSide side) {

        switch (side) {
            case RIGHT:
                rightarm.postRender(scale);

                GlStateManager.translatef(-0.0625F, 0.4375F, 0.0625F);
                break;

            case LEFT:
                leftarm.postRender(scale);
                break;
        }
    }
}
