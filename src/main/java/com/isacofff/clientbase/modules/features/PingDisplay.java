package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class PingDisplay extends Module {

    public PingDisplay() {
        super("Ping Display", Category.Render);

        this.description = "Displays your ping to the server.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.getConnection() == null) return;

        int ping = -1;
        if (mc.getConnection().getPlayerInfo(mc.player.getUniqueID()) != null) {
            ping = mc.getConnection().getPlayerInfo(mc.player.getUniqueID()).getResponseTime();
        }

        ScaledResolution sr = new ScaledResolution(mc);
        String text = "Ping: " + ping + "ms";

        int x = sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(text) - 2;
        int y = 50;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }
}