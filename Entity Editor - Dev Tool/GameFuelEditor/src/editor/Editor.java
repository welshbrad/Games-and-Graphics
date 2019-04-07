package editor;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import util.Globals;

public class Editor {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Editor() {
		initialize();
	}

	private void initialize() {
		Globals.init();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

		frame = new JFrame();

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("gameFuelIcon.gif"));
		frame.setTitle(Globals.applicationTitle);
		frame.setBounds(100, 100, Globals.WIDTH, Globals.HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setAutoRequestFocus(true);

		addMenuBar();
		addButtonSelection();

	}

	private void addButtonSelection() {
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblSelectATool = new JLabel("<html><h2>Select a tool to begin</h2></html>");
		lblSelectATool.setBounds(404, 79, 189, 57);
		panel.add(lblSelectATool);

		JButton btnEntityEditor = new JButton("Entity Editor");
		btnEntityEditor.setActionCommand("EntityEditor");
		btnEntityEditor.setBounds(444, 207, 113, 28);
		btnEntityEditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EntityEditor(frame, new JPanel());
			}
		});
		btnEntityEditor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnEntityEditor);

		JButton btnMapEditor = new JButton("Map Editor");
		btnMapEditor.setBounds(444, 156, 113, 28);
		btnMapEditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MapEditor(frame, new JPanel());
			}
		});
		btnMapEditor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnMapEditor);

	}

	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnEdit = new JMenu("Edit");
		mnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menuBar.add(mnEdit);

		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);

		JMenuItem mntmRedo = new JMenuItem("Redo");
		mnEdit.add(mntmRedo);

		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);

		JMenuItem mntmEntityeditor = new JMenuItem("EntityEditor");
		mntmEntityeditor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EntityEditor(frame, new JPanel());
			}
		});
		mnTools.add(mntmEntityeditor);

		JSeparator separator_1 = new JSeparator();
		mnTools.add(separator_1);

		JMenuItem mntmMapeditor = new JMenuItem("MapEditor");
		mnTools.add(mntmMapeditor);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		JMenuItem mntmCredits = new JMenuItem("Credits");
		mntmCredits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new About(frame, new JPanel());
			}
		});
		mnAbout.add(mntmCredits);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

	}

	public JFrame getFrmGamefuelEditor() {
		return frame;
	}

	public void setFrmGamefuelEditor(JFrame frmGamefuelEditor) {
		this.frame = frmGamefuelEditor;
	}

}
