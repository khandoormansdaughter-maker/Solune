package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;

public class BetterSprint extends Module {

    public BetterSprint() {
        super("Better Sprint", Category.Movement);

        this.description = "Sprint in all directions.";
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        if (mc.player.moveForward > 0 || mc.player.moveStrafing != 0) {
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