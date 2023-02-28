package net.fabricmc.example;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.github.clevernucleus.dataattributes.api.DataAttributesAPI;
import com.github.clevernucleus.playerex.api.ExAPI;
import com.github.clevernucleus.playerex.api.client.ClientUtil;
import com.github.clevernucleus.playerex.api.client.PageLayer;
import com.github.clevernucleus.playerex.api.client.RenderComponent;
import com.github.clevernucleus.playerex.client.PlayerExClient;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class MagicPageLayer extends PageLayer {
    private static Supplier<Float> scaleX = () -> ExAPI.getConfig().textScaleX();
    private static Supplier<Float> scaleY = () -> ExAPI.getConfig().textScaleY();
    private static float scaleZ = 0.75F;

    private static final List<RenderComponent> COMPONENTS = new ArrayList<RenderComponent>();

    public MagicPageLayer(HandledScreen<?> parent, ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(parent, handler, inventory, title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        matrices.push();
        matrices.scale(scaleX.get(), scaleY.get(), scaleZ);

        COMPONENTS.forEach(component -> component.renderText(this.client.player, matrices, this.textRenderer, this.x, this.y, scaleX.get(), scaleY.get()));

        this.textRenderer.draw(matrices, Text.of("Spell Power: "), (this.x + 21) / scaleX.get(), (this.y + 26) / scaleY.get(), 4210752);

        matrices.pop();

        COMPONENTS.forEach(component -> component.renderTooltip(this.client.player, this::renderTooltip, matrices, this.textRenderer, this.x, this.y, mouseX, mouseY, scaleX.get(), scaleY.get()));
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, PlayerExClient.GUI);
        this.drawTexture(matrices, this.x + 9, this.y + 24, 244, 9, 9, 9);
    }
    static {
        COMPONENTS.add(RenderComponent.of(() -> EntityAttributes.GENERIC_ATTACK_SPEED, value -> {
            return Text.of(ExampleMod.GetFirePower());
        }, value -> {
            List<Text> tooltip = new ArrayList<Text>();

            tooltip.add((Text.translatable("playerex.gui.page.combat.tooltip.attack_speed[1]")).formatted(Formatting.GRAY));

            return tooltip;
        }, 9, 37));
    }
}
