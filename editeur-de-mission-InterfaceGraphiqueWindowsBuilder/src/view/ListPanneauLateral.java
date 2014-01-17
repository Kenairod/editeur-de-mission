package view;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JList;

import data.Objet;


/**
 * Là où sont listés tous les objets du projet
 * @author Da Dream Team
 *
 */
public class ListPanneauLateral extends JList<ImageIcon> { 

	private Vector<ImageIcon> listeImages;
	private List<Objet> listeObjets;
	private Fenetre fenetre;
	private ArrayList<LabelArtefact> listeDraggys;
	
	public void setListe(List<Objet> list) {
		this.listeImages = new Vector<ImageIcon>();
		this.listeObjets = new ArrayList<Objet>();
		for (int i = 0; i < list.size(); i++) {
			this.listeImages.add(redimensionner(list.get(i).getImage()));
			this.listeObjets.add(list.get(i));
		}
		this.setListData(this.listeImages);
		
		int size = listeImages.size();
    	
    	if (size != 0) {
    		this.fenetre.getSupprButton().setEnabled(true);
        }
	}
	
	public ArrayList<LabelArtefact> getDraggysScene() {
		return this.listeDraggys;
	}
	
	public ListPanneauLateral(List<Objet> list, Fenetre fen) {
		this.setListe(list);
		this.fenetre = fen;
		this.listeDraggys = new ArrayList<LabelArtefact>();
		for (int i = 0; i < list.size(); i++) {
			this.listeImages.add(redimensionner(list.get(i).getImage()));
			this.listeObjets.add(list.get(i));
		}
		
		this.fenetre.getSupprButton().addActionListener(new DeleteListener());
    	
    	this.setDragEnabled(true);
		
		this.fenetre.getScene().setDropTarget(new DropTarget() {
			public void drop(DropTargetDropEvent dtde) {
				int index = getSelectedIndex();
				String artefactPath = listeObjets.get(index).getImage();
				LabelArtefact draggy = new LabelArtefact(new ImageIcon(artefactPath), listeObjets.get(index));
				fenetre.getScene().add(draggy);
				listeDraggys.add(draggy);
				fenetre.getScene().validate();
			}
		});
	}
	
	public ImageIcon redimensionner(String urlImage) {
		ImageIcon icon = new ImageIcon(urlImage); 
		if (icon.getIconHeight() > 64 || icon.getIconWidth() > 64 ) {
			Image img = icon.getImage(); 
			BufferedImage bi = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB); 
			Graphics g = bi.createGraphics(); 
			g.drawImage(img, 0, 0, 64, 64, null); 
			return new ImageIcon(bi); 
		}
		else return icon;
	}
	
	private class DeleteListener implements ActionListener {
		
        public void actionPerformed(ActionEvent e) {
        	
        		if(!isSelectionEmpty()) {
            	
	            	int index = getSelectedIndex();
	            	String idObjet = "" + listeObjets.get(index).getIdObjet();
	
	            	fenetre.supprObjet(idObjet);
	            	
	            	ArrayList<LabelArtefact> copie = new ArrayList<LabelArtefact>();
	            	for (int i=0; i < listeDraggys.size(); i++) {
	            		String s = "" + listeDraggys.get(i).getObjet().getIdObjet();
	            		if(s.equals(idObjet)) {
		            		Container parent = listeDraggys.get(i).getParent();
							parent.remove(listeDraggys.get(i));
							parent.validate();
							parent.repaint();
							copie.add(listeDraggys.get(i));
	            		}
	            	}
	            	
	            	for (int i=0; i < copie.size(); i++) {
	            		getDraggysScene().remove(copie.get(i));
	            	}           	
	            		            	
	            	listeImages.remove(index);
	            	listeObjets.remove(index);
	            		                     	
	        		repaint();
	
	            	int size = listeObjets.size();
	            	
	            	if (size == 0) {
	            		fenetre.getSupprButton().setEnabled(false);
	                } 
	            	else {
						if (index == listeObjets.size()) {
							index--;
						}
	            	}
		            setSelectedIndex(index);
		            ensureIndexIsVisible(index);
        		}
        }
	}
	
}