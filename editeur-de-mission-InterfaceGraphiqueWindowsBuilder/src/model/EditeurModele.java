package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

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
    	 this.titre = "";
    	 this.gl20 = false;
    	 this.largeur = 0;
    	 this.hauteur = 0;
    	 this.redimensionnable = false;
    	 this.imageFond = "";
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
     public EditeurModele(String titre,boolean gl20,int largeur,int hauteur, boolean redimensionnable, String imageFond,
			List<Element> elementsScene, List<Element> artefacts,List<Element> mapping) {
		this();
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
     public void createXML(String pathOut) {	              
	     try {
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
	        	 elem.detach();
	        	 elements.addContent(elem);
	         }
	         scene.addContent(elements);
	         
	         //Comme pour elements mais pour artefacts
	         Element artefacts = new Element("artefacts");
	         for (int i = 0; i < this.artefacts.size(); i++) {
	        	 Element artef = this.artefacts.get(i).clone();
	        	 artef.detach();
	        	 artefacts.addContent(artef);
	         }
	         
	         //Idem
	         Element mapping = new Element("mapping");
	         for (int i = 0; i < this.mapping.size(); i++) {
	        	 Element obj = this.mapping.get(i).clone();
	        	 obj.detach();
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
	         xmlOutput.output(document, new FileWriter("./"+pathOut+".xml"));  
	        
	     	} catch (IOException io) {  
	        	System.out.println(io.getMessage());  
	        }
     }
     
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
             System.out.println(courant.getAttributeValue("id")+"\n");
             if (courant.getAttributeValue("id").equals("monster"))
                     ret = ret + "nombre de repetition de l'Artefact : "+courant.getAttributeValue("repet")+"\n";
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
	 
	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public boolean isGl20() {
		return gl20;
	}



	public void setGl20(boolean gl20) {
		this.gl20 = gl20;
	}



	public int getLargeur() {
		return largeur;
	}



	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}



	public int getHauteur() {
		return hauteur;
	}



	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}



	public boolean isRedimensionnable() {
		return redimensionnable;
	}



	public void setRedimensionnable(boolean redimensionnable) {
		this.redimensionnable = redimensionnable;
	}



	public String getImageFond() {
		return imageFond;
	}



	public void setImageFond(String imageFond) {
		this.imageFond = imageFond;
	}



	public List<Element> getElementsScene() {
		return elementsScene;
	}



	public void setElementsScene(List<Element> elementsScene) {
		this.elementsScene = elementsScene;
	}



	public List<Element> getArtefacts() {
		return artefacts;
	}



	public void setArtefacts(List<Element> artefacts) {
		this.artefacts = artefacts;
	}



	public List<Element> getMapping() {
		return mapping;
	}



	public void setMapping(List<Element> mapping) {
		this.mapping = mapping;
	}
}
