package com.forge.gameobject.item;

import com.forge.gameobject.Player;

public class WoodenSword extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3554770171181146337L;
	public static final int SIZE = 32;

	public WoodenSword(int x, int y, Player play) {
		super(play);
		init(x, y, SIZE, SIZE, "Wooden Sword", "Wooden_Sword");
		
	}

	
}
