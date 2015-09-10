package com.exo594.tutorial;

import com.exo594.tutorial.world.ModWorldGen;
import com.exo594.tutorial.block.ModBlocks;
import com.exo594.tutorial.crafting.ModCrafting;
import com.exo594.tutorial.enchantment.ModEnchantments;
import com.exo594.tutorial.item.ModItems;
import com.exo594.tutorial.modplants.ModPlants;
import com.exo594.tutorial.potions.ModPotions;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.GameRegistry;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;

public abstract class CommonProxy {

    public void registerRenderThings() {
    }

    public void registerSounds() {
    }

    public void registerTileEntities() {

    }

    public void registerKeyBindings() {

    }
    
    public static EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    public void preInit(FMLPreInitializationEvent e) {
        ModItems.init();
        ModBlocks.init();
        ModPlants.init();
        ModAchievements.init();

        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[]) f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception z) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(z);
            }
        }

        ModPotions.init();

    }

    public void init(FMLInitializationEvent e) {
        ModCrafting.init();
        ModEnchantments.init();
        //ModTileEntities.init();

        MinecraftForge.addGrassSeed(new ItemStack(ModItems.tutorialItem), 100);
        ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(ModItems.tutorialItem), 1, 32, 100));
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 0);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
