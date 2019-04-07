package com.forge.engine;

import java.awt.Rectangle;

public class Physics {

	public static boolean checkCollision(GameObject go1, GameObject go2) {

		Rectangle r1 = new Rectangle((int) go1.getX(), (int) go1.getY(),
				(int) go1.getSX(), (int) go1.getSY());
		Rectangle r2 = new Rectangle((int) go2.getX(), (int) go2.getY(),
				(int) go2.getSX(), (int) go2.getSY());
		return r1.intersects(r2);
	}

	public static void simpleTrack(GameObject go1, GameObject target) {
		if (go1.getX() > target.getX()) {
			go1.x--;
		} else if (go1.getX() < target.getX()) {
			go1.x++;
		} else if (go1.getY() > target.getY()) {
			go1.y--;
		} else if (go1.getX() < target.getX()) {
			go1.x++;
		}
	}

}
