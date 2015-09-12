package com.exo594.tutorial.item;

import com.exo594.tutorial.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItembTutFlower extends Item {

    public ItembTutFlower(int par1, int par2, float par3, boolean par4) {
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    public String getTextureFile() {
        return "/tutorial/textures/items/tutorialFlower.png";
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.getBlock(par4, par5, par6) != Blocks.snow) {
            if (par7 == 0) {
                --par5;
            }

            if (par7 == 1) {
                ++par5;
            }

            if (par7 == 2) {
                --par6;
            }

            if (par7 == 3) {
                ++par6;
            }

            if (par7 == 4) {
                --par4;
            }

            if (par7 == 5) {
                ++par4;
            }

            if (!par3World.isAirBlock(par4, par5, par6)) {
                return false;
            }
        }
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        } else {
            //if (ModItems.tutorialFlower.canPlaceBlockAt(par3World, par4, par5, par6)) {
                --par1ItemStack.stackSize;
                //par3World.setBlock(par4, par5, par6, ModBlocks.tutorialBlockFlower);
            //}
            return true;
        }
    }
}
