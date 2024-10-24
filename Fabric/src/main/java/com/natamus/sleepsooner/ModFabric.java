package com.natamus.sleepsooner;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.collective.fabric.callbacks.CollectiveBlockEvents;
import com.natamus.sleepsooner.events.PlayerEvent;
import com.natamus.sleepsooner.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CollectiveBlockEvents.BLOCK_RIGHT_CLICK.register((Level level, Player player, InteractionHand hand, BlockPos pos, BlockHitResult hitVec) -> {
			return PlayerEvent.playerClick(level, player, hand, pos, hitVec);
		});
	}

	private static void setGlobalConstants() {

	}
}
