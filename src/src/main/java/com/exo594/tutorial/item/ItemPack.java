package com.exo594.tutorial.item;

import com.exo594.tutorial.Main;
import com.exo594.tutorial.packets.OpenGuiMessage;
import com.exo594.tutorial.packets.PacketDispatcher;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemPack extends Item {

    public ItemPack() {
        super();
        setMaxDamage(0);
        setMaxStackSize(1);
        setCreativeTab(Main.tabTutorialMod);
        setTextureName(Main.MODID + ":Pack");
    }

    // Without this method, your inventory will NOT work!!!
    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 1; // return any value greater than zero
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        
            player.openGui(Main.modInstance, Main.GUI_ITEM_INV, world, 0, 0, 0);

        
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.ITALIC + "A magic bag that holds many items");
    }
}
