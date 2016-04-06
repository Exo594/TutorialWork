package com.exo594.tutorial.block;

import com.exo594.tutorial.item.ModItems;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockTutorialFlower extends Block{

    public BlockTutorialFlower() {
        super(Material.grass);
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);
        this.setBlockName("tutorialFlower");
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
    drops.add(new ItemStack(Items.dye, world.rand.nextInt(3) + 2, 4));
    drops.add(new ItemStack(Items.redstone, world.rand.nextInt(2) + 2));
    drops.add(new ItemStack(ModItems.tutorialItem, world.rand.nextInt(2) + 1));
    drops.add(new ItemStack(ModItems.tutorialFlower, 1));
    return drops;
    }
    
    public String getTextureFile() {
        return "/tutorial/textures/blocks/tutorialFlower.png"; //Textures for these are the same as blocks, ignore this line and use block textures. I'll change it soon :P
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

   
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

   
    @Override
    public int getRenderType()
    {
        return 1;
    }
}
