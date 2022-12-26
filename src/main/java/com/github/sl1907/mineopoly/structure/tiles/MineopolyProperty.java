package com.github.sl1907.mineopoly.structure.tiles;

import com.github.sl1907.mineopoly.structure.MineopolyTile;
import com.github.sl1907.mineopoly.structure.Purchasable;
import lombok.Getter;

public class MineopolyProperty extends MineopolyTile implements Purchasable {

    @Getter private int price;
    @Getter private int[] rent;

}
