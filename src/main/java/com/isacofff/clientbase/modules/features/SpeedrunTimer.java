package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class SpeedrunTimer extends Module {

    private long startTime = 0;
    private boolean running = false;

    public Setting.BooleanSetting startStop = new Setting.BooleanSetting("Start/Stop", false);

    public SpeedrunTimer() {
        super("Speedrun Timer", Category.Render);

        this.description = "A timer for speedrunning.";

        settings.add(startStop);
    }

    @Override
    public void onEnable() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    @Override
    public void onDisable() {
        running = false;
    }

    @Override
    public void onUpdate() {
        if (startStop.getValue() && !running) {
            startTime = System.currentTimeMillis();
            running = true;
        } else if (!startStop.getValue() && running) {
            running = false;
        }
    }

    @Override
    public void onRender() {
        if (!running) return;

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        long elapsed = System.currentTimeMillis() - startTime;
        int minutes = (int) (elapsed / 60000);
        int seconds = (int) ((elapsed % 60000) / 1000);
        int millis = (int) (elapsed % 1000);

        String time = String.format("%02d:%02d.%03d", minutes, seconds, millis);

        int x = sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(time) / 2;
        int y = 40;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(time, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }
}