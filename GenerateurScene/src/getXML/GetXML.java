	package getXML;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;



public class GetXML {

	private String titre;
	private boolean gl20;
	private int largeur;
	private int hauteur;
	private boolean redimensionnable;
	private String imageFond;
	private List<Element> elementsScene;
	private List<Element> artefacts;
	private List<Element> agents;
	private List<Element> mapping;
	
	
	public GetXML(String fichier) {
		
		//La lecture se fait à l'aide d'une contructeur SAX
        SAXBuilder ConstructSAX = new SAXBuilder();
        //On récupère le fichier source
        File file = new File("xmlToParse/"+fichier);
		 
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
            Element agents = noeudRacine.getChild("agents");
            this.agents = agents.getChildren();
            Element mapping = noeudRacine.getChild("mapping");
            this.mapping = mapping.getChildren();
    }
    catch (JDOMException | IOException e) {
            e.printStackTrace();
    }
}

	public String toString() {
		
		String ret = "Nom du jeu : "+this.titre+"\n";
    	ret = ret +"========PropriÃ©tÃ©s de la fenÃªtre========\n";
    	ret = ret + "Utilise OpenGL 2.0 : "+this.gl20+"\n";
    	ret = ret + "Largeur : "+this.largeur+"\n";
    	ret = ret + "Hauteur : "+this.hauteur+"\n";
    	ret = ret + "Redimensionnable : "+this.redimensionnable+"\n";
    	ret = ret + "========PropriÃ©tÃ©s de la scÃ¨ne========\n";
    	ret = ret + "URL image de fond : "+this.imageFond+"\n";
    	Iterator i = this.elementsScene.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		ret = ret + "Objet de type : "+courant.getAttributeValue("id")+"\n";
    		ret = ret + "CoordonnÃ©e x : "+courant.getAttributeValue("x")+"\n";
    		ret = ret + "CoordonnÃ©e y : "+courant.getAttributeValue("y")+"\n\n";
    	}
    	
    	ret = ret + "========Artefacts enregistres========\n";
    	i = this.artefacts.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		ret = ret + "id de l'Artefact : "+courant.getAttributeValue("id")+"\n";
    		System.out.println(courant.getAttributeValue("id")+"\n");
    		if (courant.getAttributeValue("id").equals("monstre"))
    			ret = ret + "nombre de repetition de l'Artefact : "+courant.getAttributeValue("repet")+"\n";
    		ret = ret + "URL Image : "+courant.getAttributeValue("image")+"\n";
    		ret = ret + "Texte : "+courant.getAttributeValue("texte")+"\n";
    		ret = ret + "URL Son : "+courant.getAttributeValue("son")+"\n\n";
    	}
    	
    	ret = ret + "========Agents enregistres========\n";
    	i = this.agents.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		ret = ret + "id de l'Agent : "+courant.getAttributeValue("id")+"\n";
    		ret = ret + "URL du script : "+courant.getAttributeValue("script")+"\n\n";
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

	public boolean getGl20() {
		return gl20;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public boolean getRedimensionnable() {
		return redimensionnable;
	}

	public String getImageFond() {
		return imageFond;
	}

	public List<Element> getElementsScene() {
		return elementsScene;
	}

	public List<Element> getArtefacts() {
		return artefacts;
	}

	public List<Element> getAgents() {
		return agents;
	}
	
	public String getmonsterAI() {
		String ret = "";
		Iterator i = this.mapping.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		Iterator j = courant.getChildren().iterator();
    		Element Courant = (Element)j.next();
    		
    		if (Courant.getAttributeValue("id").equals("monstre")){	
	    		Courant = (Element)j.next();
	    		ret = Courant.getAttributeValue("script")+"\n";
    		}
	    }		
		return ret;
	}
	
	public String getbulletAI() {
		String ret = "";
		Iterator i = this.mapping.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		Iterator j = courant.getChildren().iterator();
    		Element Courant = (Element)j.next();
    		
    		if (Courant.getAttributeValue("id").equals("bullet")){	
	    		Courant = (Element)j.next();
	    		ret = Courant.getAttributeValue("script")+"\n";
    		}
	    }		
		return ret;
	}
	
	public String getplayerAI() {
		String ret = "";
		Iterator i = this.mapping.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		Iterator j = courant.getChildren().iterator();
    		Element Courant = (Element)j.next();
    		
    		if (Courant.getAttributeValue("id").equals("rambo")){	
	    		Courant = (Element)j.next();
	    		ret = Courant.getAttributeValue("script")+"\n";
    		}
	    }		
		return ret;
	}
	
	public int getNbMonsters() {
		Iterator i = this.artefacts.iterator();
    	int ret = 0;
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if (courant.getAttributeValue("id").equals("monstre"))
    			ret = Integer.parseInt(courant.getAttributeValue("repet"));
    	}
    	return ret;
	}
		
}
