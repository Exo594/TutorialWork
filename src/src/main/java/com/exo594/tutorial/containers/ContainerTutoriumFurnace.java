package com.exo594.tutorial.containers;

import com.exo594.tutorial.tileentities.TileEntityTutoriumFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerTutoriumFurnace extends Container {

    private TileEntityTutoriumFurnace tutoriumFurnace;

    public int lastBurnTime;
    public int lastCurrentItemBurnTime;
    public int lastCookTime;

    public ContainerTutoriumFurnace(InventoryPlayer inventory, TileEntityTutoriumFurnace tileEntity) {
        this.tutoriumFurnace = tileEntity;
        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 35));
        this.addSlotToContainer(new Slot(tileEntity, 1, 8, 62));
        this.addSlotToContainer(new SlotFurnace(inventory.player, tileEntity, 2, 116, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlotToContainer(new Slot(inventory, j + (i * 9) + 9, 8 + (j * 18), 84 + (i * 18)));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.tutoriumFurnace.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.tutoriumFurnace.burnTime);
        icrafting.sendProgressBarUpdate(this, 2, this.tutoriumFurnace.currentItemBurnTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); i++) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastCookTime != this.tutoriumFurnace.cookTime) {
                icrafting.sendProgressBarUpdate(this, 0, this.tutoriumFurnace.cookTime);
            }
            if (this.lastBurnTime != this.tutoriumFurnace.burnTime) {
                icrafting.sendProgressBarUpdate(this, 1, this.tutoriumFurnace.burnTime);
            }
            if (this.lastCurrentItemBurnTime != this.tutoriumFurnace.currentItemBurnTime) {
                icrafting.sendProgressBarUpdate(this, 2, this.tutoriumFurnace.currentItemBurnTime);
            }
        }
        this.lastCookTime = this.tutoriumFurnace.cookTime;
        this.lastBurnTime = this.tutoriumFurnace.burnTime;
        this.lastCurrentItemBurnTime = this.tutoriumFurnace.currentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int slot, int newValue) {
        if (slot == 0) {
            this.tutoriumFurnace.cookTime = newValue;
        }

        if (slot == 1) {
            this.tutoriumFurnace.burnTime = newValue;
        }

        if (slot == 2) {
            this.tutoriumFurnace.currentItemBurnTime = newValue;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int par2){
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(par2 == 2){
				if(!this.mergeItemStack(itemstack1, 3, 39, true)){
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			}else if(par2 != 1 && par2 != 0){
				if(FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null){
					if(!this.mergeItemStack(itemstack1, 0, 1, false)){
						return null;
					}
				}else if(TileEntityTutoriumFurnace.isItemFuel(itemstack1)){
					if(!this.mergeItemStack(itemstack1, 1, 2, false)){
						return null;
					}
				}else if(par2 >=3 && par2 < 30){
					if(!this.mergeItemStack(itemstack1, 30, 39, false)){
						return null;
					}
				}else if(par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)){
					return null;
				}
			}else if(!this.mergeItemStack(itemstack1, 3, 39, false)){
				return null;
			}
			if(itemstack1.stackSize == 0){
				slot.putStack((ItemStack)null);
			}else{
				slot.onSlotChanged();
			}
			if(itemstack1.stackSize == itemstack.stackSize){
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}
}
