package factory;

public class Factory {

	
	public static Object createAI(String type){
		Object ret = null;
		getXML.GetXML XML = new getXML.GetXML(main.StartGame.MAINXML);
		
		if (type.equals(XML.getplayerAI()))
				ret = new ai.PlayerAI(null);
		else if (type.equals(XML.getmonsterAI()))
			ret = new ai.MonsterAI(null, null, null, null);
		else if (type.equals(XML.getbulletAI()))
			ret = new ai.BulletAI(0, null, 0, 0);
		
		return ret;
	}
}
