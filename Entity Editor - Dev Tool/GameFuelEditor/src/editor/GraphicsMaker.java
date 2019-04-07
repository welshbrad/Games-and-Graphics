package editor;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import EntityObjects.EntityObject;

/**
 * Manages the graphics behind the tabbed panel in the EntityEditor.
 * 
 * @author Brad Welsh
 */
public class GraphicsMaker extends Canvas {

	private static final long serialVersionUID = 1796973522659640699L;
	private EntityObject entityObject;
	private JPanel pane;
	private String infoContent;
	private JPanel textureCanvas;
	private TextureViewer textureViewer;

	public GraphicsMaker(JPanel pane, EntityObject entity) {
		this.entityObject = entity;
		this.pane = pane;
	}

	public void init() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(245, 190, 722, 400);
		pane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("Info", null, panel);

		textureViewer = new TextureViewer(tabbedPane, this);
		JPanel canvas = textureViewer;
		tabbedPane.addTab("Texture", null, canvas);
		textureCanvas = canvas;

		Canvas modelCanvas = new Canvas();
		tabbedPane.addTab("Model", null, modelCanvas);

		Canvas iconTextureCanvas = new Canvas();
		tabbedPane.addTab("Icon Texture", null, iconTextureCanvas);

		Canvas sceneCanvas = new Canvas();
		tabbedPane.addTab("View in Scene", null, sceneCanvas);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 10, tabbedPane.getWidth() - 25, tabbedPane.getHeight() - 50);
		textPane.setText(infoContent);
		textPane.setEditable(false);
		panel.add(textPane);
	}

	public void update() {
		textureViewer.update();
		// TODO:Implement update methods for each GraphicsMaker unit and
		// call them here.
		
		// modelViewer.update();
		// iconTextureViewer.update();
		// sceneViewer.update();
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}

	public JPanel getTextureCanvas() {
		return textureCanvas;
	}

	public EntityObject getEntityObject() {
		return entityObject;
	}

}