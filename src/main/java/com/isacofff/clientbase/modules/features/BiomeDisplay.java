package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.world.biome.Biome;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class BiomeDisplay extends Module {

    public BiomeDisplay() {
        super("Biome Display", Category.Render);

        this.description = "Displays the current biome.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        Biome biome = mc.world.getBiome(mc.player.getPosition());
        String biomeName = biome.getBiomeName();

        ScaledResolution sr = new ScaledResolution(mc);
        String text = "Biome: " + biomeName;

        int x = sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(text) - 2;
        int y = 70;

        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
        GlStateManager.popMatrix();
    }
}