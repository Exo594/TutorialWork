package com.exo594.tutorial.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTutoriumGrenade extends Item {

    public ItemTutoriumGrenade() {
        super();
        this.setMaxStackSize(8);
        this.setCreativeTab(CreativeTabs.tabCombat);
        setUnlocalizedName("tutoriumGrenade");

    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        --stack.stackSize;
        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!world.isRemote) {
            world.spawnEntityInWorld(new TutoriumGrenade(world, player));
        }

        return stack;
    }

}
