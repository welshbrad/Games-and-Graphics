package Simulation;

import java.awt.Color;

public class Tree extends Plant{

	public Tree() {
		super();
	}
	
	public Tree(int posX, int posY){
		super(posX, posY);	
	}
	
	@Override
	public char getIcon(){
		return 'T';
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public Color getColor(){
		return Color.decode("0x4d7326");
	}
	
	
}
