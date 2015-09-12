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

    public EntityMiningChargePrimed(World world) {
        super(world);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityMiningChargePrimed(World world, double x, double y, double z, EntityLivingBase player) {
        this(world);
        this.setPosition(x, y, z);
        float f = (float) (Math.random() * Math.PI * 2.0D);
        this.motionX = (double) (-((float) Math.sin((double) f)) * 0.02F);
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double) (-((float) Math.cos((double) f)) * 0.02F);
        this.fuse = 80;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = player;
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
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0) {
            this.setDead();

            if (!this.worldObj.isRemote) {
                this.explode();
            }
        } else {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode() {
        float f = 1.5F;
        for(int i = 0; i < 10; i++){
        this.worldObj.createExplosion(this, this.posX, this.posY-1-i, this.posZ, f, true);
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
