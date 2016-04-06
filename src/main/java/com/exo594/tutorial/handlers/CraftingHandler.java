package com.exo594.tutorial.handlers;

import com.exo594.tutorial.item.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CraftingHandler {
    
    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent event) {

        final IInventory craftMatrix = null;
        for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
            if (event.craftMatrix.getStackInSlot(i) != null) {
                ItemStack item0 = event.craftMatrix.getStackInSlot(i);
                if (item0 != null && item0.getItem() == ModItems.craftingPendulum) {
                    ItemStack k = new ItemStack(ModItems.craftingPendulum, 2, (item0.getItemDamage() + 1));

                    if (k.getItemDamage() >= k.getMaxDamage()) {
                        k.stackSize--;
                    }
                    event.craftMatrix.setInventorySlotContents(i, k);
                }
            }
        }
    }
    
    //For dev purposes
    @SubscribeEvent
    public void onPickup(PlayerEvent.ItemPickupEvent event){
        System.out.println(event.pickedUp);
    }

}
