package com.forge.engine;

import static org.lwjgl.opengl.GL11.*;


public class HudComponent{

	private Sprite sprite;
	public boolean[] updateFlag;
	private int x;
	private int y;
	private int z;
	private int sx;
	private int sy;
	private int index = -1;

	public HudComponent(int index, int...xyz) {
		this.x = xyz[0];
		this.y = xyz[1];
		//this.z = xyz[2]; //z represents the overlay of different elements of the HUD.
		this.index = index;
		init(256, 32);
	}

	public void init(int sx, int sy) {
		this.sx = sx;
		this.sy = sy;
		sprite = new Sprite(sx, sy, "sprites/HUD" + index, x, y);
	}

	public void update() {
		
	}

	public void render() {
		glPushMatrix();
		{
			glTranslatef(x, y, z);
			sprite.render();

		}
		glPopMatrix();

	}

}
