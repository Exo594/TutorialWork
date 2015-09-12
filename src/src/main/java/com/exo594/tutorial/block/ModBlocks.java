package com.exo594.tutorial.block;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

    public static Block tutorialBlock;
    //public static BlockTutorialFlower tutorialBlockFlower;
    public static Block tutorial_ore;
    public static Block tutorial_multi_ore;

    public static Block blockTutoriumFurnaceIdle;
    public static Block blockTutoriumFurnaceActive;

    public static Block blockIngotMasherIdle;
    public static Block blockIngotMasherActive;
    
    public static Block blockMiningCharge;

    public static final void init() {
        GameRegistry.registerBlock(tutorialBlock = new BasicBlock("tutorialBlock", Material.iron), "tutorialBlock");
        //GameRegistry.registerBlock(tutorialBlockFlower = new BlockTutorialFlower(174, 3), "TutorialFlower");
        GameRegistry.registerBlock(tutorial_ore = new ModBlockOre("tutorial_ore", Material.rock, ModItems.tutorialItem, 2, 4), "tutorial_ore");
        GameRegistry.registerBlock(tutorial_multi_ore = new ModBlockMultiOre("tutorial_multi_ore", Material.rock), "tutorial_multi_ore");

        GameRegistry.registerBlock(blockTutoriumFurnaceIdle = new TutoriumFurnace("tutoriumFurnaceIdle", false, 1F), "tutoriumFurnaceIdle").setCreativeTab(Main.tabTutorialMod);
        GameRegistry.registerBlock(blockTutoriumFurnaceActive = new TutoriumFurnace("tutoriumFurnaceActive", true, 1F), "tutoriumFurnaceActive");

        GameRegistry.registerBlock(blockIngotMasherIdle = new IngotMasher("IngotMasherIdle", false), "IngotMasherIdle").setCreativeTab(Main.tabTutorialMod).setHardness(3.5F);;
        GameRegistry.registerBlock(blockIngotMasherActive = new IngotMasher("IngotMasherActive", true), "IngotMasherActive").setHardness(3.5F);;
        
        GameRegistry.registerBlock(blockMiningCharge = new MiningCharge("BlockMiningCharge", 5F), "BlockMiningCharge");
    }

}
