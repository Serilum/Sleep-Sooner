package com.serilum.sleepsooner.neoforge.events;

import com.serilum.sleepsooner.events.PlayerEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class NeoForgePlayerEvent {
	@SubscribeEvent
	public static void playerClick(PlayerInteractEvent.RightClickBlock e) {  
		Player player = e.getEntity();
		Level level = player.level();
		if (!PlayerEvent.playerClick(level, player, e.getHand(), e.getPos(), e.getHitVec())) {
			e.setCanceled(true);
		}
	}
}
