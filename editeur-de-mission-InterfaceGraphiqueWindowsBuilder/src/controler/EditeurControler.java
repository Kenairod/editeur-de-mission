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
	
	public void saveProject(String nomProjet){
		this.modele.createXML(nomProjet);
	}
}
