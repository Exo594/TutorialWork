package com.exo594.tutorial.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTutorialGun extends Item {

    public ItemTutorialGun() {
        super();
        setCreativeTab(CreativeTabs.tabCombat);
        setUnlocalizedName("tutorialRifle");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.inventory.consumeInventoryItem(ModItems.tutoriumRound)) {
            if (!par2World.isRemote) {
                par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                par2World.spawnEntityInWorld(new EntityTutoriumRound(par2World, par3EntityPlayer));
            }
        }
        return par1ItemStack;

    }
}
