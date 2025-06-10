package org.Zalmandrag.customCrafts.Parser;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class ColorParser {
    public static String ParseColor(@NotNull String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
