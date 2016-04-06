package com.exo594.tutorial.block;

import com.exo594.tutorial.Main;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockMoltenThermite extends BlockFluidClassic {

    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;
    
    private int lifeTime = 0;

    public BlockMoltenThermite(Fluid fluid, Material material) {
        super(fluid, material);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        stillIcon = register.registerIcon(Main.MODID + ":lava_still");
        flowingIcon = register.registerIcon(Main.MODID + ":lava_flow");
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        return world.getBlock(x, y, z) != Blocks.bedrock && world.getBlock(x, y, z) != Blocks.obsidian && world.getBlock(x, y, z) != ModBlocks.tutorialBlock && world.getBlock(x, y, z) != this;
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
        return world.getBlock(x, y, z) != Blocks.bedrock && world.getBlock(x, y, z) != Blocks.obsidian && world.getBlock(x, y, z) != ModBlocks.tutorialBlock && world.getBlock(x, y, z) != this;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
        
        lifeTime++;
        
        if (lifeTime >= 200){
            world.setBlock(x, y, z, ModBlocks.tutorial_ore);
        }
       
    }
}