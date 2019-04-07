package GUI;

import Simulation.Organism;

public class Renderable {

	private int width, height;
	
	public Renderable(Organism o){
		width = o.getWidth();
		height = o.getHeight();
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	
}
