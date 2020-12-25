package me.YayL.game.graphics;

public class Vector2D {
	
	private double x, y;

	public Vector2D(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void normalize() {
		double length = Math.sqrt(x*x + y*y);
		x = x == 0 ? 0 : x/length;
		y = y == 0 ? 0 : y/length;
	}
	
	public void multiply(double speed) {
		x *= speed;
		y *= speed;
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	

}
