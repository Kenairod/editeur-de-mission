package factory;


	import java.util.ArrayList;

import fr.lirmm.game.Game;
import fr.lirmm.game.agent.Agent;

public class MissionFactory extends Agent{
	
	public static void CreateMission(){
		
		ArrayList<Integer> list = main.StartGame.XML.getMissionsStart();
		
        
        for (int i = 0; i < list.size(); i++){
        	try{
        	Agent A = (Agent) Class.forName("missions.Mission"+i).newInstance();
        	Game.scenes.getCurrentScene().addAgent(A);
        	}
        	catch (Exception e) {}
        }
		/*if (nMission == 1)
			ret = new missions.Mission1();
		
		if (nMission == 2)
			ret = new missions.Mission2();
		*/
	}
	
			
}
