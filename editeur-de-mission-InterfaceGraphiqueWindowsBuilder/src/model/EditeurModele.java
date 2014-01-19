package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import observation.Observable;
import observation.Observateur;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import view.LabelArtefact;
import data.Objet;

/**
 * Permet de modéliser les éléments nécessaires à la modélisation d'une scène
 * @author Da Dream Team
 *
 */
public class EditeurModele implements Observable {
	
	private String nomProjet;
	
	/**
	 * Le titre du jeu
	 */
	 private String titre;
	 
	 /**
	  * Utilisation de GL2 (doit être passé à true pour que game fonctionne)
	  */
     private boolean gl20;
     
     /**
      * Largeur de la scène de jeu
      */
     private int largeur;
     
     /**
      * Hauteur de la scène de jeu
      */
     private int hauteur;
     
     /**
      * Indique si la fenêtre est redimensionnable ou pas
      */
     private boolean redimensionnable;
     
     /**
      * Chemin vers le fichiers image du background de la scène
      */
     private String imageFond;
     
     /**
      * Les éléments (objets) de la scène
      */
     private List<Element> elementsScene;
     
     /**
      * Les artefacts de la scène
      */
     private List<Element> artefacts;
     
     /**
      * Les associations artefact/agent
      */
     private List<Objet> mapping;
     
     /**
      * Les missions
      */
     private List<Element> missions;
     
     /**
      * Notre collection d'observateurs
      */
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
     
     /**
      * Constructeur par défaut
      */
     public EditeurModele() {
    	 this.nomProjet = new String();
    	 this.titre = new String();
    	 this.gl20 = true;
    	 this.largeur = 800;
    	 this.hauteur = 600;
    	 this.redimensionnable = false;
    	 this.imageFond = new String();
    	 this.elementsScene = new ArrayList<Element>();
    	 this.artefacts = new ArrayList<Element>();
    	 this.mapping = new ArrayList<Objet>();
    	 this.missions = new ArrayList<Element>();
    	 this.updateListeObervateur();
     }
     
     /**
      * Constructeur initialisant tous les paramètres
      * @param titre Le titre du jeu
      * @param gl20 Utilisation de GL2 (doit être passé à true pour que game fonctionne)
      * @param largeur Largeur de la scène de jeu
      * @param hauteur Hauteur de la scène de jeu
      * @param redimensionnable vrai (true) si la fenêtre du jeu pourra être redimensionnable
      * @param imageFond Chemin vers le fichiers image du background de la scène
      * @param elementsScene Les éléments (objets) de la scène
      * @param artefacts Les artefacts de la scène
      * @param mapping Les associations artefact/agent
      */
     public EditeurModele(String nomProjet, String titre, boolean gl20, int largeur, int hauteur, boolean redimensionnable, String imageFond,
			List<Element> elementsScene, List<Element> artefacts, List<Objet> mapping, List<Element> missions) {
		this.nomProjet = nomProjet;
		this.titre = titre;
		this.gl20 = gl20;
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.redimensionnable = redimensionnable;
		this.imageFond = imageFond;
		this.elementsScene = elementsScene;
		this.artefacts = artefacts;
		this.mapping = mapping;
		this.missions = missions;
		this.updateListeObervateur();
	}

     /**
      * Permet de générer un fichier XML à partir du modele
      * Pré-requis : Les membres de : elementsScene, artefacts et mapping doivent être de type Element 
      * 				et avec tous les attributs déjà initialisés
      */
     public void createXML() {	              
	     try {
	    	 JFileChooser chooser = new JFileChooser(".");
	    	 chooser.setDialogTitle("Save as");
	    	 File fichier = new File(this.getNomProjet() + ".xml");
	    	 chooser.setSelectedFile(fichier);
	    	 chooser.setApproveButtonText("Save");
	    	 if (chooser.showOpenDialog(null) == 0) {
	    	    	
		         Element jeu = new Element("jeu");
		         
		         Document document = new Document(jeu);
		         
		         // Balise titre du jeu
		         Element titre = new Element("titre-du-jeu");
		         titre.setText(this.titre);
		         
		         // Balise fenetre et ses sous-balises
		         Element fenetre = new Element("fenetre");
		         fenetre.addContent(new Element("useGL20").setText(""+this.gl20));
		         fenetre.addContent(new Element("largeur").setText(""+this.largeur));
		         fenetre.addContent(new Element("hauteur").setText(""+this.hauteur));
		         fenetre.addContent(new Element("redimensionnable").setText(""+this.redimensionnable));
		         
		         /*Balise scene et ses sous-balises (autant de sous balises "elements" 
		         que contenu dans this.elementsScene*/
		         Element scene = new Element("scene");
		         scene.addContent(new Element("fond").setAttribute(new Attribute("image", this.imageFond)));
		         Element elements = new Element("elements");
		         for (int i = 0; i < this.elementsScene.size(); i++) {
		        	 Element elem = this.elementsScene.get(i).clone();
		        	 elements.addContent(elem);
		         }
		         scene.addContent(elements);
		         
		         // Comme pour elements mais pour artefacts
		         Element artefacts = new Element("artefacts");
		         for (int i = 0; i < this.artefacts.size(); i++) {
		        	 Element artef = this.artefacts.get(i).clone();
		        	 artefacts.addContent(artef);
		         }
		         
		         // Idem
		         Element mapping = new Element("mapping");
		         for (int i = 0; i < this.mapping.size(); i++) {
		        	 Element obj = new Element("objet");
		        	 obj.setAttribute("id", ""+this.mapping.get(i).getIdObjet());
		        	 Element artefact = new Element("artefact");
		        	 artefact.setAttribute("id", this.mapping.get(i).getIdArtefact());
		        	 artefact.setAttribute("image", this.mapping.get(i).getImage());
		        	 Element agent = new Element("agent");
		        	 agent.setAttribute("script", this.mapping.get(i).getScript());
		        	 
		        	 obj.addContent(artefact);
		        	 obj.addContent(agent);
		        			 
		        	 mapping.addContent(obj);
		         }
		         
		         Element missions = new Element("missions");
		         
		         // On accroche toute les balises directements fille de la racine (à la balise jeu) à cette dernière
		         document.getRootElement().addContent(titre);
		         document.getRootElement().addContent(fenetre);
		         document.getRootElement().addContent(scene);
		         document.getRootElement().addContent(artefacts);
		         document.getRootElement().addContent(mapping);
		         document.getRootElement().addContent(missions);
		         
		         // Objet permettant la sortie en XML
		         XMLOutputter xmlOutput = new XMLOutputter();
		         
		         /*System.out pour afficher en console
		         xmlOutput.output(document, System.out);*/
		         
		         // On génère
		         xmlOutput.setFormat(Format.getPrettyFormat());  
		         xmlOutput.output(document, new FileOutputStream(chooser.getSelectedFile().toString())); 
	    	 }
		     	} catch (IOException io) {  
		        	System.out.println(io.getMessage());  
		        }
     }
     
     /**
      * Permet d'importer un fichier XML dans afin de construire ce modèle
      * @param fichier Le chemin vers le fichier source
      */
	public EditeurModele(String fichier, String nom) {
		// La lecture se fait à l'aide d'une contructeur SAX
		SAXBuilder ConstructSAX = new SAXBuilder();
		// On récupère le fichier source
		File file = new File(fichier);
		        
		this.nomProjet = nom;
		
		try {
			// On convertit le fichier en objet Document à l'aide du constructeur SAX
			Document document = ConstructSAX.build(file);
			// On récupère le noeud racine
			Element noeudRacine = document.getRootElement();
			
			// On récupère le texte contenu dans la balise passée en paramètre
			this.titre = noeudRacine.getChildText("titre-du-jeu");
			
			// On récupère le fils de noeudRacine qui s'appelle fenetre
			Element fenetre = noeudRacine.getChild("fenetre");
			// On récupère le texte contenu dans la balise useGL20 contenue dans fenêtre
			if (fenetre.getChildText("useGL20").equals("true")) {
				this.gl20 = true;
			}
			else this.gl20 = false;
			if (fenetre.getChildText("redimensionnable").equals("true")) {
				this.redimensionnable = true;
			}
			else this.redimensionnable = false;
			this.largeur = Integer.parseInt(fenetre.getChildText("largeur"));
			this.hauteur = Integer.parseInt(fenetre.getChildText("hauteur"));
			
			Element scene = noeudRacine.getChild("scene");
			Element fond = scene.getChild("fond");
			// On récupère la valeur de l'attribue image de la balise fond contenue dans la balise scene
			this.imageFond = fond.getAttributeValue("image");
			
			Element elements = scene.getChild("elements");
			this.elementsScene = elements.getChildren();
			Element artefacts = noeudRacine.getChild("artefacts");
			this.artefacts = artefacts.getChildren();
			Element mapping = noeudRacine.getChild("mapping");
			
			List<Element> listeObjets = mapping.getChildren();
			
			Iterator<Element> i = listeObjets.iterator();
			this.mapping = new ArrayList<Objet>();
			while (i.hasNext()) {
				Element courant = (Element)i.next();
				int idObj = Integer.parseInt(courant.getAttributeValue("id"));
				Iterator<Element> j = courant.getChildren().iterator();
				Element Courant = (Element)j.next();
				String idArtef = Courant.getAttributeValue("id");
				String imageArtef = Courant.getAttributeValue("image");
				Courant = (Element)j.next();
				String scriptAgent = Courant.getAttributeValue("script");
				Objet obj = new Objet(idObj, idArtef, imageArtef, scriptAgent);
				this.mapping.add(obj);
			}
			
			this.updateListeObervateur();
		}
		catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {   
		String ret = "Nom du jeu : "+this.titre+"\n";
		ret = ret +"========Propriétés de la fenêtre========\n";
		ret = ret + "Utilise OpenGL 2.0 : "+this.gl20+"\n";
		ret = ret + "Largeur : "+this.largeur+"\n";
		ret = ret + "Hauteur : "+this.hauteur+"\n";
		ret = ret + "Redimensionnable : "+this.redimensionnable+"\n";
		ret = ret + "========Propriétés de la scène========\n";
		ret = ret + "URL image de fond : "+this.imageFond+"\n";
		Iterator i = this.elementsScene.iterator();
		
		while (i.hasNext()) {
			Element courant = (Element)i.next();
			ret = ret + "Objet de type : "+courant.getAttributeValue("id")+"\n";
			ret = ret + "Coordonnée x : "+courant.getAttributeValue("x")+"\n";
			ret = ret + "Coordonnée y : "+courant.getAttributeValue("y")+"\n\n";
		}
		
		ret = ret + "========Artefacts enregistrés========\n";
		i = this.artefacts.iterator();
		
		while (i.hasNext()) {
			Element courant = (Element)i.next();
			ret = ret + "id de l'Artefact : "+courant.getAttributeValue("id")+"\n";
			ret = ret + "URL Image : "+courant.getAttributeValue("image")+"\n";
		}
		
		
		ret = ret + "========Mapping========\n";
		i = this.mapping.iterator();
		
		while (i.hasNext()) {
			Element courant = (Element)i.next();
			ret = ret + "id map : "+courant.getAttributeValue("id")+"\n";
			Iterator<Element> j = courant.getChildren().iterator();
			Element Courant = (Element)j.next();
			ret = ret + " id child : "+Courant.getAttributeValue("id")+"\n";
			Courant = (Element)j.next();
			ret = ret + " script child : "+Courant.getAttributeValue("script")+"\n";
		}
		
		return ret;
	}

	public List<Element> getElements() {
		return this.elementsScene;
	}
	
	public List<Objet> getObjets() {
		return this.mapping;
	}
	
	public String getNomProjet() {
		return this.nomProjet;
	}
	
	public void setNomProjet(String nom) {
		this.nomProjet = nom;
	}
	
	public void setFond(String urlFond) {
		this.imageFond = urlFond;
	}
	
	public String getFond() {
		return this.imageFond;
	}
	
	public boolean getRedimensionnable() {
		return this.redimensionnable;
	}
	
	public int getLargeur() {
		return this.largeur;
	}
	
	public int getHauteur() {
		return this.hauteur;
	}
	
	public void setLargeur(int x) {
		this.largeur = x;
	}
	
	public void setHauteur(int x) {
		this.hauteur = x;
	}
	
	public int getLastIdObjet() {
		if(this.mapping.size() != 0) {
			return this.mapping.get(this.mapping.size()-1).getIdObjet();
		}
		else return 0;
	}
	
	/**
	 * Permet d'ajouter un artefact dans la liste des artefacts
	 * @param idArtefact L'attribut id de l'artefact
	 * @param urlRelativeArtefact L'attribut image de l'artefact
	 */
	public void ajouterArtefactDansSaListe(String idArtefact, String urlRelativeArtefact) {
		Element artefact = new Element("artefact");
		Attribute id = new Attribute("id", idArtefact);
		Attribute image = new Attribute("image", urlRelativeArtefact);
		ArrayList<Attribute> a = new ArrayList<Attribute>();
		a.add(id);
		a.add(image);
		artefact.setAttributes(a);
		this.artefacts.add(artefact);
	}
	
	/**
	 * Supprime l'artefact de la liste et supprime également toutes ses apparitions sur la scène
	 * @param idArtefact id de l'artefact à supprimer
	 */
	public void supprimerArtefact(String idArtefact) {
		int i = 0;
		boolean supr = false;
		while (i < this.artefacts.size() && !supr) {
			if (this.artefacts.get(i).getAttributeValue("id").equals(idArtefact)) {
				this.artefacts.remove(i);
				supr = true;
			}
			i++;
		}
	}
	
	/**
	 * Rajoute un objet dans le mapping
	 * @param objet L'objet à ajouter
	 */
	public void ajouterObjetMapping(Objet objet) { // Ajoute objet au mapping
		this.mapping.add(objet);
	}
	
	/**
	 * Ajouter un objet au mapping en lui passant les attributs de l'artefact et de l'agent en valeurs <br />
	 * Rajoute également en même temps automatiquement l'artefact à la liste des artefacts
	 * @param idArtefact id (nom) de l'artefact
	 * @param urlRelativeArtefact adresse relative menant au fichier de l'artefact (ex : fichier de texture)
	 * @param scriptAgent Script de l'agent
	 */
	public void ajouterObjet(Objet obj) {
		if (!this.autresObjetsUtilisentCetArtefact(obj.getIdArtefact())) {
			this.ajouterArtefactDansSaListe(obj.getIdArtefact(), obj.getImage());
		}
		this.ajouterObjetMapping(obj);
		this.updateListeObervateur();
	}
	
	/**
	 * Permet de vérifier s'il y a des objets qui utilisent cet artefact
	 * @param idArtef L'id de l'atefact donc on vérifie s'il est utilisé
	 * @return true s'il est utilisé, false sinon
	 */
	public boolean autresObjetsUtilisentCetArtefact(String idArtef) {
		boolean ret = false;
		for (Objet obj : this.mapping) {
			if (obj.getIdArtefact().equals(idArtef)) {
				ret = true;
			}
		}
		return ret;
	}
	
	/**
	 * Supprime l'objet du projet
	 * @param chemin de l'objet à supprimer
	 */
	public void supprimerObjet(String id) {	// Supprime toute présence de l'objet
		int i = 0;
		boolean supr = false;
		this.supprimerApparitionsScene(id);
		while (i < this.mapping.size() && !supr) {
			String idObj = ""+this.mapping.get(i).getIdObjet();
			if (idObj.equals(id)) {
				String idArtef = this.mapping.get(i).getIdArtefact();
				this.mapping.remove(i);
				if (!this.autresObjetsUtilisentCetArtefact(idArtef)) {
					this.supprimerArtefact(idArtef);
				}
				
				supr = true;
			}
			i++;
		}
	}
	
	/**
	 * Supprime toutes les apparitions sur la scèhe de l'objet possédant l'id d'objet id
	 * @param id L'id fe l'objet dont on veut supprimer les apparitions
	 */
	public void supprimerApparitionsScene(String id) {
		ArrayList<Element> temp = new ArrayList<Element>();
		Iterator<Element> it = this.elementsScene.iterator();
		while (it.hasNext()) {
			Element courant = (Element)it.next();
			if (!id.equals(courant.getAttributeValue("id"))) {
				temp.add(courant);
			}
		}
		this.elementsScene = temp;
	}
	
	/**
	 * Permet de récupérer un objet grâce à son id dans le mapping
	 * @param idObjet L'id de l'objet à récupérer
	 * @return L'Objet donc l''id correpond à idObjet
	 */
	public Objet getObjetByIdObjet(String idObjet) {
		Objet o = new Objet();
		int i = 0;
		boolean trouve = false;
		while(i < this.mapping.size() && !trouve) {
			String idObjetMapping = "" + this.mapping.get(i).getIdObjet();
			if(idObjetMapping.equals(idObjet)) {
				o = this.mapping.get(i);
				trouve = true;
			}
			i++;
		}
		return o;
	}
	
	/**
	 * Permet de transformer les balises XML de la scène en labeLArtefact pouvant être affichés sur la scène
	 * @return Un ArrayList<aLbelArtefact> correspondant aux artefact à placer sur la scène
	 */
	public ArrayList<LabelArtefact> getElementsScene() {
		ArrayList<LabelArtefact> elem = new ArrayList<LabelArtefact>();
		Iterator<Element> i = this.elementsScene.iterator();
		String id = new String();
		
		while (i.hasNext()) {
			Element courant = (Element)i.next();
			id = courant.getAttributeValue("id");
			int x = Integer.parseInt(courant.getAttributeValue("x"));
			int y = Integer.parseInt(courant.getAttributeValue("y"));
			Objet o = this.getObjetByIdObjet(id);
			LabelArtefact la = new LabelArtefact(new ImageIcon(o.getImage()), o);
			la.setPosition(x, y);
			elem.add(la);
		}
		return elem;
	}
	
	/**
	 * Rajoute un objet dans la scene
	 * @param objet L'objet à ajouter
	 */
	public void ajouterObjetScene(Element objet) {
		this.elementsScene.add(objet);
	}
	
	/**
	 * Supprime tous les objets placés sur la scène
	 */
	public void viderScene() {
		this.elementsScene.clear();
	}
	
	/**
	 * Retire un objet de la scene
	 * @param objet L'objet à retirer
	 */
	public void retirerObjetScene(Element objet) {
		this.elementsScene.remove(objet);
	}
	
	/**
	 * Permet d'ajouter l'objet possédant l'id correspondant aux coordonnées x et y
	 * @param id id du mapping de l'objet à placer sur la scène
	 * @param x Coordonnée x sur la scene
	 * @param y Coornonnée y sur la scène
	 */
	public void placerObjetDansScene(int id, int x, int y) {
		Element objet = new Element ("objet");
		Attribute attId = new Attribute("id", ""+id);
		Attribute attX = new Attribute("x", ""+x);
		Attribute attY = new Attribute("y", ""+y);
		ArrayList<Attribute> a = new ArrayList<Attribute>();
		a.add(attId);
		a.add(attX);
		a.add(attY);
		objet.setAttributes(a);
		
		this.ajouterObjetScene(objet);
	}

	@Override
	/**
	 * Ajoute un observateur à la liste
	 */
	public void addObservateur(Observateur obs) {
		this.listObservateur.add(obs);
	}
	
	/**
	 * Permet de lister les obsevateurs
	 */
	public void mesObservateurs() {
		System.out.println("Je suis observé par : ");
		for(Observateur obs : this.listObservateur )
			System.out.println(obs);
	}


	/**
	 * Avertit les observateurs que l'objet observable a changé et invoque la méthode update() de chaque observateur
	 */
	@Override
	public void updateListeObervateur() {
		for(Observateur obs : this.listObservateur )
	    	obs.updateListe(this.getObjets());
	}

	/**
	 * Indique aux observateurs que le fond a changé
	 */
	@Override
	public void updateFondObervateur(){
		for(Observateur obs : this.listObservateur )
	    	obs.updateFond(this.getFond());
	}
	
	/**
	 * Indique aux observateurs que la scène a changée
	 */
	@Override
	public void updateSceneObervateur(){
		for(Observateur obs : this.listObservateur )
	    	obs.updateScene(this.getElementsScene());
	}
	
	/**
	 * Retire tous les observateurs de la liste des observateurs
	 */
	@Override
	public void delObservateur() {
		this.listObservateur = new ArrayList<Observateur>();
	}
	
}
