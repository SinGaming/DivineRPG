package divinerpg.entities.base.render;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;

public class HeldItemsLayer<T extends LivingEntity, M extends EntityModel<T> & IHasArms> extends LayerRenderer<T, M> {
    public HeldItemsLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(T entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        boolean flag = entityIn.getPrimaryHand() == HandSide.RIGHT;
        ItemStack itemstack = flag ? entityIn.getHeldItemOffhand() : entityIn.getHeldItemMainhand();
        ItemStack itemstack1 = flag ? entityIn.getHeldItemMainhand() : entityIn.getHeldItemOffhand();
        if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
            GlStateManager.pushMatrix();
            if (this.getEntityModel().isChild) {
                float f = 0.5F;
                GlStateManager.translatef(0.0F, 0.75F, 0.0F);
                GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            }

            this.renderHeldItems(entityIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HandSide.RIGHT);
            this.renderHeldItems(entityIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItems(LivingEntity entity, ItemStack stack, ItemCameraTransforms.TransformType type, HandSide handSide) {
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            if (entity.shouldRenderSneaking()) {
                GlStateManager.translatef(0.0F, 0.2F, 0.0F);
            }

            M model = this.getEntityModel();

            for (int i = 0; i < model.size(handSide); i++) {
                // Forge: moved this call down, fixes incorrect offset while sneaking.
                model.postRenderArm(i, 0.0625F, handSide);

                GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
                boolean flag = handSide == HandSide.LEFT;
                GlStateManager.translatef((float) (flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
                Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entity, stack, type, flag);
                GlStateManager.popMatrix();
            }

        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
