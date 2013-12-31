package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controler.EditeurControler;

public class EditeurVue implements Observer {
	private Fenetre fenetre;
	private EditeurControler controler;
	
	public EditeurVue(EditeurControler controleur) {
		this.controler = controleur;
		this.fenetre = new Fenetre(this);
	}
	
	public void update(Observable o, Object arg) {
		this.fenetre.repaint();
		//this.fenetre.initArbre((ArrayList<String>) arg);
	}
	
	public void saveProject() {
		this.controler.saveProject();
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
	
	public ArrayList<String> getListeNoms() {
		return this.controler.getListeNoms();
	}
	
	public void ajouterObjet(String idArtefact, String urlRelativeArtefact, String scriptAgent) {
		this.controler.ajouterObjet(idArtefact, urlRelativeArtefact, scriptAgent);
	}
}
