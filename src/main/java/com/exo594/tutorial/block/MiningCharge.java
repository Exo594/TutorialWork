package com.exo594.tutorial.block;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.entities.EntityMiningChargePrimed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class MiningCharge extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(Side.CLIENT)
    private IIcon bottomIcon;

    public MiningCharge(String unlocalizedName) {
        super(Material.tnt);
        this.setBlockName(unlocalizedName);
        this.setCreativeTab(Main.tabTutorialMod);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon(Main.MODID + ":tutoriumFurnaceOther");
        this.topIcon = icon.registerIcon(Main.MODID + ":miningCharge_top");
        this.bottomIcon = icon.registerIcon(Main.MODID + ":miningCharge_bottom");
    }
    
    /*
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? this.bottomIcon : (side == 1 ? this.topIcon : this.blockIcon);
    }

    /*
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);

        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlock(x, y, z, ModBlocks.tutorialBlock, 0, 3);
        }
    }

    /*
     * Lets the block know when one of its neighbor changes. Doesn't know which
     * neighbor changed (coordinates passed are their own) Args: x, y, z,
     * neighbor Block
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            this.onBlockDestroyedByPlayer(world, x, y, z, 1);
            world.setBlock(x, y, z, ModBlocks.tutorialBlock, 0, 3);
        }
    }

    /*
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random p_149745_1_) {
        return 1;
    }

    /*
     * Called upon the block being destroyed by an explosion
     */
    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion p_149723_5_) {
        if (!world.isRemote) {
            EntityMiningChargePrimed entitymcp = new EntityMiningChargePrimed(world, x, y, z, p_149723_5_.getExplosivePlacedBy());
            entitymcp.fuse = world.rand.nextInt(entitymcp.fuse / 4) + entitymcp.fuse / 8;
            world.spawnEntityInWorld(entitymcp);
            world.setBlock(x, y, z, ModBlocks.tutorialBlock, 0, 3);
        }
    }

    /*
     * Called right before the block is destroyed by a player. Args: world, x,
     * y, z, metaData
     */
    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData) {
        this.func_150114_a(world, x, y, z, metaData, (EntityLivingBase) null);
    }

    public void func_150114_a(World world, int x, int y, int z, int metaData, EntityLivingBase p_150114_6_) {
        if (!world.isRemote) {
            if ((metaData & 1) == 1) {
                EntityMiningChargePrimed entitymcp = new EntityMiningChargePrimed(world, x, y, z, p_150114_6_);
                world.spawnEntityInWorld(entitymcp);
                world.playSoundAtEntity(entitymcp, "game.tnt.primed", 1.0F, 1.0F);
                world.setBlock(x, y, z, ModBlocks.tutorialBlock, 0, 3);
            }
        }
    }

    /*
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel) {
            this.func_150114_a(world, x, y, z, 1, player);
            world.setBlock(x, y, z, ModBlocks.tutorialBlock, 0, 3);
            player.getCurrentEquippedItem().damageItem(1, player);
            return true;
        } else {
            return super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        }
    }

    /*
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     * Removed from functionality of MiningCharge
     */
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    }

    /*
     * Return whether this block can drop from an explosion.
     */
    @Override
    public boolean canDropFromExplosion(Explosion p_149659_1_) {
        return false;
    }
}
