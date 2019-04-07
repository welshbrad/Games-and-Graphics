package Simulation.App;
import java.util.ArrayList;
import java.util.Random;

import Simulation.Deer;
import Simulation.Grass;
import Simulation.Organism;
import Simulation.Tree;

public class EcosystemSim {

	private int numGrass, numDeer, numTrees;
	private int width, height;
	

	private boolean isReady = false;
	
	ArrayList<Organism> organisms;
	ArrayList<Organism> deleteQueue;
	
	public EcosystemSim(int numDeer, int numGrass, int numTrees){
		this.numDeer = numDeer;
		this.numGrass = numGrass;
		this.numTrees = numTrees;
		
		organisms = new ArrayList<Organism>();
		deleteQueue = new ArrayList<Organism>();
	}

	private static Random rand = new Random();
	
	private int getRandWidth(){
		int randomNum = rand.nextInt(width);
		return randomNum;
	}
	
	private int getRandHeight(){
		int randomNum = rand.nextInt(height);
		return randomNum;
	}
	
	public void initializePopulation(){

		for(int i = 0; i < numGrass; ++i){
			addOrganism(new Grass(getRandWidth(), getRandHeight()));
		}
		
		for(int i = 0; i < numDeer; ++i){
			addOrganism(new Deer(getRandWidth(), getRandHeight()));
		}
		
		for(int i = 0; i < numTrees; ++i){
			addOrganism(new Tree(getRandWidth(), getRandHeight()));
		}
	}

	
	public void update(){
		for(Organism o : organisms){
			o.setOrganismReference(organisms);
			o.update();
			for(Integer i : o.getDeletedIDs()){
				if(!deleteQueue.contains(getOrganismByID(i)))
				deleteQueue.add(getOrganismByID(i));
			}
		}
		
		for(Organism o: deleteQueue){
			deleteOrganism(o);
		}
	}
	
	private Organism getOrganismByID(int id){
		for(Organism o : organisms){
			  if(o.getID() == id){
			     return o;
			  }
		}
		return null;
	}
	
	public void deleteOrganism(Organism o){
		if(organisms.contains(o)){
			organisms.remove(o);
		}

		if(o instanceof Grass){
			numGrass--;
		}
		if(o instanceof Deer){
			numDeer--;
		}
		if(o instanceof Tree){
			numTrees--;
		}
	}
	
	//Methods to populate lists
	
	public void addOrganism(Organism organism){
		organism.setLandWidth(width);
		organisms.add(organism);
	}
	
	//Getters and Setters
	
	public int getNumGrass() {
		return numGrass;
	}

	public int getNumDeer() {
		return numDeer;
	}

	public int getNumTrees() {
		return numTrees;
	}

	public ArrayList<Organism> getOrganisms() {
		return organisms;
	}
	
	public void setReady(boolean isReady){
		this.isReady = isReady;
	}
	
	public boolean isReady(){
		return isReady;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
}
