package controler;

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
		System.out.println(modele);
	}
	
	public void setNomProjet(String name) {
		this.modele.setNomProjet(name);
	}
	
	public String getNomProjet() {
		return this.modele.getNomProjet();
	}
}
