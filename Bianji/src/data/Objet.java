package data;

import view.Fenetre;

/**
 * Classe permettant de modélier un objet
 * @author Da Dream Team
 *
 */
public class Objet {
	
	/**
	 * Id correspondant à l'objet (incrémenté de façon automatique)
	 */
	private int idObjet;
	private String idArtefact;
	private String image;
	private String script;
	
	/**
	 * Constructeur par défaut
	 */
	public Objet() {
		this.idObjet = 0;
		this.idArtefact = new String();
		this.image = new String();
		this.script = new String();
	}
	
	/**
	 * Permet de contruire ce nouvel Objet
	 * @param fenetre La fenêtre à laquelle on l'associe
	 * @param idArtefact L'id de l'artefact qu'il possède
	 * @param image Le chemin vers l'image
	 * @param script Le nom du script
	 */
	public Objet(Fenetre fenetre, String idArtefact, String image, String script) {
		this();
		this.idObjet = fenetre.getLastIdObjet()+1;
		this.idArtefact = idArtefact;
		this.image = image;
		this.script = script;
	}
	
	/**
	 * Permet de construire un objet à partir de tous ses attributs (utile pour créer un Objet à partir d'un fichier XML)
	 * @param idObjet L'id de l'objet
	 * @param idArtefact L'id de l'artefact qu'il possède
	 * @param image Le chemin vers l'image
	 * @param script Le nom du script
	 */
	public Objet(int idObjet, String idArtefact, String image, String script) {
		this();
		this.idObjet = idObjet;
		this.idArtefact = idArtefact;
		this.image = image;
		this.script = script;
	}
	
	public boolean equals(Objet o) {
		boolean same = false;
		if(this.idArtefact.equals(o.idArtefact) && 
				this.image.equals(o.image) && this.script.equals(o.script)) {
			same = true;
		}
		return same;
	}

	public int getIdObjet() {
		return this.idObjet;
	}

	public String getIdArtefact() {
		return this.idArtefact;
	}

	public String getImage() {
		return this.image;
	}

	public String getScript() {
		return this.script;
	}

}
