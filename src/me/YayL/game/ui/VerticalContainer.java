package me.YayL.game.ui;


import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;

public class VerticalContainer extends UIContainer{

    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for(UIComponent uiComponent : children){
            combinedChildHeight += uiComponent.getSize().getHeight() + uiComponent.getMargin().getVertical();

            if(uiComponent.getSize().getWidth() > widestChildWidth){
                widestChildWidth = uiComponent.getSize().getWidth();
            }
        }

        return new Size(widestChildWidth, combinedChildHeight);
    }

    @Override
    protected void calculateContentPos() {
        int currentY = padding.getTop();

        for(UIComponent uiComponent : children){
            currentY += uiComponent.getMargin().getTop();
            uiComponent.setPos(new Pos(padding.getLeft(), currentY));
            currentY += uiComponent.getSize().getHeight();
            currentY += uiComponent.getMargin().getBottom();
        }
    }

}
