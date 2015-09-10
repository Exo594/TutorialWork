package com.exo594.tutorial.potions;

import com.exo594.tutorial.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionTutoriumPotion extends Potion {

    public PotionTutoriumPotion(int id, boolean bad, int amp) {
        super(id, bad, amp);
    }

    
    @Override
    public Potion setIconIndex(int x, int y) {
        super.setIconIndex(x, y);
        return (Potion) this;
    }

    @Override
    public int getStatusIconIndex() {
        ResourceLocation r = new ResourceLocation(Main.MODID + ":textures/potionIcons.png");        //if textures fail after compilation, add ".substring(1)"

        ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(r);
        Minecraft.getMinecraft().renderEngine.bindTexture(r);

        return super.getStatusIconIndex();
    }
}
