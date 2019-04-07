package GUI;

import Simulation.App.EcosystemSim;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingUtilities;



public class SimulationGUI extends JFrame {
	private static final long serialVersionUID = -2101984566163784562L;
	private JPanel contentPane;

	private static final int HEIGHT = 200;
	private static final int WIDTH = 300;

	public static final int MAX_FIELD_SIZE = 800;
	public static final int MIN_FIELD_SIZE = 200;
	public static final int DEFAULT_FIELD_WIDTH = 800;
	public static final int DEFAULT_FIELD_HEIGHT = 600;

	public static int fieldHeight = 0;
	public static int fieldWidth = 0;

	private FieldRenderer paintable;
	
	private boolean running = false;

	/**
	 * Create the frame.
	 */
	public SimulationGUI(EcosystemSim sim) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Simulation");
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setSize(size);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSetField = new JButton("Set Field");
		btnSetField.setBounds(95, 61, 89, 23);
		btnSetField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!getFieldInfoDialogue()) {
					JOptionPane.showMessageDialog(null, "Max field width and height: " + MAX_FIELD_SIZE +" \n Min field width and height: "+MAX_FIELD_SIZE);
					return; 
				}
				assert (fieldWidth <= 600 && fieldHeight <= 600);
				setSize(fieldWidth + 15, fieldHeight + 50);
				setLocationRelativeTo(null);
				
				initCanvas();
				contentPane.remove(btnSetField);
			}
		});
		contentPane.add(btnSetField);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initCanvas() {
		paintable = new FieldRenderer();
		paintable.setBounds(0, 10, fieldWidth, fieldHeight);
		paintable.setVisible(true);
		paintable.setFocusable(false);
		contentPane.add(paintable);
		running = true;
	}

	private boolean getFieldInfoDialogue() {
		fieldHeight = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter field height.", DEFAULT_FIELD_HEIGHT));
		fieldWidth = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter field width.", DEFAULT_FIELD_WIDTH));

		if (fieldWidth >= MIN_FIELD_SIZE && fieldWidth <= MAX_FIELD_SIZE && fieldHeight >= MIN_FIELD_SIZE && fieldHeight <= MAX_FIELD_SIZE) {
			return true;
		}
		return false;
	}

	public void updateScene(EcosystemSim eco) {
		try {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	if(running){
	    				paintable.updateScene(eco);
	    				paintable.repaint();
	    			}
	            }
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public FieldRenderer getRenderer(){
		return paintable;
	}
}
