package main;

import model.EditeurModele;
import controler.EditeurControler;


public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EditeurModele modele = new EditeurModele();
		EditeurControler ctrl = new EditeurControler(modele);
	}
	
}
