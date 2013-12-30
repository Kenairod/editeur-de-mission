package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import javax.swing.JFileChooser;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Permet de modéliser les éléments nécessaires à la modélisation d'une scène
 * @author Da Dream Team
 *
 */
public class EditeurModele extends Observable {
	
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
      * redimensionnable vrai (true) si la fenêtre du jeu pourra être redimensionnable
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
     private List<Element> mapping;
     
     
     /**
      * Constructeur par défaut
      */
     public EditeurModele() {
    	 this.nomProjet = new String();
    	 this.titre = new String();
    	 this.gl20 = false;
    	 this.largeur = 0;
    	 this.hauteur = 0;
    	 this.redimensionnable = false;
    	 this.imageFond = new String();
    	 this.elementsScene = new ArrayList<Element>();
    	 this.artefacts = new ArrayList<Element>();
    	 this.mapping = new ArrayList<Element>();
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
     public EditeurModele(String nomProjet, String titre,boolean gl20,int largeur,int hauteur, boolean redimensionnable, String imageFond,
			List<Element> elementsScene, List<Element> artefacts,List<Element> mapping) {
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
	}


     /**
      * Permet de générer un fichier XML à partir du modele
      */
	//Pérequis : Les membres de : elementsScene, artefacts et mapping doivent être de type Element et avec tous les attributs déjà initialisés
     public void createXML() {	              
	     try {
	    	 JFileChooser chooser = new JFileChooser();
	    	 chooser.setDialogTitle("Save as");
	    	 File fichier = new File(this.getNomProjet() + ".xml");
	    	 chooser.setSelectedFile(fichier);
	    	 chooser.setApproveButtonText("Save");
	    	 if (chooser.showOpenDialog(null) == 0) {
	    	    	
		         Element jeu = new Element("jeu");
		         
		         Document document = new Document(jeu);
		         
		         //Balise titre du jeu
		         Element titre = new Element("titre-du-jeu");
		         titre.setText(this.titre);
		         
		         //Balise fenetre et ses sous-balises
		         Element fenetre = new Element("fenetre");
		         fenetre.addContent(new Element("useGL20").setText(""+this.gl20));
		         fenetre.addContent(new Element("largeur").setText(""+this.largeur));
		         fenetre.addContent(new Element("hauteur").setText(""+this.hauteur));
		         fenetre.addContent(new Element("redimensionnable").setText(""+this.redimensionnable));
		         
		         //Balise scene et ses sous-balises (autant de sous balises "elements" que contenu dans this.elementsScene
		         Element scene = new Element("scene");
		         scene.addContent(new Element("fond").setAttribute(new Attribute("image", this.imageFond)));
		         Element elements = new Element("elements");
		         for (int i = 0; i < this.elementsScene.size(); i++) {
		        	 Element elem = this.elementsScene.get(i).clone();
		        	 elements.addContent(elem);
		         }
		         scene.addContent(elements);
		         
		         //Comme pour elements mais pour artefacts
		         Element artefacts = new Element("artefacts");
		         for (int i = 0; i < this.artefacts.size(); i++) {
		        	 Element artef = this.artefacts.get(i).clone();
		        	 artefacts.addContent(artef);
		         }
		         
		         //Idem
		         Element mapping = new Element("mapping");
		         for (int i = 0; i < this.mapping.size(); i++) {
		        	 Element obj = this.mapping.get(i).clone();
		        	 mapping.addContent(obj);
		         }
		         
		         //On accroche toute les balises directements fille de la racine (à la balise jeu) à cette dernière
		         document.getRootElement().addContent(titre);
		         document.getRootElement().addContent(fenetre);
		         document.getRootElement().addContent(scene);
		         document.getRootElement().addContent(artefacts);
		         document.getRootElement().addContent(mapping);
		         
		         //Objet permettant la sortie en XML
		         XMLOutputter xmlOutput = new XMLOutputter();
		         
		         //System.out pour afficher en console
		         //xmlOutput.output(document, System.out);
		         
		         //On génère
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
	public EditeurModele(String fichier) {
		//La lecture se fait à l'aide d'une contructeur SAX
		SAXBuilder ConstructSAX = new SAXBuilder();
		//On récupère le fichier source
		File file = new File(fichier);
		        
		try {
			//On convertit le fichier en objet Document à l'aide du constructeur SAX
			Document document = ConstructSAX.build(file);
			//On récupère le noeud racine
			Element noeudRacine = document.getRootElement();
			
			//On récupère le texte contenu dans la balise passée en paramètre
			this.titre = noeudRacine.getChildText("titre-du-jeu");
			
			//On récupère le fils de noeudRacine qui s'appelle fenetre
			Element fenetre = noeudRacine.getChild("fenetre");
			//On récupère le texte contenu dans la balise useGL20 contenue dans fenêtre
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
			//On récupère la valeur de l'attribue image de la balise fond contenue dans la balise scene
			this.imageFond = fond.getAttributeValue("image");
			
			Element elements = scene.getChild("elements");
			this.elementsScene = elements.getChildren();
			Element artefacts = noeudRacine.getChild("artefacts");
			this.artefacts = artefacts.getChildren();
			Element mapping = noeudRacine.getChild("mapping");
			this.mapping = mapping.getChildren();
			this.setChanged();

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
			ret = ret + "Texte : "+courant.getAttributeValue("texte")+"\n";
			ret = ret + "URL Son : "+courant.getAttributeValue("son")+"\n\n";
		}
		
		
		ret = ret + "========Mapping========\n";
		i = this.mapping.iterator();
		
		while (i.hasNext()) {
			Element courant = (Element)i.next();
			ret = ret + "id map : "+courant.getAttributeValue("id")+"\n";
			Iterator j = courant.getChildren().iterator();
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
	
	public String getNomProjet() {
		return this.nomProjet;
	}
	
	public void setNomProjet(String nom) {
		this.nomProjet = nom;
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
		this.setChanged(); //On indique aux observeurs (la vue) que le modèle a changé
	}
	
	/**
	 * Supprime l'artefact de la liste et supprime également toutes ses apparitions sur la scène
	 * @param idArtefact id de l'artefact à supprimer
	 */
	public void supprimerArtefact(String idArtefact) {
		String id = this.getMapID(idArtefact);
		ArrayList<Element> temp = new ArrayList<Element>();
		Iterator it = this.elementsScene.iterator();
		while (it.hasNext()) {
			Element courant = (Element)it.next();
			if (!id.equals(courant.getAttributeValue("id"))) {
				temp.add(courant);
			}
		}
		this.elementsScene = temp;
		int i = 0;
		boolean supr = false;
		while (i < this.artefacts.size() && !supr) {
			if (this.artefacts.get(i).getAttributeValue("id").equals(idArtefact)) {
				this.artefacts.remove(i);
				this.elementsScene.get(i);
				supr = true;
			}
			i++;
		}
	}
	
	/**
	 * Rajoute un objet dans le mapping
	 * @param objet L'objet à ajouter
	 */
	public void ajouterObjetMapping(Element objet) { //Ajoute objet au mapping
		this.mapping.add(objet);
		this.setChanged(); //On indique aux observeurs (la vue) que le modèle a changé
	}
	
	/**
	 * 
	 * @return Le nombre d'objets enregistrés dans le XML
	 */
	public int getNbObjets() {
		return this.mapping.size();
	}
	
	/**
	 * Ajouter un objet au mapping en lui passant les attributs de l'artefact et de l'agent en valeurs <br />
	 * Rajoute également en même temps automatiquement l'artefact à la liste des artefacts
	 * @param idArtefact id (nom) de l'artefact
	 * @param urlRelativeArtefact adresse relative menant au fichier de l'artefact (ex : fichier de texture)
	 * @param idAgent id (nom) de l'agent
	 * @param scriptAgent script : nom du fichier Java dans le code de l'utilisateur
	 */
	public void ajouterObjet(String idArtefact, String urlRelativeArtefact, String idAgent, String scriptAgent) {
		Element objet = new Element("objet");
		objet.setAttribute(new Attribute("id",""+(this.getNbObjets()+1)));
		
		//Création et ajout de la balise artefact
		Element artefact = new Element("artefact");
		Attribute id = new Attribute("id", idArtefact);
		Attribute image = new Attribute("image", urlRelativeArtefact);
		ArrayList<Attribute> a = new ArrayList<Attribute>();
		a.add(id);
		a.add(image);
		artefact.setAttributes(a);
		objet.addContent(artefact);
		this.ajouterArtefactDansSaListe(idArtefact, urlRelativeArtefact);
		
		//Création et ajout de la balise agent
		Element agent = new Element("agent");
		id = new Attribute("id", idAgent);
		Attribute script = new Attribute("script", scriptAgent);
		a.clear();
		a.add(id);
		a.add(script);
		agent.setAttributes(a);
		objet.addContent(agent);

		this.ajouterObjetMapping(objet);
	}
	
	/**
	 * Supprime l'objet du projet
	 * @param id id de l'objet à supprimer
	 */
	public void supprimerObjet(int id) {	// Supprime toute présence de l'objet
		int i = 0;
		boolean supr = false;
		while (i < this.mapping.size() && !supr) {
			if (this.mapping.get(i).getAttributeValue("id").equals(""+id)) {
				this.supprimerArtefact(this.mapping.get(i).getChild("artefact").getAttributeValue("id"));
				this.mapping.remove(this.mapping.get(i));
				supr = true;
			}
			i++;
		}
	}
	
	public String getMapID(String s) {
		String ret = "";
		Iterator i = this.mapping.iterator();

		while (i.hasNext()) {
			Element courant = (Element)i.next();
			Iterator j = courant.getChildren().iterator();
			Element Courant = (Element)j.next();
			
			if (Courant.getAttributeValue("id").equals(s)){        
				Courant = (Element)j.next();
				ret = courant.getAttributeValue("id");
			}
		}                
		return ret;
	}
	
	/**
	 * @return Le nom des artefacts de la scène
	 */
	public ArrayList<String> getNomArtefacts() {
		//List<Element> elem = this.getElements();
		ArrayList<String> str = new ArrayList<String>();
		for (int i=0; i<this.mapping.size(); i++) {
			str.add(this.mapping.get(i).getChild("artefact").getAttributeValue("id"));
		}
		return str;
	}
	
	/**
	 * Permet à partir d'une balise objet de type scene de récupérer l'id de l'artefact qui lui correspond dans le mapping
	 * @param objet L'Element objet contenu dans la balise scene
	 * @return L'id de l'artefact correspondant à cet objet
	 */
	public String getIdArtefactFilsScene(Element objet) {
		int id = Integer.parseInt(objet.getAttributeValue("id"));
		for (Element objetMapped : this.mapping) {
			int idMapped = Integer.parseInt(objetMapped.getAttributeValue("id"));
			if (id == idMapped) {
				return objetMapped.getChild("artefact").getAttributeValue("id");
			}
		}
		return "Erreur !";
	}
	
	/**
	 * Rajoute un objet dans la scene
	 * @param objet L'objet à ajouter
	 */
	public void ajouterObjetScene(Element objet) {
		this.elementsScene.add(objet);
		this.setChanged(); //On indique aux observeurs (la vue) que le modèle a changé
	}
	
	/**
	 * Retire un objet de la scene
	 * @param objet L'objet à retirer
	 */
	public void retirerObjetScene(Element objet) {
		this.elementsScene.remove(objet);
		this.setChanged(); //On indique aux observeurs (la vue) que le modèle a changé
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
	
	/**
	 * Retirer l'objet spécifier de la scène
	 * @param id id du mapping de l'objet à retirer de la scène
	 * @param x Coordonnée x sur la scene
	 * @param y Coornonnée y sur la scène
	 */
	public void retirerObjetDeScene(int id, int x, int y) {
		for (int i = 0; i < this.elementsScene.size(); i++) {
			if (this.elementsScene.get(i).getAttributeValue("id").equals(""+id) && this.elementsScene.get(i).getAttributeValue("x").equals(""+x) && this.elementsScene.get(i).getAttributeValue("y").equals(""+y)) {
				this.retirerObjetScene(this.elementsScene.get(i));
			}
		}		
	}
	
	public void updateGrid(){
		notifyObservers();
	}
	
}
