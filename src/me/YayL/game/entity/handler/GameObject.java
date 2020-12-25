package me.YayL.game.entity.handler;

import java.awt.*;

public abstract class GameObject {

	protected Pos pos;
	protected Size size;
	
	public abstract void update();
	public abstract Image getSprite();

	public Pos getPos() {
		return pos;
	}
	
	public void setPos(Pos pos) {
		this.pos = pos;
	}

	public Size getSize() {
		return size;
	}
	
}
