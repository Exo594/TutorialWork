package com.exo594.tutorial.guis;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.containers.ContainerTutoriumFurnace;
import com.exo594.tutorial.tileentities.TileEntityTutoriumFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTutoriumFurnace extends GuiContainer {

    public static final ResourceLocation bground = new ResourceLocation(Main.MODID + ":textures/guis/guitutoriumfurnace.png");

    public TileEntityTutoriumFurnace tutoriumFurnace;

    public GuiTutoriumFurnace(InventoryPlayer inventoryPlayer, TileEntityTutoriumFurnace entity) {
        super(new ContainerTutoriumFurnace(inventoryPlayer, entity));
        this.tutoriumFurnace = entity;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int b, int c) {
        String name = "Tutorium Furnace";

        this.fontRendererObj.drawString(name, xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 118, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float a, int b, int c) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        this.mc.getTextureManager().bindTexture(bground);
        //Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
        
        int left = (this.width - this.xSize) / 2;
        int top = (this.height - this.ySize) / 2;
        
        this.drawTexturedModalRect(left, top, 0, 0, this.xSize, this.ySize);

        if (this.tutoriumFurnace.isBurning()) {
            int k = this.tutoriumFurnace.getBurnTimeRemainingScaled(40);
            int j = 40 - k;
            this.drawTexturedModalRect(left + 29, top + 65, 176, 0, 40 - j, 10);
        }

        int k = this.tutoriumFurnace.getCookProgressScaled(24);
        this.drawTexturedModalRect(left + 79, top + 34, 176, 10, k + 1, 16);

    }

}
