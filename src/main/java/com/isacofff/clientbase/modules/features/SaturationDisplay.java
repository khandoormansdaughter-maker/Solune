package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class SaturationDisplay extends Module {

    public SaturationDisplay() {
        super("Saturation Display", Category.Render);

        this.description = "Displays your hunger saturation.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        float saturation = mc.player.getFoodStats().getSaturationLevel();
        ScaledResolution sr = new ScaledResolution(mc);
        String text = "Saturation: " + String.format("%.1f", saturation);

        int x = sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(text) - 2;
        int y = 60;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }
}