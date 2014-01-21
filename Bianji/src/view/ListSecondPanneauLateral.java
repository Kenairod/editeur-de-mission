package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;

public class ListSecondPanneauLateral extends JList<String> { 
	
	private Fenetre fenetre;
	private Vector<String> listeMissions;
	
	public ListSecondPanneauLateral(List<String> listeMi, Fenetre fen) {
		this.setListe(listeMi);
		this.fenetre = fen;
		for (int i = 0; i < listeMi.size(); i++) {
			this.listeMissions.add("Mission " + listeMi.get(i));
		}
		
		this.fenetre.getSupprButtonMissions().addActionListener(new DeleteListener());
	}
	
	public void setListe(List<String> listeMi) {
		this.listeMissions = new Vector<String>();
		for (int i = 0; i < listeMi.size(); i++) {
			this.listeMissions.add("Mission " + listeMi.get(i));
		}
		this.setListData(this.listeMissions);
		
		int size = listeMissions.size();
    	
    	if (size != 0) {
    		this.fenetre.getSupprButtonMissions().setEnabled(true);
        }
	}
	
	private class DeleteListener implements ActionListener {
		
        public void actionPerformed(ActionEvent e) {
        	
        		if(!isSelectionEmpty()) {
            	
	            	int index = getSelectedIndex();
	            	String[] idMission = listeMissions.get(index).split("Mission ");

	            	fenetre.retireMission(idMission[1]);         
	            	
	        		repaint();
	
	            	int size = listeMissions.size();
	            	
	            	if (size == 0) {
	            		fenetre.getSupprButtonMissions().setEnabled(false);
	                } 
	            	else {
						if (index == listeMissions.size()) {
							index--;
						}
	            	}
		            setSelectedIndex(index);
		            ensureIndexIsVisible(index);
        		}
        }
	}
		
}
