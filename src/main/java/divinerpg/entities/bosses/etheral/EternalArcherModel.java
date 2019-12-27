package divinerpg.entities.bosses.etheral;

import divinerpg.entities.base.DivineBoss;
import divinerpg.entities.base.render.DivineBossModel;
import divinerpg.entities.base.render.IHasArms;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.List;

public class EternalArcherModel extends DivineBossModel<DivineBoss> implements IHasArms {
    public RendererModel head;
    public RendererModel shoulder6;
    public RendererModel[] armsRight = new RendererModel[3];
    public RendererModel[] armsLeft = new RendererModel[3];
    public RendererModel rightleg;
    public RendererModel leftleg;
    public RendererModel body;
    public RendererModel shoulder1;
    public RendererModel shoulder4;
    public RendererModel shoulder3;
    public RendererModel shoulder2;
    public RendererModel shoulder5;

    public EternalArcherModel() {
        textureWidth = 128;
        textureHeight = 32;

        head = new RendererModel(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0F, 0F, 0F);
        head.setTextureSize(128, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        shoulder6 = new RendererModel(this, 41, 1);
        shoulder6.addBox(-4F, 0F, -2F, 10, 3, 4);
        shoulder6.setRotationPoint(18F, -6F, 0F);
        shoulder6.setTextureSize(128, 32);
        shoulder6.mirror = true;
        setRotation(shoulder6, 0F, 0F, 0F);
        armsRight[0] = new RendererModel(this, 110, 16);
        armsRight[0].addBox(-3F, -2 - 0.5F, -2F, 4, 12, 4);
        armsRight[0].setRotationPoint(-21F, -1F, 0F);
        armsRight[0].setTextureSize(128, 32);
        armsRight[0].mirror = true;
        setRotation(armsRight[0], 0F, 0F, 0F);
        armsLeft[2] = new RendererModel(this, 110, 16);
        armsLeft[2].addBox(-1F, -2 - 0.5F, -2F, 4, 12, 4);
        armsLeft[2].setRotationPoint(21F, -1F, 0F);
        armsLeft[2].setTextureSize(128, 32);
        armsLeft[2].mirror = true;
        setRotation(armsLeft[2], 0F, 0F, 0F);
        rightleg = new RendererModel(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-3F, 12F, 0F);
        rightleg.setTextureSize(128, 32);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new RendererModel(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(3F, 12F, 0F);
        leftleg.setTextureSize(128, 32);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);
        armsRight[2] = new RendererModel(this, 71, 16);
        armsRight[2].addBox(-3F, -2 - 0.5F, -2F, 4, 12, 4);
        armsRight[2].setRotationPoint(-7F, 5F, 0F);
        armsRight[2].setTextureSize(128, 32);
        armsRight[2].mirror = true;
        setRotation(armsRight[2], 0F, 0F, 0F);
        armsLeft[0] = new RendererModel(this, 71, 16);
        armsLeft[0].addBox(-1F, -2 - 0.5F, -2F, 4, 12, 4);
        armsLeft[0].setRotationPoint(7F, 5F, 0F);
        armsLeft[0].setTextureSize(128, 32);
        armsLeft[0].mirror = true;
        setRotation(armsLeft[0], 0F, 0F, 0F);
        armsLeft[1] = new RendererModel(this, 91, 16);
        armsLeft[1].addBox(-1F, -2 - 0.5F, -2F, 4, 12, 4);
        armsLeft[1].setRotationPoint(14F, 2F, 0F);
        armsLeft[1].setTextureSize(128, 32);
        armsLeft[1].mirror = true;
        setRotation(armsLeft[1], 0F, 0F, 0F);
        armsRight[1] = new RendererModel(this, 91, 16);
        armsRight[1].addBox(-3F, -2 - 0.5F, -2F, 4, 12, 4);
        armsRight[1].setRotationPoint(-14F, 2F, 0F);
        armsRight[1].setTextureSize(128, 32);
        armsRight[1].mirror = true;
        setRotation(armsRight[1], 0F, 0F, 0F);
        body = new RendererModel(this, 46, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(128, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        shoulder1 = new RendererModel(this, 41, 1);
        shoulder1.addBox(-4F, 0F, -2F, 10, 3, 4);
        shoulder1.setRotationPoint(-20F, -6F, 0F);
        shoulder1.setTextureSize(128, 32);
        shoulder1.mirror = true;
        setRotation(shoulder1, 0F, 0F, 0F);
        shoulder4 = new RendererModel(this, 19, 24);
        shoulder4.addBox(-4F, 0F, -2F, 6, 3, 4);
        shoulder4.setRotationPoint(8F, 0F, 0F);
        shoulder4.setTextureSize(128, 32);
        shoulder4.mirror = true;
        setRotation(shoulder4, 0F, 0F, 0F);
        shoulder3 = new RendererModel(this, 19, 24);
        shoulder3.addBox(-4F, 0F, -2F, 6, 3, 4);
        shoulder3.setRotationPoint(-6F, 0F, 0F);
        shoulder3.setTextureSize(128, 32);
        shoulder3.mirror = true;
        setRotation(shoulder3, 0F, 0F, 0F);
        shoulder2 = new RendererModel(this, 17, 16);
        shoulder2.addBox(-4F, 0F, -2F, 10, 3, 4);
        shoulder2.setRotationPoint(-13F, -3F, 0F);
        shoulder2.setTextureSize(128, 32);
        shoulder2.mirror = true;
        setRotation(shoulder2, 0F, 0F, 0F);
        shoulder5 = new RendererModel(this, 17, 16);
        shoulder5.addBox(-4F, 0F, -2F, 10, 3, 4);
        shoulder5.setRotationPoint(11F, -3F, 0F);
        shoulder5.setTextureSize(128, 32);
        shoulder5.mirror = true;
        setRotation(shoulder5, 0F, 0F, 0F);
    }

    @Override
    protected List<RendererModel> getRenderers() {
        List<RendererModel> result = super.getRenderers();

        result.addAll(Arrays.asList(armsLeft));
        result.addAll(Arrays.asList(armsRight));

        return result;
    }

    @Override
    public void setRotationAngles(DivineBoss entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

        this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        for (RendererModel r : this.armsRight) {
            r.rotateAngleX = 0;
        }

        for (RendererModel r : this.armsLeft) {
            r.rotateAngleX = 0;
        }
    }

    @Override
    public int size(HandSide side) {
        return 3;
    }

    @Override
    public void postRenderArm(int index, float scale, HandSide side) {
        boolean right = side == HandSide.RIGHT;

        RendererModel[] arms = right
                ? armsRight
                : armsLeft;

        if (index >= arms.length)
            return;

        RendererModel arm = arms[index];
        if (arm == null)
            return;

        arm.postRender(scale);

//        GlStateManager.translatef(-0.0625F, 0.4375F, 0.0625F);
//        GlStateManager.translatef(right ? 0.03F : 0.08F, 0F, 0F);
//        GlStateManager.rotatef(90, 0, 1, 0);
//        GlStateManager.rotatef(45, 0, 0, -1);
//        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
