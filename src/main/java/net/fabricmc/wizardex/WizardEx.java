package net.fabricmc.wizardex;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.clevernucleus.dataattributes.api.DataAttributesAPI;
import com.github.clevernucleus.playerex.api.EntityAttributeSupplier;
import com.github.clevernucleus.playerex.api.ExAPI;


public class WizardEx implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("WizardEx -------------------------");
	

	// Overall spell stat gain from int levels
	public static final Identifier spell_power_all = new Identifier("wizardex:spell_power_all");

	// Indvidual Spell Levels
	public static final Identifier spell_power_fire = new Identifier("wizardex:spell_power_fire");
	public static final Identifier spell_power_frost = new Identifier("wizardex:spell_power_frost");
	public static final Identifier spell_power_lightning = new Identifier("wizardex:spell_power_lightning");
	public static final Identifier spell_power_arcane = new Identifier("wizardex:spell_power_arcane");
	public static final Identifier spell_power_crit_chance = new Identifier("spell_power:critical_chance");
	public static final Identifier spell_power_crit_damage = new Identifier("spell_power:critical_damage");
	public static final Identifier spell_power_healing = new Identifier("wizardex:spell_power_healing");
	public static final Identifier spell_power_soul = new Identifier("wizardex:spell_power_soul");
	public static final Identifier spell_power_haste = new Identifier("wizardex:spell_haste_factor");
	public static final Identifier magic_damage = new Identifier("playerex:magic_damage");
	public static final Identifier magic_resist = new Identifier("playerex:magic_resistance");
    public static final Identifier tamed_damage = new Identifier("playerex:tamed_damage");
    public static final Identifier tamed_resist = new Identifier("playerex:tamed_resistance");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Starting WizardEx!");
		registerRefundConditions();
	}
	
	private void registerRefundConditions() {
		ExAPI.registerRefundCondition((data, player) -> {
			var attribute = EntityAttributeSupplier.of(spell_power_fire);
			return DataAttributesAPI.ifPresent(player, attribute, 0.0D, value -> data.get(attribute));
		});
		ExAPI.registerRefundCondition((data, player) -> {
			var attribute = EntityAttributeSupplier.of(spell_power_frost);
			return DataAttributesAPI.ifPresent(player, attribute, 0.0D, value -> data.get(attribute));
		});
		ExAPI.registerRefundCondition((data, player) -> {
			var attribute = EntityAttributeSupplier.of(spell_power_arcane);
			return DataAttributesAPI.ifPresent(player, attribute, 0.0D, value -> data.get(attribute));
		});
		ExAPI.registerRefundCondition((data, player) -> {
			var attribute = EntityAttributeSupplier.of(spell_power_healing);
			return DataAttributesAPI.ifPresent(player, attribute, 0.0D, value -> data.get(attribute));
		});
		ExAPI.registerRefundCondition((data, player) -> {
			var attribute = EntityAttributeSupplier.of(spell_power_lightning);
			return DataAttributesAPI.ifPresent(player, attribute, 0.0D, value -> data.get(attribute));
		});
		ExAPI.registerRefundCondition((data, player) -> {
			var attribute = EntityAttributeSupplier.of(spell_power_soul);
			return DataAttributesAPI.ifPresent(player, attribute, 0.0D, value -> data.get(attribute));
		});
	}
}
