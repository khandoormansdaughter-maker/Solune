package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.util.math.Vec3d;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class PearlPredictor extends Module {

    public PearlPredictor() {
        super("Pearl Predictor", Category.Render);

        this.description = "Predicts ender pearl landing coordinates.";
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        // Find the nearest ender pearl
        EntityEnderPearl pearl = null;
        double closestDist = Double.MAX_VALUE;
        for (EntityEnderPearl p : mc.world.getEntities(EntityEnderPearl.class, e -> true)) {
            double dist = mc.player.getDistanceSqToEntity(p);
            if (dist < closestDist) {
                closestDist = dist;
                pearl = p;
            }
        }

        if (pearl != null && mc.objectMouseOver != null && mc.objectMouseOver.entityHit == pearl) {
            Vec3d landing = predictLanding(pearl);
            if (landing != null) {
                ScaledResolution sr = new ScaledResolution(mc);
                String text = String.format("Landing: %.1f %.1f %.1f", landing.xCoord, landing.yCoord, landing.zCoord);
                int x = sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(text) / 2;
                int y = sr.getScaledHeight() / 2 + 30;

                GlStateManager.pushMatrix();
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                mc.fontRendererObj.drawStringWithShadow(text, x * 2, y * 2, -1);
                GlStateManager.popMatrix();
            }
        }
    }

    private Vec3d predictLanding(EntityEnderPearl pearl) {
        Vec3d pos = new Vec3d(pearl.posX, pearl.posY, pearl.posZ);
        Vec3d vel = new Vec3d(pearl.motionX, pearl.motionY, pearl.motionZ);

        // Simulate trajectory
        for (int i = 0; i < 200; i++) { // Max 200 ticks
            pos = pos.add(vel);
            vel = vel.addVector(0, -0.03, 0); // Gravity

            // Check if hit ground
            if (pos.yCoord <= 0) {
                return new Vec3d(pos.xCoord, 0, pos.zCoord);
            }
        }

        return null; // Didn't land
    }
}