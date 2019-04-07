package com.forge.engine;
import static org.lwjgl.opengl.GL11.*;


import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.forge.game.Game;

public class Main{

	/**
	 * @author Brad
	 */
	private static final long serialVersionUID = 1L;
	public static String TITLE = "RPG Alpha -- 0.10 ";
	public static int HEIGHT = 800;
	public static int WIDTH = HEIGHT * 16 / 9;
	public static boolean running = false;
	public static Game game;
	
	public Main(){
		loadNatives();
		setDisplay();
		init();
		initGame();
		start();
		cleanup();
	}

	public static void setDisplay(){
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
			Display.setVSyncEnabled(true);
			Display.setResizable(false);
			
			Display.setTitle(TITLE);
			Keyboard.create();
		} catch (LWJGLException e) {
			System.out.println("Could not set the resolution properly.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	private static void initGame(){
		game = new Game();
	}
	public static void start() {
		running = true;

		
		while (!Display.isCloseRequested() && running) {
			getInput();
			update();
			render();

		}

	}
	public static void getInput(){
		game.getInput();
	}
	public static void update(){
		game.update();
	}

	public static void render(){
		glClear(GL_COLOR_BUFFER_BIT); 
		glLoadIdentity();

		game.render();
		
		Display.sync(60);
		Display.update();
		
		
	}

	public static void cleanup(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
		System.out.println("Shutting down.");
		System.exit(0);
	}
	public static void init(){	
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);      
		glDisable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(1,1,1,1);
	}

	public static void loadNatives() {
		try {
			NativesLoader.loadNativeLibrary();
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("Killed Process -- Could not load native library for your operating system.");
			System.exit(0);
		}

		System.out.println("Loaded Native Libraries.");
	}
	public static void main(String[] args) {

		new Main();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
