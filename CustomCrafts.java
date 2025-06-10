package org.Zalmandrag.customCrafts;

import org.Zalmandrag.customCrafts.Commands.CommandExecutor;
import org.Zalmandrag.customCrafts.Yaml.YamlWorker;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Objects;

public final class CustomCrafts extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            YamlWorker.CheckYaml(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (YamlWorker.ValidateYamlCrafts(this)) Objects.requireNonNull(getCommand("CustomCrafts")).setExecutor(new CommandExecutor(this));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
