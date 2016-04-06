package com.exo594.tutorial.entities;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityCyclops extends EntityMob implements IBossDisplayData{

    public static Random random;
    public static int chance;

    public EntityCyclops(World world) {
        super(world);

        this.preventEntitySpawning = false;

        random = new Random();

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true));
    }

    /*
     @Override
     protected void addRandomArmor() {
     super.addRandomArmor();
     this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_axe));
     this.setCurrentItemOrArmor(1, new ItemStack(Items.chainmail_leggings));
     this.setCurrentItemOrArmor(1, new ItemStack(Items.chainmail_boots));
     }

     @Override
     protected Item getDropItem() {
     return Items.apple;
     }

     @Override
     protected void dropRareDrop(int par1) {
     this.entityDropItem(new ItemStack(Items.gold_ingot, 1, 1), 0.0F);
     }

     /*
     @Override
     protected String getLivingSound() {
     return "nealecraft:cyclops-say";
     }

     @Override
     protected String getHurtSound() {
     return "nealecraft:cyclops-hurt";
     }

    
     @Override
     protected String getDeathSound() {
     return "nealecraft:cyclops-death";
     }
     */
    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
    }

    @Override
    protected void applyEntityAttributes() {

        super.applyEntityAttributes();
        //this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(.5D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);

    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        chance = random.nextInt(100);

        if (chance > 95) {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2, true);
        }
    }

}
