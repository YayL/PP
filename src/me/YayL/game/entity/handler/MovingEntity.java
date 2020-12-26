package me.YayL.game.entity.handler;

import java.util.List;

import me.YayL.game.entity.Ball;
import me.YayL.game.entity.Player;
import me.YayL.game.graphics.Display;
import me.YayL.game.states.State;
import me.YayL.game.controllers.Controller;

public abstract class MovingEntity extends GameObject{

	protected Controller controller;
	protected Movement movement;
	
	protected static int paddleSpeed = 5;
	protected int ballSpeed = 5;
	
	protected int Width = Display.Width;
	protected int Height = Display.Height;
	
	protected GameObject player;
	protected GameObject ball;
	protected GameObject comp;
	
	public static List<GameObject> gameObjects;

	public MovingEntity(Controller controller) {
		super();
		this.controller = controller;
		this.movement = new Movement(Player.speed, 0, 0);
		this.gameObjects = State.getGameObjects();
	}

	protected boolean isOutOfBounds(GameObject object) {
		return ((object.getPos().getY() + movement.getVector().getY()) >= 0 && (object.getPos().getY() + movement.getVector().getY()) < (Height-object.getSize().getHeight())) ? true : false; 
	}

	protected static double[] getObjectSpecs(GameObject object, int type) {
		if (type == 0) {
			return new double[]{
					object.getPos().getX() + object.getSize().getWidth(), //Right part of the player
					object.getPos().getY(), // Top part of the player
					object.getPos().getY() + object.getSize().getHeight()}; // Bottom part of the player
		} else if (type == 1) {
			return new double[]{
					object.getPos().getX(), // Left part of the computer
					object.getPos().getY(), // Top part of the computer
					object.getPos().getY() + object.getSize().getHeight()}; // Bottom part of the computer
		} else if (type == 2) {
			return new double[]{
					object.getPos().getX(), // Left part of the ball
					object.getPos().getX() + object.getSize().getWidth(), // Right part of the ball
					object.getPos().getY(), // Top part of the computer
					object.getPos().getY() + object.getSize().getHeight()};
		}
		return null;
	}


	public void update() {
		player = gameObjects.get(0);
		comp = gameObjects.get(1);
		
		movement.update(controller);

		if(isOutOfBounds(player)) {
			pos.apply(movement, "player");
		}else {
			double val = player.getPos().getY() > 500 ?  594 : 0;
			player.setPos(new Pos(5, val));
		}

		if(isOutOfBounds(comp)){
			pos.apply(movement, "comp");
		}else {
			double val = comp.getPos().getY() > 500 ?  594 : 0;
			comp.setPos(new Pos(Width-(comp.getSize().getWidth()+5), val));
		}

	}

	
}
