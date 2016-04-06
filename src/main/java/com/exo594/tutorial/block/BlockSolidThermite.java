package com.exo594.tutorial.block;

import com.exo594.tutorial.Main;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockSolidThermite extends Block{
    
    public BlockSolidThermite(String unlocalizedName, Material material){
        super(material);
        this.setBlockName(unlocalizedName);
        this.setCreativeTab(Main.tabTutorialMod);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon(Main.MODID + ":tutoriumFurnaceOther");
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);

        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlock(x, y, z, ModBlocks.blockMoltenThermite, 0, 3);
            this.passHeatToLocals(world, x, y, z);
        }
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlock(x, y, z, ModBlocks.blockMoltenThermite, 0, 3);
            this.passHeatToLocals(world, x, y, z);
        }
    }
    
    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 1;
    }
    
    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion p_149723_5_) {
        if (!world.isRemote) {
            world.setBlock(x, y, z, ModBlocks.blockMoltenThermite, 0, 3);
            this.passHeatToLocals(world, x, y, z);
        }
    }
    
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {
        this.func_150114_a(world, x, y, z, metaData, (EntityLivingBase) null);
    }

    public void func_150114_a(World world, int x, int y, int z, int metaData, EntityLivingBase p_150114_6_) {
        if (!world.isRemote) {
            if ((metaData & 1) == 1) {  //? The fuck is this for.
                world.setBlock(x, y, z, ModBlocks.blockMoltenThermite, 0, 3);
                this.passHeatToLocals(world, x, y, z);
            }
        }
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
            this.func_150114_a(world, x, y, z, 1, player);
            world.setBlock(x, y, z, ModBlocks.blockMoltenThermite, 0, 3);
            this.passHeatToLocals(world, x, y, z);
            player.getCurrentEquippedItem().damageItem(1, player);
            return true;
        } else {
            return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        }
    }
    
    @Override
    public boolean canDropFromExplosion(Explosion p_149659_1_) {
        return false;
    }
    
    public void passHeatToLocals(World world, int x, int y, int z){
        BlockSolidThermite neighBlock;
        if(world.getBlock(x+1, y, z) == ModBlocks.blockSolidThermite){
            neighBlock = (BlockSolidThermite)(world.getBlock(x+1, y, z));
            neighBlock.func_150114_a(world, x+1, y, z, 1, (EntityLivingBase) null);  
        }
        if(world.getBlock(x-1, y, z) == ModBlocks.blockSolidThermite){
            neighBlock = (BlockSolidThermite)(world.getBlock(x-1, y, z));
            neighBlock.func_150114_a(world, x-1, y, z, 1, (EntityLivingBase) null);  
        }
        if(world.getBlock(x, y+1, z) == ModBlocks.blockSolidThermite){
            neighBlock = (BlockSolidThermite)(world.getBlock(x, y+1, z));
            neighBlock.func_150114_a(world, x, y+1, z, 1, (EntityLivingBase) null);    
        }
        if(world.getBlock(x, y-1, z) == ModBlocks.blockSolidThermite){
            neighBlock = (BlockSolidThermite)(world.getBlock(x, y-1, z));
            neighBlock.func_150114_a(world, x, y-1, z, 1, (EntityLivingBase) null);    
        }
        if(world.getBlock(x, y, z+1) == ModBlocks.blockSolidThermite){
            neighBlock = (BlockSolidThermite)(world.getBlock(x, y, z+1));
            neighBlock.func_150114_a(world, x, y, z+1, 1, (EntityLivingBase) null);   
        }
        if(world.getBlock(x, y, z-1) == ModBlocks.blockSolidThermite){
            neighBlock = (BlockSolidThermite)(world.getBlock(x, y, z-1));
            neighBlock.func_150114_a(world, x, y, z-1, 1, (EntityLivingBase) null);  
        }
        
    }
}
