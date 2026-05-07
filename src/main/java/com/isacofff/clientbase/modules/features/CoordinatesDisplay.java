package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class CoordinatesDisplay extends Module {

    public CoordinatesDisplay() {
        super("Coordinates", Category.Render);

        this.description = "Displays your current coordinates on screen.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int x = (int) mc.player.posX;
        int y = (int) mc.player.posY;
        int z = (int) mc.player.posZ;
        String text = "XYZ: " + x + " " + y + " " + z;

        int screenX = sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(text) / 2;
        int screenY = 2;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, screenX * 2, screenY * 2, -1);
        GlStateManager.popMatrix();
    }
}