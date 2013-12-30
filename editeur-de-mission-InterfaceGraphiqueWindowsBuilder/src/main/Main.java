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
		EditeurModele bench = new EditeurModele("xml/bench.xml");
		bench.ajouterObjet("plante", "assets/plante.png", "plante1", "PlanteAI");
		bench.placerObjetDansScene(4, 300, 300);
		bench.supprimerObjet(2);
		//System.out.println(bench);
		//bench.createXML();
	}
	
}
