package com.exo594.tutorial.item;

import com.exo594.tutorial.entities.EntityTutoriumRound;
import com.exo594.tutorial.Main;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemModSword extends ItemSword {

    public int timeStored = 0;
    public int harvestLevel;
    public int durability;
    public float miningSpeed;
    public float damageVsEntities;
    public int enchantability;

    public ItemModSword(String unlocalizedName, int harvestLevel, int durability, float miningSpeed, float damageVsEntities, int enchantability) {
        super(EnumHelper.addToolMaterial("tutorialItem", harvestLevel, durability, miningSpeed, damageVsEntities, enchantability));
        this.setUnlocalizedName(unlocalizedName);
        this.harvestLevel = harvestLevel;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.damageVsEntities = damageVsEntities;
        this.enchantability = enchantability;
        this.setTextureName(Main.MODID + ":" + unlocalizedName);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) {
        list.add("A strong sword made from a strange material");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer par3EntityPlayer) {
        if (world.isRemote) {
            System.out.println(timeStored);
            System.out.println(durability);
        }
        if (par3EntityPlayer.inventory.consumeInventoryItem(ModItems.tutoriumRound)) {
            if (!world.isRemote) {
                world.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                world.spawnEntityInWorld(new EntityTutoriumRound(world, par3EntityPlayer));
            }
        }
        return par1ItemStack;
    }

    @Override                                   //Overrides MATERIAL attack damage for custom numbers. Currently using a RNG for damage output
    public Multimap getItemAttributeModifiers() {
        Random rand = new Random();

        Multimap multimap = super.getItemAttributeModifiers();
        multimap.clear();                                       //Req'd, otherwise multimap will not update with new random. Alternative is to create a new multimap every tick. I have no reason to believe so, but I think this current method is cheaper on memory n' shit than the alternative.
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (int) rand.nextInt(30), 0));
        multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 1, 1));
        
        return multimap;
    }
}
