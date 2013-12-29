package view;

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
	}
	
	public void saveProject(){
		this.controler.saveProject();
	}
	
	public void setNomProjet(String name) {
		this.controler.setNomProjet(name);
	}
	
	public String getNomProjet() {
		return this.controler.getNomProjet();
	}
}
