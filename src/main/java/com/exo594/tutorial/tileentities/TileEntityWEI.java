package com.exo594.tutorial.tileentities;

import com.exo594.tutorial.handlers.TututorialForgeEventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWEI extends TileEntity implements ISidedInventory {

    public ItemStack slots[];

    private String customName;

    private static final int[] slot = new int[]{0};

    public TileEntityWEI() {

        slots = new ItemStack[1];

    }

    public boolean nearby(int x1, int x2, int y1, int y2, int z1, int z2) {      //checks if wither is within 32 blocks.
        int dx = x2 - x1;
        int dy = y2 - y1;
        int dz = z2 - z1;

        return (dx * dx + dy * dy + dz * dz) <= 1024;
    }

    @Override
    public void updateEntity() {
        if (TututorialForgeEventHandler.witherPresent) {
            if (nearby(xCoord, TututorialForgeEventHandler.xWither, yCoord, TututorialForgeEventHandler.yWither, zCoord, TututorialForgeEventHandler.zWither)) {
                System.out.println("AAAAAAAAAAAAH! Don't let the Wither crunch me!");
                this.infuse();
                this.markDirty();
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
        return slot;
    }

    @Override
    public boolean canInsertItem(int i, ItemStack is, int i1) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack is, int i1) {
        return false;
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
    public void setInventorySlotContents(int i, ItemStack is) {
        slots[i] = is;
        if (is != null && is.stackSize > getInventoryStackLimit()) {
            is.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "container.WEI";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer ep) {
        if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
            return false;
        } else {
            return ep.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64;
        }
    }

    @Override
    public void openInventory() {
        //noop
    }

    @Override
    public void closeInventory() {
        //noop
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack is) {
        return is.getItem() == Item.getItemFromBlock(Blocks.diamond_block);
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

    private void infuse() {
        if (this.slots[0].getItem() == Item.getItemFromBlock(Blocks.diamond_block)) {
            ItemStack itemstack = new ItemStack(Items.nether_star, 1);
            this.slots[0].stackSize--;
            this.slots[0] = itemstack.copy();
        }
    }
}
