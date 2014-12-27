package net.divinerpg.utils.events;

import net.divinerpg.client.render.gui.GUIOverlay;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EventExtraArmor {
	
	private final GUIOverlay gui = new GUIOverlay();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event){
		if(event.isCancelable() || event.type != ElementType.) return;
		if((EventArmorTick.size != 0) && !Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
			//gui.drawArmor();
		}
	}
}