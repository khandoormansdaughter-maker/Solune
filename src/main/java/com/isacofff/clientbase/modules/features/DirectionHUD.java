package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class DirectionHUD extends Module {

    public DirectionHUD() {
        super("Direction HUD", Category.Render);

        this.description = "Displays the direction you're facing.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        float yaw = mc.player.rotationYaw;
        String direction = getDirection(yaw);

        ScaledResolution sr = new ScaledResolution(mc);
        String text = "Facing: " + direction;

        int x = sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(text) / 2;
        int y = 30;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }

    private String getDirection(float yaw) {
        yaw = (yaw % 360 + 360) % 360;
        if (yaw >= 337.5 || yaw < 22.5) return "South";
        if (yaw >= 22.5 && yaw < 67.5) return "Southwest";
        if (yaw >= 67.5 && yaw < 112.5) return "West";
        if (yaw >= 112.5 && yaw < 157.5) return "Northwest";
        if (yaw >= 157.5 && yaw < 202.5) return "North";
        if (yaw >= 202.5 && yaw < 247.5) return "Northeast";
        if (yaw >= 247.5 && yaw < 292.5) return "East";
        if (yaw >= 292.5 && yaw < 337.5) return "Southeast";
        return "Unknown";
    }
}