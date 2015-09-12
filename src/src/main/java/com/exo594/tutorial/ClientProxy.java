package com.exo594.tutorial;

import com.exo594.tutorial.client.settings.Keybindings;
import com.exo594.tutorial.entities.EntityMiningChargePrimed;
import com.exo594.tutorial.item.EntityTutoriumRound;
import com.exo594.tutorial.item.ModItems;
import com.exo594.tutorial.item.TutoriumGrenade;
import com.exo594.tutorial.models.Model3dArmor;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy {
    
    public static ServerProxy serverProxy;

    public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();

    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityTutoriumRound.class, new RenderSnowball(ModItems.tutoriumRound));
        RenderingRegistry.registerEntityRenderingHandler(TutoriumGrenade.class, new RenderSnowball(ModItems.tutoriumGrenade));
        RenderingRegistry.registerEntityRenderingHandler(EntityMiningChargePrimed.class, new ExplosiveRender());    //Not behaving properly

        Model3dArmor tutorial_3d_armor = new Model3dArmor(1F);
        Model3dArmor tutorial_3d_legs = new Model3dArmor(0.5F);

        armorModels.put(ModItems.tutorial3dHelmet, tutorial_3d_armor);
        armorModels.put(ModItems.tutorial3dChestplate, tutorial_3d_armor);
        armorModels.put(ModItems.tutorial3dLeggings, tutorial_3d_legs);
        armorModels.put(ModItems.tutorial3dBoots, tutorial_3d_armor);
    }

    @Override
    public void registerSounds() {
    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

    @Override
    public void registerKeyBindings() {
        ClientRegistry.registerKeyBinding(Keybindings.charge);
        ClientRegistry.registerKeyBinding(Keybindings.release);
    }

    //@Override
    public static EntityPlayer getPlayerEntity(MessageContext ctx) {
 // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : ServerProxy.getPlayerEntity(ctx));
    }

}
