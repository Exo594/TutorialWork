package com.exo594.tutorial;

import com.exo594.tutorial.block.ModBlocks;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictDickery {
    
    public static final void init(){
        OreDictionary.registerOre("oreCopper", ModBlocks.tutorial_ore);        
    }
    
}
