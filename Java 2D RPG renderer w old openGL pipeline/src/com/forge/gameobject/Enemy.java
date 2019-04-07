package com.forge.gameobject;

import com.forge.engine.GameObject;
import com.forge.engine.file.Util;
import com.forge.game.Stats;

public class Enemy extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -965798965448450227L;
	private Stats stats;
	private GameObject target;

	public Enemy(float x, float y, int level) {
		stats = new Stats(3, false);
		target = null;
	}

	@Override
	public void update() {
		if (target == null) {
			look();
		} else {
			chase();
		}

		if (Util.LineOfSight(this, target)) {
			attack();
		}

		if (stats.getCurrentHealth() <= 0) {
			die();
		}
	}

	protected void chase() {

	}

	protected void look() {

	}

	protected void attack() {

	}

	protected void die() {

	}
}
