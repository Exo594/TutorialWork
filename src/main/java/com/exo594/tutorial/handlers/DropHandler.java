package com.exo594.tutorial.handlers;

import com.exo594.tutorial.item.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class DropHandler {

    public static Random random;
    public static int dropped;
    public static int chance;

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {

        if (event.entityLiving instanceof EntityWitch) {
            random = new Random();
            dropped = random.nextInt(2) + 1; //DO NOT CHANGE THIS
            chance = random.nextInt(5);

            if (chance >= 3) {
                event.entityLiving.entityDropItem(new ItemStack(ModItems.tutorialItem), dropped);
            }
        }

    }

}
