package me.YayL.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import me.YayL.game.entity.handler.MovingEntity;
import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;
import me.YayL.game.graphics.Display;
import me.YayL.game.controllers.AIController;
import me.YayL.game.controllers.Controller;

public class Player extends MovingEntity {

	public static final int speed = paddleSpeed;
	
	private final int Width = Display.Width;

	public Player(Controller controller) {
		super(controller);

		size = new Size(25, 125);
		int height = Display.Height;
		pos = new Pos(5, (height - size.getHeight()) / 2);
	}

	@Override
	public void update() {
		if(controller instanceof AIController){
			AIController.calculateMovement(1, gameObjects.get(0));
		}
		super.update();

	}

	@Override
	public Image getSprite() {
		BufferedImage img = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = img.createGraphics();

		graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, size.getWidth(), size.getHeight());

		graphics.dispose();
		return img;
	}

}
