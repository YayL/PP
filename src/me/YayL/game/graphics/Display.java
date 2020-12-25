package me.YayL.game.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import me.YayL.game.listeners.Input;
import me.YayL.game.states.State;

public class Display extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private Renderer renderer;
	public static int Height, Width;
	
	public Display(int width, int height, Input input) {
		Display.Height = height;
		Display.Width = width;
		
		setTitle("An awesome 2D game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		this.renderer = new Renderer();
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		add(canvas);
		addKeyListener(input);
		pack();
		
		canvas.createBufferStrategy(3);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void render(State state) {
		BufferStrategy buffer = canvas.getBufferStrategy();
		Graphics graphics = buffer.getDrawGraphics();
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		renderer.render(state, graphics);
		
		graphics.dispose();
		buffer.show();
	}
}
