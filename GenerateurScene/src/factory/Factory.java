package factory;

import fr.lirmm.game.agent.Agent;

public class Factory {

	
	public static Agent createAI(String type) {
        Agent ret = null;
        String id;
        

        	id = main.StartGame.XML.getMapID(type);
        	System.out.println(main.StartGame.XML.getAI(type));
       
        if (id.equals(main.StartGame.XML.getMapIDAI("PlayerAI"))) {
        	String AI = "ai.PlayerAI";
        	String AI2 = "ai."+main.StartGame.XML.getAI(type);
        	System.out.println(AI);
        	System.out.println(AI2);
        	try
            {
              ret = (Agent) Class.forName(AI).newInstance ();
            }
            catch (Exception e)
            {}
        }
        
        if (id.equals(main.StartGame.XML.getMapIDAI("MonsterAI"))) {
        	try
            {
              ret = (Agent) Class.forName("ai.MonsterAI").newInstance ();
            }
            catch (Exception e)
            {}
        }
        
        if (id.equals(main.StartGame.XML.getMapIDAI("BulletAI"))) {
        	try
            {
              ret = (Agent) Class.forName("ai.BulletAI").newInstance ();
            }
            catch (Exception e)
            {}
        }
		
		return ret;
	}
}
