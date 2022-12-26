package com.github.sl1907.mineopoly;

import co.aikar.commands.PaperCommandManager;
import com.github.sl1907.mineopoly.commands.MineopolyCommand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mineopoly extends JavaPlugin {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Getter private static Mineopoly instance;
    @Getter private PaperCommandManager commandManager;
    @Getter private MineopolySetManager setManager;

    @Override
    public void onEnable() {
        instance = this;

        this.commandManager = new PaperCommandManager(this);
        this.setManager = new MineopolySetManager();

        this.commandManager.registerCommand(new MineopolyCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
