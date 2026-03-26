package com.natamus.sleepsooner.forge.events;

import com.natamus.sleepsooner.events.PlayerEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgePlayerEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgePlayerEvent.class);

		PlayerInteractEvent.RightClickBlock.BUS.addListener(ForgePlayerEvent::playerClick);
	}

	@SubscribeEvent
	public static boolean playerClick(PlayerInteractEvent.RightClickBlock e) {  
		Player player = e.getEntity();
		Level level = player.level();
		if (!PlayerEvent.playerClick(level, player, e.getHand(), e.getPos(), e.getHitVec())) {
			return true;
		}
		return false;
	}
}
