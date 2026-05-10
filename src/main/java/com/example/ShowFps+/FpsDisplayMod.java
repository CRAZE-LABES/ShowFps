package com.example.fpsdisplay;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class FpsDisplayMod implements ClientModInitializer {

    public static final String MOD_ID = "fpsdisplay";

    @Override
    public void onInitializeClient() {
        FpsDisplayConfig.load();
        HudRenderCallback.EVENT.register(new FpsHudRenderer());
    }
}
