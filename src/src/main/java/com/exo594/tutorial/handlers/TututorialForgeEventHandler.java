package com.exo594.tutorial.handlers;

import com.exo594.tutorial.ModAchievements;
import com.exo594.tutorial.item.ModItems;
import com.exo594.tutorial.potions.ModPotions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class TututorialForgeEventHandler {

    EntityPlayer player;

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingUpdateEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            ItemStack heldItem = player.getHeldItem();
            if ((player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem().equals(ModItems.tutorialChestplate)) || player.capabilities.isCreativeMode) {
                player.capabilities.allowFlying = true;
            } else {
                player.capabilities.allowFlying = false;
            }
        }
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent e) {
        if (e.entityLiving.isPotionActive(ModPotions.tutoriumPotion)) {
            if (e.entityLiving.getActivePotionEffect(ModPotions.tutoriumPotion).getDuration() == 0) {
                e.entityLiving.removePotionEffect(ModPotions.tutoriumPotion.id);
            } else if (e.entityLiving.worldObj.rand.nextInt(10) == 0) {
                e.entityLiving.attackEntityFrom(DamageSource.generic, 2);
            }
        }
    }

    @SubscribeEvent
    public void notifyPickup(PlayerEvent.ItemPickupEvent e) {
        if (e.pickedUp.getEntityItem().isItemEqual(
                new ItemStack(ModItems.tutorialItem))) {
            e.player.addStat(ModAchievements.tutorialItem, 1);
        }
    }

    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent e) {
        if (e.crafting.getItem().equals(ModItems.tutoriumRound)) {
            e.player.addStat(ModAchievements.tutoriumRound, 1);
        }

    }

    @SubscribeEvent
    public void onSmelting(PlayerEvent.ItemSmeltedEvent e) {
        if (e.smelting.getItem().equals(ModItems.tutorialItem)) {
            e.player.addStat(ModAchievements.expensiveFuckery, 1);
        }
    }
}
