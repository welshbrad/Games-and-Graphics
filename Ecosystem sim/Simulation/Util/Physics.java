package Simulation.Util;

import Simulation.Organism;

public class Physics {

	/**
	 * Returns boolean to determine if two Organisms are colliding 
	 * @param o1 Any Organism with set position and dimensions
	 * @param o2 Any Organism with set position and dimensions
	 * @return True if o1 collides with o2, false otherwise
	 */
	public static boolean rectCollision(Organism o1, Organism o2) {

		if (	o1.getPosX() < o2.getPosX() + o2.getWidth() 
				&& o1.getPosX() + o1.getWidth() > o2.getPosX() 
				&& o1.getPosY() < o2.getPosY()+ o2.getHeight() 
				&& o1.getHeight() + o1.getPosY() > o2.getPosY()) 
		{
			return true;
			//overlapping rectangles
		}
		return false;
	}
	
	
	/**
	 * Distance from this Organism to the given Organism
	 * @param o1 Any Organism with set position
	 * @param o2 Any Organism with set position
	 * @return Double to show linear distance from o1 to o2
	 */
	public static double getDistance(Organism o1, Organism o2){
		return Math.sqrt(
						Math.pow(Math.abs(o1.getPosX() - o2.getPosX()), 2) 
						+ 
						Math.pow(Math.abs(o1.getPosY() - o2.getPosY()), 2))
						;
	}
}
