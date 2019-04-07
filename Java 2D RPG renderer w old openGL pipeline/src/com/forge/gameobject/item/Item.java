package com.forge.gameobject.item;

import com.forge.engine.GameObject;
import com.forge.engine.Physics;
import com.forge.engine.Sprite;
import com.forge.gameobject.Player;

public class Item extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5222185444641196501L;

	protected Player player;

	protected String name;
	protected int id;
	protected String textureName;
	
	public Item(Player play){
		this.player = play;
	}
	
	public boolean pickUp(){
		
		if(player.addItem(this)){
			return true;
		}
		return false;
	}
	
	public String getName(){
		return name;
	}

	protected void init(int x, int y, int sx, int sy, String name, String textureName){
		this.x = x;
		this.y = y;
		this.objType = 1;
		this.spr = new Sprite(sx, sy, textureName, x, y);
		this.name = name;
	}
	public void update(){
		if(Physics.checkCollision(this, player)){
			if(pickUp()){
			flags[0] = true;}
			else {
				flags[0] = false;
			}
		}
	}
}
