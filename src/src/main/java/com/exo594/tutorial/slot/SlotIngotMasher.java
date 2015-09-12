package com.exo594.tutorial.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotIngotMasher extends Slot {

    private EntityPlayer thePlayer;
    private int field_75228_b;

    public SlotIngotMasher(EntityPlayer player, IInventory iiventory, int i, int j, int k) {
        super(iiventory, i, j, k);
        this.thePlayer = player;
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        return false;
    }
    
    @Override
    public ItemStack decrStackSize(int p_75209_1_)
    {
        if (this.getHasStack())
        {
            this.field_75228_b += Math.min(p_75209_1_, this.getStack().stackSize);
        }

        return super.decrStackSize(p_75209_1_);
    }
}
