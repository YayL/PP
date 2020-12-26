package me.YayL.game.graphics;

import java.awt.Graphics;

import me.YayL.game.states.State;

public class Renderer {

	private void renderGameObject(State state, Graphics graphics){
		State.getGameObjects().forEach(gameObject -> graphics.drawImage(
				gameObject.getSprite(),
				gameObject.getPos().intX(),
				gameObject.getPos().intY(),
				null
		));
	}

	public void render(State state, Graphics graphics) {
		renderGameObject(state, graphics);
		renderUI(state, graphics);
	}

	private void renderUI(State state, Graphics graphics) {
		state.getUiContainers().forEach(uiContainer -> graphics.drawImage(
				uiContainer.getSprite(),
				uiContainer.getPos().intX(),
				uiContainer.getPos().intY(),
				null
		));
	}

}
