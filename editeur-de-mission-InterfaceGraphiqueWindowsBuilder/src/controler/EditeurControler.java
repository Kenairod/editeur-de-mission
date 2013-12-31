package controler;

import java.util.ArrayList;

import model.EditeurModele;
import view.EditeurVue;

public class EditeurControler {
	private EditeurModele modele;
	private EditeurVue vue;
	
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
		System.out.println(this.modele.toString());
	}
	
	public ArrayList<String> getListeNoms() {
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
		this.modele.updateList();
	}
}
