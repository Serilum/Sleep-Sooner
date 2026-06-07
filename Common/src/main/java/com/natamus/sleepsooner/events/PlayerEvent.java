package com.natamus.sleepsooner.events;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.collective.functions.WorldFunctions;
import com.natamus.sleepsooner.config.ConfigHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;

public class PlayerEvent {
	public static boolean playerClick(Level level, Player player, InteractionHand hand, BlockPos hitpos, BlockHitResult hitVec) {
		if (level.isClientSide() || !hand.equals(InteractionHand.MAIN_HAND)) {
			return true;
		}
		
		if (!ConfigHandler.enableSleepSooner) {
			return true;
		}

		Block block = level.getBlockState(hitpos).getBlock();
		if (!(block instanceof BedBlock)) {
			return true;
		}
		
		int sleepTime = ConfigHandler.whenSleepIsPossibleInTicks;
		int worldTime = WorldFunctions.getWorldTime((ServerLevel)level);
		
		if (sleepTime > 12540) {
			if (worldTime > 12540 && worldTime < sleepTime) {
				MessageFunctions.sendTranslatableMessage(player, "collective.sleepsooner.message.tooearlysleep", ChatFormatting.DARK_GREEN);
				
				return false;
			}
		}
		
		if (worldTime > 12540) {
			return true;
		}
		
		if (worldTime < sleepTime) {
			return true;
		}
		
		WorldFunctions.setWorldTime((ServerLevel)level, 12540);

		if (ConfigHandler.enablePreSleepMessage) {
			String unique = GlobalVariables.lingerMessages.get(GlobalVariables.random.nextInt(GlobalVariables.lingerMessages.size()));
			
			MessageFunctions.sendTranslatableMessage(player, "collective.sleepsooner.message.untilduskmay", ChatFormatting.DARK_GREEN);
		}
		
		return true;
	}
}
