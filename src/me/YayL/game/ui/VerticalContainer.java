package me.YayL.game.ui;


import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;

import java.util.Collection;
import java.util.Iterator;

public class VerticalContainer extends UIContainer{

    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight = 0;
        int widesstChildWidth = 0;

        for(UIComponent uiComponent : children){
            combinedChildHeight += uiComponent.getSize().getHeight() + uiComponent.getMargin().getVertical();

            if(uiComponent.getSize().getWidth() > widesstChildWidth){
                widesstChildWidth = uiComponent.getSize().getWidth();
            }
        }

        return new Size(widesstChildWidth, combinedChildHeight);
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

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<UIContainer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(UIContainer uiContainer) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends UIContainer> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
