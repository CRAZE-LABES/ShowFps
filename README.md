# FPS Display Mod

A Fabric mod for **Minecraft 26.1.2** that renders a customizable FPS counter on your HUD.

---

## Features

- Live FPS counter rendered directly on screen
- Fully configurable via **Mod Menu** (no manual file editing needed)
- All settings saved automatically to `config/fpsdisplay.json`

---

## Customizable Options

### General
| Option | Default | Description |
|---|---|---|
| Enabled | `true` | Toggle the FPS display on/off |
| Position | `TOP_LEFT` | `TOP_LEFT`, `TOP_RIGHT`, `BOTTOM_LEFT`, `BOTTOM_RIGHT`, `CUSTOM` |
| Custom X / Y | `4, 4` | Pixel coordinates when Position is `CUSTOM` |
| Update Interval | `500ms` | How often the counter refreshes (100–2000 ms) |

### Appearance
| Option | Default | Description |
|---|---|---|
| Text Color | `WHITE` | `WHITE`, `YELLOW`, `GREEN`, `CYAN`, `RED`, `ORANGE`, `PINK`, `RAINBOW` |
| Scale | `1.0` | Size multiplier (0.5 – 3.0) |
| Bold Text | `false` | Render FPS number in bold |
| Show Suffix | `true` | Show text after number (e.g. " FPS") |
| Suffix Text | `" FPS"` | Customize the suffix string |

### Background
| Option | Default | Description |
|---|---|---|
| Show Background | `true` | Draw a box behind the FPS text |
| Background Color | `#80000000` | ARGB color of the background (supports transparency) |
| Padding X | `4` | Horizontal padding inside background box |
| Padding Y | `2` | Vertical padding inside background box |

---

## Requirements

- Minecraft **1.21.4** (Java Edition)
- [Fabric Loader](https://fabricmc.net/use/installer/) `0.16.9+`
- [Fabric API](https://modrinth.com/mod/fabric-api) `0.114.0+1.21.4`
- [Cloth Config](https://modrinth.com/mod/cloth-config) `17.0.144`
- [Mod Menu](https://modrinth.com/mod/modmenu) `13.0.0`

---

## Building from Source

1. **Clone / download** this folder
2. Make sure you have **Java 21** installed
3. Run:

```bash
# Generate the Gradle wrapper first (run once)
gradle wrapper

# Build the mod JAR
./gradlew build
```

4. The built JAR will be in `build/libs/fpsdisplay-1.0.0.jar`
5. Drop it into your `.minecraft/mods/` folder alongside the required mods above

---

## Configuration

Open the **Mods** screen in Minecraft → click **FPS Display** → click **Config**.

Changes take effect immediately and are saved when you close the screen.

The config file lives at: `.minecraft/config/fpsdisplay.json`

---

## Notes

- The overlay is automatically hidden when the debug screen (`F3`) is open, since Minecraft already shows FPS there
- `RAINBOW` color mode cycles through all hues smoothly
