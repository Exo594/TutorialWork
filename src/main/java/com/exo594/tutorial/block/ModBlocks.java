package com.exo594.tutorial.block;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModBlocks {
    
    public static Fluid tutoriumSolution;
    public static Block blockTutoriumSolution;
    
    public static Fluid moltenThermite;
    public static Block blockMoltenThermite;
    
    public static Block blockSolidThermite;
    
    public static Block tutorialBlock;
    public static BlockTutorialFlower tutorialBlockFlower;
    public static Block tutorial_ore;
    public static Block tutorial_multi_ore;

    public static Block blockTutoriumFurnaceIdle;
    public static Block blockTutoriumFurnaceActive;

    public static Block blockIngotMasherIdle;
    public static Block blockIngotMasherActive;
    
    public static Block blockTemporalChamberIdle;
    public static Block blockTemporalChamberActive;
    
    public static Block blockMiningCharge;
    
    public static Block blockWEI;
     

    public static final void init() {
        FluidRegistry.registerFluid(tutoriumSolution = new Fluid("tutoriumSolution").setViscosity(1000).setDensity(1010));
        blockTutoriumSolution = new BlockTutoriumSolution(tutoriumSolution, Material.water).setBlockName("tutoriumSolution");
        GameRegistry.registerBlock(blockTutoriumSolution, "blockTutoriumSolution");
        tutoriumSolution.setUnlocalizedName(blockTutoriumSolution.getUnlocalizedName());
        
        FluidRegistry.registerFluid(moltenThermite = new Fluid("tutoriumSolution").setViscosity(3000).setDensity(3000));
        blockMoltenThermite = new BlockMoltenThermite(moltenThermite, Material.lava).setBlockName("tutoriumSolution");
        GameRegistry.registerBlock(blockMoltenThermite, "blockMoltenThermite");
        moltenThermite.setUnlocalizedName(blockMoltenThermite.getUnlocalizedName());
        
        blockSolidThermite = new BlockSolidThermite("solidThermite", Material.iron);
        GameRegistry.registerBlock(blockSolidThermite, "blockSolidThermite");
        
        GameRegistry.registerBlock(tutorialBlock = new BasicBlock("tutorialBlock", Material.rock), "tutorialBlock").setLightLevel(2F);
        
        GameRegistry.registerBlock(tutorialBlockFlower = new BlockTutorialFlower(), "TutorialFlower");
        
        GameRegistry.registerBlock(tutorial_ore = new ModBlockOre("tutorial_ore", Material.rock, ModItems.tutorialItem, 2, 4), "tutorial_ore");
        
        GameRegistry.registerBlock(tutorial_multi_ore = new ModBlockMultiOre("tutorial_multi_ore", Material.rock), "tutorial_multi_ore");

        GameRegistry.registerBlock(blockTutoriumFurnaceIdle = new TutoriumFurnace("tutoriumFurnaceIdle", false), "tutoriumFurnaceIdle").setCreativeTab(Main.tabTutorialMod);
        GameRegistry.registerBlock(blockTutoriumFurnaceActive = new TutoriumFurnace("tutoriumFurnaceActive", true), "tutoriumFurnaceActive");

        GameRegistry.registerBlock(blockIngotMasherIdle = new IngotMasher("ingotMasherIdle", false), "ingotMasherIdle").setCreativeTab(Main.tabTutorialMod).setHardness(3.5F);;
        GameRegistry.registerBlock(blockIngotMasherActive = new IngotMasher("ingotMasherActive", true), "ingotMasherActive").setHardness(3.5F);
        
        GameRegistry.registerBlock(blockTemporalChamberIdle = new TemporalChamber("temporalChamberIdle", false), "temporalChamberIdle").setCreativeTab(Main.tabTutorialMod);
        GameRegistry.registerBlock(blockTemporalChamberActive = new TemporalChamber("temporalChamberActive", true), "temporalChamberActive");
        
        GameRegistry.registerBlock(blockMiningCharge = new MiningCharge("blockMiningCharge"), "blockMiningCharge");
        
        GameRegistry.registerBlock(blockWEI = new BlockWitherEnergyInfuser("blockWEI"), "blockWEI");
    }

}
