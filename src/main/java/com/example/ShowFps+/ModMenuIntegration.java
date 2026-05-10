package com.example.fpsdisplay;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.List;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            FpsDisplayConfig cfg = FpsDisplayConfig.get();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.literal("FPS Display Settings"))
                    .setSavingRunnable(FpsDisplayConfig::save);

            ConfigEntryBuilder entry = builder.entryBuilder();

            ConfigCategory general = builder.getOrCreateCategory(Text.literal("General"));

            general.addEntry(entry
                    .startBooleanToggle(Text.literal("Enabled"), cfg.enabled)
                    .setDefaultValue(true)
                    .setTooltip(Text.literal("Toggle the FPS display on or off"))
                    .setSaveConsumer(v -> cfg.enabled = v)
                    .build());

            general.addEntry(entry
                    .startEnumSelector(Text.literal("Position"), FpsDisplayConfig.Position.class, cfg.position)
                    .setDefaultValue(FpsDisplayConfig.Position.TOP_LEFT)
                    .setTooltip(Text.literal("Where the FPS counter appears on screen"))
                    .setSaveConsumer(v -> cfg.position = v)
                    .build());

            general.addEntry(entry
                    .startIntField(Text.literal("Custom X"), cfg.customX)
                    .setDefaultValue(4)
                    .setTooltip(Text.literal("X position when Position is set to CUSTOM"))
                    .setSaveConsumer(v -> cfg.customX = v)
                    .build());

            general.addEntry(entry
                    .startIntField(Text.literal("Custom Y"), cfg.customY)
                    .setDefaultValue(4)
                    .setTooltip(Text.literal("Y position when Position is set to CUSTOM"))
                    .setSaveConsumer(v -> cfg.customY = v)
                    .build());

            general.addEntry(entry
                    .startIntSlider(Text.literal("Update Interval (ms)"), cfg.updateIntervalMs, 100, 2000)
                    .setDefaultValue(500)
                    .setTooltip(Text.literal("How often the FPS value refreshes (in milliseconds)"))
                    .setSaveConsumer(v -> cfg.updateIntervalMs = v)
                    .build());

            ConfigCategory appearance = builder.getOrCreateCategory(Text.literal("Appearance"));

            appearance.addEntry(entry
                    .startEnumSelector(Text.literal("Text Color"), FpsDisplayConfig.TextColor.class, cfg.textColor)
                    .setDefaultValue(FpsDisplayConfig.TextColor.WHITE)
                    .setTooltip(Text.literal("Color of the FPS text. RAINBOW cycles through all hues."))
                    .setSaveConsumer(v -> cfg.textColor = v)
                    .build());

            appearance.addEntry(entry
                    .startFloatField(Text.literal("Scale"), cfg.scale)
                    .setDefaultValue(1.0f)
                    .setMin(0.5f)
                    .setMax(3.0f)
                    .setTooltip(Text.literal("Scale multiplier for the FPS display (0.5 – 3.0)"))
                    .setSaveConsumer(v -> cfg.scale = v)
                    .build());

            appearance.addEntry(entry
                    .startBooleanToggle(Text.literal("Bold Text"), cfg.boldText)
                    .setDefaultValue(false)
                    .setTooltip(Text.literal("Make the FPS number bold"))
                    .setSaveConsumer(v -> cfg.boldText = v)
                    .build());

            appearance.addEntry(entry
                    .startBooleanToggle(Text.literal("Show Suffix"), cfg.showSuffix)
                    .setDefaultValue(true)
                    .setTooltip(Text.literal("Show \" FPS\" after the number"))
                    .setSaveConsumer(v -> cfg.showSuffix = v)
                    .build());

            appearance.addEntry(entry
                    .startStrField(Text.literal("Suffix Text"), cfg.suffix)
                    .setDefaultValue(" FPS")
                    .setTooltip(Text.literal("The text shown after the FPS number"))
                    .setSaveConsumer(v -> cfg.suffix = v)
                    .build());

            ConfigCategory background = builder.getOrCreateCategory(Text.literal("Background"));

            background.addEntry(entry
                    .startBooleanToggle(Text.literal("Show Background"), cfg.showBackground)
                    .setDefaultValue(true)
                    .setTooltip(Text.literal("Draw a dark background box behind the FPS text"))
                    .setSaveConsumer(v -> cfg.showBackground = v)
                    .build());

            background.addEntry(entry
                    .startAlphaColorField(Text.literal("Background Color"), cfg.backgroundColor)
                    .setDefaultValue(0x80000000)
                    .setTooltip(Text.literal("Background color with alpha (ARGB)"))
                    .setSaveConsumer(v -> cfg.backgroundColor = v)
                    .build());

            background.addEntry(entry
                    .startIntSlider(Text.literal("Padding X"), cfg.paddingX, 0, 16)
                    .setDefaultValue(4)
                    .setTooltip(Text.literal("Horizontal padding inside the background box"))
                    .setSaveConsumer(v -> cfg.paddingX = v)
                    .build());

            background.addEntry(entry
                    .startIntSlider(Text.literal("Padding Y"), cfg.paddingY, 0, 16)
                    .setDefaultValue(2)
                    .setTooltip(Text.literal("Vertical padding inside the background box"))
                    .setSaveConsumer(v -> cfg.paddingY = v)
                    .build());

            return builder.build();
        };
    }
}
