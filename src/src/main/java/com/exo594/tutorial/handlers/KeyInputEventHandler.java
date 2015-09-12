package com.exo594.tutorial.handlers;

import com.exo594.tutorial.Key;
import com.exo594.tutorial.client.settings.Keybindings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler {
    
    private static Key getPressedKeybinding(){
         if (Keybindings.charge.isPressed())
        {
            return Key.CHARGE;
        }
        else if (Keybindings.release.isPressed())
        {
            return Key.RELEASE;
        }

        return Key.UNKNOWN;
    }
    
    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event){
        System.out.println(getPressedKeybinding());
    }
    
}
