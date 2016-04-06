package com.exo594.tutorial.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCursedIngot extends EntityItem {

    public EntityCursedIngot(final World world) {
        super(world);
    }

    public EntityCursedIngot(final World world, final double x, final double y, final double z, final ItemStack stack) {
        super(world, x, y, z, stack);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        float targetDistance = 64;
        World world = worldObj;
        Entity target = null;
        
        for (int i = 0; i < world.loadedEntityList.size(); i++) {
            if (((Entity) world.loadedEntityList.get(i)).getDistanceToEntity(this) < targetDistance) {
                if (world.loadedEntityList.get(i) instanceof EntityMob) {
                    targetDistance = ((Entity) world.loadedEntityList.get(i)).getDistanceToEntity(this);
                    target = ((Entity) world.loadedEntityList.get(i));
                }
            }
        }
        if (target != null) {
            world.addWeatherEffect(new EntityLightningBolt(world, target.posX, target.posY, target.posZ));
        }
    }
}
