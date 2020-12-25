package me.YayL.game.ui;

import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;
import me.YayL.game.states.State;

import java.awt.*;

public abstract class UIComponent {

    protected Pos pos;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    public UIComponent(){
        pos = new Pos(0, 0);
        size = new Size(1, 1);
        margin = new Spacing(5);
        padding = new Spacing(5);
    }

    public abstract Image getSprite();
    public abstract void update(State state);

    public void setPos(Pos pos) {
        this.pos = pos;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setMargin(Spacing margin) {
        this.margin = margin;
    }

    public void setPadding(Spacing padding) {
        this.padding = padding;
    }

    public Pos getPos() {
        return pos;
    }

    public Size getSize() {
        return size;
    }

    public Spacing getMargin() {
        return margin;
    }

    public Spacing getPadding() {
        return padding;
    }
}
