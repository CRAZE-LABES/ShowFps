package com.example.fpsdisplay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;

public class FpsDisplayConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("fpsdisplay.json");

    private static FpsDisplayConfig INSTANCE = new FpsDisplayConfig();

    public enum Position {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, CUSTOM
    }

    public enum TextColor {
        WHITE(0xFFFFFFFF),
        YELLOW(0xFFFFFF00),
        GREEN(0xFF00FF00),
        CYAN(0xFF00FFFF),
        RED(0xFFFF4444),
        ORANGE(0xFFFF8800),
        PINK(0xFFFF66CC),
        RAINBOW(-1);

        public final int argb;
        TextColor(int argb) { this.argb = argb; }
    }

    public boolean enabled = true;
    public Position position = Position.TOP_LEFT;
    public int customX = 4;
    public int customY = 4;
    public TextColor textColor = TextColor.WHITE;
    public float scale = 1.0f;
    public boolean showBackground = true;
    public int backgroundColor = 0x80000000;
    public boolean boldText = false;
    public boolean showSuffix = true;
    public String suffix = " FPS";
    public int updateIntervalMs = 500;
    public int paddingX = 4;
    public int paddingY = 2;

    public static FpsDisplayConfig get() {
        return INSTANCE;
    }

    public static void load() {
        if (CONFIG_PATH.toFile().exists()) {
            try (Reader reader = new FileReader(CONFIG_PATH.toFile())) {
                INSTANCE = GSON.fromJson(reader, FpsDisplayConfig.class);
            } catch (IOException e) {
                INSTANCE = new FpsDisplayConfig();
            }
        }
        save();
    }

    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
