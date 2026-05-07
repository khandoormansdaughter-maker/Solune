package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class FPSDisplay extends Module {

    public FPSDisplay() {
        super("FPS Display", Category.Render);

        this.description = "Displays the current FPS on screen.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int fps = Minecraft.getDebugFPS();
        String text = "FPS: " + fps;

        int x = sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(text) - 2;
        int y = 2;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }
}