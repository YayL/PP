package me.YayL.game.controllers;

public class ComputerController implements Controller{


	@Override
	public boolean isRequestingUp() {
		return false;
	}

	@Override
	public boolean isRequestingDown() {
		return false;
	}

	@Override
	public boolean isRequestingRight() {
		return false;
	}

	@Override
	public boolean isRequestingLeft() {
		return false;
	}

}
