package me.YayL.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import me.YayL.game.entity.handler.*;
import me.YayL.game.graphics.Vector2D;
import me.YayL.game.controllers.AIController;

public class Computer extends MovingEntity{

	private int compDiff = 1;

	private final int speed = paddleSpeed;
	
	private Vector2D vector;
	
	private Vector2D getVector() {
		return this.vector;
	}
	
	private void setVector(Vector2D vector) {
		this.vector = vector;
	}
	
	public Computer(AIController controller) {
		super(controller);
		size = new Size(25, 125);
		pos = new Pos(1280-(size.getWidth()+5), (720-size.getHeight())/2);
	}

	public void update() {
		AIController.calculateMovement(compDiff, gameObjects.get(1));
		super.update();
	}
	

	@Override
	public Image getSprite() {
		BufferedImage img = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = img.createGraphics();
		
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
		
		graphics.dispose();
		return img;
	}
	



}
