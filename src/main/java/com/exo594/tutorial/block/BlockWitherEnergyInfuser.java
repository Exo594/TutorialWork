package com.exo594.tutorial.block;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.tileentities.TileEntityWEI;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWitherEnergyInfuser extends BlockContainer {
    
    private Random rand;
    private static boolean keepInventory = false;

    public BlockWitherEnergyInfuser(String unlocalizedName) {
        super(Material.iron);
        rand = new Random();
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(Main.MODID + ":" + unlocalizedName);
        this.setCreativeTab(Main.tabTutorialMod);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else if (!player.isSneaking()) {
            TileEntityWEI entity = (TileEntityWEI) world.getTileEntity(x, y, z);
            if (entity != null) {
                FMLNetworkHandler.openGui(player, Main.modInstance, Main.guiIDWEI, world, x, y, z);
            }
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityWEI();
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block oldblock, int oldMetadata) {
        if (!keepInventory) {
            TileEntityWEI tileentity = (TileEntityWEI) world.getTileEntity(x, y, z);

            if (tileentity != null) {
                for (int i = 0; i < tileentity.getSizeInventory(); i++) {
                    ItemStack itemstack = tileentity.getStackInSlot(i);

                    if (itemstack != null) {
                        float f = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int j = this.rand.nextInt(21) + 10;

                            if (j > itemstack.stackSize) {
                                j = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j;

                            EntityItem item = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound()) {
                                item.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                            }

                            world.spawnEntityInWorld(item);
                        }
                    }
                }

                world.func_147453_f(x, y, z, oldblock);
            }
        }

        super.breakBlock(world, x, y, z, oldblock, oldMetadata);
    }
}
