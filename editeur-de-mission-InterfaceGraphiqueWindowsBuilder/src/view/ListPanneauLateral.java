package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JList;


/**
 * Là où sont listés tous les objets du projet
 * @author Da Dream Team
 *
 */
public class ListPanneauLateral extends JList<ImageIcon> { 

	private Vector<ImageIcon> listeImages;
	private ArrayList<String> listePaths;
	private Fenetre fenetre;
	
	public void setListe(ArrayList<String> list) {
		this.listeImages = new Vector<ImageIcon>();
		this.listePaths = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			this.listeImages.add(redimensionner(list.get(i)));
			this.listePaths.add(list.get(i));
		}
		this.setListData(this.listeImages);
		
		int size = listeImages.size();
    	
    	if (size != 0) {
    		fenetre.getSupprButton().setEnabled(true);
        } 
	}
	
	
	public ListPanneauLateral(ArrayList<String> list, Fenetre fen) {
		this.setListe(list);
		this.fenetre = fen;
		for (int i = 0; i < list.size(); i++) {
			this.listeImages.add(redimensionner(list.get(i)));
			this.listePaths.add(list.get(i));
		}
		
		this.fenetre.getSupprButton().addActionListener(new DeleteListener());	
	}
	
	public ImageIcon redimensionner(String urlImage) {
		ImageIcon icon = new ImageIcon(urlImage); 
		if (icon.getIconHeight() > 64) {
			Image img = icon.getImage(); 
			BufferedImage bi = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB); 
			Graphics g = bi.createGraphics(); 
			g.drawImage(img, 0, 0, 64, 64, null); 
			return new ImageIcon(bi); 
		}
		else return icon;
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
	            	
	            	if (size == 0) {
	            		fenetre.getSupprButton().setEnabled(false);
	                } 
	            	else {
						if (index == listePaths.size()) {
							index--;
						}
	            	}
		            setSelectedIndex(index);
		            ensureIndexIsVisible(index);
        		}
         }
	}
	
}