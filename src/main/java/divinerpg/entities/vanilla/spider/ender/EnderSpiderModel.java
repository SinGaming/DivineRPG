package divinerpg.entities.vanilla.spider.ender;

import divinerpg.entities.vanilla.spider.SpiderModel;
import org.lwjgl.opengl.GL11;

public class EnderSpiderModel extends SpiderModel<EnderSpider> {

    public EnderSpiderModel() {
        super(1);
    }

    @Override
    public void render(EnderSpider entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GL11.glScaled(0.5f, 0.5f, 0.5f);
        GL11.glTranslatef(0f, 1.25f, 0f);

        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
}
