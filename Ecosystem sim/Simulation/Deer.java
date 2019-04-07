package Simulation;

import java.awt.Color;

import Simulation.Util.Physics;

public class Deer extends Animal {

	private static final int viewDistance = 500;
	
	public Deer() {
		super();
	}

	public Deer(int posX, int posY) {
		super(posX, posY);
		setWidth(10);
		setHeight(10);
	}

	@Override
	public char getIcon() {
		return 'D';
	}

	@Override
	public Color getColor(){
		return Color.decode("0x734d26");
	}
	
	@Override
	public void update() {
		graze();
	}

	private void graze() {
		Organism closestGrass = null;
		double closestDistance = Integer.MAX_VALUE;

		for (Organism o : organismArrayList) {
			if (o instanceof Grass) {
				double distance = Physics.getDistance(o, this);
				if ((closestGrass == null) || (distance < closestDistance)) {
					closestGrass = o;
					closestDistance = distance;
				}
			}
		}
		if(closestDistance < viewDistance){
			if(walkOneStep(closestGrass.posX, closestGrass.posY)){
				eatGrass((closestGrass));
			}
		}
	}

	private void eatGrass(Organism o) {
		killByID(o.getID());
	}

}
