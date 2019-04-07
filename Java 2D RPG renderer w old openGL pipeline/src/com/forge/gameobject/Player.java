package com.forge.gameobject;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import com.forge.engine.GameObject;
import com.forge.engine.Main;
import com.forge.engine.Physics;
import com.forge.engine.Sprite;
import com.forge.engine.file.IOStream;
import com.forge.game.Inventory;
import com.forge.game.Stats;
import com.forge.gameobject.item.Item;

public class Player extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038395807603553671L;
	public static final int sizey = 64;
	public static final int sizex = sizey / 2;

	private Stats stats;
	private Inventory inv;
	
	public int x = (int) ((Main.WIDTH / 2) - (sizex / 2));
	public int y = (int) ((Main.HEIGHT / 2) - (sizey / 2));
	public int level;
	public int xp;
	
	public Player() {
		xp = Integer.parseInt(IOStream.readFile("brad", 1));
		init(getX(), getY(), sizex, sizey, 1, "player");
		stats = new Stats(xp, true);
		inv = new Inventory(32);
	}
	public int getX(){
		return (int) x;
	}
	public int getY(){
		return (int) y;
	}

	public void reset(){
		x=0;
		y=0;
		xp=0;
		stats.reset();
	}
	public int getXp(){
		return xp;
	}


	public void getInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Main.cleanup();
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			move(0, -1);

		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			move(0, 1);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			move(-1, 0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			move(1, 0);
		}

	}

	public boolean addItem(Item item) {
		if (inv.add(item)) {
			System.out.println("Item added to inventory: " + item.getName());
			return true;
		} else
			System.out.println("Inventory full. Unable to pick up "
					+ item.getName());
		return false;

	}

	public void move(float magX, float magY) {
		y += magY * getSpeed();
		xOffset += magX * getSpeed();
		x += magX * getSpeed();
		yOffset += magY * getSpeed();
	}
	public void render(){
		glPushMatrix();
		{	
			spr.render();

		}
		glPopMatrix();
	}

	@Override
	public void update() {
		// System.out.println("Stats: SPEED: " + getSpeed() + " LEVEL: "
		// + getLevel() + " MAX HP: " + getMaxHealth() + " STRENGTH: "
		// + getStrength() + " MAGIC STRENGTH: " + getMagicStrength());
		
		System.out.println("X: "+x+"  Y: "+y);

	}


	public float getSpeed() {
		return stats.getSpeed();
	}

	public int getLevel() {
		return stats.getLevel();
	}

	public int getCurrentHealth() {
		return stats.getCurrentHealth();
	}

	public void addXp(float amt) {
		stats.addXp(amt);
	}

	public int getMaxHealth() {
		return stats.getMaxHealth();
	}

	public int getStrength() {
		return stats.getStrength();
	}

	public int getMagicStrength() {
		return stats.getMagicStrength();
	}


}
