package Simulation;

import java.awt.Color;

public class Grass extends Plant {

	public Grass() {
		super();
	}
	
	public Grass(int posX, int posY){
		super(posX, posY);	
	}
	
	
	@Override
	public char getIcon(){
		return 'G';
	}
	
	
	@Override
	public Color getColor(){
		return Color.decode("0x39ac39");
	}
	
}
