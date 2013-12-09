package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class StartGame {
		
	public static String MAINXML ="";
	
		public static void main(String[] args)
		{
			// création d'une fenetre pour recuperer le XML
			getXML.fenetreGet xml = new getXML.fenetreGet();
			MAINXML = xml.inXML; // MAINXML recupere le string du fichier xml afin de le réutiliser
			getXML.GetXML XML = new getXML.GetXML(MAINXML);
			
			LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
			System.out.println(XML);
			cfg.title = XML.getTitre();
			cfg.useGL20 = XML.getGl20();
			cfg.width = XML.getLargeur();
			cfg.height = XML.getHauteur();
			cfg.resizable = XML.getRedimensionnable();
			new LwjglApplication(new Main(), cfg);	
		}
	}

