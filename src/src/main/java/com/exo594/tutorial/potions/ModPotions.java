package com.exo594.tutorial.potions;

import net.minecraft.potion.Potion;

public class ModPotions {

    public static Potion tutoriumPotion;

    public static final void init() {
        tutoriumPotion = (new PotionTutoriumPotion(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.tutoriumPotion");

    }
}
