package com.exo594.tutorial.handlers;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.containers.ContainerIngotMasher;
import com.exo594.tutorial.containers.ContainerItem;
import com.exo594.tutorial.containers.ContainerTemporalChamber;
import com.exo594.tutorial.containers.ContainerTutoriumFurnace;
import com.exo594.tutorial.containers.ContainerWEI;
import com.exo594.tutorial.guis.GuiIngotMasher;
import com.exo594.tutorial.guis.GuiInventoryItem;
import com.exo594.tutorial.guis.GuiTemporalChamber;
import com.exo594.tutorial.guis.GuiTutoriumFurnace;
import com.exo594.tutorial.guis.GuiWEI;
import com.exo594.tutorial.item.InventoryItem;
import com.exo594.tutorial.tileentities.TileEntityIngotMasher;
import com.exo594.tutorial.tileentities.TileEntityTemporalChamber;
import com.exo594.tutorial.tileentities.TileEntityTutoriumFurnace;
import com.exo594.tutorial.tileentities.TileEntityWEI;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GUIHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity != null) {
            if (ID == Main.guiIDTutoriumFurnace) {

                if (entity instanceof TileEntityTutoriumFurnace) {
                    return new ContainerTutoriumFurnace(player.inventory, (TileEntityTutoriumFurnace) entity);
                }
                return null;
            } else if (ID == Main.guiIDIngotMasher) {
                if (entity instanceof TileEntityIngotMasher) {
                    return new ContainerIngotMasher(player.inventory, (TileEntityIngotMasher) entity);
                }
                return null;
            } else if (ID == Main.guiIDTemporalChamber) {
                if (entity instanceof TileEntityTemporalChamber) {
                    return new ContainerTemporalChamber(player.inventory, (TileEntityTemporalChamber) entity);
                }
                return null;
            } else if (ID == Main.guiIDWEI) {
                if (entity instanceof TileEntityWEI) {
                    return new ContainerWEI(player.inventory, (TileEntityWEI) entity);
                }
                return null;
            } else if (ID == Main.GUI_ITEM_INV) {
                // Use the player's held item to create the inventory
                return new ContainerItem(player, player.inventory, new InventoryItem(player.getHeldItem()));
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity != null) {
            if (ID == Main.guiIDTutoriumFurnace) {
                if (entity instanceof TileEntityTutoriumFurnace) {
                    return new GuiTutoriumFurnace(player.inventory, (TileEntityTutoriumFurnace) entity);
                }
                return null;
            } else if (ID == Main.guiIDIngotMasher) {
                if (entity instanceof TileEntityIngotMasher) {
                    return new GuiIngotMasher(player.inventory, (TileEntityIngotMasher) entity);
                }
                return null;
            } else if (ID == Main.guiIDTemporalChamber) {
                if (entity instanceof TileEntityTemporalChamber) {
                    return new GuiTemporalChamber(player.inventory, (TileEntityTemporalChamber) entity);
                }
                return null;
            } else if (ID == Main.guiIDWEI) {
                if (entity instanceof TileEntityWEI) {
                    return new GuiWEI(player.inventory, (TileEntityWEI) entity);
                }
                return null;
            } else if (ID == Main.GUI_ITEM_INV) {
                // Use the player's held item to create the inventory
                return new GuiInventoryItem(player, player.inventory, new InventoryItem(player.getHeldItem()));
            }
            return null;
        }
        return null;
    }
}
