package me.YayL.game.main;

public class Launcher {
	
	public static void main(String[] args) {
		new Thread(new GameLoop(new Game(1280, 720))).start();
	}
	
}
