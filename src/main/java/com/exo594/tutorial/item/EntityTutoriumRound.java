package com.exo594.tutorial.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTutoriumRound extends EntityThrowable {

    private double speed = 3.5;

    public EntityTutoriumRound(World par1World) {
        super(par1World);
    }

    public EntityTutoriumRound(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
    }

    public EntityTutoriumRound(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1, true);
        if (par1MovingObjectPosition.entityHit != null) {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.magic, 5.0F);
        }
        this.setDead();
    }

    @Override
    protected float getGravityVelocity() {

        this.motionX *= speed;
        this.motionY *= speed;
        this.motionZ *= speed;
        return 0;

    }

    public float getAirResistance() {
        return 0.0F;
    }

    public float getGravity() {
        return 0.0F;
    }

}
