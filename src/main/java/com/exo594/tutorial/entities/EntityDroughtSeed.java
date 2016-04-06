
package com.exo594.tutorial.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDroughtSeed extends EntityThrowable {

    private double speed = 1.5;
    private int diameter;

    public EntityDroughtSeed(World world) {
        super(world);
    }

    public EntityDroughtSeed(World world, EntityLivingBase entityBase) {
        super(world, entityBase);
    }

    public EntityDroughtSeed(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
    
    @Override
    public void onUpdate(){
        if(worldObj.getBlock((int) this.posX, (int) this.posY,(int) this.posZ) == Blocks.water){
            this.deleteWater(worldObj, (int) this.posX, (int) this.posY,(int) this.posZ);
            this.setDead();
        }
        super.onUpdate();
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

    private void deleteWater(World world, int x, int y, int z) {
        for (int i = 0; i < this.diameter; i++) {
            for (int j = 0; j < this.diameter; j++) {
                for (int k = 0; k < this.diameter; k++) {
                    if (world.getBlock((x - ((this.diameter - 1) / 2) + i), (y - ((this.diameter - 1) / 2) + j), (z - ((this.diameter - 1) / 2)) + k) == Blocks.water) {
                        world.setBlock((x - ((this.diameter - 1) / 2) + i), (y - ((this.diameter - 1) / 2) + j), ((z - ((this.diameter - 1) / 2)) + k), Blocks.air, 0, 2); //What number should REALLY go here?
                    }
                }

            }
        }
    }
    
    public void setDiameter(int diameter){
        this.diameter = diameter;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        this.deleteWater(worldObj, (int) this.posX, (int) this.posY,(int) this.posZ);
        this.setDead();
    }
}
