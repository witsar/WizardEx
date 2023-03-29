package net.fabricmc.wizardex;

import com.github.clevernucleus.playerex.api.client.PageRegistry;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WizardExClient implements ClientModInitializer {
	private static final Identifier magicIcon = new Identifier("wizardex:textures/gui/staff.png");
	private static final Identifier magicPage = new Identifier("wizardex", "magic");
	
	@Override
	public void onInitializeClient() {
		// TODO Auto-generated method stub
		registerSpellPowerPage();
	}
	
	private void registerSpellPowerPage() {
		PageRegistry.registerPage(magicPage, magicIcon, Text.translatable("wizardex.gui.page.title"));
		PageRegistry.registerLayer(magicPage, MagicPageLayer::new);
	}
}
