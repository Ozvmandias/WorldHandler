package exopandora.worldhandler.util;

import com.mojang.blaze3d.systems.RenderSystem;

import exopandora.worldhandler.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UtilRender
{
	public static void drawWatchIntoGui(Screen gui, int width, int height, long worldTicks, boolean smooth)
	{
		float hour = TextFormatting.getHour(worldTicks);
		float minute = TextFormatting.getMinute(worldTicks);
		
		if(smooth)
		{
			hour = (worldTicks + 6000) / 1000F;
			minute = (float) ((worldTicks + 6000F - Math.floor(hour) * 1000) * 6 / 100);
		}
		
		float rotationHour = (360 / 12) * (hour >= 12 ? (hour - 12) : hour) - 180F;
		float rotationMinute = (360 / 60) * minute - 180F;
		
		RenderSystem.pushMatrix();
		RenderSystem.translatef(width + 5, height + 5, 0F);
		RenderSystem.scalef(0.25F, 0.25F, 0.25F);
		
		RenderSystem.rotatef(rotationHour, 0F, 0F, 1F);
		Screen.fill(-1, -1, 1, 11, 0xFF383838);
		RenderSystem.rotatef(-rotationHour, 0F, 0F, 1F);
		
		RenderSystem.rotatef(rotationMinute, 0F, 0F, 1F);
		Screen.fill(-1, -1, 1, 15, 0xFF6F6F6F);
		RenderSystem.rotatef(-rotationMinute, 0F, 0F, 1F);
		
		RenderSystem.color3f(1.0F, 1.0F, 1.0F);
		RenderSystem.popMatrix();
		
		RenderSystem.color3f(Config.getSkin().getButtonRedF(), Config.getSkin().getButtonGreenF(), Config.getSkin().getButtonBlueF());
		Minecraft.getInstance().getTextureManager().bindTexture(ResourceHelper.getIconTexture());
		
		gui.blit(width + 0, height, 48, 0, 10, 10);
		
		RenderSystem.pushMatrix();
		RenderSystem.scalef(0.5F, 0.5F, 0.5F);
		Screen.fill((width + 5) * 2 - 1, (height + 4) * 2 + 1, (width + 6) * 2 - 1, (height + 5) * 2 + 1, 0xFF000000);
		RenderSystem.popMatrix();
	}
}
