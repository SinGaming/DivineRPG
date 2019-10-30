package divinerpg.arcana.client;

import divinerpg.DivineRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber
@OnlyIn(Dist.CLIENT)
public class ArcanaRender {

    private static final Minecraft mc = Minecraft.getInstance();
    private static final ResourceLocation bar = new ResourceLocation(DivineRPG.MODID, "textures/gui/arcana_bar.png");
    public static int percentage = 100;

    @SubscribeEvent
    public static void onRender(RenderGameOverlayEvent.Pre e) {
        if (e.isCanceled())
            return;

        int y = e.getWindow().getScaledHeight() - DivineRPG.CONFIG.arcanaBar.height.get();
        int x = e.getWindow().getScaledWidth() - DivineRPG.CONFIG.arcanaBar.width.get();

        // TODO arcana renders over heart bar
        // use transparent background
        GL11.glEnable(GL11.GL_BLEND);
        mc.getTextureManager().bindTexture(bar);
        GuiUtils.drawTexturedModalRect(x, y, 0, 0, 100, 9, 0);
        GuiUtils.drawTexturedModalRect(x, y, 0, 9, percentage, 9, 0);
    }
}
