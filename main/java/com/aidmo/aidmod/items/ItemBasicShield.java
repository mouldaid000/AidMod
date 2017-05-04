package com.aidmo.aidmod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by mouldaid000 on 5/4/2017.
 */
public class ItemBasicShield extends AidItem{

    public ItemBasicShield(){

        super("basicshield");
        this.setFull3D();
        this.setMaxStackSize(1);

    }
    public void setupTag(ItemStack stack){

        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setDouble("defense",0);
        stack.getTagCompound().setDouble("maxdefense",75);
        stack.getTagCompound().setString("newshield", "none");

    }
    @Override @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        tooltip.clear();
        tooltip.add("A basic shield.");
        if(!stack.hasTagCompound()){
            setupTag(stack);
        }
    }
    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player){
        //player.addStat()
        if(!stack.hasTagCompound()){
            setupTag(stack);
        }
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        if(player.getHeldItem(hand).hasTagCompound()){
            if(player.getHeldItem(hand).getTagCompound().hasKey("defense")){
                double defense = player.getHeldItem(hand).getTagCompound().getDouble("defense");
                String message = "";

                player.sendStatusMessage(new TextComponentString(message), true);
            }
            else {
                setupTag(player.getHeldItem(hand));
            }
        }
        return new ActionResult<ItemStack>((EnumActionResult.PASS), player.getHeldItem(hand));
    }

}
