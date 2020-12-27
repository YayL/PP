package me.YayL.game.states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import me.YayL.game.entity.handler.Size;
import me.YayL.game.graphics.Display;
import me.YayL.game.listeners.Input;
import me.YayL.game.ui.*;

public class MenuState extends State{

	private final Color transparent = new Color(0,0,0,0);

	public MenuState(Input input) {
		super(input);
		initUI(new Size(Display.Width, Display.Height));
	}

	private void initUI(Size windowSize){
		UIContainer text = new VerticalContainer(windowSize);
		text.setPadding(new Spacing(5));
		text.setBackgroundColor(transparent);
		text.setAlignment(new Alignment(Alignment.Pos.CENTER, Alignment.Pos.START));

		UIContainer button = new VerticalContainer(windowSize);
		button.setPadding(new Spacing(5));
		button.setBackgroundColor(new Color(109, 102, 102, 150));
		button.setAlignment(new Alignment(Alignment.Pos.CENTER, Alignment.Pos.CENTER));


		text.addUIComponent(new UIText("MAIN MENU"));
		button.addUIComponent(new UIText("PRESS SPACE TO"));
		button.addUIComponent(new UIText("  START GAME"));
		uiContainers.addAll(List.of(text, button));
	}
	
	public void keyBinds() {
		if(System.currentTimeMillis() - lastTimeSinceStateSwitch >= 100) {
			if(input.isPressed(KeyEvent.VK_SPACE)) {
				newState("gameState");
				resetDelay();
			}
		}
	}
	
	public void update() {
		keyBinds();
		super.update();
	}

}
