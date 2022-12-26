package com.github.sl1907.mineopoly.utils;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class MineopolyTileRegion {

    @Getter private final String id;
    private final int xMin;
    private final int xMax;
    private final int yMin;
    private final int yMax;
    private final int zMin;
    private final int zMax;

    private final World world;

    public MineopolyTileRegion(World world, String id, int[] pos1, int[] pos2) {
        this.world = world;
        this.id = id;

        this.xMin = pos1[0];
        this.yMin = pos1[1];
        this.zMin = pos1[2];

        this.xMax = pos2[0];
        this.yMax = pos2[1];
        this.zMax = pos2[2];
    }

    public boolean isIn(Location loc) {
        return loc.getBlockX() >= this.xMin && loc.getBlockX() <= this.xMax
                && loc.getBlockY() >= this.yMin && loc.getBlockY() <= this.yMax
                && loc.getBlockZ() >= this.zMin && loc.getBlockZ() <= this.zMax;
    }

    public boolean isIn(Player player) {
        return this.isIn(player.getLocation());
    }

    public Location getCenter() {
        return new Location(this.world,
                (double) (this.xMax - this.xMin) / 2 + this.xMin,
                (double) (this.yMax - this.yMin) / 2 + this.yMin,
                (double) (this.zMax - this.zMin) / 2 + this.zMin);
    }
}
