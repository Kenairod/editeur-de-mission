package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JList;


/**
 * Là où sont listés tous les objets du projet
 * @author Da Dream Team
 *
 */
public class ListPanneauLateral extends JList { 

	private Vector<ImageIcon> listeImages;
	private ArrayList<String> listePaths;
	private Fenetre fenetre;
	
	public void setListe(ArrayList<String> list) {
		this.listeImages = new Vector<ImageIcon>();
		this.listePaths = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			this.listeImages.add(new ImageIcon(list.get(i)));
			this.listePaths.add(list.get(i));
		}
		this.setListData(this.listeImages);
	}
	
	public ListPanneauLateral(ArrayList<String> list,Fenetre fen) {
		this.setListe(list);
		this.fenetre = fen;
		for (int i = 0; i < list.size(); i++) {
			this.listeImages.add(new ImageIcon(list.get(i)));
			this.listePaths.add(list.get(i));
		}
		
		this.fenetre.getSupprButton().addActionListener(new DeleteListener());	
	}
	
	class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
        		if(!isSelectionEmpty()) {
            	
	            	int index = getSelectedIndex();
	            	String artefactPath = listePaths.get(index).toString();
	            	
	            	fenetre.supprObjet(artefactPath);
	            	
	            	listeImages.remove(index);
	            	listePaths.remove(index);
	            	
	        		repaint();
	
	            	int size = listePaths.size();
	
					if (index == listePaths.size()) {
						//removed item in last position
						index--;
					}
	                    
		            setSelectedIndex(index);
		            ensureIndexIsVisible(index);
        		}
         }
	}
	
}