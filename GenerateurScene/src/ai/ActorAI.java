package ai;

import fr.lirmm.game.agent.Agent;

public class ActorAI<A> extends Agent {

	A actor;
	
	ActorAI(A a)
	{
		this.actor = a;
	}
}
