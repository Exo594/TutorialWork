/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exo594.tutorial.handlers;

import com.exo594.tutorial.item.ModItems;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

/**
 *
 * @author Jacob Norman
 */
public class TradeHandler implements IVillageTradeHandler
{
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		switch(villager.getProfession()) {
		case 0: // FARMER
			// standard trade
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 2), new ItemStack(ModItems.itemDroughtSeed3, 1)));
			break;
		case 1: // LIBRARIAN
			// use metadata in either case
			recipeList.add(new MerchantRecipe(new ItemStack(Items.dye, 4, 15), // dye of metadata 15 is bonemeal, so we need 4 bonemeals
					new ItemStack(ModItems.cursedIngot, 1, 6))); // to buy 1 mod item of metadata value 6
			
			// use the vanilla Item method to easily construct an ItemStack containing an enchanted book of any level
                        recipeList.add(new MerchantRecipe(new ItemStack(Items.diamond, 1), Items.enchanted_book.getEnchantedItemStack(new EnchantmentData(Enchantment.flame, 1))));
			break;
		case 2: // PRIEST
			// trading two itemstacks for one itemstack in return
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 6), new ItemStack(ModItems.tutorialItem, 2), new ItemStack(ModItems.furnaceToken, 2)));
			break;
		case 3: // BLACKSMITH
			// using the passed in Random to randomize amounts; nextInt(value) returns an int between 0 and value (non-inclusive)
			recipeList.add(new MerchantRecipe(new ItemStack(Items.leather, 6 + random.nextInt(6)), new ItemStack(Items.bone, 5 + random.nextInt(4)), new ItemStack(ModItems.tutorial3dChestplate, 1)));
			break;
		case 4: // BUTCHER
			// You can also add directly to the villager with 2 different methods:
                    
                        //Exo594 EDIT: Doesn't appear to work in 1.7.10

			// Method 1: takes the list, an item ID that may be bought OR sold, rand, and a float value that
			// determines how common the trade is. The price of the item is determined in the HashMap
			// blacksmithSellingList, which we'll add our custom Item to first:
			//villager.blacksmithSellingList.put(ModItems.craftingPendulum, new Tuple(Integer.valueOf(4), Integer.valueOf(8)));
			// Then add the trade, which will buy or sell for between 4 and 8 emeralds
			//villager.addBlacksmithItem(recipeList, ModItems.craftingPendulum, random, 0.5F);

			// Method 2: Basically the same as above, but only for selling items and at a fixed price of 1 emerald
			// However, the stack sold will have a variable size determined by the HashMap villagerStockList,
			// to which we first need to add our custom Item:
			//villager.villagerStockList.put(Integer.valueOf(YourMod.YourItem.itemID), new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
			// Then add the trade, which will sell between 16 and 24 of our Item for 1 emerald
			//villager.addMerchantItem(recipeList, ItemToSell.itemID, random, 0.5F);
			break;
		default:
			break;
		}
	}
}