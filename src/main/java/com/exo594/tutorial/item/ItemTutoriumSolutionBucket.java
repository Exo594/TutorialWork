package com.exo594.tutorial.item;

import com.exo594.tutorial.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class ItemTutoriumSolutionBucket extends ItemBucket{
    
    public ItemTutoriumSolutionBucket(Block block) {
      super(ModBlocks.blockTutoriumSolution);
        this.setContainerItem(Items.bucket);
        this.setUnlocalizedName("itemTutoriumSolutionBucket");
        this.maxStackSize = 1;
   }

}