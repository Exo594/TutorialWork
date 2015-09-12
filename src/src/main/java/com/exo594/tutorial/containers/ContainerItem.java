package com.exo594.tutorial.containers;

import com.exo594.tutorial.item.InventoryItem;
import com.exo594.tutorial.slot.SlotItemInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerItem extends Container {

    /**
     * The Item Inventory for this Container
     */
    public final InventoryItem inventory;

    private static final int ARMOR_START = InventoryItem.INV_SIZE, ARMOR_END = ARMOR_START + 3,
            INV_START = ARMOR_END + 1, INV_END = INV_START + 26,
            HOTBAR_START = INV_END + 1, HOTBAR_END = HOTBAR_START + 8;

    public ContainerItem(EntityPlayer player, InventoryPlayer inv, InventoryItem bag) {
        int i = 0;
        inventory = bag;

        // CUSTOM INVENTORY SLOTS
        for (i = 0; i < InventoryItem.INV_SIZE; ++i) {
            addSlotToContainer(new SlotItemInv(inventory, i, 80 + (18 * (i % 5)), 8 + (18 * (int) (i / 5))));
        }

        // ARMOR SLOTS
        /*for (i = 0; i < 4; ++i) {
         addSlotToContainer(new SlotArmor(player, inv, inv.getSizeInventory() - 1 - i, 8, 8 + i * 18, i));
         }*/
        // PLAYER INVENTORY - uses default locations for standard inventory texture file
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // PLAYER ACTION BAR - uses default locations for standard action bar texture file
        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(inv, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        // be sure to return the inventory's isUseableByPlayer method
        // if you defined special behavior there:
        return inventory.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            // If item is in our custom Inventory or an ARMOR slot
            if (index < INV_START) {
                // try to place in player inventory / action bar
                if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } // Item is in inventory / hotbar, try to place in custom inventory or armor slots
            else {
                // Item being shift-clicked is armor - try to put in armor slot
                if (itemstack1.getItem() instanceof ItemArmor) {
                    int type = ((ItemArmor) itemstack1.getItem()).armorType;
                    if (!this.mergeItemStack(itemstack1, ARMOR_START + type, ARMOR_START + type + 1, false)) {
                        return null;
                    }
                } // item is in inventory or action bar
                else if (index >= INV_START) {
                    // place in custom inventory
                    if (!this.mergeItemStack(itemstack1, 0, ARMOR_START, false)) {
                        return null;
                    }
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) {
        // this will prevent the player from interacting with the item that opened the inventory:
        if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) {
            return null;
        }
        return super.slotClick(slot, button, flag, player);
    }

    // IMPORTANT to override the mergeItemStack method if your inventory stack size limit is 1
    /*
     * Vanilla method fails to account for stack size limits of one, resulting
     * in only one item getting placed in the slot and the rest disappearing
     * into thin air; vanilla method also fails to check whether stack is valid
     * for slot
     */
    @Override
    protected boolean mergeItemStack(ItemStack stack, int start, int end, boolean backwards) {
        boolean flag1 = false;
        int k = (backwards ? end - 1 : start);
        Slot slot;
        ItemStack itemstack1;

        if (stack.isStackable()) {
            while (stack.stackSize > 0 && (!backwards && k < end || backwards && k >= start)) {
                slot = (Slot) inventorySlots.get(k);
                itemstack1 = slot.getStack();

                if (!slot.isItemValid(stack)) {
                    k += (backwards ? -1 : 1);
                    continue;
                }

                if (itemstack1 != null && itemstack1.getItem() == stack.getItem()
                        && (!stack.getHasSubtypes() || stack.getItemDamage() == itemstack1.getItemDamage())
                        && ItemStack.areItemStackTagsEqual(stack, itemstack1)) {
                    int l = itemstack1.stackSize + stack.stackSize;

                    if (l <= stack.getMaxStackSize() && l <= slot.getSlotStackLimit()) {
                        stack.stackSize = 0;
                        itemstack1.stackSize = l;
                        inventory.markDirty();
                        flag1 = true;
                    } else if (itemstack1.stackSize < stack.getMaxStackSize() && l < slot.getSlotStackLimit()) {
                        stack.stackSize -= stack.getMaxStackSize() - itemstack1.stackSize;
                        itemstack1.stackSize = stack.getMaxStackSize();
                        inventory.markDirty();
                        flag1 = true;
                    }
                }

                k += (backwards ? -1 : 1);
            }
        }

        if (stack.stackSize > 0) {
            k = (backwards ? end - 1 : start);

            while (!backwards && k < end || backwards && k >= start) {
                slot = (Slot) inventorySlots.get(k);
                itemstack1 = slot.getStack();

                if (!slot.isItemValid(stack)) {
                    k += (backwards ? -1 : 1);
                    continue;
                }

                if (itemstack1 == null) {
                    int l = stack.stackSize;

                    if (l <= slot.getSlotStackLimit()) {
                        slot.putStack(stack.copy());
                        stack.stackSize = 0;
                        inventory.markDirty();
                        flag1 = true;
                        break;
                    } else {
                        putStackInSlot(k, new ItemStack(stack.getItem(), slot.getSlotStackLimit(), stack.getItemDamage()));
                        stack.stackSize -= slot.getSlotStackLimit();
                        inventory.markDirty();
                        flag1 = true;
                    }
                }

                k += (backwards ? -1 : 1);
            }
        }

        return flag1;
    }
}
