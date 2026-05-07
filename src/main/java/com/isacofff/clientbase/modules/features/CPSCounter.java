package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class CPSCounter extends Module {

    private int leftClicks = 0;
    private int rightClicks = 0;
    private long lastTime = System.currentTimeMillis();

    public CPSCounter() {
        super("CPS Counter", Category.Render);

        this.description = "Displays clicks per second.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        long now = System.currentTimeMillis();
        if (now - lastTime >= 1000) {
            leftClicks = 0;
            rightClicks = 0;
            lastTime = now;
        }

        ScaledResolution sr = new ScaledResolution(mc);
        String text = "CPS: L" + leftClicks + " R" + rightClicks;

        int x = sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(text) / 2;
        int y = sr.getScaledHeight() - 20;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }

    public void onLeftClick() {
        leftClicks++;
    }

    public void onRightClick() {
        rightClicks++;
    }
}