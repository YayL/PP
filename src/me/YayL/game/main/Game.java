package me.YayL.game.main;

import me.YayL.game.graphics.Display;
import me.YayL.game.listeners.Input;
import me.YayL.game.states.GameState;
import me.YayL.game.states.MenuState;
import me.YayL.game.states.State;

public class Game {

	private Display display;
	private static Input input;
	
	private State state;
	
	public Game(int width, int height) {
		input = new Input();
		display = new Display(width, height, input);
		state = new MenuState(input);
	}
	
	public void render() {
		display.render(state);
	}
	
	
	
	public void update() {
		State tempState = State.getState();
		if(tempState != null) {
			state = tempState;
		}
		state.update();
		
	}
	
}
