package com.aidmo.aidmod.event;

import com.aidmo.aidmod.items.AidItem;
import com.aidmo.aidmod.items.AidItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {

   @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e){

   }
   @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e){
        e.getRegistry().register(AidItems.basicsword);
   }
   @SubscribeEvent
    public static void registerModels(ModelRegistryEvent e){
        setModel(AidItems.basicsword);
   }

   public static void registerRecipes(){

   }

   public static void setModel(AidItem item){
        ModelLoader.setCustomModelResourceLocation(item,0,item.getRes());
   }

}
