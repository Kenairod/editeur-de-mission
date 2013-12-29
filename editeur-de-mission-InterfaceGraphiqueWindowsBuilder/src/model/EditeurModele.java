package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Permet de modéliser les éléments nécessaires à la modélisation d'une scène
 * @author Da Dream Team
 *
 */
public class EditeurModele {
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
     public void createXML() {	              
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
	         for (Element elem : this.elementsScene) {
	        	 elements.addContent(elem);
	         }
	         scene.addContent(elements);
	         
	         //Comme pour elements mais pour artefacts
	         Element artefacts = new Element("artefacts");
	         for (Element artef : this.artefacts) {
	        	 elements.addContent(artef);
	         }
	         
	         //Idem
	         Element mapping = new Element("mapping");
	         for (Element obj : this.mapping) {
	        	 elements.addContent(obj);
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
	         xmlOutput.output(document, System.out);
	         
	         //On génère
	         xmlOutput.setFormat(Format.getPrettyFormat());  
	         xmlOutput.output(document, new FileWriter("XMLgenere/test.xml"));  
	        
	     	} catch (IOException io) {  
	        	System.out.println(io.getMessage());  
	        }
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
