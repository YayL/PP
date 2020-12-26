package me.YayL.game.controllers;

import java.awt.event.KeyEvent;

import me.YayL.game.listeners.Input;

public class PlayerController implements Controller{

	private final Input input;
	
	public PlayerController(Input input) {
		this.input = input;
	}
	
	@Override
	public boolean isRequestingUp() {
		return input.isPressed(KeyEvent.VK_W);
	}

	@Override
	public boolean isRequestingDown() {
		return input.isPressed(KeyEvent.VK_S);
	}

	@Override
	public boolean isRequestingRight() {
		//return input.isPressed(KeyEvent.VK_D);
		return false;
	}

	@Override
	public boolean isRequestingLeft() {
		//return input.isPressed(KeyEvent.VK_A);
		return false;
	}

}
