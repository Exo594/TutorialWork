package com.exo594.tutorial.item;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.entities.EntityDroughtSeed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DroughtSeed extends Item {

    private int diameter;
    private boolean consumed;

    public DroughtSeed(String unlocalizedName, int diameter, boolean consumed) {
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.diameter = diameter;
        this.consumed = consumed;
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
        this.setCreativeTab(Main.tabTutorialMod);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

            player.inventory.decrStackSize(player.inventory.currentItem, 1);

            if (!world.isRemote) {
                world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                EntityDroughtSeed entity = new EntityDroughtSeed(world, player);
                entity.setDiameter(this.diameter);
                world.spawnEntityInWorld(entity);
            }
        
        return stack;

    }
}
