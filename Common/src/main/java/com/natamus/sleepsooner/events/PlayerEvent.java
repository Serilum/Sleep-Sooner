package com.natamus.sleepsooner.events;

import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.StringFunctions;
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
		if (level.isClientSide || !hand.equals(InteractionHand.MAIN_HAND)) {
			return true;
		}
		
		if (!ConfigHandler.enableSleepSooner) {
			return true;
		}

		Block block = level.getBlockState(hitpos).getBlock();
		if (!(block instanceof BedBlock)) {
			return true;
		}
		
		int sleeptime = ConfigHandler.whenSleepIsPossibleInTicks;
		
		int currenttime = (int)level.getDayTime();
		int days = (int)Math.floor((double)currenttime/24000);
		int daytime = currenttime - (days*24000);
		
		if (sleeptime > 12540) {
			if (daytime > 12540 && daytime < sleeptime) {
				StringFunctions.sendMessage(player, "It's too early to sleep.", ChatFormatting.DARK_GREEN);
				
				return false;
			}
		}
		
		if (daytime > 12540) {
			return true;
		}
		
		if (daytime < sleeptime) {
			return true;
		}
		
		WorldFunctions.setWorldTime((ServerLevel)level, 12540);

		if (ConfigHandler.enablePreSleepMessage) {
			String unique = GlobalVariables.lingerMessages.get(GlobalVariables.random.nextInt(GlobalVariables.lingerMessages.size()));
			
			StringFunctions.sendMessage(player, "You " + unique + " until dusk. You may now sleep.", ChatFormatting.DARK_GREEN);
		}
		
		return true;
	}
}
