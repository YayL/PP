package me.YayL.game.states;

import me.YayL.game.entity.handler.GameObject;
import me.YayL.game.listeners.Input;
import me.YayL.game.ui.UIContainer;

import java.util.ArrayList;
import java.util.List;

public abstract class State {
	
	protected static List<GameObject> gameObjects;
	protected static Input input;
	protected List<UIContainer> uiContainers;

	private static State state;

	public State(Input input) {
		State.input = input;
		gameObjects = new ArrayList<>();
		uiContainers = new ArrayList<>();
	}
	
	public void update() {
		gameObjects.forEach(gameObject -> gameObject.update());
		uiContainers.forEach(uiContainer -> uiContainer.update(this));
	}

	public static List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public long lastTimeSinceStateSwitch = System.currentTimeMillis();
	
	public void resetDelay() {
		this.lastTimeSinceStateSwitch = System.currentTimeMillis();
	}
	
	public static State getState() {
		return state;
	}
	
	public static void newState(String state) {
		if(state == "gameState") {
			State.state = new GameState(input);
			State gameState = State.state;
		}else if(state == "menuState") {
			State.state = new MenuState(input);
			State menuState = State.state;
		}
	}

	public List<UIContainer> getUiContainers() {
		return uiContainers;
	}

}
