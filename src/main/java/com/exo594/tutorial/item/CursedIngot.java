package com.exo594.tutorial.item;

import com.exo594.tutorial.entities.EntityCursedIngot;
import com.exo594.tutorial.Main;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CursedIngot extends Item{
    
    public CursedIngot(String unlocalizedName){
        super();
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
        this.setCreativeTab(Main.tabTutorialMod);
    }
    
    @Override
    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public boolean hasCustomEntity(ItemStack stack){
        return true;
    }
    
    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        EntityCursedIngot cursedIngot = new EntityCursedIngot(world, location.posX, location.posY, location.posZ,itemstack);
	cursedIngot.delayBeforeCanPickup = 10;
	cursedIngot.motionX = location.motionX;
	cursedIngot.motionY = location.motionY;
	cursedIngot.motionZ = location.motionZ;
	return cursedIngot;
    }
    
}
