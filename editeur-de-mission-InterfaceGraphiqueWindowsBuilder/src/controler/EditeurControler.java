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
		this.modele.updateFondObervateur();
		System.out.println(this.modele.toString());
	}
	
	public String [] getListePaths() {
		return this.modele.getArtefactsPath();
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
	
	public void ajouterObjet(String idArtefact, String urlRelativeArtefact, String scriptAgent) {
		this.modele.ajouterObjet(idArtefact, urlRelativeArtefact, scriptAgent);
	}
	
	public void restartProject() {
		EditeurControler ctrl = new EditeurControler(new EditeurModele());
	}
	
	public void setFond(String urlBg) {
		this.modele.setFond(urlBg);
	}
	
	public void supprObjet(String artefactPath) {
		this.modele.supprimerObjet(this.modele.getIdArtefactByPath(artefactPath));
	}
}
