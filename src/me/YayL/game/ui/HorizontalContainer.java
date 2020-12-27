package me.YayL.game.ui;

import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;


public class HorizontalContainer extends UIContainer{

    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;

        for(UIComponent uiComponent : children){
            combinedChildWidth += uiComponent.getSize().getWidth() + uiComponent.getMargin().getHorizontal();

            if(uiComponent.getSize().getHeight() > tallestChildHeight){
                tallestChildHeight = uiComponent.getSize().getHeight();
            }
        }

        return new Size(combinedChildWidth, tallestChildHeight);
    }

    @Override
    protected void calculateContentPos() {
        int currentX = padding.getLeft();

        for(UIComponent uiComponent : children){
            currentX += uiComponent.getMargin().getLeft();
            uiComponent.setPos(new Pos(currentX, padding.getTop()));
            currentX += uiComponent.getSize().getWidth();
            currentX += uiComponent.getMargin().getRight();
        }
    }

}
