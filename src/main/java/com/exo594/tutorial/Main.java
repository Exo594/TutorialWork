package com.exo594.tutorial;

import com.exo594.tutorial.handlers.FuelHandler;
import com.exo594.tutorial.handlers.GUIHandler;
import com.exo594.tutorial.handlers.TututorialForgeEventHandler;
import com.exo594.tutorial.handlers.KeyInputEventHandler;
import com.exo594.tutorial.handlers.CraftingHandler;
import com.exo594.tutorial.configs.ConfigurationHandler;
import com.exo594.tutorial.handlers.DropHandler;
import com.exo594.tutorial.item.EntityTutoriumRound;
import com.exo594.tutorial.item.ModItems;
import com.exo594.tutorial.item.TutoriumGrenade;
import com.exo594.tutorial.tileentities.TileEntityIngotMasher;
import com.exo594.tutorial.tileentities.TileEntityTutoriumFurnace;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION, guiFactory = Main.GUI_FACTORY_CLASS)
public class Main {
    
    public static final class Keys
    {
        public static final String CATEGORY = "keys.tutorial.category";
        public static final String CHARGE = "keys.tutorial.charge";
        public static final String RELEASE = "keys.tutorial.release";
    }

    public static final String MODID = "tutorial";
    public static final String MODNAME = "Tutorial Mod";
    public static final String VERSION = "1.0.0";
    public static final String GUI_FACTORY_CLASS = "com.exo594.tutorial.guis.GuiFactory";

    
    @Instance(Main.MODID)
    public static Main modInstance;
    
    public static final int guiIDTutoriumFurnace = 0;
    public static final int guiIDIngotMasher = 1;
    public static final int GUI_ITEM_INV = 2;
    
    public static SimpleNetworkWrapper snw;

    @SidedProxy(clientSide = "com.exo594.tutorial.ClientProxy", serverSide = "com.exo594.tutorial.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
        proxy.registerTileEntities();
        proxy.registerKeyBindings();
        ConfigurationHandler.init(e.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        
        //ModTileEntities.init();
        GameRegistry.registerTileEntity(TileEntityTutoriumFurnace.class, "tutoriumFurnace");
        GameRegistry.registerTileEntity(TileEntityIngotMasher.class, "IngotMasher");
        
        //snw = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
       // snw.registerMessage(OpenGuiMessageHandler.class, OpenGuiMessage.class, 0, Side.SERVER);
        
        
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);

        EntityRegistry.registerModEntity(TutoriumGrenade.class, "Grenade", 1, this, 80, 3, true); //?
        EntityRegistry.registerModEntity(EntityTutoriumRound.class, "Round", 2, this, 80, 3, true); //? I have no idea what any of these values mean.

        GameRegistry.registerFuelHandler(new FuelHandler());
        
        FMLCommonHandler.instance().bus().register(new CraftingHandler());
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
        MinecraftForge.EVENT_BUS.register(new DropHandler());
        
        proxy.registerRenderThings();
        proxy.registerSounds();
        
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GUIHandler());
        
        
        
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);

        FMLCommonHandler.instance().bus().register(new TututorialForgeEventHandler());
        MinecraftForge.EVENT_BUS.register(new TututorialForgeEventHandler());
    }

    public static CreativeTabs tabTutorialMod = new CreativeTabs("tabTutorialMod") {
        @Override
        public Item getTabIconItem() {
            return new ItemStack(ModItems.tutoriumRound).getItem();
        }
    };
}
