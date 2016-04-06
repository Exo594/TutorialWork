package com.exo594.tutorial.guis;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.containers.ContainerWEI;
import com.exo594.tutorial.tileentities.TileEntityWEI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiWEI extends GuiContainer {

    public ResourceLocation texture = new ResourceLocation(Main.MODID + ":textures/guis/temporalChamberGui.png");
    public TileEntityWEI infuser;

    public GuiWEI(InventoryPlayer invPlayer, TileEntityWEI wei) {
        super(new ContainerWEI(invPlayer, wei));
        infuser = wei;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        String name = "Temporal Chamber";

        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 5, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
