package com.example.fpsdisplay;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class FpsHudRenderer implements HudRenderCallback {

    private int displayedFps = 0;
    private long lastUpdate = 0;
    private long rainbowTick = 0;

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        FpsDisplayConfig cfg = FpsDisplayConfig.get();
        if (!cfg.enabled) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.debugEnabled) return;

        long now = System.currentTimeMillis();
        if (now - lastUpdate >= cfg.updateIntervalMs) {
            displayedFps = client.getCurrentFps();
            lastUpdate = now;
        }
        rainbowTick++;

        String text = cfg.showSuffix
                ? displayedFps + cfg.suffix
                : String.valueOf(displayedFps);

        int textWidth = client.textRenderer.getWidth(text);
        int textHeight = client.textRenderer.fontHeight;
        int screenW = client.getWindow().getScaledWidth();
        int screenH = client.getWindow().getScaledHeight();

        int x;
        int y;
        switch (cfg.position) {
            case TOP_RIGHT ->    { x = screenW - textWidth - cfg.paddingX * 2 - 2; y = 2; }
            case BOTTOM_LEFT ->  { x = 2; y = screenH - textHeight - cfg.paddingY * 2 - 2; }
            case BOTTOM_RIGHT -> { x = screenW - textWidth - cfg.paddingX * 2 - 2;
                                   y = screenH - textHeight - cfg.paddingY * 2 - 2; }
            case CUSTOM ->       { x = cfg.customX; y = cfg.customY; }
            default ->           { x = 2; y = 2; }
        }

        context.getMatrices().push();
        context.getMatrices().scale(cfg.scale, cfg.scale, 1.0f);

        int scaledX = (int)(x / cfg.scale);
        int scaledY = (int)(y / cfg.scale);

        if (cfg.showBackground) {
            context.fill(
                scaledX - cfg.paddingX,
                scaledY - cfg.paddingY,
                scaledX + textWidth + cfg.paddingX,
                scaledY + textHeight + cfg.paddingY,
                cfg.backgroundColor
            );
        }

        int color;
        if (cfg.textColor == FpsDisplayConfig.TextColor.RAINBOW) {
            float hue = (rainbowTick % 360) / 360.0f;
            int rgb = java.awt.Color.HSBtoRGB(hue, 1.0f, 1.0f);
            color = 0xFF000000 | rgb;
        } else {
            color = cfg.textColor.argb;
        }

        if (cfg.boldText) {
            context.drawTextWithShadow(client.textRenderer, "§l" + text, scaledX, scaledY, color);
        } else {
            context.drawTextWithShadow(client.textRenderer, text, scaledX, scaledY, color);
        }

        context.getMatrices().pop();
    }
}
