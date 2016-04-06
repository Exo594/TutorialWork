package com.exo594.tutorial.containers;

import com.exo594.tutorial.item.ModItems;
import com.exo594.tutorial.tileentities.TileEntityWEI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerWEI extends Container {

    private final TileEntityWEI infuser;
    public static final int witherSlot = 0;

    public ContainerWEI(InventoryPlayer invPlayer, TileEntityWEI wei) {

        this.infuser = wei;

        this.addSlotToContainer(new Slot(wei, 0, 79, 24));

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

    /*
     @Override
     public void addCraftingToCrafters(ICrafting crafting) {                         //not sure if needed
     super.addCraftingToCrafters(crafting);
     crafting.sendProgressBarUpdate(this, 0, this.chamber.timeStored);
     }
     */
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return infuser.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

// If itemstack is in Output stack
            if (par2 == witherSlot) {
// try to place in player inventory / action bar; add 36+1 because mergeItemStack uses < index,
// so the last slot in the inventory won't get checked if you don't add 1
                if (!this.mergeItemStack(itemstack1, witherSlot + 1, witherSlot + 36 + 1, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } // itemstack is in player inventory, try to place in appropriate furnace slot
            else if (par2 != witherSlot) {
// if it can be smelted, place in the input slots
                if (itemstack.getItem() == Item.getItemFromBlock(Blocks.diamond_block)) {
// try to place in either Input slot; add 1 to final input slot because mergeItemStack uses < index
                    if (!this.mergeItemStack(itemstack1, witherSlot, witherSlot + 1, false)) {
                        return null;
                    }
                } // item in player's inventory, but not in action bar
                else if (par2 >= witherSlot + 1 && par2 < witherSlot + 28) {
// place in action bar
                    if (!this.mergeItemStack(itemstack1, witherSlot + 28, witherSlot + 37, false)) {
                        return null;
                    }
                } // item in action bar - place in player inventory
                else if (par2 >= witherSlot + 28 && par2 < witherSlot + 37 && !this.mergeItemStack(itemstack1, witherSlot + 1, witherSlot + 28, false)) {
                    return null;
                }
            } // In one of the infuser slots; try to place in player inventory / action bar
            else if (!this.mergeItemStack(itemstack1, witherSlot + 1, witherSlot + 37, false)) {
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

/*@Override
 public void detectAndSendChanges() {
 super.detectAndSendChanges();
 for (int i = 0; i < this.crafters.size(); i++) {
 ICrafting icrafting = (ICrafting) this.crafters.get(i);

 if (this.dualCookTime != this.masher.dualCookTime) {
 icrafting.sendProgressBarUpdate(this, 0, this.masher.dualCookTime);
 }
 if (this.dualPower != this.masher.dualPower) {
 icrafting.sendProgressBarUpdate(this, 1, this.masher.dualPower);
 }
 }
 this.dualCookTime = this.masher.dualCookTime;
 this.dualPower = this.masher.dualPower;
 }

 @SideOnly(Side.CLIENT)
 @Override
 public void updateProgressBar(int slot, int newValue) {
 if (slot == 0) {
 this.masher.dualCookTime = newValue;
 }

 if (slot == 1) {
 this.masher.dualPower = newValue;
 }
 }*/
}
