package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

import com.isacofff.clientbase.settings.Setting;

public class ArmorHUD extends Module {

    public Setting.NumberSetting posX = new Setting.NumberSetting("X Position", 2, 0, 1000, 1);
    public Setting.NumberSetting posY = new Setting.NumberSetting("Y Position", 100, 0, 1000, 1);

    public ArmorHUD() {
        super("Armor HUD", Category.Render);

        this.description = "Displays armor durability on screen.";

        settings.add(posX);
        settings.add(posY);
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int x = posX.getValue().intValue();
        int y = posY.getValue().intValue();

        for (int i = 0; i < 4; i++) {
            ItemStack stack = mc.player.inventory.armorInventory.get(i);
            if (stack.getItem() != null) {
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                mc.getRenderItem().renderItemIntoGUI(stack, x * 2, y * 2);
                String dur = stack.getMaxDamage() - stack.getItemDamage() + "";
                mc.fontRendererObj.drawStringWithShadow(dur, (x + 18) * 2, (y + 4) * 2, -1);
                GlStateManager.popMatrix();
                y += 18;
            }
        }
    }
}