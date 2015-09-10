package com.exo594.tutorial.handlers;

import com.exo594.tutorial.item.ModItems;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler{
    
    private static int burnTime = 0;
    
    @Override
    public int getBurnTime(ItemStack fuel){
        if(fuel.getItem() == ModItems.tutoriumRound){
            burnTime = 3200;
        }
        return burnTime;
    }
    
}
