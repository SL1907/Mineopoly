package com.github.sl1907.mineopoly;

import com.github.sl1907.mineopoly.structure.MineopolySet;
import com.github.sl1907.mineopoly.structure.MineopolyTile;
import com.github.sl1907.mineopoly.structure.Purchasable;
import com.github.sl1907.mineopoly.structure.tiles.MineopolyCard;
import com.github.sl1907.mineopoly.utils.MineopolyTileColor;
import com.github.sl1907.mineopoly.utils.MineopolyTileRegion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.github.sl1907.mineopoly.Mineopoly.GSON;

public class MineopolySetManager {

    private final AtomicReference<MineopolySet> currentSet = new AtomicReference<>();

    public MineopolySet loadSet(InputStream resource) {
        InputStreamReader reader = new InputStreamReader(resource);
        MineopolySet mineopolySet = GSON.fromJson(reader, MineopolySet.class);
        if (mineopolySet == null) {
            throw new RuntimeException("Set could not be loaded.");
        }

        List<MineopolyTile> tiles = new ArrayList<>();
        tiles.addAll(mineopolySet.getProperties());
        tiles.addAll(mineopolySet.getTaxes());
        tiles.addAll(mineopolySet.getStations());
        tiles.addAll(mineopolySet.getCorners());
        tiles.addAll(mineopolySet.getCards());
        mineopolySet.setAllTiles(tiles);

        for (MineopolyTile tile : mineopolySet.getAllTiles()) {
            tile.setRegion(new MineopolyTileRegion(Bukkit.getWorld("world"), tile.getId(), tile.getPos1(), tile.getPos2()));
        }

        this.currentSet.set(mineopolySet);
        this.createHolograms();

        return mineopolySet;
    }

    private void createHolograms() {
        MineopolySet mineopolySet = this.currentSet.get();

        for (MineopolyTile tile : mineopolySet.getAllTiles()) {
            this.spawnHologram(tile, tile.getName(), tile.getRegion().getCenter().add(0.5, 0, 0.5));
            if (tile instanceof Purchasable pTile) {
                String currency = mineopolySet.getCurrency();
                int price = pTile.getPrice();
                this.spawnHologram(tile, "%s%s".formatted(currency, price), tile.getRegion().getCenter().add(0.5, -0.25, 0.5));
            }
        }
    }

    private void spawnHologram(MineopolyTile tile, String displayName, Location location) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location.add(0, 2, 0), EntityType.ARMOR_STAND);
        if (tile instanceof MineopolyCard) {
            armorStand.customName(Component.text(displayName, Style.style(MineopolyTileColor.getColorById(tile.getId()), TextDecoration.BOLD)));
        } else {
            armorStand.customName(Component.text(displayName, MineopolyTileColor.getColorById(tile.getId())));
        }
        armorStand.setInvisible(true);
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        armorStand.setInvulnerable(true);
    }
}
