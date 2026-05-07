package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class ToggleSprint extends Module {

    public ToggleSprint() {
        super("Toggle Sprint", Category.Movement);

        this.description = "Automatically sprints when moving forward.";
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        if (mc.player.moveForward > 0 && !mc.player.isSprinting()) {
            mc.player.setSprinting(true);
        }
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player != null && mc.player.isSprinting()) {
            mc.player.setSprinting(false);
        }
    }
}