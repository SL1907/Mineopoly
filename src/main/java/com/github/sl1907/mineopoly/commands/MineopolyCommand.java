package com.github.sl1907.mineopoly.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.github.sl1907.mineopoly.Mineopoly;
import com.github.sl1907.mineopoly.MineopolySetManager;
import com.github.sl1907.mineopoly.structure.MineopolySet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.io.InputStream;

@CommandAlias("mineopoly")
public class MineopolyCommand extends BaseCommand {

    @Default
    @Description("Mineopoly base command.")
    public static void onBase(Player player) {
        Mineopoly mineopoly = Mineopoly.getInstance();
        MineopolySetManager setManager = mineopoly.getSetManager();

        InputStream resource = mineopoly.getResource("london.json");
        player.sendMessage(Component.text("Attempting to load set...", NamedTextColor.GREEN));

        if (resource == null) {
            player.sendMessage(Component.text("An error occurred while loading the set.", NamedTextColor.RED));
            return;
        }

        MineopolySet mineopolySet = setManager.loadSet(resource);
        player.sendMessage(Component.text("Loaded set %s successfully.".formatted(mineopolySet.getName()), NamedTextColor.GREEN));

    }



}
