package controler;

import model.EditeurModele;
import view.EditeurVue;

public class EditeurControler {
	private EditeurModele modele;
	private EditeurVue vue;
	
	public EditeurControler(EditeurModele model) {
		this.modele = model;
		this.vue = new EditeurVue(this);
		this.modele.addObservateur(this.vue);
		//this.modele.mesObservateurs();
	}
	
	public void saveProject() {
		this.modele.createXML();
	}
	
	public void importProject(String path, String nom) {
		this.modele = new EditeurModele(path,nom);
		this.modele.addObservateur(this.vue);
		//this.modele.mesObservateurs();
		this.modele.updateListeObervateur();
		System.out.println(this.modele.toString());
	}
	
	public String [] getListeNoms() {
		return this.modele.getNomArtefacts();
	}
	
	public void setNomProjet(String name) {
		this.modele.setNomProjet(name);
	}
	
	public String getNomProjet() {
		return this.modele.getNomProjet();
	}
	
	public void ajouterObjet(String idArtefact, String urlRelativeArtefact, String scriptAgent) {
		this.modele.ajouterObjet(idArtefact, urlRelativeArtefact, scriptAgent);
	}
	
	public void restartProject() {
		EditeurControler ctrl = new EditeurControler(new EditeurModele());
	}
	
	public void setFond(String urlBg) {
		this.modele.setFond(urlBg);
	}
}
