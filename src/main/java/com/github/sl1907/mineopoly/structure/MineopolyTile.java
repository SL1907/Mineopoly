package com.github.sl1907.mineopoly.structure;

import com.github.sl1907.mineopoly.utils.MineopolyTileRegion;
import lombok.Getter;
import lombok.Setter;

public abstract class MineopolyTile {

    @Getter private String id;
    @Getter private String name;
    @Getter private int[] pos1;
    @Getter private int[] pos2;

    @Getter @Setter private MineopolyTileRegion region;

}
