package me.YayL.game.entity.handler;

import me.YayL.game.graphics.Vector2D;
import me.YayL.game.main.Game;

public class Pos {
	private double x;
	private double y;
	
	public Pos(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public int intX() {
		return (int)Math.round(x);
	}
	
	public int intY() {
		return (int) Math.round(y);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double val) {
		y = val;
	}
	

	public void apply(Movement movement, String object) {
		Vector2D vector = movement.getVector();
		x += vector.getX();
		y += vector.getY();

	}
}
