package com.forge.engine;
import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Sprite {

	private String textureName;
	private int sx;
	private int sy;
	org.newdawn.slick.opengl.Texture texture;
	private int x = 0;
	private int y = 0;
	private int z = 0;
	

	
	public Sprite(int sx, int sy, String textureName, int...xyz){
		this.x = xyz[0];
		this.y = xyz[1];
		//this.z = xyz[2];
		this.sx = sx;
		this.sy = sy;
		this.textureName = textureName;
		
		init();
		
	}
		
	private void init(){
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/"+textureName+".png"));
		} catch (IOException e) {
			System.out.println("Could not load texture: "+textureName);
			e.printStackTrace();
		}
	}
	public void render() {
		Color.white.bind();
		texture.bind();
		glTranslatef(x, y, z);
		glBegin(GL_QUADS);
		
		{
			glTexCoord2f(0,0);
			glVertex2f(0,0);
			
			glTexCoord2f(1,0);
			glVertex2f(sx, 0);
			
			glTexCoord2f(1,1);
			glVertex2f(sx,sy);
			
			glTexCoord2f(0,1);
			glVertex2f(0, sy);

		}
		glEnd();
	}

	public int getSX(){
		return sx;
	}
	public int getSY(){
		return sy;
	}
	public void setSX(int sx){
		this.sx = sx;
	}
	public void setSY(int sy){
		this.sy = sy;
	}



}
