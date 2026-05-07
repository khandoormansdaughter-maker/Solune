package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.math.BlockPos;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class LightLevel extends Module {

    public LightLevel() {
        super("Light Level", Category.Render);

        this.description = "Displays the light level at your feet.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        BlockPos pos = mc.player.getPosition();
        int light = mc.world.getLight(pos);

        ScaledResolution sr = new ScaledResolution(mc);
        String text = "Light: " + light;

        int x = sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(text) - 2;
        int y = 80;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }
}