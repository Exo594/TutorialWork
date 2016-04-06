package com.exo594.tutorial.item;

import com.exo594.tutorial.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItembTutFlower extends Item {

    public ItembTutFlower() {
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setUnlocalizedName("itemTutorialFlower");
    }

    public String getTextureFile() {
        return "/tutorial/textures/items/tutorialFlower.png";
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int side, float par8, float par9, float par10) {
        if (par2EntityPlayer.canPlayerEdit(x, y, z, side, par1ItemStack)) {
            if (par3World.getBlock(x, y, z) == ModBlocks.tutorialBlock) {
                if (side == 1) {
                    if (par3World.isAirBlock(x, y + 1, z)) {
                        --par1ItemStack.stackSize;
                        par3World.setBlock(x, y + 1, z, ModBlocks.tutorialBlockFlower);
                    return true;
                    }
                    
                }
            }
        }
        return false;
    }
}
