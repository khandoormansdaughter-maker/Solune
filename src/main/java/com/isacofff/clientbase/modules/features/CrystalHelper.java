package com.isacofff.clientbase.modules.features;

import com.isacofff.clientbase.modules.Module;
import com.isacofff.clientbase.Category;
import com.isacofff.clientbase.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class CrystalHelper extends Module {

    public Setting.NumberSetting range = new Setting.NumberSetting("Range", 6, 1, 10, 1);

    public CrystalHelper() {
        super("Crystal Helper", Category.Combat);

        this.description = "Shows potential crystal placement spots.";

        settings.add(range);
    }

    @Override
    public void onRender() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null) return;

        int r = range.getValue().intValue();
        BlockPos playerPos = mc.player.getPosition();

        for (int x = playerPos.getX() - r; x <= playerPos.getX() + r; x++) {
            for (int y = playerPos.getY() - r; y <= playerPos.getY() + r; y++) {
                for (int z = playerPos.getZ() - r; z <= playerPos.getZ() + r; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (canPlaceCrystal(pos)) {
                        drawBox(pos);
                    }
                }
            }
        }
    }

    private boolean canPlaceCrystal(BlockPos pos) {
        Minecraft mc = Minecraft.getMinecraft();
        BlockPos below = pos.down();
        return mc.world.isAirBlock(pos) && (mc.world.getBlockState(below).getBlock() == Blocks.OBSIDIAN || mc.world.getBlockState(below).getBlock() == Blocks.BEDROCK);
    }

    private void drawBox(BlockPos pos) {
        Minecraft mc = Minecraft.getMinecraft();
        double x = pos.getX() - mc.getRenderManager().viewerPosX;
        double y = pos.getY() - mc.getRenderManager().viewerPosY;
        double z = pos.getZ() - mc.getRenderManager().viewerPosZ;

        AxisAlignedBB box = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1);

        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderGlobal.drawSelectionBoundingBox(box, 0.0F, 1.0F, 0.0F, 0.5F);
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
}