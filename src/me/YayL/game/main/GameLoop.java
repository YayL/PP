package me.YayL.game.main;

public class GameLoop implements Runnable{

	private final Game game;

	private long nextStatTime;
	private int fps, ups;
	
	public GameLoop(Game game) {
		this.game = game;
	}
	
	public void run() {
		boolean running = true;
		double accumulator = 0;
		long currentTime, lastUpdate = System.currentTimeMillis();
		nextStatTime = System.currentTimeMillis() + 1000;
		
		while(running) {
			currentTime = System.currentTimeMillis();
			double lastRenderTimeInSeconds = (currentTime - lastUpdate)/1000d;
			accumulator += lastRenderTimeInSeconds;
			lastUpdate = currentTime;

			double updateRate = 1.0d / 60.0d;
			while(accumulator > updateRate) {
				update();
				accumulator -= updateRate;
			}
			
			render();
			printStats();
		}
	}
	
	private void printStats() {
		if(System.currentTimeMillis() > nextStatTime) {
			System.out.printf("FPS: %d, UPS: %d%n", fps, ups);
			fps = 0;
			ups = 0;
			nextStatTime = System.currentTimeMillis() + 1000;
			
		}
	}


	private void render() {
		game.render();
		fps++;
	}

	private void update() {
		game.update();
		ups++;
	}

}
