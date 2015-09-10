package com.exo594.tutorial.modplants;

import com.exo594.tutorial.Main;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockTutoriumBerry extends RecipeBlockCrops {

    public BlockTutoriumBerry() {
        setBlockName("tutoriumBerries");
        setBlockTextureName(Main.MODID + ":tutoriumBerries_stage_0");
    }


    @Override
    public int quantityDropped(int parMetadata, int parFortune, Random parRand) {
        return (parMetadata / 2);
    }

    @Override
    public Item getItemDropped(int parMetadata, Random parRand, int parFortune) {
        return (ModPlants.tutoriumBerry);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister parIIconRegister) {
        iIcon = new IIcon[maxGrowthStage + 1];
        iIcon[0] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_0");
        iIcon[1] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_1");
        iIcon[2] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_2");
        iIcon[3] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_3");
        iIcon[4] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_4");
        iIcon[5] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_5");
        iIcon[6] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_6");
        iIcon[7] = parIIconRegister.registerIcon(Main.MODID + ":tutoriumBerries_stage_7");
    }
}
