package com.exo594.tutorial.enchantment;

import net.minecraft.enchantment.Enchantment;

public class ModEnchantments {

    public static Enchantment addFlight;
    public static int ADDFLIGHT_ID = 100;

    public static void init() {

        addFlight = new EnchantmentAddFlight();
        Enchantment.addToBookList(addFlight);
    }
}
