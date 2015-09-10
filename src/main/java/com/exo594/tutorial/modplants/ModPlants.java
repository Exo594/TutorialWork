package com.exo594.tutorial.modplants;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ModPlants {
    
    public static Item tutoriumBerry;
    
    public final static Block blockTutoriumBerry = new BlockTutoriumBerry(); 
    
    public static final void init() {
        
        tutoriumBerry = new ItemTutoriumBerry();
        GameRegistry.registerItem(tutoriumBerry, "tutoriumBerry");
        
        
        GameRegistry.registerBlock(blockTutoriumBerry, "tutoriumBerries");
    }
    
}
