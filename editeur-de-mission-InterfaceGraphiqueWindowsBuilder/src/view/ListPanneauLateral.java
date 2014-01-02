package view;

import javax.swing.JList;

/**
 * Là où sont listés tous les objets du projet
 * @author Da Dream Team
 *
 */
public class ListPanneauLateral extends JList {

	private String [] listeTab;
	
	public void setListe(String[] list) {
		for (int i = 0; i < list.length; i++) {
			this.listeTab[i] = list[i];
		}
	}
	
	public ListPanneauLateral(String [] listeTab) {
		super(listeTab);
		this.listeTab = listeTab;
	}
	
}
