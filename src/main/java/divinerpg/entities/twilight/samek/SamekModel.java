package divinerpg.entities.twilight.samek;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import divinerpg.entities.base.render.DivineBipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class SamekModel extends DivineBipedModel<MobEntity> {
    ModelRenderer secondHead;

    public SamekModel() {
        this.secondHead = new ModelRenderer(this, 0, 0);
        this.secondHead.func_228300_a_(0.0F, 0.0F, 0.0F, 8, 8, 8);
        this.secondHead.setRotationPoint(1.466667F, -8.0F, -4.0F);
        this.secondHead.setTextureSize(64, 32);
        this.secondHead.mirror = true;

        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.func_228300_a_(-11.0F, -8.0F, -4.0F, 8, 8, 8);
        this.bipedHead.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.bipedHead.setTextureSize(64, 32);
        this.bipedHead.mirror = true;
    }

    @Override
    public Iterable<ModelRenderer> getHeadParts() {
        return Iterables.concat(super.getHeadParts(), ImmutableList.of(secondHead));
    }

    @Override
    public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.secondHead.rotateAngleX = bipedHead.rotateAngleX;
        this.secondHead.rotateAngleY = bipedHead.rotateAngleY;
    }
}
