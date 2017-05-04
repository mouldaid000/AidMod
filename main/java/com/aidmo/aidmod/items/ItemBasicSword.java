package com.aidmo.aidmod.items;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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

public class ItemBasicSword extends AidItem{

    public ItemBasicSword(){

        super("basicsword");
        this.setFull3D();
        this.setMaxStackSize(1);
        this.setMaxDamage(16);

    }

    public void setupTag(ItemStack stack){

        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setDouble("strength",0);
        stack.getTagCompound().setDouble("maxstrength",100);
        stack.getTagCompound().setDouble("strengthpersecond", 2);
        stack.getTagCompound().setString("newsword", "none");
        stack.getTagCompound().setLong("strengthtime", System.currentTimeMillis());
    }

    @Override @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
        tooltip.clear();
        tooltip.add("A basic sword.");
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
    public boolean onEntitySwing(EntityLivingBase ent, ItemStack stack){
        if(!stack.hasTagCompound()){
            setupTag(stack);
        }

        return false;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand){
        if(player.getHeldItem(hand).hasTagCompound()){
            if(player.getHeldItem(hand).getTagCompound().hasKey("strength")){
                double strength = player.getHeldItem(hand).getTagCompound().getDouble("strength");
                String message = "";
                if(strength >= (50)){
                    message += TextFormatting.BLUE + "" + TextFormatting.ITALIC + TextFormatting.BOLD + "Double damage!";
                    BlockPos p = player.rayTrace(10, 1.0F).getBlockPos();
                    player.getHeldItem(hand).getTagCompound().setDouble("strength", strength - 1);
                    player.getHeldItem(hand).damageItem(2, player);
                }
                else {
                    message += TextFormatting.RED + "" + TextFormatting.BOLD + "Not Enough Strength";
                }
                player.sendStatusMessage(new TextComponentString(message), true);
            }
            else {
                setupTag(player.getHeldItem(hand));
            }
        }
        return new ActionResult<ItemStack>((EnumActionResult.PASS), player.getHeldItem(hand));
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){

        if(!stack.hasTagCompound()){
            setupTag(stack);
        }
        if(System.currentTimeMillis() - stack.getTagCompound().getLong("strengthtime") >= 1000){
            double strength = stack.getTagCompound().getDouble("strength");
            stack.getTagCompound().setDouble("strength", strength + stack.getTagCompound().getDouble("strengthpersecond"));
            stack.getTagCompound().setLong("strengthtime", System.currentTimeMillis());
        }
        if(stack.getTagCompound().getDouble("strength") > stack.getTagCompound().getDouble("maxstrength")){
            stack.getTagCompound().setDouble("strength", stack.getTagCompound().getDouble("maxmana"));
        }
        stack.setStackDisplayName("Basic Sword " + TextFormatting.BLUE + (int)stack.getTagCompound().getDouble("strength"));

    }
}
