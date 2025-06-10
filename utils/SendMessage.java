package org.Zalmandrag.customCrafts.utils;

import org.Zalmandrag.customCrafts.CustomCrafts;
import org.Zalmandrag.customCrafts.Parser.ColorParser;
import org.bukkit.command.CommandSender;

public class SendMessage {
    public static void SendMessage(CommandSender sender, String message) {
        sender.sendMessage(ColorParser.ParseColor((message)));
    }
    public static void SendMessage(CustomCrafts plugin, String message) {
        plugin.getLogger().info(message);
    }
}
