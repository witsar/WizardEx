package net.fabricmc.wizardex;
import com.github.clevernucleus.playerex.api.client.PageRegistry;
import com.github.clevernucleus.playerex.client.factory.EventFactoryClient;
import com.github.clevernucleus.playerex.client.factory.NetworkFactoryClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import com.github.clevernucleus.playerex.factory.NetworkFactory;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Client implements ClientModInitializer {
    private static final Identifier magicIcon = new Identifier("wizardex:textures/gui/staff.png");

    @Override
    public void onInitializeClient() {
        WizardEx.LOGGER.info("RUNNING CIENT INIT");
        ClientLoginNetworking.registerGlobalReceiver(NetworkFactory.CONFIG, NetworkFactoryClient::loginQueryReceived);
        ClientPlayNetworking.registerGlobalReceiver(NetworkFactory.NOTIFY, NetworkFactoryClient::notifiedLevelUp);

        PageRegistry.registerPage(new Identifier("wizardex", "magic"), magicIcon, Text.of("Magic"));
        PageRegistry.registerLayer(new Identifier("wizardex", "magic"), MagicPageLayer::new);
    }
}
