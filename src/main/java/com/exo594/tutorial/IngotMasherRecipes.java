package com.exo594.tutorial;

import com.exo594.tutorial.item.ModItems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class IngotMasherRecipes {

    public static final IngotMasherRecipes mashingBase = new IngotMasherRecipes();

    private static Map<ArrayList<ItemStack>, ItemStack> mashingList = new HashMap<ArrayList<ItemStack>, ItemStack>();
    public static ArrayList<ItemStack> addingKeyList;

    private static final Item[] ITEMS = new Item[]{Items.bread, ModItems.tutorialItem};

    //ArrayList<ItemStack> addingValueList = new ArrayList<ItemStack>();
    public static IngotMasherRecipes mashing() {
        return mashingBase;
    }

    private IngotMasherRecipes() {
    }

    //used to tell transferItemInStack to play nice with items, apparantly not quite working properly
    public static Boolean itemCanBeMashed(ItemStack itemstack) {
        if (Arrays.asList(ITEMS).contains(itemstack.getItem())) {
            return true;
        } else {
            return false;
        }
    }

    public static void addMashingRecipeWithOneBlock(Block blockIn, Item itemIn, ItemStack itemOut) {
        addMashingRecipeWithTwoItems(Item.getItemFromBlock(blockIn), itemIn, itemOut);
    }

    public static void addMashingRecipeWithTwoBlocks(Block blockOneIn, Block blockTwoIn, ItemStack itemOut) {
        addMashingRecipeWithTwoItems(Item.getItemFromBlock(blockOneIn), Item.getItemFromBlock(blockTwoIn), itemOut);
    }

    public static void addMashingRecipeWithTwoItems(Item itemOneIn, Item itemTwoIn, ItemStack itemOut) {
        addRecipeFinal(new ItemStack(itemOneIn, 1, 0), new ItemStack(itemTwoIn, 1, 0), itemOut);
    }

    public static void addRecipeFinal(ItemStack itemOneIn, ItemStack itemTwoIn, ItemStack itemOut) {
        addingKeyList = new ArrayList<ItemStack>();
        addingKeyList.add(0, itemOneIn);
        addingKeyList.add(1, itemTwoIn);

        IngotMasherRecipes.mashingList.put(addingKeyList, itemOut);
    }

    public static ItemStack getMashingResult(ArrayList<ItemStack> list) {
        /*
         if (mashingList.containsKey(list)) {
         System.out.println("RETURNING ITEMSTACK!!!");
         return (ItemStack) mashingList.get(list);           
         } else {
         System.out.println("KEYS DON'T MATCH!!!");
         return null;                    
         }*/

        Iterator iterator = mashingList.entrySet().iterator();
        Map.Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entry = (Map.Entry) iterator.next();
        } while (!func_151397_a(list, (ArrayList<ItemStack>) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private static boolean func_151397_a(ArrayList<ItemStack> list, ArrayList<ItemStack> keyBeingChecked) {

        if (keyBeingChecked.isEmpty()) {
            return false;
        } else {
            Item itemKey0 = keyBeingChecked.get(0).getItem();
            Item itemKey1 = keyBeingChecked.get(1).getItem();
            Item itemList0 = list.get(0).getItem();
            Item itemList1 = list.get(1).getItem();

            if (itemKey0 == itemList0 && itemKey1 == itemList1) {
                return true;
            } else if (itemKey0 == itemList1 && itemKey1 == itemList0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Map getMashingList() {
        return this.mashingList;
    }
}
