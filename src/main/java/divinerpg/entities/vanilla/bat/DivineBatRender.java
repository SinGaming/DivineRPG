package divinerpg.entities.vanilla.bat;

import com.mojang.blaze3d.matrix.MatrixStack;
import divinerpg.entities.base.render.DeobfHelper;
import divinerpg.entities.base.render.DivineRender;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BatModel;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;

public class DivineBatRender extends DivineRender<BatEntity, BatModel> {
    public DivineBatRender(EntityRendererManager manager) {
        super(manager, new BatModel(), 0, "", 0.7F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(BatEntity entity) {
        return textureBasedOnType(entity);
    }

    @Override
    protected void func_225621_a_(BatEntity entity, MatrixStack stack, float ageInTicks, float p_225621_4_, float p_225621_5_) {
        if (entity.getIsBatHanging()) {
            DeobfHelper.translate(stack, 0.0F, -0.1F, 0.0F);
        } else {
            DeobfHelper.translate(stack, 0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
        }

        super.func_225621_a_(entity, stack, ageInTicks, p_225621_4_, p_225621_5_);
    }
}
