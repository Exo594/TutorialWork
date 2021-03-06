package com.exo594.tutorial.block;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.item.ModItems;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModBlockMultiOre extends Block {

    protected ModBlockMultiOre(String unlocalizedName, Material material) {
        super(material);
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setStepSound(soundTypeStone);
        this.setHardness(10.0f);
        this.setResistance(20.0f);
        this.setHarvestLevel("pickaxe", 2);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(Items.coal, world.rand.nextInt(3) + 1));
        drops.add(new ItemStack(Items.iron_ingot, world.rand.nextInt(2) + 1));
        drops.add(new ItemStack(Items.gold_ingot, world.rand.nextInt(2) + 1));
        drops.add(new ItemStack(Items.dye, world.rand.nextInt(3) + 2, 4));
        drops.add(new ItemStack(Items.redstone, world.rand.nextInt(2) + 2));
        drops.add(new ItemStack(ModItems.tutorialItem, world.rand.nextInt(2) + 1));
        if (world.rand.nextFloat() < 0.5F) {
            drops.add(new ItemStack(Items.diamond));
        }
        return drops;
    }
}
