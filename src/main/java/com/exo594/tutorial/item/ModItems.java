package com.exo594.tutorial.item;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.block.ModBlocks;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;

public final class ModItems {

    public static Item tutorialItem;
    public static Item tutorialSword;
    public static Item tutorialMultitool;

    public static Item tutorialHelmet;
    public static Item tutorialChestplate;
    public static Item tutorialLeggings;
    public static Item tutorialBoots;

    public static Item tutorial3dHelmet;
    public static Item tutorial3dChestplate;
    public static Item tutorial3dLeggings;
    public static Item tutorial3dBoots;

    public static Item tutorialRifle;
    public static Item tutoriumGrenade;
    public static Item tutoriumRound;

    public static Item tutoriumLoaf;

    public static Item tutorialFlower;
    public static Item tutorialKey;

    public static Item craftingPendulum;

    public static Item furnaceToken;

    public static Item itemPack;

    public static Item itemTutoriumSolutionBucket;

    public static Item itemDroughtSeed1;
    public static Item itemDroughtSeed3;
    public static Item itemDroughtSeed5;
    public static Item itemDroughtSeed7;
    public static Item itemDroughtSeed9;
    public static Item itemDroughtSeed11;
    public static Item itemDroughtSeed13;
    public static Item itemDroughtSeed15;

    public static Item cursedIngot;
    
    public static Item woodKukri;
    public static Item stoneKukri;
    public static Item ironKukri;
    public static Item goldKukri;
    public static Item diamondKukri;
    public static Item tutoriumKukri;
    
    public static final Item.ToolMaterial TUTORIAL = EnumHelper.addToolMaterial("tutorialItem", 10, -1, 30.0F, 10.0F, 30);
    public static final ItemArmor.ArmorMaterial TutorialArmor = EnumHelper.addArmorMaterial("tutorialItem", 100, new int[]{3, 8, 6, 3}, 40);

    public static final void init() {

        tutorialRifle = new ItemTutorialGun().setTextureName(Main.MODID + ":tutorialRifle");
        tutoriumGrenade = new ItemTutoriumGrenade().setTextureName(Main.MODID + ":tutoriumGrenade");
        tutoriumRound = new ItemTutoriumRound().setTextureName(Main.MODID + ":tutoriumRound");

        tutorialItem = new Item().setUnlocalizedName("tutorialItem").setCreativeTab(CreativeTabs.tabMisc).setTextureName(Main.MODID + ":tutorialItem");
        GameRegistry.registerItem(tutorialItem, "tutorialItem");

        tutoriumLoaf = new ItemFood(10, 10F, true).setUnlocalizedName("tutoriumLoaf").setTextureName(Main.MODID + ":tutoriumLoaf");
        GameRegistry.registerItem(tutoriumLoaf, "tutoriumLoaf");

        tutorialKey = new ItemTutorialKey();
        GameRegistry.registerItem(tutorialKey, "tutorialKey");

        craftingPendulum = new BreakableItem("craftingPendulum", 128);
        GameRegistry.registerItem(craftingPendulum, "craftingPendulum");

        furnaceToken = new Item().setUnlocalizedName("furnaceToken").setTextureName(Main.MODID + ":furnaceToken").setCreativeTab(Main.tabTutorialMod);
        GameRegistry.registerItem(furnaceToken, "furnaceToken");

        itemPack = new ItemPack().setUnlocalizedName("itemPack").setCreativeTab(Main.tabTutorialMod);
        GameRegistry.registerItem(itemPack, "itemPack");

        GameRegistry.registerItem(tutorialSword = new ItemModSword("tutorial_sword", 10, -1, 30.0F, 10.0F, 30), "tutorial_sword");
        GameRegistry.registerItem(tutorialMultitool = new ItemModMultitool("tutorial_multitool", TUTORIAL), "tutorial_multitool");

        GameRegistry.registerItem(tutorialHelmet = new ItemModArmor("tutorial_helmet", TutorialArmor, "tutorial_armor", 0, 0), "tutorial_helmet");
        GameRegistry.registerItem(tutorialChestplate = new ItemModArmor("tutorial_chestplate", TutorialArmor, "tutorial_armor", 1, 0), "tutorial_chestplate");
        GameRegistry.registerItem(tutorialLeggings = new ItemModArmor("tutorial_leggings", TutorialArmor, "tutorial_armor", 2, 0), "tutorial_leggings");
        GameRegistry.registerItem(tutorialBoots = new ItemModArmor("tutorial_boots", TutorialArmor, "tutorial_armor", 3, 0), "tutorial_boots");

        GameRegistry.registerItem(tutorial3dHelmet = new ItemModArmor("tutorial_3d_helmet", TutorialArmor, "tutorial_3d_armor", 0, 1), "tutorial_3d_helmet");
        GameRegistry.registerItem(tutorial3dChestplate = new ItemModArmor("tutorial_3d_chestplate", TutorialArmor, "tutorial_3d_armor", 1, 1), "tutorial_3d_chestplate");
        GameRegistry.registerItem(tutorial3dLeggings = new ItemModArmor("tutorial_3d_leggings", TutorialArmor, "tutorial_3d_armor", 2, 1), "tutorial_3d_leggings");
        GameRegistry.registerItem(tutorial3dBoots = new ItemModArmor("tutorial_3d_boots", TutorialArmor, "tutorial_3d_armor", 3, 1), "tutorial_3d_boots");

        GameRegistry.registerItem(tutorialFlower = new ItembTutFlower(), "tutorialFlower");
        GameRegistry.registerItem(tutorialRifle, "tutorialRifle");
        GameRegistry.registerItem(tutoriumGrenade, "tutoriumGrenade");
        GameRegistry.registerItem(tutoriumRound, "tutoriumRound");

        GameRegistry.registerItem(itemDroughtSeed1 = new DroughtSeed("droughtBucket", 1, false), "droughtBucket");
        GameRegistry.registerItem(itemDroughtSeed3 = new DroughtSeed("droughtSeedDiameter3", 3, true), "droughtSeedDiameter3");
        GameRegistry.registerItem(itemDroughtSeed5 = new DroughtSeed("droughtSeedDiameter5", 5, true), "droughtSeedDiameter5");
        GameRegistry.registerItem(itemDroughtSeed7 = new DroughtSeed("droughtSeedDiameter7", 7, true), "droughtSeedDiameter7");
        GameRegistry.registerItem(itemDroughtSeed9 = new DroughtSeed("droughtSeedDiameter9", 9, true), "droughtSeedDiameter9");
        GameRegistry.registerItem(itemDroughtSeed11 = new DroughtSeed("droughtSeedDiameter11", 11, true), "droughtSeedDiameter11");
        GameRegistry.registerItem(itemDroughtSeed13 = new DroughtSeed("droughtSeedDiameter13", 13, true), "droughtSeedDiameter13");
        GameRegistry.registerItem(itemDroughtSeed15 = new DroughtSeed("droughtSeedDiameter15", 15, true), "droughtSeedDiameter15");
        
        GameRegistry.registerItem(cursedIngot = new CursedIngot("cursedIngot"), "cursedIngot");
        
        GameRegistry.registerItem(woodKukri = new ItemModKukri("wood_kukri", ToolMaterial.WOOD), "wood_kukri");
        GameRegistry.registerItem(stoneKukri = new ItemModKukri("stone_kukri", ToolMaterial.STONE), "stone_kukri");
        GameRegistry.registerItem(ironKukri = new ItemModKukri("iron_kukri", ToolMaterial.IRON), "iron_kukri");
        GameRegistry.registerItem(goldKukri = new ItemModKukri("gold_kukri", ToolMaterial.GOLD), "gold_kukri");
        GameRegistry.registerItem(diamondKukri = new ItemModKukri("diamond_kukri", ToolMaterial.EMERALD), "diamond_kukri");
        GameRegistry.registerItem(tutoriumKukri = new ItemModKukri("tutorium_kukri", TUTORIAL), "tutorium_kukri");
   
        GameRegistry.registerItem(itemTutoriumSolutionBucket = new ItemTutoriumSolutionBucket(ModBlocks.blockTutoriumSolution), "itemTutoriumSolutionBucket");
        FluidContainerRegistry.registerFluidContainer(ModBlocks.tutoriumSolution, new ItemStack(ModItems.itemTutoriumSolutionBucket), new ItemStack(Items.bucket));
    }

}
