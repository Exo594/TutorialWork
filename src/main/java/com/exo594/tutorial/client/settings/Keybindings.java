/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exo594.tutorial.client.settings;

import com.exo594.tutorial.Main;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;


/**
 *
 * @author Jacob
 */
public class Keybindings {
    
    public static KeyBinding charge = new KeyBinding(Main.Keys.CHARGE, Keyboard.KEY_C, Main.Keys.CATEGORY);
    public static KeyBinding release = new KeyBinding(Main.Keys.RELEASE, Keyboard.KEY_R, Main.Keys.CATEGORY);
    
}
