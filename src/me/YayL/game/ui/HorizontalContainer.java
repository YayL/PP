package me.YayL.game.ui;

import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;

import java.util.Collection;
import java.util.Iterator;

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
