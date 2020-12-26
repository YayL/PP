package me.YayL.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import me.YayL.game.entity.handler.GameObject;
import me.YayL.game.entity.handler.Movement;
import me.YayL.game.entity.handler.MovingEntity;
import me.YayL.game.entity.handler.Pos;
import me.YayL.game.entity.handler.Size;
import me.YayL.game.graphics.Display;
import me.YayL.game.graphics.Vector2D;
import me.YayL.game.states.GameState;

public class Ball extends MovingEntity{

	private Movement movement;
	private int speed = ballSpeed;
	private Vector2D vector;
	
	private int Width = Display.Width, Height = Display.Height;
	
	private Vector2D getVector() {
		return this.vector;
	}
	
	private void setVector(Vector2D vector) {
		this.vector = vector;
	}
	
	public static boolean isBallDead(GameObject ball) {
		if(ball.getPos().getX() <= 0 || ball.getPos().getX()+(ball.getSize().getWidth()) >= 720) {
			return true;
		}
		return false;
	}
	
	private int startVel(int x, int y) {
		x = (int) Math.round(Math.pow(x + 1, 1.0/3.0));
		if(y>=1) {
			return x*-1;
		}
		
		return x;
	}

	private static int hits;

	public static int getHits() {
		return hits;
	}

	public static void setHits(int hits) {
		Ball.hits = hits;
	}
	
	public Ball() {
		super(null);
		Random rand = new Random();
		this.movement = new Movement(speed, startVel(rand.nextInt(10000), rand.nextInt(3)), startVel(rand.nextInt(10), rand.nextInt(3)));
		this.hits = 0;
		
		size = new Size(25, 25);
		pos = new Pos(Width/2 - size.getWidth(), (Height-size.getHeight())/2);
	}

	// Ball Movement: 
	
	public void update() {

		vector = calculateMovement(new Pos(gameObjects.get(2).getPos().getX(), gameObjects.get(2).getPos().getY()));
		vector.normalize();

		double extraSpeed = ((Math.pow(Math.log(hits), 3))/6.289);

		vector.multiply(speed + ((extraSpeed>=1) ? extraSpeed : 0)); // +20 at 150 hits
		
		if(vector != null) {
			setVector(vector);
		}
		
		movement = new Movement(speed, (int) Math.round(vector.getX()), (int) Math.round(vector.getY()));
		
		pos.apply(movement, null);
	}
	
	
	private Vector2D calculateMovement(Pos pos) {
		vector = collision(getVector());
		if(vector != null) {
			return vector;
		}
		
		return new Vector2D(movement.getVector().getX(), movement.getVector().getY());
	}
	
	private Vector2D collision(Vector2D vector) {
		double[] plr = getObjectSpecs(gameObjects.get(0), 0); // [0] Right [1] Top [2] Bottom
		double[] comp = getObjectSpecs(gameObjects.get(1), 1);// [0] Left [1] Top [2] Bottom
		double[] circle = getObjectSpecs(gameObjects.get(2), 2);// [0] Left [1] Right [2] Top [3] Bottom

		if(vector == null){
			return vector;
		}

		if(circle[0]+vector.getX() <= plr[0] && circle[2] < plr[2] && circle[3] > plr[1] ) { // If ball hits paddle
			setHits(getHits()+1);
			return new Vector2D(vector.getX()*-1, vector.getY());
		}
		
		if(circle[1]+vector.getX() >= comp[0] && circle[2] < comp[2] && circle[3] > comp[1]) {
			setHits(getHits()+1);
			return new Vector2D(vector.getX()*-1, vector.getY());
		}
		
		if( (circle[3] >= Height) || circle[2] <= 0) {
			return new Vector2D(vector.getX(), vector.getY()*-1);
		}
		
		return vector;
	}

	// Ball Rendering
	
	@Override
	public Image getSprite() {
		BufferedImage img = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = img.createGraphics();
		
		graphics.setColor(Color.GREEN);
		graphics.fillOval(0, 0, size.getWidth(), size.getHeight());
		
		graphics.dispose();
		return img;
	}
	
}
