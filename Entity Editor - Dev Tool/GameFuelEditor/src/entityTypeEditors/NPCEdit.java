package entityTypeEditors;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;


public class NPCEdit {

    public NPCEdit() 
    {
    }

	public JPanel getPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "NPC",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(64, 64,
						64)));


		JButton btnConfigureStats = new JButton("Configure NPC Properties");
		btnConfigureStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfigureStats.setBounds(10, 106, 177, 23);
		panel.add(btnConfigureStats);
		return panel;
	}

}
