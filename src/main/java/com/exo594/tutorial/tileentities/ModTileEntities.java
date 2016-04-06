package com.exo594.tutorial.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {
    
    public static final void init(){
        GameRegistry.registerTileEntity(TileEntityTutoriumFurnace.class, "tutoriumFurnace");
        GameRegistry.registerTileEntity(TileEntityIngotMasher.class, "ingotMasher");
        GameRegistry.registerTileEntity(TileEntityTemporalChamber.class, "temporalChamber");
        GameRegistry.registerTileEntity(TileEntityWEI.class, "WEI");
    }
    
}
