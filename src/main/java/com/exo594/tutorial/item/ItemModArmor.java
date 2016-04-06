package com.exo594.tutorial.item;

import com.exo594.tutorial.ClientProxy;
import com.exo594.tutorial.Main;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.nodes.IRevealer;

public class ItemModArmor extends ItemArmor implements IRevealer, IGoggles, IRunicArmor, IWarpingGear {

    public String textureName;

    public ItemModArmor(String unlocalizedName, ArmorMaterial material, String textureName, int type, int render_idx) {
        super(material, render_idx, type);
        this.textureName = textureName;
        this.setUnlocalizedName(unlocalizedName);
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
        canRepair = true;

    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repairMaterial) {
        if (repairMaterial.getItem().equals(ModItems.tutorialItem) && toRepair.getItem().equals(this)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return Main.MODID + ":textures/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (player.getCurrentArmor(1) != null && player.getCurrentArmor(1).getItem().equals(ModItems.tutorialLeggings)) {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40, 1));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot) {

        ModelBiped armorModel = ClientProxy.armorModels.get(this);

        if (armorModel != null) {
            armorModel.bipedHead.showModel = armorSlot == 0;
            armorModel.bipedHeadwear.showModel = false;
            armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
            armorModel.bipedRightArm.showModel = armorSlot == 1;
            armorModel.bipedLeftArm.showModel = armorSlot == 1;
            armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
            armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();

            armorModel.heldItemRight = 0;
            armorModel.aimedBow = false;

            EntityPlayer player = (EntityPlayer) entityLiving;

            ItemStack held_item = player.getEquipmentInSlot(0);

            if (held_item != null) {
                armorModel.heldItemRight = 1;

                if (player.getItemInUseCount() > 0) {

                    EnumAction enumaction = held_item.getItemUseAction();

                    if (enumaction == EnumAction.bow) {
                        armorModel.aimedBow = true;
                    } else if (enumaction == EnumAction.block) {
                        armorModel.heldItemRight = 3;
                    }

                }

            }

        }

        return armorModel;
    }

    @Override
    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            EntityPlayer entplayer = (EntityPlayer) player;
            if (entplayer.getCurrentArmor(3) != null && entplayer.getCurrentArmor(3).getItem().equals(ModItems.tutorialHelmet)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            EntityPlayer entplayer = (EntityPlayer) player;
            if (entplayer.getCurrentArmor(3) != null && entplayer.getCurrentArmor(3).getItem().equals(ModItems.tutorialHelmet)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 5;
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 5;
    }

}
