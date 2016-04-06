package com.exo594.tutorial.containers;

import com.exo594.tutorial.IngotMasherRecipes;
import com.exo594.tutorial.slot.SlotIngotMasher;
import com.exo594.tutorial.tileentities.TileEntityIngotMasher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerIngotMasher extends Container {

    private TileEntityIngotMasher masher;
    private int dualCookTime;
    private int dualPower;
    private int lastItemBurnTime;
    public static final int INPUT_1 = 0, INPUT_2 = 1, FUEL = 2, OUTPUT = 3;

    public ContainerIngotMasher(InventoryPlayer invPlayer, TileEntityIngotMasher teIngotMasher) {
        dualCookTime = 0;
        dualPower = 0;
        lastItemBurnTime = 0;

        masher = teIngotMasher;

        this.addSlotToContainer(new Slot(teIngotMasher, INPUT_1, 45, 17));
        this.addSlotToContainer(new Slot(teIngotMasher, INPUT_2, 45, 49));
        this.addSlotToContainer(new Slot(teIngotMasher, FUEL, 8, 56));
        this.addSlotToContainer(new SlotIngotMasher(invPlayer.player, teIngotMasher, OUTPUT, 113, 33));

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
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.masher.dualCookTime);
        crafting.sendProgressBarUpdate(this, 1, this.masher.dualPower);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

// If itemstack is in Output stack
            if (par2 == OUTPUT) {
// try to place in player inventory / action bar; add 36+1 because mergeItemStack uses < index,
// so the last slot in the inventory won't get checked if you don't add 1
                if (!this.mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 36 + 1, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } // itemstack is in player inventory, try to place in appropriate furnace slot
            else if (par2 != FUEL && par2 != INPUT_1 && par2 != INPUT_2) {
// if it can be smelted, place in the input slots
                if (IngotMasherRecipes.itemCanBeMashed(itemstack1)) {
// try to place in either Input slot; add 1 to final input slot because mergeItemStack uses < index
                    if (!this.mergeItemStack(itemstack1, INPUT_1, INPUT_2 + 1, false)) {
                        return null;
                    }
                } // if it's an energy source, place in Fuel slot
                else if (TileEntityIngotMasher.hasItemPower(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, FUEL, FUEL + 1, false)) {
                        return null;
                    }
                } // item in player's inventory, but not in action bar
                else if (par2 >= OUTPUT + 1 && par2 < OUTPUT + 28) {
// place in action bar
                    if (!this.mergeItemStack(itemstack1, OUTPUT + 28, OUTPUT + 37, false)) {
                        return null;
                    }
                } // item in action bar - place in player inventory
                else if (par2 >= OUTPUT + 28 && par2 < OUTPUT + 37 && !this.mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 28, false)) {
                    return null;
                }
            } // In one of the infuser slots; try to place in player inventory / action bar
            else if (!this.mergeItemStack(itemstack1, OUTPUT + 1, OUTPUT + 37, false)) {
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

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return masher.isUseableByPlayer(player);
    }

    @Override
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
    }
}
