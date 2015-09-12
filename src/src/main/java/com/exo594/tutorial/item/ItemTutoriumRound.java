package com.exo594.tutorial.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemTutoriumRound extends Item {

    public ItemTutoriumRound() {
        super();
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTabs.tabCombat);
        setUnlocalizedName("tutoriumRound");

    }
}