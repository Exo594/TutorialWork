package com.exo594.tutorial.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentAddFlight extends Enchantment {

    public EnchantmentAddFlight() {
        super(ModEnchantments.ADDFLIGHT_ID, 1000, EnumEnchantmentType.armor_torso);
        this.setName("addFlight");
    }

    public int getMinEnchantability() {
        return 30;
    }

    public int getMaxEnchantability() {
        return 50;
    }
    
    @Override
    public int getMaxLevel() {
        return 1;
    }
}
