package editor;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class JPanelManager {

	protected JFrame frame;
	protected JPanel panel;
	static Object currentView;

	public JPanelManager(JFrame frame, JPanel panel) {
		setCurrentView(null);
		this.frame = frame;
		this.panel = panel;
		init();
	}

	public abstract void init();

	public abstract void setPanel();

	public abstract void populate();

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setCurrentView(Object o) {
		currentView = o;
	}

	public Object getCurrentView() {
		return currentView;
	}
}
