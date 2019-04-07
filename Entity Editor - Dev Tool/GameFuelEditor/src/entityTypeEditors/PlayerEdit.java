package entityTypeEditors;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class PlayerEdit implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6088150003598940709L;

	public PlayerEdit() {

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel getPanel() {

		JPanel panel = new JPanel();

		panel.setLayout(null);

		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player", TitledBorder.CENTER, TitledBorder.TOP, null,
				new Color(64, 64, 64)));

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "Standard", "Moderator", "Administrator" }));
		comboBox_1.setBounds(100, 11, 134, 20);
		panel.add(comboBox_1);

		JLabel lblPlayerRights = new JLabel("Player Rights");
		lblPlayerRights.setBounds(10, 14, 94, 14);
		panel.add(lblPlayerRights);

		JButton btnConfigureStats = new JButton("Configure Player Properties");
		btnConfigureStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfigureStats.setBounds(10, 106, 177, 23);
		panel.add(btnConfigureStats);
		return panel;
	}

}
