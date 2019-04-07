package com.forge.engine;

import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject{

	protected int x;
	protected int y; // position

	public static int xOffset;
	public static int yOffset;
	protected int objType;
	protected Sprite spr;

	
	protected boolean[] flags = new boolean[1];

	public void update() {

	}

	public void render() {
		glPushMatrix();
		{	
			glTranslatef(x - xOffset, y - yOffset, 0);
			spr.render();

		}
		glPopMatrix();

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSX() {
		return spr.getSX();
	}

	public int getSY() {
		return spr.getSY();
	}

	public int getType() {
		return objType;
	}

	public boolean getRemove() {
		return flags[0];
	}

	public void remove(){
		flags[0] = true;
	}

	protected void init(int x, int y, int sx,
			int sy, int objType, String textureName) {
		this.x = x;
		this.y = y;
		this.objType = objType;
		this.spr = new Sprite(sx, sy, textureName, x, y);
	}

}
