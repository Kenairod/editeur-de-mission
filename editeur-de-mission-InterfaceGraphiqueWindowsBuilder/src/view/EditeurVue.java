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
	public void update(Observable o, Object arg) {
		System.out.println("MAJ");
		if (arg.getClass().equals(new ArrayList<String>().getClass())) {
			ArrayList<String> liste = ((ArrayList<String>) arg);
			this.fenetre.changeListeObjets(liste);
		}
		this.fenetre.repaint();
	}
	
	public EditeurVue(EditeurControler controler) {
		this.controler = controler;
		this.fenetre = new Fenetre(this.controler.getListeNoms(), this);
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
	
	public void ajouterObjet(String idArtefact, String urlRelativeArtefact, String scriptAgent) {
		this.controler.ajouterObjet(idArtefact, urlRelativeArtefact, scriptAgent);
	}
	
	public void restartProject() {
		this.controler.restartProject();
	}
}
