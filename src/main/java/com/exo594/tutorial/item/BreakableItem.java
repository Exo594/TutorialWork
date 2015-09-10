package com.exo594.tutorial.item;

import com.exo594.tutorial.Main;
import net.minecraft.item.Item;


public class BreakableItem extends Item{
    
    public BreakableItem(String unlocalizedName, int durability){
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
        this.maxStackSize = 1;
        this.setMaxDamage(durability);
        this.setNoRepair();
        this.setCreativeTab(Main.tabTutorialMod);
    }
    
}
