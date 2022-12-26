package com.github.sl1907.mineopoly.structure;

import com.github.sl1907.mineopoly.structure.tiles.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MineopolySet {

    @Getter private String name;
    @Getter private String country;
    @Getter private String currency;

    @Getter private List<MineopolyCorner> corners;
    @Getter private List<MineopolyProperty> properties;
    @Getter private List<MineopolyStation> stations;
    @Getter private List<MineopolyTax> taxes;
    @Getter private List<MineopolyCard> cards;

    @Getter @Setter private List<MineopolyTile> allTiles;

}
