package com.forge.gameobject;

public class Zombie extends Enemy{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6137789676974328553L;
	private static int sizex = 32;
	private static int sizey = sizex * 2;
	
	public Zombie(int x, int y, int level) {
		super(x, y, level);
		this.init(x, y, sizex, sizey, 0, "Zombie");
		
	}
	@Override
	protected void look(){
		
	}
	@Override
	protected void attack(){
		
	}
}
