package view;

import observation.Observateur;
import controler.EditeurControler;

/**
 * La vue
 * @author Da Dream Team
 *
 */
public class EditeurVue implements Observateur {
	private Fenetre fenetre;
	private EditeurControler controler;

	@Override
	public void updateListe(String [] liste) {
		this.fenetre.changeListeObjets(liste);
		this.fenetre.repaint();
	}
	
	@Override
	public void updateFond(String url) {
		this.fenetre.ajouterBg(url);
		this.fenetre.repaint();
	}
	
	@Override
	public void updateScene() {
		
	}
	
	public EditeurVue(EditeurControler controler) {
		this.controler = controler;
		this.fenetre = new Fenetre(this.controler.getListePaths(), this.controler.getImageFond(), this);
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
	
	public void setFond(String urlBg) {
		this.controler.setFond(urlBg);
	}
}
