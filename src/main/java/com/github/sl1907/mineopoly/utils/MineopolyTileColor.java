package com.github.sl1907.mineopoly.utils;

import net.kyori.adventure.text.format.TextColor;

public enum MineopolyTileColor {

    BROWN(TextColor.color(97, 60, 32)),
    LIGHT_BLUE(TextColor.color(35, 137, 199)),
    PINK(TextColor.color(169, 48, 159)),
    ORANGE(TextColor.color(225, 99, 2)),
    RED(TextColor.color(143, 33, 33)),
    YELLOW(TextColor.color(239, 174, 21)),
    GREEN(TextColor.color(74, 92, 37)),
    DARK_BLUE(TextColor.color(45, 47, 144));

    private final TextColor color;

    MineopolyTileColor(TextColor color) {
        this.color = color;
    }

    public static TextColor getColorById(String id) {
        return switch (id.replaceAll("\\d","")) {
            case "BR" -> BROWN.getColor();
            case "LB" -> LIGHT_BLUE.getColor();
            case "PK" -> PINK.getColor();
            case "OR" -> ORANGE.getColor();
            case "RE" -> RED.getColor();
            case "YL" -> YELLOW.getColor();
            case "GR" -> GREEN.getColor();
            case "DB" -> DARK_BLUE.getColor();
            default -> TextColor.color(54, 57, 61);
        };
    }

    private TextColor getColor() {
        return this.color;
    }
}
