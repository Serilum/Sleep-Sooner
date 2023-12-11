package com.natamus.sleepsooner.neoforge.events;

import com.natamus.sleepsooner.events.PlayerEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
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
