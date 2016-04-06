/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exo594.tutorial.item;

import com.exo594.tutorial.Main;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/**
 *
 * @author Jacob Norman
 */
public class ItemModKukri extends ItemSword {
    
    float efficiencyOnProperMaterial;
    
    protected ItemModKukri(String unlocalizedName, ToolMaterial material) {
        super(material);
        this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
    }
    
    @Override
    public float func_150893_a(ItemStack p_150893_1_, Block block)
    {
        if (block == Blocks.web)
        {
            return 15.0F;
        }
        else
        {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd && material != Material.wood? 1.0F : this.efficiencyOnProperMaterial;
        }
    }
    
    
}
