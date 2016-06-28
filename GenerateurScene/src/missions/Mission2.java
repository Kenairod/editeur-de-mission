package missions;

import ai.Mission;
import factory.MissionFactory;
import fr.lirmm.game.Game;
import fr.lirmm.game.agent.Agent;

public class Mission2 extends ai.Mission{
	public static long timer = 0;
	public Mission2(){
		super(2);
	}
	
	public void onUpdate(int delta) {
		super.onUpdate(delta);
		Timer();
		if (Succes() && !failed ){
			if (!succeded){
				succeded = onSucces();
			}
		}
		else if (Failure() && !succeded){
			if (!failed){
				failed = onFailure();
			}
		}
	
	}
	
	public void onBegin(){
		super.onBegin();
		resetMkilled();
		resett0();
	}
	
	
	public void Timer(){
		timer = (System.currentTimeMillis() - t0)/1000;
	}

	@Override
	public boolean Succes() {
		boolean ret = false;
		if (Mkilled == 10)
			ret = true;
		return ret;
	}

	@Override
	public boolean Failure() {
		boolean ret = false;
		if (timer >= 10)
			ret = true;
		return ret;
	}

	@Override
	public boolean onSucces() {

		System.out.println("SUCCES M2");
		return true;
	}

	@Override
	public boolean onFailure() {
		
		System.out.println("FAILLURE M2");
		return true;
		
	}
	
	
}


