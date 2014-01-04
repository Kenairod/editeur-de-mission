package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Vector<String> listePaths;
	private Fenetre fenetre;
	
	public void setListe(String[] list) {
		this.listeImages = new Vector<ImageIcon>();
		this.listePaths = new Vector<String>();
		for (int i = 0; i < list.length; i++) {
			this.listeImages.add(new ImageIcon(list[i]));
			this.listePaths.add(list[i]);
		}
		this.setListData(this.listeImages);
	}
	
	public ListPanneauLateral(String [] list,Fenetre fen) {
		this.setListe(list);
		this.fenetre = fen;
		for (int i = 0; i < list.length; i++) {
			this.listeImages.add(new ImageIcon(list[i]));
		}
		
		this.fenetre.getSupprButton().addActionListener(new FireListener());	
	}
	
	class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = getSelectedIndex();
            if(index != -1) {
            	String artefactPath = listePaths.get(index).toString();
            	listeImages.remove(index);
	            setSelectedIndex(index);
	            ensureIndexIsVisible(index);
	            fenetre.supprObjet(artefactPath);
            }
         }
	}
	
}
