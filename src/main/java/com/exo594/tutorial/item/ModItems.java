package com.exo594.tutorial.item;

import com.exo594.tutorial.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;

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
    
    //public static Item tutorialFlower;
    
    public static Item tutorialKey;
    
    public static Item craftingPendulum;
    
    public static Item furnaceToken;
    
    public static Item itemPack;

    public static final Item.ToolMaterial TUTORIAL = EnumHelper.addToolMaterial("tutorialItem", 10, -1, 30.0F, 10.0F, 30);
    public static final ItemArmor.ArmorMaterial TutorialArmor = EnumHelper.addArmorMaterial("tutorialItem", 100, new int[]{3,8,6,3}, 40);
    
    
    

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

        GameRegistry.registerItem(tutorialSword = new ItemModSword("tutorial_sword", TUTORIAL), "tutorial_sword");
        GameRegistry.registerItem(tutorialMultitool = new ItemModMultitool("tutorial_multitool", TUTORIAL), "tutorial_multitool");

        GameRegistry.registerItem(tutorialHelmet = new ItemModArmor("tutorial_helmet", TutorialArmor, "tutorial_armor", 0, 0), "tutorial_helmet");
        GameRegistry.registerItem(tutorialChestplate = new ItemModArmor("tutorial_chestplate", TutorialArmor, "tutorial_armor", 1, 0), "tutorial_chestplate");
        GameRegistry.registerItem(tutorialLeggings = new ItemModArmor("tutorial_leggings", TutorialArmor, "tutorial_armor", 2, 0), "tutorial_leggings");
        GameRegistry.registerItem(tutorialBoots = new ItemModArmor("tutorial_boots", TutorialArmor, "tutorial_armor", 3, 0), "tutorial_boots");
        
        GameRegistry.registerItem(tutorial3dHelmet = new ItemModArmor("tutorial_3d_helmet", TutorialArmor, "tutorial_3d_armor", 0, 1), "tutorial_3d_helmet");
        GameRegistry.registerItem(tutorial3dChestplate = new ItemModArmor("tutorial_3d_chestplate", TutorialArmor, "tutorial_3d_armor", 1, 1), "tutorial_3d_chestplate");
        GameRegistry.registerItem(tutorial3dLeggings = new ItemModArmor("tutorial_3d_leggings", TutorialArmor, "tutorial_3d_armor", 2, 1), "tutorial_3d_leggings");
        GameRegistry.registerItem(tutorial3dBoots = new ItemModArmor("tutorial_3d_boots", TutorialArmor, "tutorial_3d_armor", 3, 1), "tutorial_3d_boots");
        
        //GameRegistry.registerItem(tutorialFlower = new ItembTutFlower(1, 1, 1F, true));
        
        GameRegistry.registerItem(tutorialRifle, "tutorialRifle");
        GameRegistry.registerItem(tutoriumGrenade, "tutoriumGrenade");
        GameRegistry.registerItem(tutoriumRound, "tutoriumRound");
    }

}
