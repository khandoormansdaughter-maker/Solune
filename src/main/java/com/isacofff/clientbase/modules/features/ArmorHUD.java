package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class ArmorHUD extends Module {

    public ArmorHUD() {
        super("Armor HUD", Category.Render);

        this.description = "Displays armor durability on screen.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int x = 2;
        int y = sr.getScaledHeight() / 2;

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