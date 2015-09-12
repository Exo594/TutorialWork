package com.exo594.tutorial.item;

import com.exo594.tutorial.Main;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemModSword extends ItemSword {

    public ItemModSword(String unlocalizedName, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);

    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {
        list.add("A strong sword made from a strange material");
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
