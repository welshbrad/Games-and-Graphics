package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Simulation.Organism;
import Simulation.App.EcosystemSim;

public class FieldRenderer extends JPanel {

	private static final long serialVersionUID = -2100527681190264103L;
	private EcosystemSim eco;
	
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
		
		setBackground(Color.decode("0xAC7339"));
		
		if(eco != null)
		for(Organism o : eco.getOrganisms()){
			Renderable renderable = new Renderable(o);
			
			g.setColor(o.getColor());
			g.drawRect(o.getPosX(), o.getPosY(), renderable.getWidth(), renderable.getHeight());
			g.fillRect(o.getPosX(), o.getPosY(), renderable.getWidth(), renderable.getHeight());
		}
	}
	
	
	public void updateScene(EcosystemSim eco){
		this.eco = eco;
		
	}
}
