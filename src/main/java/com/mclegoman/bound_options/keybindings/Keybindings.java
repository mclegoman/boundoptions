package com.mclegoman.bound_options.keybindings;

import com.mclegoman.bound_options.BoundOptions;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.message.ChatVisibility;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class Keybindings {
	public static final KeyBinding toggleSneak;
	public static final KeyBinding toggleSprint;
	public static final KeyBinding toggleSubtitles;
	public static final KeyBinding toggleMainHand;
	public static final KeyBinding toggleAutoJump;
	public static final KeyBinding cycleChat;
	public static final KeyBinding[] allKeybindings;

	static {
		allKeybindings = new KeyBinding[]{
				toggleSneak = getKeybinding(BoundOptions.MOD_ID, BoundOptions.MOD_ID, "toggle_sneak", GLFW.GLFW_KEY_UNKNOWN),
				toggleSprint = getKeybinding(BoundOptions.MOD_ID, BoundOptions.MOD_ID, "toggle_sprint", GLFW.GLFW_KEY_UNKNOWN),
				toggleSubtitles = getKeybinding(BoundOptions.MOD_ID, BoundOptions.MOD_ID, "toggle_subtitles", GLFW.GLFW_KEY_UNKNOWN),
				toggleMainHand = getKeybinding(BoundOptions.MOD_ID, BoundOptions.MOD_ID, "toggle_main_hand", GLFW.GLFW_KEY_UNKNOWN),
				toggleAutoJump = getKeybinding(BoundOptions.MOD_ID, BoundOptions.MOD_ID, "toggle_auto_jump", GLFW.GLFW_KEY_UNKNOWN),
				cycleChat = getKeybinding(BoundOptions.MOD_ID, BoundOptions.MOD_ID, "cycle_chat", GLFW.GLFW_KEY_UNKNOWN)
		};
	}
	public static void init() {
	}
	public static void tick() {
		if (MinecraftClient.getInstance().player != null) {
			boolean shouldSave = false;
			if (toggleSneak.wasPressed()) {
				MinecraftClient.getInstance().options.getSneakToggled().setValue(!MinecraftClient.getInstance().options.getSneakToggled().getValue());
				MinecraftClient.getInstance().player.sendMessage(Text.translatable("gui.bound_options.message.keybinding", Text.translatable(toggleSneak.getTranslationKey()), Text.translatable(getTranslationKey(MinecraftClient.getInstance().options.getSneakToggled().getValue()))), true);
				shouldSave = true;
			}
			if (toggleSprint.wasPressed()) {
				MinecraftClient.getInstance().options.getSprintToggled().setValue(!MinecraftClient.getInstance().options.getSprintToggled().getValue());
				MinecraftClient.getInstance().player.sendMessage(Text.translatable("gui.bound_options.message.keybinding", Text.translatable(toggleSprint.getTranslationKey()), Text.translatable(getTranslationKey(MinecraftClient.getInstance().options.getSprintToggled().getValue()))), true);
				shouldSave = true;
			}
			if (toggleSubtitles.wasPressed()) {
				MinecraftClient.getInstance().options.getShowSubtitles().setValue(!MinecraftClient.getInstance().options.getShowSubtitles().getValue());
				MinecraftClient.getInstance().player.sendMessage(Text.translatable("gui.bound_options.message.keybinding", Text.translatable(toggleSubtitles.getTranslationKey()), Text.translatable(getTranslationKey(MinecraftClient.getInstance().options.getShowSubtitles().getValue()))), true);
				shouldSave = true;
			}
			if (toggleMainHand.wasPressed()) {
				MinecraftClient.getInstance().options.getMainArm().setValue(MinecraftClient.getInstance().options.getMainArm().getValue().getOpposite());
				MinecraftClient.getInstance().player.sendMessage(Text.translatable("gui.bound_options.message.keybinding", Text.translatable(toggleMainHand.getTranslationKey()), Text.translatable(MinecraftClient.getInstance().options.getMainArm().getValue().getTranslationKey())), true);
				shouldSave = true;
			}
			if (toggleAutoJump.wasPressed()) {
				MinecraftClient.getInstance().options.getAutoJump().setValue(!MinecraftClient.getInstance().options.getAutoJump().getValue());
				MinecraftClient.getInstance().player.sendMessage(Text.translatable("gui.bound_options.message.keybinding", Text.translatable(toggleAutoJump.getTranslationKey()), Text.translatable(getTranslationKey(MinecraftClient.getInstance().options.getAutoJump().getValue()))), true);
				shouldSave = true;
			}
			if (cycleChat.wasPressed()) {
				MinecraftClient.getInstance().options.getChatVisibility().setValue(switch(MinecraftClient.getInstance().options.getChatVisibility().getValue()) {
					case FULL -> ChatVisibility.SYSTEM;
					case SYSTEM -> ChatVisibility.HIDDEN;
					case HIDDEN -> ChatVisibility.FULL;
				});
				MinecraftClient.getInstance().player.sendMessage(Text.translatable("gui.bound_options.message.keybinding", Text.translatable(cycleChat.getTranslationKey()), Text.translatable(MinecraftClient.getInstance().options.getChatVisibility().getValue().getTranslationKey())), true);
				shouldSave = true;
			}
			if (shouldSave) save();
		}
	}
	public static void save() {
		MinecraftClient.getInstance().options.write();
	}
	public static KeyBinding getKeybinding(String namespace, String category, String key, int keyCode) {
		return KeyBindingHelper.registerKeyBinding(new KeyBinding("gui." + namespace + ".keybindings.keybinding." + key, InputUtil.Type.KEYSYM, keyCode, "gui." + namespace + ".keybindings.category." + category));
	}
	public static String getTranslationKey(boolean value) {
		return value ? "options.on" : "options.off";
	}
}