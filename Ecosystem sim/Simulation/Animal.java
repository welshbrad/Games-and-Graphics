package Simulation;

import java.util.ArrayList;

import Simulation.Util.Physics;

public class Animal extends Organism {
	
	private static final int WALKSTEP = 1;

	public Animal() {
		super();
	}

	public Animal(int posX, int posY) {
		super(posX, posY);
	}
	
	/**
	 * Will tell you if the @animal given is the closest Animal to the destination coordinates
	 * @param animal
	 * @param animalTempList
	 * @param destinationX
	 * @param destinationY
	 * @return true if the @animal given is the closest to the destination point, false otherwise
	 */
	protected boolean ClosestToDestination(Animal animal, ArrayList<Animal> animalTempList, int destinationX, int destinationY){		
		Organism o = new Organism(destinationX, destinationY);
		for(Animal a : animalTempList){
			if(Physics.getDistance(a, o) < Physics.getDistance(animal, o)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Walk one step in a single x, y direction
	 * @param destinationX
	 * @param destinationY
	 * @return boolean to indicate if the destination has been reached or not
	 */
	protected boolean walkOneStep(int destinationX, int destinationY) {
		//FIX THIS -- If the deer is not the closest, then set the destination elsewhere, to avoid wasting time.
		
		ArrayList<Animal> animalTempList = new ArrayList<Animal>();
		boolean stopMoving = false;
		
		
		//Find all animals in the given Organism ArrayList that are in the path of 'this' and add them to a list that will be used
		//to test who will be able to walk first ( The one in front).
		for(Organism o : organismArrayList){
			if(o instanceof Animal && id != o.getID() && Physics.rectCollision(this, o)){
				animalTempList.add((Animal) o);
				stopMoving = true;
			}
		}
		//If flagged to stop moving, and not the closest Animal to the destination, return false
		 if(stopMoving && !ClosestToDestination(this, animalTempList, destinationX, destinationY)){
			return false;
		 }
		
		 
		 
		if (destinationX > posX)
			posX+= WALKSTEP;
		else if (destinationX < posX)
			posX-= WALKSTEP;
		if (destinationY > posY)
			posY+= WALKSTEP;
		else if (destinationY < posY)
			posY-= WALKSTEP;

		if (destinationX == posX && destinationY == posY) 
			return true;
		
		
		return false;
		
	}
}
