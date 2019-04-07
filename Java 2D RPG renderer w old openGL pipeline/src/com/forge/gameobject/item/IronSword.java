package com.forge.gameobject.item;

import com.forge.gameobject.Player;

public class IronSword extends Item {
	/**
	 * 
	 */
	private static final long serialVersionUID = -88808598167695425L;
	public static final int SIZE = 32;

	public IronSword(int x, int y, Player play) {
		super(play);
		init(x, y, SIZE, SIZE, "Iron Sword", "Iron_Sword");
		
	}

}
