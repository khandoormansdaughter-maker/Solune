package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class AutoEat extends Module {

    public AutoEat() {
        super("Auto Eat", Category.Player);

        this.description = "Automatically eats food when hungry.";
    }

    @Override
    public void onUpdate() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        if (mc.player.getFoodStats().getFoodLevel() < 18 && mc.player.isHandActive()) {
            for (int i = 0; i < mc.player.inventory.getSizeInventory(); i++) {
                ItemStack stack = mc.player.inventory.getStackInSlot(i);
                if (stack.getItem() != null && stack.getItem() instanceof ItemFood) {
                    mc.player.inventory.currentItem = i;
                    mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
                    break;
                }
            }
        }
    }
}