package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddMissionDialog extends JDialog {
	
	private Fenetre fenetre;
	private JTextField missionId;
	private JLabel missionIdLabel;
	
	public AddMissionDialog(Fenetre parent, String title, boolean modal){
		super(parent,title,modal);
		this.fenetre = parent;
		this.setSize(440, 135);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}
	
	private void initComponent(){
	
		JPanel panMissionId = new JPanel();
		panMissionId.setBackground(Color.white);
		panMissionId.setPreferredSize(new Dimension(430, 60));
		this.missionId = new JTextField();
		this.missionId.setPreferredSize(new Dimension(200, 25));
		panMissionId.setBorder(BorderFactory.createTitledBorder("Mission's Id"));
		this.missionIdLabel = new JLabel("Enter the Mission's Id :");
		panMissionId.add(this.missionIdLabel);
		panMissionId.add(this.missionId);
		
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panMissionId);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
	
		okBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean lettreMission = false;
				if (missionId.getText().trim().length() != 0) {
					int i = 0;
					while(i < missionId.getText().trim().length() && !lettreMission) {
						if(!Character.isDigit(missionId.getText().trim().charAt(i))) {
							lettreMission = true;
						}
						i++;
					}
					if(!lettreMission) {
						List<String> missions = fenetre.getMissions();
						boolean alreadyUse = false;
						i = 0;
						while(i < missions.size() && !alreadyUse) {
							if(missionId.getText().trim().equals(missions.get(i))) {
								alreadyUse = true;
							}
							i++;
						}
						if(!alreadyUse) {
							ajoutMission();
							setVisible(false);
						}
					}
				}
			}
		}); 
	   
		JButton cancelBouton = new JButton("Cancel");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}      
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		    
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
		}
	
	public void ajoutMission() {
		this.fenetre.ajoutMission(this.missionId.getText().trim());
	}

}
