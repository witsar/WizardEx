package net.fabricmc.wizardex;

import com.github.clevernucleus.dataattributes.DataAttributes;
import com.github.clevernucleus.dataattributes.DataAttributesClient;
import com.github.clevernucleus.dataattributes.api.DataAttributesAPI;
import com.github.clevernucleus.dataattributes.api.event.EntityAttributeModifiedEvents;
import com.github.clevernucleus.playerex.api.EntityAttributeSupplier;
import com.github.clevernucleus.playerex.api.ExAPI;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.spell_power.api.attributes.EntityAttributes_SpellPower;
import com.github.clevernucleus.playerex.api.client.PageRegistry;


public class WizardEx implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("WizardEx -------------------------");
	private static final Identifier magicIcon = new Identifier("wizardex:textures/gui/staff.png");

	// Overall spell stat gain from int levels
	public static final Identifier spell_power_all = new Identifier("wizardex:spell_power_all");

	// Indvidual Spell Levels
	public static final Identifier spell_power_fire = new Identifier("wizardex:spell_power_fire");
	public static final Identifier spell_power_frost = new Identifier("wizardex:spell_power_frost");
	public static final Identifier spell_power_lightning = new Identifier("wizardex:spell_power_lightning");
	public static final Identifier spell_power_arcane = new Identifier("wizardex:spell_power_arcane");
//	enchantment.wizardex.spell_power

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Starting WizardEx!");
		RegisterSpellPowerPage();
	}

	public void RegisterSpellPowerPage() {
		PageRegistry.registerPage(new Identifier("wizardex", "magic"), magicIcon, Text.of("Magic"));
		PageRegistry.registerLayer(new Identifier("wizardex", "magic"), MagicPageLayer::new);
	}
}
