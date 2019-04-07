package editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import EntityObjects.EntityObject;
import util.Globals;
import util.IO;
import util.SerializedReader;
import util.SerializedWriter;
import entityTypeEditors.*;

/**
 * Provides tools for creating and editing entities within a game context.
 *
 * @author Brad Welsh
 */
public class EntityEditor extends JPanelManager implements Serializable {

	private static final long serialVersionUID = 3499821697979297947L;

	protected EntityObject entity;

	protected JTextField modelPathField;
	protected JTextField textureField;
	protected JTextField textField_1;
	protected JTextField textField_3;
	protected JTextField textField_4;

	protected GraphicsMaker g;

	protected PlayerEdit playerEdit;
	protected NPCEdit npcEdit;
	protected EnemyEdit enemyEdit;
	protected ItemEdit itemEdit;
	protected WorldObjectEdit worldObjectEdit;
	protected LightSourceEdit lightSourceEdit;
	protected ParticleSourceEdit particleSourceEdit;

	protected JTextField idField;
	protected JTextField nameField;
	protected JTextField commandTextField;
	protected int entityType;
	protected JPanel activeEntityPanel;

	protected String textFieldName;
	protected String textFieldId;

	protected String texturePath;
	protected String modelPath;

	protected String entityId;
	protected String entityName;
	protected String currentFile;
	protected boolean fileIsLoaded = false;

	public EntityEditor(JFrame frame, JPanel panel) {
		super(frame, panel);
		setCurrentView(this);
	}

	public void init() {
		frame.setTitle("GameFuel Editor  :  Entity Editor");
		playerEdit = new PlayerEdit();
		npcEdit = new NPCEdit();
		worldObjectEdit = new WorldObjectEdit();
		enemyEdit = new EnemyEdit();
		itemEdit = new ItemEdit();
		lightSourceEdit = new LightSourceEdit();
		particleSourceEdit = new ParticleSourceEdit();

		g = new GraphicsMaker(panel, entity);

		update();
		setPanel();

	}

	/**
	 * When an entity file is loaded, all protected fields are refreshed with
	 * the entity-specific values.
	 */
	private void update() {
		if (fileIsLoaded) {
			entityName = entity.getName();
			entityId = entity.getId();
			entityType = entity.getType();
			texturePath = entity.getTexturePath();
			frame.setTitle("GameFuel Editor  :  Entity Editor : " + getCurrentFile());
			g = new GraphicsMaker(panel, entity);
			g.setInfoContent(EntityObject.toString(entity));

		}

	}

	/**
	 * Clears the content pane and prepares it for an updated draw.
	 */
	@Override
	public void setPanel() {

		frame.setResizable(false);
		super.panel = panel;
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
		populate();
		g.update();
	}

	/**
	 * Creates a new save file for an entity and stores it under the
	 * "saves/entities/" directory.
	 */
	private void newEntity() {
		String input;
		setEntityId(idField.getText().trim());
		setEntityName(nameField.getText().trim());
		String id = getEntityId().trim();
		setTexturePath(textureField.getText());
		if (id.length() > 5) {
			JOptionPane.showMessageDialog(panel, "The id: '" + id + "' Must be a maximum of 5 digits");
			return;
		} else if (id.matches("^a-z\\sA-Z]")) {
			JOptionPane.showMessageDialog(panel, "The id: '" + id + "' Must only contain numbers");
			return;
		}

		input = JOptionPane.showInputDialog(frame, "Save as: ", "ex: Entity");
		if (input.length() == 0 || input == null) {
			JOptionPane.showMessageDialog(panel, "The file name must contain at least one alpha-numeric character", "No file name entered", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (IO.fileExists(Globals.ENTITY_DIRECTORY, input + ".ser")) {
			int overwrite = -1;
			overwrite = JOptionPane.showConfirmDialog(panel, "The file: '" + input + ".ser' already exists. Would you like to overwrite it?", "Overwrite File?", JOptionPane.YES_NO_CANCEL_OPTION);
			if (overwrite != 0 || input == "") {
				return;
			}
		} else {

			entity = new EntityObject(getEntityType(), getEntityName(), getEntityId(), getTexturePath());
			IO.createFile(Globals.ENTITY_DIRECTORY + input + ".ser");
			fileIsLoaded = true;
			setCurrentFile(input);
			SerializedWriter.writeObject(entity, "entities", getCurrentFile());
			update();
			setPanel();
		}
	}

	/** Calls static SerializedWriter methods to save an entity to its file. */
	private void saveEntity() {
		setEntityId(idField.getText().trim());
		setEntityName(nameField.getText().trim());
		setTexturePath(textureField.getText());
		entity = new EntityObject(getEntityType(), getEntityName(), getEntityId(), getTexturePath());
		String fileName = getCurrentFile().replace(".ser", "");
		SerializedWriter.writeObject(entity, "entities", fileName);
		update();
		setPanel();

	}

	/**
	 * Calls static IO methods to load a texture to an entity for display on the
	 * GraphicsMaker canvas.
	 */
	public void loadTexture() {
		List<String> results = new ArrayList<String>();

		File[] files = new File("saves/textures/").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.toString());
			}
		}
		Object o = JOptionPane.showInputDialog(panel, "Select an Entity", "Load Entity", JOptionPane.PLAIN_MESSAGE, null, files, results);
		if (o == null)
			return;
		String selection = o.toString();
		String input = selection.replace("saves\\entities\\", "");
		if (input != "") {
			setTexturePath(input);
			addVisualSettings();
		}
	}

	/** Calls static SerializedReader methods to load an entity from a file. */
	public void loadEntity() {
		List<String> results = new ArrayList<String>();

		File[] files = new File("saves/entities/").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.toString());

			}
		}
		Object o = JOptionPane.showInputDialog(panel, "Select an Entity", "Load Entity ('saves/entities/')", JOptionPane.PLAIN_MESSAGE, null, files, results);
		if (o == null)
			return;
		String selection = o.toString();
		String input = selection.replace("saves\\entities\\", "");
		// TODO: Make initial file show up as last file used.

		// String input = JOptionPane.showInputDialog(frame,
		// "Enter entity name:");
		if (IO.fileExists(Globals.ENTITY_DIRECTORY, input)) {
			setCurrentFile(input);
			try {
				entity = (EntityObject) SerializedReader.readObjectFromFile("entities", input);
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Entity could not be loaded.");
				e.printStackTrace();
			}
			fileIsLoaded = true;
			update();
			setPanel();
		} else {
			JOptionPane.showMessageDialog(panel, "No File: '" + input + "' in GameFuelEditor/saves/entities/", "No File Found", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** Settings specific to the entity of type "PLAYER". */
	public JPanel playerEdit() {
		JPanel panel_1 = playerEdit.getPanel();

		panel_1.setBorder(new TitledBorder(null, "Player Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(245, 24, 722, 141);
		panel.add(panel_1);
		return panel_1;
	}

	/** Settings specific to the entity of type "NPC". */
	public JPanel npcEdit() {
		JPanel panel_1 = playerEdit.getPanel();

		panel_1.setBorder(new TitledBorder(null, "NPC Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(245, 24, 722, 141);
		panel.add(panel_1);
		return panel_1;
	}

	/** Settings specific to the entity of type "ITEM". */
	public JPanel itemEdit() {
		JPanel panel_1 = playerEdit.getPanel();

		panel_1.setBorder(new TitledBorder(null, "Item Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(245, 24, 722, 141);
		panel.add(panel_1);
		return panel_1;
	}

	/** Settings specific to the entity of type "WORLD_OBJECT". */
	public JPanel worldObjectEdit() {
		JPanel panel_1 = playerEdit.getPanel();

		panel_1.setBorder(new TitledBorder(null, "World Object Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(245, 24, 722, 141);
		panel.add(panel_1);
		return panel_1;
	}

	/** Settings specific to the entity of type "LIGHT_SOURCE". */
	public JPanel lightSourceEdit() {
		JPanel panel_1 = playerEdit.getPanel();

		panel_1.setBorder(new TitledBorder(null, "Light Source Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(245, 24, 722, 141);
		panel.add(panel_1);
		return panel_1;
	}

	/** Settings specific to the entity of type "PARTICLE_SOURCE". */
	public JPanel particleSourceEdit() {
		JPanel panel_1 = playerEdit.getPanel();

		panel_1.setBorder(new TitledBorder(null, "Particle Source Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(245, 24, 722, 141);
		panel.add(panel_1);
		return panel_1;
	}

	/** Settings specific to the entity of type "ENEMY". */
	public JPanel enemyEdit() {
		JPanel panel_1 = playerEdit.getPanel();

		panel_1.setBorder(new TitledBorder(null, "Enemy Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(245, 24, 722, 141);
		panel.add(panel_1);
		return panel_1;
	}

	/** Pieces together each of the components in the EntityEditor. */
	@Override
	public void populate() {

		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		/**
		 * @wbp.parser.entryPoint
		 */
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		JLabel lblCommandLine = new JLabel("Command Line");
		lblCommandLine.setBounds(10, 716, 99, 14);
		panel.add(lblCommandLine);

		commandTextField = new JTextField();
		commandTextField.setBounds(109, 713, 720, 20);
		panel.add(commandTextField);
		commandTextField.setColumns(10);

		addMainMenu();

		switch (entityType) {
		case 0:
			JPanel panel_1 = new JPanel();
			JLabel selectTypeLabel = new JLabel("<html><div style='color:#999999'>Please select a type to adjust settings.</div></html>");
			panel_1.add(selectTypeLabel);
			panel_1.setBorder(new TitledBorder(null, "Entity", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			panel_1.setBounds(245, 24, 722, 141);
			panel.add(panel_1);
			break;
		case 1:
			panel.add(playerEdit());
			break;
		case 2:
			panel.add(npcEdit());
			break;
		case 3:
			panel.add(enemyEdit());
			break;
		case 4:
			panel.add(worldObjectEdit());
			break;
		case 5:
			panel.add(itemEdit());
			break;
		case 6:
			panel.add(lightSourceEdit());
			break;
		case 7:
			panel.add(particleSourceEdit());
			break;

		}

		addCanvas();
		addVisualSettings();

		if (!fileIsLoaded) {
			for (Component c : panel.getComponents()) {
				c.setEnabled(false);
			}
		}
	}

	/**
	 * Displays the basic information and load/save/new buttons in the
	 * EntityEditor.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addMainMenu() {

		JPanel mainPanel = new JPanel();

		mainPanel.setBounds(10, 24, 220, 150);
		mainPanel.setBorder(new TitledBorder(null, "Main Settings", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		mainPanel.setVisible(true);
		mainPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 117, 89, 14);
		mainPanel.add(lblNewLabel);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 92, 46, 14);
		mainPanel.add(lblName);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(10, 67, 46, 14);
		mainPanel.add(lblType);

		JButton btnLoad = new JButton("Load");
		btnLoad.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if (!fileIsLoaded) {
			btnLoad.setBackground(Color.cyan);
		}
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadEntity();
			}
		});
		btnLoad.setBounds(78, 20, 60, 23);
		mainPanel.add(btnLoad);

		JButton btnNewButton = new JButton("New");
		btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if (!fileIsLoaded) {
			btnNewButton.setBackground(Color.cyan);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newEntity();
			}
		});
		btnNewButton.setBounds(13, 20, 60, 23);
		mainPanel.add(btnNewButton);

		idField = new JTextField();
		idField.setBounds(109, 114, 89, 20);
		idField.setColumns(10);
		idField.setText(entityId);
		if (fileIsLoaded) {
			idField.setText(entity.getId());
		} else {

		}
		mainPanel.add(idField);

		nameField = new JTextField();
		nameField.setBounds(66, 89, 132, 20);
		if (fileIsLoaded) {
			nameField.setText(entity.getName());
		}
		mainPanel.add(nameField);
		nameField.setColumns(10);

		JButton btnSaveButton;
		btnSaveButton = new JButton("Save");
		if (!fileIsLoaded) {
			btnSaveButton.setEnabled(false);
		}
		btnSaveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveEntity();
			}
		});
		btnSaveButton.setBounds(143, 20, 60, 23);
		mainPanel.add(btnSaveButton);

		JComboBox comboBox = new JComboBox();
		comboBox.setAutoscrolls(false);
		comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "<html><div style='color:#80bfff'> (Select a type) </div></html>", "Player", "NPC", "Enemy", "WorldObject", "Item", "LightSource",
				"ParticleSource" }));

		if (getEntityType() != 0) {
			comboBox.setSelectedIndex(getEntityType());
		}
		comboBox.setBounds(66, 64, 132, 20);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String value = (String) comboBox.getSelectedItem();
				switch (value) {
				case "Player":
					setEntityType(1);
					break;
				case "NPC":
					setEntityType(2);
					break;
				case "Enemy":
					setEntityType(3);
					break;
				case "WorldObject":
					setEntityType(4);
					break;
				case "Item":
					setEntityType(5);
					break;
				case "LightSource":
					setEntityType(6);
					break;
				case "ParticleSource":
					setEntityType(7);
					break;
				default:
					setEntityType(0);
				}

			}

		});

		mainPanel.add(comboBox);
		if (!fileIsLoaded) {
			for (Component c : mainPanel.getComponents()) {
				c.setEnabled(false);
			}
			btnNewButton.setEnabled(true);
			btnLoad.setEnabled(true);
		}
		panel.add(mainPanel);
	}

	/**
	 * Constructs and manages the panel that controls the visual settings of an
	 * entity.
	 */
	private void addVisualSettings() {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.removeAll();
		tabbedPane.revalidate();
		tabbedPane.repaint();
		tabbedPane.setBounds(10, 190, 230, 400);
		panel.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Properties", null, panel, null);
		panel.setLayout(null);

		modelPathField = new JTextField();
		modelPathField.setColumns(10);
		modelPathField.setText(modelPath);
		modelPathField.setBounds(106, 37, 109, 20);
		panel.add(modelPathField);

		JCheckBox chckbxSetCanpassthrough = new JCheckBox("Set HasCollision");
		chckbxSetCanpassthrough.setBounds(6, 180, 131, 23);
		panel.add(chckbxSetCanpassthrough);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Set Transparency");
		chckbxNewCheckBox.setBounds(6, 206, 131, 23);
		panel.add(chckbxNewCheckBox);

		JButton btnBrowse = new JButton("Texture File");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTexture();
			}
		});
		btnBrowse.setBounds(6, 15, 97, 14);
		panel.add(btnBrowse);

		textureField = new JTextField();
		textureField.setBounds(106, 12, 109, 20);
		panel.add(textureField);
		textureField.setText(getTexturePath());
		textureField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileIsLoaded) {
					entity.setTexturePath(textureField.getText().trim());
				}
				setTexturePath(textureField.getText().trim());
				setPanel();
			}

		});
		textureField.setColumns(10);

		JButton btnModelFile = new JButton("Model File");
		btnModelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModelFile.setBounds(6, 40, 97, 14);
		panel.add(btnModelFile);

		JButton btnScriptFile = new JButton("Script File");
		btnScriptFile.setBounds(6, 65, 97, 14);
		panel.add(btnScriptFile);

		textField_1 = new JTextField();
		textField_1.setBounds(106, 62, 109, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JSlider slider = new JSlider();
		slider.setBounds(107, 134, 118, 14);
		panel.add(slider);

		JSeparator separator = new JSeparator();
		separator.setBounds(11, 121, 196, 2);
		panel.add(separator);

		JSlider slider_1 = new JSlider();
		slider_1.setBounds(107, 159, 118, 14);
		panel.add(slider_1);

		JLabel lblShinyness = new JLabel("Shinyness");
		lblShinyness.setBounds(6, 134, 62, 14);
		panel.add(lblShinyness);

		JLabel lblReflectivity = new JLabel("Reflectivity");
		lblReflectivity.setBounds(6, 159, 62, 14);
		panel.add(lblReflectivity);

		textField_3 = new JTextField();
		textField_3.setBounds(69, 134, 32, 14);
		panel.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(69, 159, 32, 14);
		panel.add(textField_4);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(184, 90, 35, 20);
		panel.add(spinner);

		JLabel lblNumberOfRows = new JLabel("Number of Rows in Texture Atlas");
		lblNumberOfRows.setBounds(6, 93, 176, 14);
		panel.add(lblNumberOfRows);

		JCheckBox chckbxNaturalLight = new JCheckBox("Natural Light");
		chckbxNaturalLight.setToolTipText("If unselected, object will generate artificial lighting on all faces");
		chckbxNaturalLight.setBounds(6, 232, 97, 23);
		panel.add(chckbxNaturalLight);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Effects", null, panel_1, null);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("New Effect");
		btnNewButton.setBounds(10, 11, 205, 23);
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Interactions", null, panel_2, null);
		panel_2.setLayout(null);

		JButton btnAddActionevent = new JButton("Add Action/Event");
		btnAddActionevent.setBounds(10, 11, 205, 23);
		panel_2.add(btnAddActionevent);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 91, 205, 2);
		panel_2.add(separator_1);

		JButton btnNewButton_1 = new JButton("Remove Action/Event");
		btnNewButton_1.setBounds(10, 59, 205, 23);
		panel_2.add(btnNewButton_1);

		JButton btnEditActionevent = new JButton("Edit Action/Event");
		btnEditActionevent.setBounds(10, 35, 205, 23);
		panel_2.add(btnEditActionevent);

		JLabel lblInteractions = new JLabel("Interactions");
		lblInteractions.setBounds(79, 96, 64, 14);
		panel_2.add(lblInteractions);

		if (!fileIsLoaded) {
			for (Component c : panel.getComponents()) {
				c.setEnabled(false);
			}
		}

	}

	/**
	 * Adds the instance of the GraphicsMaker class to the EntityEditor and
	 * initializes it.
	 */
	private void addCanvas() {
		panel.add(g);
		g.init();
	}

	// *** GET/SET********///////////////////////////////////
	private void setEntityType(int value) {
		entityType = value;
	}

	private int getEntityType() {
		return entityType;
	}

	private String getEntityName() {
		return entityName;
	}

	private void setEntityName(String value) {
		entityName = value;
	}

	public void setCurrentFile(String fileName) {
		currentFile = fileName;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String textFieldName2) {
		this.entityId = textFieldName2;
	}

	public String getTexturePath() {
		return texturePath;
	}

	public void setTexturePath(String texturePath) {
		this.texturePath = texturePath;
	}

}
