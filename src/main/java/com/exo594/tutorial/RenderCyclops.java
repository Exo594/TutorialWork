package com.exo594.tutorial;

import com.exo594.tutorial.entities.EntityCyclops;
import com.exo594.tutorial.models.ModelCyclops;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class RenderCyclops extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation(Main.MODID + ":" + "textures/model/Cyclops.png");

    protected ModelCyclops modelEntity;

    public RenderCyclops(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
        modelEntity = ((ModelCyclops) mainModel);
    }

    public void renderCyclops(EntityCyclops entity, double x, double y, double z, float u, float v) {
        super.doRender(entity, x, y, z, u, v);
        BossStatus.setBossStatus(entity, true);
    }

    public void doRenderLiving(EntityLiving entityLiving, double x, double y, double z, float u, float v) {
        renderCyclops((EntityCyclops) entityLiving, x, y, z, u, v);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float u, float v) {
        renderCyclops((EntityCyclops) entity, x, y, z, u, v);
    }


    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return texture;
    }

}
