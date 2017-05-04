package com.aidmo.aidmod.items;

import com.aidmo.aidmod.Aidmod.Aidmod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AidItem extends Item {

    private ModelResourceLocation res;

    public AidItem(String name){
        super();
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        res = new ModelResourceLocation(Aidmod.MODID + ":" + name, "inventory");
    }

    public ModelResourceLocation getRes(){
        return this.res;
    }

}
