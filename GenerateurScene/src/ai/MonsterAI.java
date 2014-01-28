package ai;

import model.Monster;
import fr.lirmm.game.Game;
import fr.lirmm.game.action.Action;
import fr.lirmm.game.action.SequentialAction;
import fr.lirmm.game.action.artefacts.MoveToAction;
import fr.lirmm.game.action.delegate.RepeatAction;

public class MonsterAI extends ActorAI<Monster> {
	private Action plan;
	
	public MonsterAI(Monster m, int[] duration, float[] pathx, float[] pathy) 
	{
		super(m);
		SequentialAction seq= new SequentialAction();
		for (int i = 0; i < duration.length; i++) {
			MoveToAction a = new MoveToAction(this.actor.getMyArtefact(), duration[i],pathx[i],pathy[i]);
			seq.addAction(a);
		}
		plan = new RepeatAction(seq, -1);
	}
	
	public MonsterAI() 
	{
		super(null);
	}
	
	public void setAI(Monster m, int[] duration, float[] pathx, float[] pathy) 
	{
		super.createAI(m);
		SequentialAction seq= new SequentialAction();
		for (int i = 0; i < duration.length; i++) {
			MoveToAction a = new MoveToAction(this.actor.getMyArtefact(), duration[i],pathx[i],pathy[i]);
			seq.addAction(a);
		}
		plan = new RepeatAction(seq, -1);
	}

	@Override
	public void onUpdate(int delta) {
		super.onUpdate(delta);
		if(!this.plan.isDone())
		{
			Game.actions.submit(this.plan);
		}
	}
	
}
