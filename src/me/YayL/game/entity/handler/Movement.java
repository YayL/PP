package me.YayL.game.entity.handler;

import me.YayL.game.graphics.Vector2D;
import me.YayL.game.controllers.Controller;

public class Movement {
	
	private Vector2D vector;
	private final double speed;
	
	public Movement(double speed, int startX, int startY) {
		this.vector = new Vector2D(startX, startY);
		this.speed = speed;
	}
	
	public void update(Controller controller) {
		int deltaX = 0, deltaY = 0;
		
		if(controller.isRequestingUp()) {
			deltaY--;
		}
		
		if(controller.isRequestingDown()) {
			deltaY++;
		}
		
		if(controller.isRequestingRight()) {
			deltaX++;
		}
		
		if(controller.isRequestingLeft()) {
			deltaX--;
		}
		
		vector = new Vector2D(deltaX, deltaY);
		vector.normalize();
		vector.multiply(speed);
	}

	public Vector2D getVector() {
		return vector;
	}
	
}
