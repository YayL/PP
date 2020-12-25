package me.YayL.game.states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import me.YayL.game.controllers.PlayerController;
import me.YayL.game.entity.Ball;
import me.YayL.game.entity.Computer;
import me.YayL.game.entity.Player;
import me.YayL.game.entity.handler.Size;
import me.YayL.game.graphics.Display;
import me.YayL.game.listeners.Input;
import me.YayL.game.ui.*;
import me.YayL.game.controllers.AIController;

public class GameState extends State {

	private static boolean running;
	
	public GameState(Input input) {
		super(input);
		initCharacters();
		initUI(new Size(Display.Width, Display.Height));
	}

	private void initUI(Size windowSize){
		UIContainer container = new VerticalContainer(windowSize);
		container.setPadding(new Spacing(50));
		container.setBackgroundColor(new Color(0, 0, 0, 0));

		uiContainers.add(container);
	}
	
	private void initCharacters() {
		running = true;
		Player player = new Player(new PlayerController(input));
		Computer comp = new Computer(new AIController());
		Ball ball = new Ball();
		gameObjects.addAll(List.of(player, comp, ball));
	}
	
	private void keyBinds() {
		if(System.currentTimeMillis() - lastTimeSinceStateSwitch >= 250) {
			if(input.isPressed(KeyEvent.VK_ESCAPE)) {
				newState("menuState");
				resetDelay();
			}else if(input.isPressed(KeyEvent.VK_RIGHT)){
				Ball.setHits(Ball.getHits()+1);
			}else if(input.isPressed(KeyEvent.VK_LEFT)){
				Ball.setHits(Ball.getHits()-1);
			}
		}
	}
	
	public void update() {
		keyBinds();

		if(running) {
			super.update();
		}
	}
	
	public static void goalScore() {
		
	}
	
	public static void stop() {
		running = false;
	}
	
	public static void start() {
		running = true;
	}
	
	

}
