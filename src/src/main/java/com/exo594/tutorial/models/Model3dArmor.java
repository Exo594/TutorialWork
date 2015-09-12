package com.exo594.tutorial.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Model3dArmor extends ModelBiped {

    ModelRenderer rightShoulderHorn;
    ModelRenderer rightShoulder;
    ModelRenderer leftShoulderHorn;
    ModelRenderer rightShoulderHornBase;
    ModelRenderer leftShoulder;
    ModelRenderer leftShoulderHornBase;
    ModelRenderer baseHorn;
    ModelRenderer longHorn;

    //ModelRenderer leftleg;
    //ModelRenderer leftarm;
    //ModelRenderer rightleg;
    //ModelRenderer rightarm;
    //ModelRenderer body;
    //ModelRenderer head;
    
    // This is really useful for converting the source from a Techne model export
// which will have absolute rotation points that need to be converted before
// creating the addChild() relationship
    protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild) {
        // move child rotation point to be relative to parent
        parChild.rotationPointX -= parParent.rotationPointX;
        parChild.rotationPointY -= parParent.rotationPointY;
        parChild.rotationPointZ -= parParent.rotationPointZ;
        // make rotations relative to parent
        parChild.rotateAngleX -= parParent.rotateAngleX;
        parChild.rotateAngleY -= parParent.rotateAngleY;
        parChild.rotateAngleZ -= parParent.rotateAngleZ;
        // create relationship
        parParent.addChild(parChild);
    }

    public Model3dArmor(float expand) {     

        super(expand, 0, 64, 64);

        rightShoulderHorn = new ModelRenderer(this, 36, 32);
        rightShoulderHorn.addBox(-13F, 1F, -0.5333334F, 7, 1, 1);
        rightShoulderHorn.setRotationPoint(-5F, 2F, 0F);
        rightShoulderHorn.setTextureSize(64, 64);
        rightShoulderHorn.mirror = true;
        setRotation(rightShoulderHorn, 0F, 0F, 0.8726646F);
        convertToChild(this.bipedRightArm, rightShoulderHorn);
        
        rightShoulderHornBase = new ModelRenderer(this, 22, 32);
        rightShoulderHornBase.addBox(-5.5F, -4F, -2F, 3, 3, 4);
        rightShoulderHornBase.setRotationPoint(-5F, 2F, 0F);
        rightShoulderHornBase.setTextureSize(64, 64);
        rightShoulderHornBase.mirror = true;
        setRotation(rightShoulderHornBase, 0F, 0F, 0F);
        convertToChild(this.bipedRightArm, rightShoulderHornBase);
        
        rightShoulder = new ModelRenderer(this, 0, 32);
        rightShoulder.addBox(-4.5F, -3F, -3F, 5, 5, 6);
        rightShoulder.setRotationPoint(-5F, 2F, 0F);
        rightShoulder.setTextureSize(64, 64);
        rightShoulder.mirror = true;
        setRotation(rightShoulder, 0F, 0F, 0F);
        convertToChild(this.bipedRightArm, rightShoulder);

        leftShoulderHorn = new ModelRenderer(this, 36, 32);
        leftShoulderHorn.addBox(5.5F, 1F, -0.5F, 7, 1, 1);
        leftShoulderHorn.setRotationPoint(5F, 2F, 0F);
        leftShoulderHorn.setTextureSize(64, 64);
        leftShoulderHorn.mirror = true;
        setRotation(leftShoulderHorn, 0F, 0F, -0.8726646F);
        convertToChild(this.bipedLeftArm, leftShoulderHorn);
        
        leftShoulderHornBase = new ModelRenderer(this, 22, 32);
        leftShoulderHornBase.addBox(2.5F, -4F, -2F, 3, 3, 4);
        leftShoulderHornBase.setRotationPoint(5F, 2F, 0F);
        leftShoulderHornBase.setTextureSize(64, 64);
        leftShoulderHornBase.mirror = true;
        setRotation(leftShoulderHornBase, 0F, 0F, 0F);
        convertToChild(this.bipedLeftArm, leftShoulderHornBase);
        
        leftShoulder = new ModelRenderer(this, 0, 32);
        leftShoulder.addBox(-0.5F, -3F, -3F, 5, 5, 6);
        leftShoulder.setRotationPoint(5F, 2F, 0F);
        leftShoulder.setTextureSize(64, 64);
        leftShoulder.mirror = true;
        setRotation(leftShoulder, 0F, 0F, 0F);
        convertToChild(this.bipedLeftArm, leftShoulder);
        
        longHorn = new ModelRenderer(this, 36, 34);
        longHorn.addBox(-0.5F, -16F, 1F, 1, 7, 1);
        longHorn.setRotationPoint(0F, 0F, 0F);
        longHorn.setTextureSize(64, 64);
        longHorn.mirror = true;
        setRotation(longHorn, 0.6981317F, 0F, 0F);
        convertToChild(this.bipedHead, longHorn);
        
        baseHorn = new ModelRenderer(this, 22, 39);
        baseHorn.addBox(-1F, -9F, -5F, 2, 2, 2);
        baseHorn.setRotationPoint(0F, 0F, 0F);
        baseHorn.setTextureSize(64, 64);
        baseHorn.mirror = true;
        setRotation(baseHorn, 0F, 0F, 0F);
        convertToChild(this.bipedHead, baseHorn);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
}
