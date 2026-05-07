package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;
import net.lax1dude.eaglercraft.Keyboard;

public class Keystrokes extends Module {

    public Keystrokes() {
        super("Keystrokes", Category.Render);

        this.description = "Displays pressed keys.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int x = sr.getScaledWidth() - 80;
        int y = sr.getScaledHeight() / 2;

        drawKey(x, y, "W", Keyboard.isKeyDown(Keyboard.KEY_W));
        drawKey(x + 20, y, "A", Keyboard.isKeyDown(Keyboard.KEY_A));
        drawKey(x + 20, y + 20, "S", Keyboard.isKeyDown(Keyboard.KEY_S));
        drawKey(x + 40, y + 20, "D", Keyboard.isKeyDown(Keyboard.KEY_D));
    }

    private void drawKey(int x, int y, String key, boolean pressed) {
        Minecraft mc = Minecraft.getMinecraft();
        int color = pressed ? 0xFF00FF00 : 0xFF888888;
        mc.fontRendererObj.drawStringWithShadow(key, x, y, color);
    }
}