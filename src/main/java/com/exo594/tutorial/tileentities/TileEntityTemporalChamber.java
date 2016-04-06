package com.exo594.tutorial.tileentities;

import com.exo594.tutorial.item.ItemModSword;
import com.exo594.tutorial.item.ModItems;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTemporalChamber extends TileEntity implements ISidedInventory {

    public static final ArrayList<Item> temporalList = new ArrayList<Item>();

    public ItemStack slots[];

    private static final int[] slot = new int[]{0};

    private String customName;

    public ItemStack result;

    public TileEntityTemporalChamber() {
        slots = new ItemStack[1];
        temporalList.add(ModItems.tutorialSword);
    }

    @Override
    public int getSizeInventory() {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return slots[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (slots[i] != null) {
            ItemStack itemstack = slots[i];
            slots[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        slots[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
        return slot;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
            return false;
        } else {
            return player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64;
        }
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return temporalList.contains(itemstack.getItem());
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (slots[i] != null) {
            if (slots[i].stackSize <= j) {
                ItemStack itemstack = slots[i];
                slots[i] = null;
                return itemstack;
            }

            ItemStack itemstack1 = slots[i].splitStack(j);

            if (slots[i].stackSize == 0) {
                slots[i] = null;
            }

            return itemstack1;
        } else {
            return null;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items", 10);
        slots = new ItemStack[getSizeInventory()];

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound nbt1 = (NBTTagCompound) list.getCompoundTagAt(i);
            byte b0 = nbt1.getByte("Slot");

            if (b0 >= 0 && b0 < slots.length) {
                slots[b0] = ItemStack.loadItemStackFromNBT(nbt1);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null) {
                NBTTagCompound nbt1 = new NBTTagCompound();
                nbt1.setByte("Slot", (byte) i);
                slots[i].writeToNBT(nbt1);
                list.appendTag(nbt1);
            }
        }

        nbt.setTag("Items", list);
    }

    @Override
    public String getInventoryName() {
        return "container.temporalChamber";
    }

    @Override
    public boolean canInsertItem(int var1, ItemStack itemstack, int var3) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    private void infuseItem() {
        if (this.slots[0].getItem() instanceof ItemModSword) {
            ItemModSword sword = (ItemModSword) this.slots[0].getItem();
            sword.timeStored++;
            System.out.println("Infusing the fuck out of this sword!");
        }
    }

    @Override
    public void updateEntity() {
        if (this.slots[0] != null && this.temporalList.contains(this.slots[0].getItem())) {
            infuseItem();
        }

    }
}
