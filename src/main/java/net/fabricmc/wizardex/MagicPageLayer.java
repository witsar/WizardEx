package net.fabricmc.wizardex;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.github.clevernucleus.dataattributes.api.DataAttributesAPI;
import com.github.clevernucleus.dataattributes.api.attribute.IEntityAttribute;
import com.github.clevernucleus.playerex.PlayerEx;
import com.github.clevernucleus.playerex.api.EntityAttributeSupplier;
import com.github.clevernucleus.playerex.api.ExAPI;
import com.github.clevernucleus.playerex.api.PacketType;
import com.github.clevernucleus.playerex.api.PlayerData;
import com.github.clevernucleus.playerex.api.client.ClientUtil;
import com.github.clevernucleus.playerex.api.client.PageLayer;
import com.github.clevernucleus.playerex.api.client.RenderComponent;
import com.github.clevernucleus.playerex.client.PlayerExClient;
import com.github.clevernucleus.playerex.client.gui.widget.ScreenButtonWidget;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;


import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.GameModeSelectionScreen.ButtonWidget;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.spell_power.api.MagicSchool;
import net.spell_power.api.SpellPower;

@Environment(EnvType.CLIENT)
public class MagicPageLayer extends PageLayer {
    private static Supplier<Float> scaleX = () -> ExAPI.getConfig().textScaleX();
    private static Supplier<Float> scaleY = () -> ExAPI.getConfig().textScaleY();
    private static float scaleZ = 0.75F;
    private PlayerData playerData;

    private static final List<RenderComponent> COMPONENTS = new ArrayList<RenderComponent>();
    public static final Identifier GUI = new Identifier("wizardex", "textures/gui/gui.png");
    public static final Identifier schools = new Identifier("wizardex", "textures/gui/schools.png");

    private static final List<Identifier> BUTTON_KEYS = ImmutableList.of(ExAPI.LEVEL.getId(), ExAPI.CONSTITUTION.getId(), ExAPI.STRENGTH.getId(), ExAPI.DEXTERITY.getId(), ExAPI.INTELLIGENCE.getId(), ExAPI.LUCKINESS.getId());

    public MagicPageLayer(HandledScreen<?> parent, ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(parent, handler, inventory, title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        matrices.push();
        matrices.scale(scaleX.get(), scaleY.get(), scaleZ);

        COMPONENTS.forEach(component -> component.renderText(this.client.player, matrices, this.textRenderer, this.x,
                this.y, scaleX.get(), scaleY.get()));

        // this.textRenderer.draw(matrices, Text.of("Spell Power: "), (this.x + 21) /
        // scaleX.get(), (this.y + 26) / scaleY.get(), 4210752);

        this.textRenderer.draw(matrices, Text.of("Fire: "), (this.x + 30) / scaleX.get(), (this.y + 45) / scaleY.get(),
                4210752);
        this.textRenderer.draw(matrices, Text.of("Frost: "), (this.x + 30) / scaleX.get(), (this.y + 65) / scaleY.get(),
                4210752);
        this.textRenderer.draw(matrices, Text.of("Lightning: "), (this.x + 30) / scaleX.get(),
                (this.y + 85) / scaleY.get(), 4210752);
        this.textRenderer.draw(matrices, Text.of("Arcane: "), (this.x + 30) / scaleX.get(),
                (this.y + 105) / scaleY.get(), 4210752);

        matrices.pop();

        COMPONENTS.forEach(component -> component.renderTooltip(this.client.player, this::renderTooltip, matrices,
                this.textRenderer, this.x, this.y, mouseX, mouseY, scaleX.get(), scaleY.get()));
    }

    @Override
    public void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, schools);
        // Fire Icon
        this.drawTexture(matrices, this.x + 9, this.y + 45, 0, 0, 16, 16);
        // Frost Icon
        this.drawTexture(matrices, this.x + 8, this.y + 65, 16, 0, 16, 16);
        // Lightning Icon
        this.drawTexture(matrices, this.x + 8, this.y + 85, 32, 0, 16, 16);
        // Arcane Icon
        this.drawTexture(matrices, this.x + 8, this.y + 105, 48, 0, 16, 16);
    }

    @Override
	protected void init() {
		super.init();
		this.playerData = ExAPI.PLAYER_DATA.get(this.client.player);
	}

    public static Supplier<EntityAttribute> GetSpellPowerAll() {
        Supplier<EntityAttribute> spellPowerSupplier = EntityAttributeSupplier.of(WizardEx.spell_power_all);
        return spellPowerSupplier;
    }

    public static Supplier<EntityAttribute> GetSpellPowerFire() {
        Supplier<EntityAttribute> spellPowerSupplier = EntityAttributeSupplier.of(WizardEx.spell_power_fire);
        return spellPowerSupplier;
    }

    public static Supplier<EntityAttribute> GetSpellPowerFrost() {
        Supplier<EntityAttribute> spellPowerSupplier = EntityAttributeSupplier.of(WizardEx.spell_power_fire);
        return spellPowerSupplier;
    }

    public static Supplier<EntityAttribute> GetSpellPowerLightning() {
        Supplier<EntityAttribute> spellPowerSupplier = EntityAttributeSupplier.of(WizardEx.spell_power_fire);
        return spellPowerSupplier;
    }

    public static Supplier<EntityAttribute> GetSpellPowerArcane() {
        Supplier<EntityAttribute> spellPowerSupplier = EntityAttributeSupplier.of(WizardEx.spell_power_fire);
        return spellPowerSupplier;
    }

    static {
        // SECTION: WizardEx Stats
        // add Indiviual magic school stats
        // ----------------------------------
        COMPONENTS.add(RenderComponent.of(GetSpellPowerFire(), value -> {
            return Text.of(value.toString());
        }, value -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Fire Specalization")));

            // ClientUtil.appendChildrenToTooltip(tooltip, ExAPI.CONSTITUTION);
            return tooltip;
        }, 48, 45));

        COMPONENTS.add(RenderComponent.of(GetSpellPowerFrost(), value -> {
            return Text.of(value.toString());
        }, value -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Frost Specalization")));

            // ClientUtil.appendChildrenToTooltip(tooltip, ExAPI.CONSTITUTION);
            return tooltip;
        }, 54, 65));

        COMPONENTS.add(RenderComponent.of(GetSpellPowerLightning(), value -> {
            return Text.of(value.toString());
        }, value -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Lightning Specalization")));

            // ClientUtil.appendChildrenToTooltip(tooltip, ExAPI.CONSTITUTION);
            return tooltip;
        }, 65, 85));

        COMPONENTS.add(RenderComponent.of(GetSpellPowerArcane(), value -> {
            return Text.of(value.toString());
        }, value -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Arcane Specalization")));

            // ClientUtil.appendChildrenToTooltip(tooltip, ExAPI.CONSTITUTION);
            return tooltip;
        }, 59, 105));

        // SECTION: Spellpower Stats
        // show resulting stats from SpellPower API
        // -----------------------------------------
        COMPONENTS.add(RenderComponent.of(entity -> {
            var fireSchool = SpellPower.getSpellPower(MagicSchool.FIRE, entity);
            var fireValue = fireSchool.baseValue();
            return Text.of(String.valueOf(fireValue));
        }, entity -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Fire")));
            return tooltip;
        }, 32, 55));
        COMPONENTS.add(RenderComponent.of(entity -> {
            var school = SpellPower.getSpellPower(MagicSchool.FROST, entity);
            var value = school.baseValue();
            return Text.of(String.valueOf(value));
        }, entity -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Frost")));
            return tooltip;
        }, 32, 75));
        COMPONENTS.add(RenderComponent.of(entity -> {
            var school = SpellPower.getSpellPower(MagicSchool.LIGHTNING, entity);
            var value = school.baseValue();
            return Text.of(String.valueOf(value));
        }, entity -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Lightning")));
            return tooltip;
        }, 32, 95));
        COMPONENTS.add(RenderComponent.of(entity -> {
            var school = SpellPower.getSpellPower(MagicSchool.ARCANE, entity);
            var value = school.baseValue();
            return Text.of(String.valueOf(value));
        }, entity -> {
            List<Text> tooltip = new ArrayList<Text>();
            tooltip.add((Text.of("Arcane")));
            return tooltip;
        }, 32, 115));
    }
}
