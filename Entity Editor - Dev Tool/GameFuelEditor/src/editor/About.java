package editor;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JPanelManager {

	public About(JFrame frame, JPanel panel) {
		super(frame, panel);
	}

	@Override
	public void setPanel() {
		super.panel = panel;
		super.panel.setLayout(null);
		JPanel contentPane = (JPanel) frame.getContentPane();
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
		populate();
	}

	@Override
	public void populate() {
		String html = "<html><table><tr><h3>Credits:</h3><th>" + "  GameFuelEditor Created by Brad Welsh for the GameFuel engine.</th></tr><tr><td>"
				+ "<h3>Version:</h3></td><td>" + "  Build 1.1 | December 4, 2015</td></tr><tr><td>" + "<h3>Website:</h3></td><td>"
				+ "</td></tr></table></html>";
		JLabel about = new JLabel(html);
		about.setBounds(15, 15, 500, 200);
		panel.add(about);
		try {
			addHyperlink();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private void addHyperlink() throws URISyntaxException {
		final URI uri = new URI("http://brad-welsh.com");
		class OpenUrlAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				open(uri);
			}
		}
		JButton hyperlink = new JButton();
		hyperlink.setBounds(90, 150, 110, 25);
		hyperlink.setText("<HTML><FONT color=\"#000099\"><U>Brad-Welsh.com</U></FONT></HTML>");
		hyperlink.setBorderPainted(false);
		hyperlink.setOpaque(true);
		hyperlink.setBackground(Color.WHITE);
		hyperlink.setToolTipText(uri.toString());
		hyperlink.addActionListener(new OpenUrlAction());
		panel.add(hyperlink);
	}

	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
			}
		} else {
		}
	}

	@Override
	public void init() {
		setPanel();

	}

}
