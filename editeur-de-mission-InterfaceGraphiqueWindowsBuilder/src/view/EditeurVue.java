package view;

import java.util.ArrayList;
import java.util.List;

import observation.Observateur;
import controler.EditeurControler;
import data.Objet;

/**
 * La vue
 * @author Da Dream Team
 *
 */
public class EditeurVue implements Observateur {
	private Fenetre fenetre;
	private EditeurControler controler;

	/**
	 * Permet de mettre à jour la liste des objet dans le panneau latéral
	 */
	@Override
	public void updateListe(List<Objet> liste) {
		this.fenetre.changeListeObjets(liste);
		this.fenetre.repaint();
	}
	
	/**
	 * Permet de mettre à jour l'image de fond de la scène qui est affichée
	 */
	@Override
	public void updateFond(String url) {
		this.fenetre.ajouterBg(url);
		this.fenetre.repaint();
	}
	
	/**
	 * Permet de mettre à jour la position des différents objets sur la scène
	 */
	@Override
	public void updateScene(ArrayList<LabelArtefact> elems) {
		this.fenetre.updateListeLabelArtef(elems);
	}
	
	public EditeurVue(EditeurControler controler) {
		this.controler = controler;
		this.fenetre = new Fenetre(this.controler.getObjets(), this.controler.getImageFond(), this);
	}
	
	public void saveProject(ArrayList<LabelArtefact> listeDraggys) {
		this.controler.saveProject(listeDraggys);
	}
	
	public void importProject(String path, String nom) {
		this.controler.importProject(path,nom);
	}
	
	public void setNomProjet(String name) {
		this.controler.setNomProjet(name);
	}
	
	public String getNomProjet() {
		return this.controler.getNomProjet();
	}
	
	public void ajouterObjet(Objet o) {
		this.controler.ajouterObjet(o);
	}
	
	public void restartProject() {
		this.controler.restartProject();
	}
	
	public void setFond(String urlBg) {
		this.controler.setFond(urlBg);
	}
	
	public void supprObjet(String idObjet) {
		this.controler.supprObjet(idObjet);
	}
	
	public int getLargeur() {
		return this.controler.getLargeur();
	}
	
	public int getHauteur() {
		return this.controler.getHauteur();
	}
	
	public void setLargeur(int x) {
		this.controler.setLargeur(x);
	}
	
	public void setHauteur(int x) {
		this.controler.setHauteur(x);
	}
	
	public boolean getRedimensionnable() {
		return this.controler.getRedimensionnable();
	}
	
	public int getLastIdObjet() {
		return this.controler.getLastIdObjet();
	}
	
}
