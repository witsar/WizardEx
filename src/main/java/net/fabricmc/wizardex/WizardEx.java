package net.fabricmc.wizardex;

import com.github.clevernucleus.playerex.api.ExAPI;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.spell_power.api.attributes.EntityAttributes_SpellPower;
import com.github.clevernucleus.playerex.api.client.PageRegistry;


public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	private static final Identifier magicIcon = new Identifier("modid:textures/gui/staff.png");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		RegisterSpellPowerPage();
	}

	public void RegisterSpellPowerPage() {
		PageRegistry.registerPage(new Identifier("playerex", "magic"), magicIcon, Text.of("Magic"));
		PageRegistry.registerLayer(new Identifier("playerex", "magic"), MagicPageLayer::new);
	}

	public static String GetFirePower() {
		var powerLevel = EntityAttributes_SpellPower.POWER.get(ExAPI.PLAYER_DATA.getId());
		return powerLevel.toString();
	}
}
