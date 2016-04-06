package com.exo594.tutorial.containers;

import com.exo594.tutorial.tileentities.TileEntityTemporalChamber;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTemporalChamber extends Container {

    private final TileEntityTemporalChamber chamber;
    public static final int timeSlot = 0;

    public ContainerTemporalChamber(InventoryPlayer invPlayer, TileEntityTemporalChamber tetc) {

        chamber = tetc;

        this.addSlotToContainer(new Slot(tetc, 0, 79, 24));

        //Inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        //ActionBar
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return chamber.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

// If itemstack is in Output stack
            if (par2 == timeSlot) {
// try to place in player inventory / action bar; add 36+1 because mergeItemStack uses < index,
// so the last slot in the inventory won't get checked if you don't add 1
                if (!this.mergeItemStack(itemstack1, timeSlot + 1, timeSlot + 36 + 1, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } // itemstack is in player inventory, try to place in appropriate furnace slot
            else if (par2 != timeSlot) {
// if it can be smelted, place in the input slots
                if (chamber.temporalList.contains(itemstack.getItem())) {
// try to place in either Input slot; add 1 to final input slot because mergeItemStack uses < index
                    if (!this.mergeItemStack(itemstack1, timeSlot, timeSlot + 1, false)) {
                        return null;
                    }
                } // item in player's inventory, but not in action bar
                else if (par2 >= timeSlot + 1 && par2 < timeSlot + 28) {
// place in action bar
                    if (!this.mergeItemStack(itemstack1, timeSlot + 28, timeSlot + 37, false)) {
                        return null;
                    }
                } // item in action bar - place in player inventory
                else if (par2 >= timeSlot + 28 && par2 < timeSlot + 37 && !this.mergeItemStack(itemstack1, timeSlot + 1, timeSlot + 28, false)) {
                    return null;
                }
            } // In one of the infuser slots; try to place in player inventory / action bar
            else if (!this.mergeItemStack(itemstack1, timeSlot + 1, timeSlot + 37, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;

    }
}
