package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class AutoWalk extends Module {

    public AutoWalk() {
        super("Auto Walk", Category.Movement);

        this.description = "Automatically walks forward.";
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        mc.player.moveForward = 1.0f;
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player != null) {
            mc.player.moveForward = 0.0f;
        }
    }
}