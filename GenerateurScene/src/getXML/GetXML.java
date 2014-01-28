package getXML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
	private List<Element> missions;
	private List<Element> mapping;
	
	
	public GetXML(String fichier) {
		
		//La lecture se fait � l'aide d'une contructeur SAX
        SAXBuilder ConstructSAX = new SAXBuilder();
        //On r�cup�re le fichier source
        File file = new File("xmlToParse"+"/"+fichier);
        System.out.println(file.toString()); 
        try {
            //On convertit le fichier en objet Document � l'aide du constructeur SAX
            Document document = ConstructSAX.build(file);
            //On r�cup�re le noeud racine
            Element noeudRacine = document.getRootElement();
           
            //On r�cup�re le texte contenu dans la balise pass�e en param�tre
            this.titre = noeudRacine.getChildText("titre-du-jeu");
           
            //On r�cup�re le fils de noeudRacine qui s'appelle fenetre
            Element fenetre = noeudRacine.getChild("fenetre");
            //On r�cup�re le texte contenu dans la balise useGL20 contenue dans fen�tre
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
            //On r�cup�re la valeur de l'attribue image de la balise fond contenue dans la balise scene
            this.imageFond = fond.getAttributeValue("image");
           
            Element elements = scene.getChild("elements");
            this.elementsScene = elements.getChildren();
            Element artefacts = noeudRacine.getChild("artefacts");
            this.artefacts = artefacts.getChildren();
            Element mapping = noeudRacine.getChild("mapping");
            this.mapping = mapping.getChildren();
            Element missions = noeudRacine.getChild("missions");
            this.missions = missions.getChildren(); 
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
    	
    	ret = ret + "========Artefacts enregistres========\n";
    	i = this.artefacts.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		ret = ret + "id de l'Artefact : "+courant.getAttributeValue("id")+"\n";
    		System.out.println(courant.getAttributeValue("id")+"\n");
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
    	
    	ret = ret + "========Missions========\n";
    	
    	i = this.missions.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		ret = ret + "id de la mission : "+courant.getAttributeValue("id")+"\n";
    		System.out.println(courant.getAttributeValue("id")+"\n");
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
	
	public List<Element> getMissions() {
		return missions;
	}

	public ArrayList<Integer> getMissionsStart(){
		ArrayList<Integer> ret = new ArrayList<Integer>();;
		Iterator<Element> i = this.getMissions().iterator();	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		ret.add(Integer.parseInt(courant.getAttributeValue("id")));
    		System.out.println(ret.toString());
    	}
		return ret;
	}
	
	public String recupSkinPlayer(){
		String ret="";
		Iterator<Element> i = getArtefacts().iterator();	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if ( courant.getAttributeValue("id").equals("rambo")){
    			ret = courant.getAttributeValue("image");
    		}
    	}
		return (String) ret;
	}
	
	public String recupSkin(String s){
		String ret="";
		Iterator<Element> i = this.getArtefacts().iterator();	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if ( courant.getAttributeValue("id").equals(s)){
    			ret = courant.getAttributeValue("image");
    		}
    	}
		return ret;
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
	
	public String getAI(String s) { // retourne un string contenant le nom de la classe possedant l'AI du string s dans le mapping
		String ret = "";
		Iterator i = this.mapping.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		Iterator j = courant.getChildren().iterator();
    		Element Courant = (Element)j.next();
    		
    		if (Courant.getAttributeValue("id").equals(s)){	
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
	
	public String getMapID(String s) { // retourne un string contenant l'id du string en parametre dans le mapping
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
	
	public String getMapIDAI(String s) { // retourne un string avec l'id dans le mapping de l'AI pass�e en parametre
		String ret = "";
		Iterator i = this.mapping.iterator();
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		Iterator j = courant.getChildren().iterator();
    		Element Courant = (Element)j.next();
    		Courant = (Element)j.next();
    		
    		if (Courant.getAttributeValue("script").equals(s)){	
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
	
	public int getNbIterations(String s) { // retourne un int du nombre de monstres instanci�s dans les objets
		Iterator i = this.elementsScene.iterator();
    	int ret = 0;
    	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if (courant.getAttributeValue("id").equals(getMapID(s)))
    			ret++;
    	}
    	return ret;
    }
	
	public String getPos(String s) {  // retourne un string de la suite de la position des objets instanci�s ayant le nom donn� dans le string
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
	
	public float getPlayerx() {  // retourne un string de la position du joueur instanci� dans les objets
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
    	Scanner sc = new Scanner(temp); // Scanner sur le temp des pos de cr�ation du joueur
		ret = (float) sc.nextInt();
    	return ret;	
	}
	
	public float getPlayery() {  // retourne un string de la position du joueur instanci� dans les objets
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
    	Scanner sc = new Scanner(temp); // Scanner sur le temp des pos de cr�ation du joueur
    	sc.nextInt();
		ret = (float) sc.nextInt();
    	return ret;	
	}
	
	public String gettoLoad( String s ){
		
		String toLoad ="";
		int nbIter = this.getNbIterations(s); // lis le fichier XML et recupere repet de l'artefact s
		Scanner sc = new Scanner(this.getPos(s)); // Scanner sur le string des pos de cr�ation de s
		
		for (int i = 0; i < nbIter ; i++ ){ // boucle pour generer le string de cr�ation
			toLoad = toLoad + s + " ";
			int x = sc.nextInt();
			int y = sc.nextInt();
			toLoad = toLoad + x + " " + y +" ;";
		}
		sc.close();
		
		return toLoad;
	}
	
}
