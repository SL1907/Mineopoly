package com.github.sl1907.mineopoly;

import com.github.sl1907.mineopoly.structure.MineopolySet;
import com.github.sl1907.mineopoly.structure.tiles.MineopolyProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.lucko.helper.Commands;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

public final class Mineopoly extends JavaPlugin {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void onEnable() {
        Commands.create()
                .assertPlayer()
                .handler(c -> {
                    URL resource = this.getClass().getResource("london.json");
                    if (resource != null) {
                        MineopolySet mineopolySet = this.readJson(resource);
                        if (mineopolySet == null) {
                            return;
                        }

                        for (MineopolyProperty property : mineopolySet.getProperties()) {
                            c.sender().sendMessage(Component.text(
                                    "%s | %s %s".formatted(property.getId(), property.getName(), Arrays.toString(property.getRent()))
                            ));
                        }
                    }
                })
                .register("mineopoly");
    }

    private MineopolySet readJson(URL path) {
        try {
            File file = new File(path.toURI());
            try (FileReader fileReader = new FileReader(file)) {
                return GSON.fromJson(fileReader, MineopolySet.class);
            } catch (IOException e) {
                return null;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
    }
}
