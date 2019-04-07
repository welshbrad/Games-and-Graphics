package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import util.IO;

/**
 * Texture tab of GraphicsMaker panel
 * 
 * @author Brad Welsh
 */
public class TextureViewer extends JPanel {

	private static final long serialVersionUID = -8526549788880756905L;
	private GraphicsMaker gm;
	private BufferedImage img;
	private String path;

	public TextureViewer(JTabbedPane pane, GraphicsMaker gm) {
		this.gm = gm;
	}

	public void update() {
		try {
			path = gm.getEntityObject().getTexturePath();
			img = IO.getImage(path);
		} catch (NullPointerException e) {
		}
	}

	public void paintComponent(Graphics g) {

		super.setBackground(Color.GRAY);
		setIgnoreRepaint(true);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.black);

		if (img == null) {
			g.setColor(Color.RED);
			g.drawString("No texture data available", 5, 15);

		} else {
			try {
				g.drawImage(img, 0, 0, null);
			} catch (NullPointerException np) {
				g.drawString("Image data corrupted or unreadable.", 5, 15);
				return;
			}
		}

	}

}
