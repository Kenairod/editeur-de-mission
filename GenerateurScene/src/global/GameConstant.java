package global;

import main.StartGame;

public class GameConstant {

	
	public static final String FONT_STANDARD = "";
	public static final String SCENE_TITLE = StartGame.XML.getTitre();
	public static final String SCENE_JEU = "Game";
    public static final String TEXTURE_BG = StartGame.XML.getImageFond();
	public static final String TEXTURE_RAMBO = StartGame.XML.recupSkinPlayer();
	public static final String TEXTURE_MONSTRE = StartGame.XML.recupSkin("monster");
	public static final String TEXTURE_EXPLOSION = StartGame.XML.recupSkin("explosion");
	public static final String TEXTURE_BULLET = StartGame.XML.recupSkin("bullet");

	public static final float pi = 3.14156f;
	
	
}
