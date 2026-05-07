package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class TargetInfo extends Module {

    public TargetInfo() {
        super("Target Info", Category.Combat);

        this.description = "Displays information about the entity you're looking at.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.objectMouseOver == null) return;

        if (mc.objectMouseOver.typeOfHit == net.minecraft.util.math.RayTraceResult.Type.ENTITY) {
            EntityLivingBase entity = (EntityLivingBase) mc.objectMouseOver.entityHit;
            if (entity != null) {
                ScaledResolution sr = new ScaledResolution(mc);
                String name = entity.getName();
                String health = "Health: " + (int) entity.getHealth() + "/" + (int) entity.getMaxHealth();

                int x = sr.getScaledWidth() / 2 - 50;
                int y = sr.getScaledHeight() / 2 + 20;

                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                mc.fontRendererObj.drawStringWithShadow(name, x * 2, y * 2, -1);
                mc.fontRendererObj.drawStringWithShadow(health, x * 2, (y + 10) * 2, -1);
                GlStateManager.popMatrix();
            }
        }
    }
}