package missions;

import ai.Mission;
import factory.MissionFactory;
import fr.lirmm.game.Game;
import fr.lirmm.game.agent.Agent;


public final class Mission1 extends ai.Mission {

	
	public Mission1(){
		super(1);
	}

	@Override
	public boolean Succes() {
		boolean ret = false;
		if (Mkilled == 6)
			ret = true;
		return ret;
	}

	@Override
	public boolean Failure() {
		boolean ret = false;
		if (bulShot >= 15)
			ret = true;
		return ret;
	}

	public void onBegin(){
		super.onBegin();
		resetMkilled();
		resetbulShot();
	}
	
	@Override
	public boolean onSucces() {

		System.out.println("SUCCES M1, GAME WON");
		return true;
	}

	@Override
	public boolean onFailure() {
		
		System.out.println("FAILURE M1");
		return true;
		
	}
	
	
}
