package com.exo594.tutorial.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class TutoriumGrenade extends EntityThrowable {

    public TutoriumGrenade(World p_i1773_1_) {
        super(p_i1773_1_);
    }

    public TutoriumGrenade(World p_i1774_1_, EntityLivingBase p_i1774_2_) {
        super(p_i1774_1_, p_i1774_2_);
    }

    public TutoriumGrenade(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_) {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 5, true);
        if (par1MovingObjectPosition.entityHit != null) {
            byte b0 = 10;

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) b0);
        }

        for (int i = 0; i < 8; ++i) {
            this.worldObj.spawnParticle("crit", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
