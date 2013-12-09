package factory;

public class Factory {

	
	public Object createAI(String type , String args){
		Object ret;
		getXML.GetXML XML = new getXML.GetXML(main.StartGame.MAINXML);
		
		if (type.equals(XML.getplayerAI()))
				ret = new ai.PlayerAI();
		else if (type.equals(XML.getmonsterAI()))
			ret = new ai.MonsterAI();
		else if (type.equals(XML.getbulletAI()))
			ret = new ai.BulletAI();
		
		return ret;
	}
}
