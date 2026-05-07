package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

import com.isacofff.clientbase.settings.Setting;

public class FPSDisplay extends Module {

    public Setting.NumberSetting posX = new Setting.NumberSetting("X Position", 2, 0, 1000, 1);
    public Setting.NumberSetting posY = new Setting.NumberSetting("Y Position", 2, 0, 1000, 1);

    public FPSDisplay() {
        super("FPS Display", Category.Render);

        this.description = "Displays the current FPS on screen.";

        settings.add(posX);
        settings.add(posY);
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int fps = Minecraft.getDebugFPS();
        String text = "FPS: " + fps;

        int x = posX.getValue().intValue();
        int y = posY.getValue().intValue();

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }
}