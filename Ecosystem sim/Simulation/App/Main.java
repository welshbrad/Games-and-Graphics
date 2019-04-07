package Simulation.App;

import GUI.SimulationGUI;


public class Main {

	private static EcosystemSim ecosystemSim;
	//private static Land land;
	
	private static final int STARTING_DEER = 20;
	private static final int STARTING_GRASS = 250;
	private static final int STARTING_TREES = 5;
	
	private static SimulationGUI gui;

	
	
	public static void main(String args[]){
		ecosystemSim = new EcosystemSim(STARTING_DEER, STARTING_GRASS, STARTING_TREES);
		gui = new SimulationGUI(ecosystemSim);
		while(!ecosystemSim.isReady()){
			if(gui.isRunning()){
				ecosystemSim.setWidth(gui.getRenderer().getWidth());
				ecosystemSim.setHeight(gui.getRenderer().getHeight());
				ecosystemSim.setReady(true);
			}
		}
		ecosystemSim.initializePopulation();
		loop();
	}
	
	private static void loop(){
		while(true){			
			ecosystemSim.update();			
			gui.updateScene(ecosystemSim);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}


