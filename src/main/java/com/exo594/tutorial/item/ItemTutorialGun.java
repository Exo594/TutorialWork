package com.exo594.tutorial.item;

import com.exo594.tutorial.entities.EntityTutoriumRound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player) {

        if (player.isSneaking()) {
            player.inventory.decrStackSize(player.inventory.currentItem, 1);
            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(ModItems.tutorialMultitool));

        } else if (player.inventory.consumeInventoryItem(ModItems.tutoriumRound)) {
            if (!par2World.isRemote) {
                par2World.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                par2World.spawnEntityInWorld(new EntityTutoriumRound(par2World, player));
            }
        }
        return par1ItemStack;

    }
     
    @SideOnly(Side.CLIENT)
    @Override
    public boolean isFull3D()
    {
        return true;
    }
}
