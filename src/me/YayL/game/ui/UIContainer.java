package me.YayL.game.ui;

import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;
import me.YayL.game.graphics.ImageUtils;
import me.YayL.game.states.State;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent {

    protected Color backgroundColor;

    protected final List<UIComponent> children;

    protected Alignment alignment;
    protected final Size windowSize;

    public UIContainer(Size windowSize){
        super();
        this.windowSize = windowSize;
        alignment = new Alignment(Alignment.Pos.START, Alignment.Pos.START);
        backgroundColor = Color.red;
        children = new ArrayList<>();
        calculateSize();
        calculatePos();
    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPos();

    private void calculateSize(){
        Size calculatedContentSize = calculateContentSize();
        size = new Size(
                padding.getHorizontal() + calculatedContentSize.getWidth(),
                padding.getVertical() +calculatedContentSize.getHeight()
        );
    }

    private void calculatePos(){

        int x = padding.getLeft();
        if (alignment.getHorizontal().equals(Alignment.Pos.CENTER)) {
            x = (windowSize.getWidth() - size.getWidth())/2;
        }
        if(alignment.getHorizontal().equals(Alignment.Pos.END)){
            x = windowSize.getWidth()-size.getWidth() - margin.getRight();
        }

        int y = padding.getTop();
        if (alignment.getVertical().equals(Alignment.Pos.CENTER)) {
            y = (windowSize.getHeight() - size.getHeight())/2;
        }
        if(alignment.getVertical().equals(Alignment.Pos.END)){
            y = windowSize.getHeight()-size.getHeight() - margin.getBottom();
        }

        this.pos = new Pos(x, y);
        calculateContentPos();
    }

    @Override
    public Image getSprite() {
        BufferedImage img = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = img.createGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

        for(UIComponent uiComponent : children){
            graphics.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getPos().intX(),
                    uiComponent.getPos().intY(),
                    null
            );
        }

        graphics.dispose();
        return img;
    }

    @Override
    public void update(State state) {
        children.forEach(component -> component.update(state));
        calculateSize();
        calculatePos();
    }

    public void addUIComponent(UIComponent uiComponent){
        children.add(uiComponent);
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }
}
