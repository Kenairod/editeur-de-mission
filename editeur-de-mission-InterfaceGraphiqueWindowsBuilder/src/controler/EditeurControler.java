package controler;

import java.util.ArrayList;

import model.EditeurModele;
import view.EditeurVue;

public class EditeurControler {
	private EditeurModele modele;
	private EditeurVue vue;
	private ArrayList<String> nomArtefacts;
	
	public EditeurControler(EditeurModele model) {
		this.modele = model;
		this.vue = new EditeurVue(this);
		this.modele.addObserver(this.vue);
	}
	
	public void saveProject() {
		this.modele.createXML();
	}
	
	public void importProject(String path) {
		this.modele = new EditeurModele(path);
		this.nomArtefacts = this.modele.getNomArtefacts();
	}
	
	public ArrayList<String> getListeNoms() {
		return this.nomArtefacts;
	}
	
	public void setNomProjet(String name) {
		this.modele.setNomProjet(name);
	}
	
	public String getNomProjet() {
		return this.modele.getNomProjet();
	}
	
	public void ajouterObjet(String idArtefact, String urlRelativeArtefact, String idAgent, String scriptAgent) {
		this.modele.ajouterObjet(idArtefact, urlRelativeArtefact, idAgent, scriptAgent);
	}
}
