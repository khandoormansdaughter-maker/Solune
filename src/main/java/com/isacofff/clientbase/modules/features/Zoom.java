package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class Zoom extends Module {

    private float originalFOV = 70.0f;

    public Zoom() {
        super("Zoom", Category.Render);

        this.description = "Zooms in the view.";
    }

    @Override
    public void onEnable() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player != null) {
            originalFOV = mc.gameSettings.fovSetting;
            mc.gameSettings.fovSetting = 30.0f;
        }
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.fovSetting = originalFOV;
    }
}