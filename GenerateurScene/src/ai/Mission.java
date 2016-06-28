package ai;

import java.util.ArrayList;

import fr.lirmm.game.agent.Agent;

public abstract class Mission extends Agent{

	protected Boolean succeded = false;
	protected Boolean failed = false;
	int numMission;
	public static int bulShot = 0;
	public static int Mkilled = 0;
	public static ArrayList<Mission> Missionslist = new ArrayList<Mission>();
	public static long t0 = System.currentTimeMillis();
	
	
	public Mission(int a){
		Missionslist.add(0,null);
		numMission = a;
		Missionslist.add(numMission,this);
	}
	
	public int getnumMission(){
		return numMission;
	}
	
	public void resetMkilled(){
		Mkilled = 0;
	}
	
	public void resetbulShot(){
		bulShot = 0;
	}
	
	public void resett0(){
		t0 = System.currentTimeMillis();
	}
	
	public void onBegin(){
		
		System.out.println("mission n: "+numMission);
	}
	
	public void onUpdate(int delta) {
		super.onUpdate(delta);
		
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
	
	public abstract boolean Succes();
	
	public abstract boolean Failure();
	
	public abstract boolean onSucces();
		
	public abstract boolean onFailure();
}
