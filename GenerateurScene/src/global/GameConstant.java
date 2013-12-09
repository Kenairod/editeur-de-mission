package global;

import java.util.Iterator;

import main.StartGame;

import org.jdom2.Element;

public class GameConstant {

	private static getXML.GetXML XML = new getXML.GetXML(StartGame.MAINXML); // lis le xML chargé dans le main
	public static final String FONT_STANDARD = "";
	public static final String SCENE_TITLE = XML.getTitre();
	public static final String SCENE_JEU = "Game";
    public static final String TEXTURE_BG = XML.getImageFond();
	public static final String TEXTURE_RAMBO = recupSkinPlayer();
	public static final String TEXTURE_MONSTRE = recupSkinMonster();
	public static final String TEXTURE_EXPLOSION = recupSkinExplo();
	public static final String TEXTURE_BULLET = recupSkinBullet();

	public static final float pi = 3.14156f;
	
	
	public static String recupSkinPlayer(){
		String ret="";
		Iterator<Element> i = XML.getArtefacts().iterator();	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if ( courant.getAttributeValue("id").equals("rambo")){
    			ret = courant.getAttributeValue("image");
    		}
    	}
		return (String) ret;
	}
	
	public static String recupSkinMonster(){
		String ret="";
		Iterator<Element> i = XML.getArtefacts().iterator();	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if ( courant.getAttributeValue("id").equals("monstre")){
    			ret = courant.getAttributeValue("image");
    		}
    	}
		return ret;
	}
	
	public static String recupSkinExplo(){
		String ret="";
		Iterator<Element> i = XML.getArtefacts().iterator();	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if ( courant.getAttributeValue("id").equals("explosion")){
    			ret = courant.getAttributeValue("image");
    		}
    	}
		return (String) ret;
	}
	
	public static String recupSkinBullet(){
		String ret="";
		Iterator<Element> i = XML.getArtefacts().iterator();	
    	while (i.hasNext()) {
    		Element courant = (Element)i.next();
    		if ( courant.getAttributeValue("id").equals("bullet")){
    			ret = courant.getAttributeValue("image");
    		}
    	}
		return (String) ret;
	}
}
