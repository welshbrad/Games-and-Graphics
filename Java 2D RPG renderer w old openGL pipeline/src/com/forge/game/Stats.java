package com.forge.game;

import com.forge.engine.Main;

public class Stats {
	
	private int xp = 0;
	private int health = 10;
	private boolean levelable = true;
	private int level = 0;
	
	public Stats(int xp, boolean levelable){
		this.levelable = levelable;
		if(levelable){
			this.xp = xp;
			this.level = 1;
		} else {
			this.xp = -1;
			this.level = (int) xp;
		}
		if(xp > 0 && xp < Integer.MAX_VALUE){
		this.xp = xp;
		} else {
			Main.running = false;
		}
		health = getMaxHealth();
	}
	
	public float getSpeed() {
		return 4f;
	}
	public int getLevel() {
		if(!levelable)
			return level;
		return (int) ((Math.sqrt(xp - 11) - 1) / 2) + 1;
	}

	public void reset()
	{
		xp = 0;
		level = getLevel();
		health = 10;
	}
	public int getCurrentHealth() {
		int max = getMaxHealth();
		if (health > max) {
			health = max;
		}
		return health;
	}

	public void addXp(float amt) {
		xp += amt;
	}

	public int getMaxHealth() {
		return getLevel() * 10;
	}

	public int getStrength() {
		return (getLevel() * 4);
	}

	public int getMagicStrength() {
		return getLevel() * 4;
	}

	
}
