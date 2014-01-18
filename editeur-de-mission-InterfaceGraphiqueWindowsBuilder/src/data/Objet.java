package data;

import view.Fenetre;

public class Objet {
	
	private int idObjet;
	private String idArtefact;
	private String image;
	private String script;
	
	public Objet() {
		this.idObjet = 0;
		this.idArtefact = new String();
		this.image = new String();
		this.script = new String();
	}
	
	public Objet(Fenetre fenetre, String idArtefact, String image, String script) {
		this();
		this.idObjet = fenetre.getLastIdObjet()+1;
		this.idArtefact = idArtefact;
		this.image = image;
		this.script = script;
	}
	
	public Objet(int idObjet, String idArtefact, String image, String script) {
		this();
		this.idObjet = idObjet;
		this.idArtefact = idArtefact;
		this.image = image;
		this.script = script;
	}

	public int getIdObjet() {
		return this.idObjet;
	}

	public void setIdObjet(int idObjet) {
		this.idObjet = idObjet;
	}

	public String getIdArtefact() {
		return this.idArtefact;
	}

	public void setIdArtefact(String idArtefact) {
		this.idArtefact = idArtefact;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
