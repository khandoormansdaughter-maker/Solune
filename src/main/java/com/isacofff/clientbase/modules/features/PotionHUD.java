package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.potion.PotionEffect;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class PotionHUD extends Module {

    public PotionHUD() {
        super("Potion HUD", Category.Render);

        this.description = "Displays active potion effects.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int x = 2;
        int y = sr.getScaledHeight() / 2 + 50;

        for (PotionEffect effect : mc.player.getActivePotionEffects()) {
            String name = effect.getPotion().getName();
            int duration = effect.getDuration() / 20; // seconds
            String text = name + " " + duration + "s";

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
            mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
            GlStateManager.popMatrix();
            y += 10;
        }
    }
}