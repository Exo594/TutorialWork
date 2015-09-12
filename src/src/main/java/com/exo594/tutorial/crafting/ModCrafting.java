package com.exo594.tutorial.crafting;

import static com.exo594.tutorial.IngotMasherRecipes.addMashingRecipeWithOneBlock;
import static com.exo594.tutorial.IngotMasherRecipes.addMashingRecipeWithTwoItems;
import com.exo594.tutorial.block.ModBlocks;
import com.exo594.tutorial.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModCrafting {

    public static final void init() {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tutorialItem), new Object[]{Items.redstone, new ItemStack(Items.dye, 1, 4)});
        GameRegistry.addSmelting(Items.diamond, new ItemStack(ModItems.tutorialItem), 1.0F);
        GameRegistry.addRecipe(new ItemStack(ModBlocks.tutorialBlock), "##", "##", '#', ModItems.tutorialItem);
        GameRegistry.addRecipe(new ItemStack(ModItems.tutorialKey), "###", "''#", '#', ModItems.tutorialItem);

        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.tutoriumRound, 32), new Object[]{ModItems.tutorialItem, Items.redstone, Items.glowstone_dust, new ItemStack(ModItems.craftingPendulum, 1, OreDictionary.WILDCARD_VALUE)});

        addMashingRecipeWithTwoItems(ModItems.tutorialItem, Items.bread, new ItemStack(ModItems.tutoriumLoaf));
        addMashingRecipeWithTwoItems(Items.iron_ingot, ModItems.tutorialItem, new ItemStack(ModItems.craftingPendulum));
        addMashingRecipeWithOneBlock(Blocks.glowstone, Items.diamond, new ItemStack(ModItems.tutoriumGrenade, 1));

    }

}
