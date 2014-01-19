package controler;

import java.util.ArrayList;
import java.util.List;

import data.Objet;
import model.EditeurModele;
import view.EditeurVue;
import view.LabelArtefact;

public class EditeurControler {
	private EditeurModele modele;
	private EditeurVue vue;
	
	public EditeurControler(EditeurModele model) {
		this.modele = model;
		this.vue = new EditeurVue(this);
		this.modele.addObservateur(this.vue);
	}
	
	public void saveProject(ArrayList<LabelArtefact> listeDraggys) {
		this.modele.viderScene();
		for (LabelArtefact draggy : listeDraggys) {
			this.modele.placerObjetDansScene(draggy.getObjet().getIdObjet(), draggy.getX(), draggy.getY());
		}
		this.modele.createXML();
	}
	
	public void importProject(String path, String nom) {
		this.modele = new EditeurModele(path,nom);
		this.modele.addObservateur(this.vue);
		this.modele.updateListeObervateur();
		this.modele.updateFondObervateur();
		this.modele.updateSceneObervateur();
	}
	
	public List<Objet> getObjets() {
		return this.modele.getObjets();
	}
	
	public String getImageFond() {
		return this.modele.getFond();
	}
	
	public void setNomProjet(String name) {
		this.modele.setNomProjet(name);
	}
	
	public String getNomProjet() {
		return this.modele.getNomProjet();
	}
	
	public void ajouterObjet(Objet o) {
		this.modele.ajouterObjet(o);
	}
	
	public void restartProject() {
		EditeurControler ctrl = new EditeurControler(new EditeurModele());
	}
	
	public void setFond(String urlBg) {
		this.modele.setFond(urlBg);
	}
	
	public void supprObjet(String idObjet) {
		this.modele.supprimerObjet(idObjet);
	}
	
	public int getLargeur() {
		return this.modele.getLargeur();
	}
	
	public int getHauteur() {
		return this.modele.getHauteur();
	}
	
	public void setLargeur(int x) {
		this.modele.setLargeur(x);
	}
	
	public void setHauteur(int x) {
		this.modele.setHauteur(x);
	}
	
	public void placerObjetDansScene(int id, int x, int y) {
		this.modele.placerObjetDansScene(id, x, y);
	}
	
	public boolean getRedimensionnable() {
		return this.modele.getRedimensionnable();
	}
	
	/*public ArrayList<LabelArtefact> chargementElementsScene() {
		return this.modele.chargementElementsScene();
	}*/
	
	public int getLastIdObjet() {
		return this.modele.getLastIdObjet();
	}
	
}
