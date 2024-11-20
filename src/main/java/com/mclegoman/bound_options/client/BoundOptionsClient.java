package com.mclegoman.bound_options.client;

import com.mclegoman.bound_options.client.keybindings.Keybindings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class BoundOptionsClient implements ClientModInitializer {
	public static final String MOD_ID = "bound_options";
	public void onInitializeClient() {
		Keybindings.init();
		ClientTickEvents.START_CLIENT_TICK.register((client) -> Keybindings.tick());
	}
}