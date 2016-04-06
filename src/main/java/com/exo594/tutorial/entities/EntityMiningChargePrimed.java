package com.exo594.tutorial.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMiningChargePrimed extends Entity {

    public int fuse;
    private EntityLivingBase tntPlacedBy;
    public int originalPosY;

    public EntityMiningChargePrimed(World world) {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityMiningChargePrimed(World world, double x, int y, double z, EntityLivingBase player) {
        this(world);
        this.setPosition(x, -1, z);
        this.fuse = 60;
        this.tntPlacedBy = player;
        this.posX = x;
        this.originalPosY = y;
    }

    @Override
    protected void entityInit() {
    }

    /*
     * returns if this entity triggers Block.onEntityWalking on the blocks they
     * walk on. used for spiders and wolves to prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    /*
     * Returns true if other Entities should be prevented from moving through
     * this Entity.
     */
    @Override
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    /*
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        if (this.fuse-- <= 0) {
            this.setDead();
            worldObj.setBlockToAir((int)this.posX, (int)this.originalPosY + 1, (int)this.posZ);

            if (!this.worldObj.isRemote) {
                this.explode();
            }
        } else {
            this.worldObj.spawnParticle("smoke", this.posX, this.originalPosY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode() {
        float f = 1.5F;
        this.worldObj.createExplosion(this, this.posX, this.originalPosY, this.posZ, 3, true);
        for(int i = 0; i < 10; i++){
        this.worldObj.createExplosion(this, this.posX, this.originalPosY-i, this.posZ, f, true);
        }
    }

    /*
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    
    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        p_70014_1_.setByte("Fuse", (byte) this.fuse);
    }

    /*
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        this.fuse = p_70037_1_.getByte("Fuse");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getShadowSize() {
        return 0.0F;
    }

    /*
     * returns null or the entityliving it was placed or ignited by
     */
    public EntityLivingBase getTntPlacedBy() {
        return this.tntPlacedBy;
    }
}
