package com.aidmo.aidmod.Aidmod;

import com.aidmo.aidmod.achievement.Achievements;
import com.aidmo.aidmod.items.AidItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Aidmod.MODID, version = Aidmod.VERSION)
public class Aidmod{
    public static final String MODID = "aidmod";
    public static final String VERSION = "1.0";



    public static CreativeTabs tab = new CreativeTabs("aidtab"){
        @Override
        public ItemStack getTabIconItem(){
            return new ItemStack(AidItems.basicsword);
        }
    };
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //EventHandler.registerRecipes();
        //Achievements.registerAchievements();

    }
}
