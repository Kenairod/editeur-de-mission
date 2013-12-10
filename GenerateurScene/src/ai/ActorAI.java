package ai;

import model.Bullet;
import fr.lirmm.game.agent.Agent;

public class ActorAI<A> extends Agent {

	A actor;
	
	ActorAI(A a)
	{
		this.actor = a;
	}
	
	public void createAI(A a){
		this.actor = a;
	}
}
