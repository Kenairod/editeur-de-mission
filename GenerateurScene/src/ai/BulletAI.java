package ai;

import model.Bullet;
import controller.GameController;
import fr.lirmm.game.Game;
import fr.lirmm.game.action.Action;
import fr.lirmm.game.action.artefacts.MoveToAction;

public class BulletAI extends ActorAI<Bullet>{
	private Action plan;
	private int bulletid;
	
	public BulletAI(int id , Bullet b, float x, float y) 
	{
		super(b);
		MoveToAction a = new MoveToAction(this.actor.getMyArtefact(), 100, x,y);
		plan = a;
		this.bulletid = id;
	}
	
	public BulletAI() 
	{
		super(null);
	}
	
	public void setAI(int id , Bullet b, float x, float y){
		super.createAI(b);
		MoveToAction a = new MoveToAction(this.actor.getMyArtefact(), 100, x,y);
		plan = a;
		this.bulletid = id;
	}

	@Override
	public void onUpdate(int delta) {
		super.onUpdate(delta);
		if(!this.plan.isDone())
		{
			Game.actions.submit(this.plan);
		}
		else
		{
			//self termination
			//TODO should take the Bullet actor object to deactivate it
			GameController.getInstance().destroyBullet(bulletid);
			Game.agents.removeAgent(this);
		}
	}

}
