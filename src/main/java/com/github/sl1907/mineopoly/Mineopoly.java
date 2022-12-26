package com.github.sl1907.mineopoly;

import com.github.sl1907.mineopoly.structure.MineopolySet;
import com.github.sl1907.mineopoly.structure.tiles.MineopolyProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.lucko.helper.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public final class Mineopoly extends JavaPlugin {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void onEnable() {
        Commands.create()
                .assertPlayer()
                .handler(c -> {
                    InputStream resource = this.getResource("london.json");

                    if (resource == null) {
                        c.sender().sendMessage(Component.text("An error occurred while loading the set.", NamedTextColor.RED));
                        return;
                    }

                    MineopolySet mineopolySet = this.readJson(resource);
                    if (mineopolySet == null) {
                        c.sender().sendMessage(Component.text("An error occurred.", NamedTextColor.RED));
                        return;
                    }

                    c.sender().sendMessage(Component.text("Loaded set %s successfully.".formatted(mineopolySet.getName()), NamedTextColor.GREEN));
                    for (MineopolyProperty property : mineopolySet.getProperties()) {
                        c.sender().sendMessage(Component.text(
                                "%s | %s %s".formatted(property.getId(), property.getName(), Arrays.toString(property.getPos1())),
                                NamedTextColor.AQUA
                        ));
                    }
                })
                .register("mineopoly");
    }

    private MineopolySet readJson(InputStream stream) {
        InputStreamReader reader = new InputStreamReader(stream);
        return GSON.fromJson(reader, MineopolySet.class);
    }

    @Override
    public void onDisable() {
    }
}
