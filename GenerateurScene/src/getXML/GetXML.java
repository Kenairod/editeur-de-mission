	package getXML;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import main.StartGame;

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

	
	public String getmonsterAI() { // retourne un string contenant le nom de la classe possedant l'AI du monstre dans le mapping
		String ret = "";
		Iterator i = this.mapping.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		Iterator j = courant.getChildren().iterator();
    		Element Courant = (Element)j.next();
    		
    		if (Courant.getAttributeValue("id").equals("monster")){	
	    		Courant = (Element)j.next();
	    		ret = Courant.getAttributeValue("script")+"\n";
    		}
	    }		
		return ret;
	}
	
	public String getbulletAI() { // retourne un string contenant le nom de la classe possedant l'AI de la balle dans le mapping
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
	
	public String getplayerAI() { // retourne un string contenant le nom de la classe possedant l'AI du joueur dans le mapping
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
	
	public String getMapID(String s) { // retourne un string contenant l'id du monstre dans le mapping
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
	
	public String getplayerMapID() { // retourne un string contenant l'id du joueur dans le mapping
		String ret = "";
		Iterator i = this.mapping.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		Iterator j = courant.getChildren().iterator();
    		Element Courant = (Element)j.next();
    		
    		if (Courant.getAttributeValue("id").equals("rambo")){	
	    		Courant = (Element)j.next();
	    		ret = courant.getAttributeValue("id");
    		}
	    }		
		return ret;
	}
	
	public int getNbIterations(String s) { // retourne un int du nombre de monstres instanciés dans les objets
		Iterator i = this.elementsScene.iterator();
    	int ret = 0;
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if (courant.getAttributeValue("id").equals(getMapID(s)))
    			ret++;
    	}
    	return ret;
    }
	
	public String getPos(String s) {  // retourne un string de la suite de la position des objets instanciés ayant le nom donné dans le string
		String ret ="";
		Iterator i = this.elementsScene.iterator();

    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		
    		if (courant.getAttributeValue("id").equals(getMapID(s))){
    			ret = ret + courant.getAttributeValue("x") + " ";
    			ret = ret + courant.getAttributeValue("y") + " ";
    		}
    	}
    	return ret;	
	}
	
	public float getPlayerx() {  // retourne un string de la position du joueur instancié dans les objets
		String temp ="";
		float ret;
		Iterator i = this.elementsScene.iterator();

    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		
    		if (courant.getAttributeValue("id").equals(getplayerMapID())) {
    			temp = temp + courant.getAttributeValue("x") + " ";
    			temp = temp + courant.getAttributeValue("y") + " ";
    		}
    	}
    	Scanner sc = new Scanner(temp); // Scanner sur le temp des pos de création du joueur
		ret = (float) sc.nextInt();
    	return ret;	
	}
	
	public float getPlayery() {  // retourne un string de la position du joueur instancié dans les objets
		String temp ="";
		float ret;
		Iterator i = this.elementsScene.iterator();

    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		
    		if (courant.getAttributeValue("id").equals(getplayerMapID())) {
    			temp = temp + courant.getAttributeValue("x") + " ";
    			temp = temp + courant.getAttributeValue("y") + " ";
    		}
    	}
    	Scanner sc = new Scanner(temp); // Scanner sur le temp des pos de création du joueur
    	sc.nextInt();
		ret = (float) sc.nextInt();
    	return ret;	
	}
	
	public String gettoLoad( String s ){
		
		String toLoad ="";
		int nbMonsters = StartGame.XML.getNbIterations(s); // lis le fichier XML et recupere repet de l'artefact s
		Scanner sc = new Scanner(StartGame.XML.getPos(s)); // Scanner sur le srting des pos de création de s
		
		for (int i = 0; i < nbMonsters ; i++ ){ // boucle pour generer le string de création
			toLoad = toLoad + s + " ";
			int x = sc.nextInt();
			int y = sc.nextInt();
			toLoad = toLoad + x + " " + y +" ;";
		}
		
		return toLoad;
	}
	
	
}
