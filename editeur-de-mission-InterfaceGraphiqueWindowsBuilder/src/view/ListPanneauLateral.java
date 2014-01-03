package view;

import java.util.Vector;

import javax.swing.JList;

/**
 * Là où sont listés tous les objets du projet
 * @author Da Dream Team
 *
 */
public class ListPanneauLateral extends JList<String> {

	private Vector<String> listeVect;
	
	public void setListe(String[] list) {
		this.listeVect = new Vector<String>();
		for (int i = 0; i < list.length; i++) {
			this.listeVect.add(list[i]);
		}
		this.setListData(this.listeVect);
	}
	
	public ListPanneauLateral(String [] list) {
		this.setListe(list);
		for (int i = 0; i < list.length; i++) {
			this.listeVect.add(list[i]);
		}
	}
	
}
