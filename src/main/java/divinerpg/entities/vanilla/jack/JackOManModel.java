package divinerpg.entities.vanilla.jack;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

// TODO remake model, note arms and legs
public class JackOManModel extends BipedModel<JackOMan> {
    public JackOManModel() {
        super(0);

        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.func_228301_a_(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.func_228301_a_(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.func_228301_a_(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0);
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
    }

    @Override
    public void func_225597_a_(JackOMan entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
                               float netHeadYaw, float headPitch) {
        super.func_225597_a_(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        ItemStack itemstack = entityIn.getHeldItemMainhand();
        if (entityIn.isAggressive() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof net.minecraft.item.BowItem))) {
            float f = MathHelper.sin(this.swingProgress * (float) Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
            this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
            this.bipedRightArm.rotateAngleX = (-(float) Math.PI / 2F);
            this.bipedLeftArm.rotateAngleX = (-(float) Math.PI / 2F);
            this.bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        }
    }
}
