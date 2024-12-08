package dev.berogov.changecolor.block.changecolor;

import net.minecraft.util.IStringSerializable;

public enum ColorState implements IStringSerializable {
    WHITE("white"),
    BLACK("black");

    private final String name;

    ColorState(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return this.name;
    }
}