package me.YayL.game.ui;

public class Alignment {

    public enum Pos{
        START, CENTER, END
    }

    private final Pos vertical;
    private final Pos horizontal;

    public Alignment(Pos horizontal, Pos vertical) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public Pos getVertical() {
        return vertical;
    }

    public Pos getHorizontal() {
        return horizontal;
    }
}
