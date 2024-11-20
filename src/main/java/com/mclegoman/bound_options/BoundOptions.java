package com.mclegoman.bound_options;

import com.mclegoman.bound_options.keybindings.Keybindings;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class BoundOptions implements ModInitializer {
	public static final String MOD_ID = "bound_options";
	@Override
	public void onInitialize() {
		Keybindings.init();
		ClientTickEvents.START_CLIENT_TICK.register((client) -> Keybindings.tick());
	}
}