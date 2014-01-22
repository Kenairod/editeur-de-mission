package controler;

import java.util.ArrayList;
import java.util.List;

import data.Objet;
import model.EditeurModele;
import view.EditeurVue;
import view.LabelArtefact;

/**
 * Classe Contrôleur du logiciel construite sur le modèle du Pattern MVC
 * @author Da Dream Team
 *
 */
public class EditeurControler {
	
	private EditeurModele modele;
	private EditeurVue vue;
	
	/**
	 * Constructeur qui permet d'initialiser le logiciel
	 * @param model le modèle à utiliser
	 */
	public EditeurControler(EditeurModele model) {
		this.modele = model;
		this.vue = new EditeurVue(this);
		this.modele.addObservateur(this.vue);
	}
	
	/**
	 * Génère le XML correspondant au modèle
	 * @param listeDraggys La liste contenant les emplacement es objets sur la scène
	 */
	public void saveProject(ArrayList<LabelArtefact> listeDraggys) {
		this.modele.viderScene();
		for (LabelArtefact draggy : listeDraggys) {
			this.modele.placerObjetDansScene(draggy.getObjet().getIdObjet(), draggy.getX(), draggy.getY());
		}
		this.modele.createXML();
	}
	
	/**
	 * Permet de créer un modèle à l'aide d'un fichier XML et de mettre à  jour la vue
	 * @param path Le chemin vers le fichier XML à ouvrir
	 * @param nom Le nom du projet
	 */
	public void importProject(String path, String nom) {
		this.modele = new EditeurModele(path,nom);
		this.modele.addObservateur(this.vue);
		this.modele.updateListeObervateur();
		this.modele.updateFondObervateur();
		this.modele.updateSceneObervateur();
		this.modele.updateMissionsObservateur();
	}
	
	/**
	 * Permet de connaitre les objets listé dans le mapping du modèle
	 * @return La liste des Objets contenus dans le modèle
	 */
	public List<Objet> getObjets() {
		return this.modele.getObjets();
	}
	
	public List<String> getMissions() {
		return this.modele.getMissions();
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
	
	/**
	 * Ajoute un objet au mapping du modèle
	 * @param o L'objet à ajouter au mapping
	 */
	public void ajouterObjet(Objet o) {
		this.modele.ajouterObjet(o);
	}
	
	/**
	 * Permet de relancer l'éditeur
	 */
	public void restartProject() {
		EditeurModele modele = new EditeurModele();
		new EditeurControler(modele);
	}
	
	/**
	 * Permet de modifier le chemin d'accès vers l'image de fond dans le modèle
	 * @param urlBg Le nouveau chemin à enregistrer dans le modèle
	 */
	public void setFond(String urlBg) {
		this.modele.setFond(urlBg);
	}
	
	/**
	 * Supprime un objet du mapping dans le modèle
	 * @param idObjet L'id de l'Objet à supprimer
	 */
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
	
	/**
	 * Permet dans le modèle de placer l'objet d'id id et de coordonnées x et y sur la scène
	 * @param id L'idée de l'objet à placer
	 * @param x Sa coordonnée x
	 * @param y Sa coordonnée y
	 */
	public void placerObjetDansScene(int id, int x, int y) {
		this.modele.placerObjetDansScene(id, x, y);
	}
	
	public boolean getRedimensionnable() {
		return this.modele.getRedimensionnable();
	}
	
	public int getLastIdObjet() {
		return this.modele.getLastIdObjet();
	}
	
	public void ajoutMission(String idMission) {
		this.modele.ajoutMission(idMission);
	}
	
	public void retireMission(String idMission) {
		this.modele.retireMission(idMission);
	}
	
}
