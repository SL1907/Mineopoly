package com.github.sl1907.mineopoly.structure;

import com.github.sl1907.mineopoly.structure.tiles.MineopolyCorner;
import com.github.sl1907.mineopoly.structure.tiles.MineopolyProperty;
import com.github.sl1907.mineopoly.structure.tiles.MineopolyStation;
import com.github.sl1907.mineopoly.structure.tiles.MineopolyTax;
import lombok.Getter;

import java.util.List;

public class MineopolySet {

    @Getter private String name;
    @Getter private String country;
    @Getter private String currency;

    @Getter private List<MineopolyCorner> corners;
    @Getter private List<MineopolyProperty> properties;
    @Getter private List<MineopolyStation> stations;
    @Getter private List<MineopolyTax> taxes;

}
