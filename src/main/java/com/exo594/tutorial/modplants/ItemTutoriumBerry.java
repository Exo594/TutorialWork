package com.exo594.tutorial.modplants;

import com.exo594.tutorial.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

public class ItemTutoriumBerry extends RecipeItemSeedFood {

    public ItemTutoriumBerry() {
        super(1, 0.3F, ModPlants.blockTutoriumBerry, Blocks.farmland);
        setUnlocalizedName("tutoriumBerry");
        setTextureName(Main.MODID + ":tutoriumBerry");
        setCreativeTab(CreativeTabs.tabFood);
    }
}
