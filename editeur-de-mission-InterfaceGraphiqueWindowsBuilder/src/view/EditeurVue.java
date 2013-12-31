package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controler.EditeurControler;

/**
 * La vue
 * @author Da Dream Team
 *
 */
public class EditeurVue implements Observer {
	private Fenetre fenetre;
	private EditeurControler controler;

	@Override
	public void update(Observable arg0, Object arg1) {
		this.fenetre.repaint();
	}
	
	public EditeurVue(EditeurControler controler) {
		this.controler = controler;
		this.fenetre = new Fenetre(this);
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
