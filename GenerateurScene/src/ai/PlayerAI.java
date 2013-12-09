package ai;

import model.Rambo;
import controller.GameController;
import fr.lirmm.game.Game;
import fr.lirmm.game.agent.Agent;
import fr.lirmm.game.artefact.Artefact;
import fr.lirmm.game.artefact.Attr;
import fr.lirmm.game.input.device.Keyboard.Key;
import fr.lirmm.game.input.device.Mouse.Button;
import fr.lirmm.game.input.listener.KeyboardListener;
import fr.lirmm.game.input.listener.MouseListener;
import global.GameConstant;

public class PlayerAI extends ActorAI<Rambo> implements KeyboardListener, MouseListener{
	private float velocity ; 
	private float dx = 0;
	private float dy = 0;
	
	public PlayerAI(Rambo a)
	{
		super(a);
		this.velocity = 2.f;
	}	

	@Override
	public void onBegin() {
		super.onBegin();
		Game.input.addKeyboardListener(this);
		Game.input.addMouseListener(this);
	}

	@Override
	public void onEnd() {
		super.onEnd();
		Game.input.removeKeyboardListener(this);
		Game.input.removeMouseListener(this);
		
	}
	@Override
	public void onUpdate(int delta) {
		super.onUpdate(delta);
		float currentX = this.actor.getMyArtefact().getFloat(Attr.X);
		float currentY = this.actor.getMyArtefact().getFloat(Attr.Y);
		currentX += velocity * dx;
		currentY += velocity * dy;
		this.actor.getMyArtefact().setFloat(Attr.X, currentX);
		this.actor.getMyArtefact().setFloat(Attr.Y, currentY);		
	}
	@Override
	public void onKeyDown(Key arg0) 
	{
	int v = 1;
	switch (arg0) {
	case LEFT:
		dx -= v;
		break;
	case RIGHT:
		dx += v;
		break;
	case UP:
		dy -= v;
		break;
	case DOWN:
		dy += v;
		break;
	default:
		break;
	}	
	}

	@Override
	public void onKeyTyped(char arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyUp(Key arg0) {
		int v = -1;
		switch (arg0) {
		case LEFT:
			dx -= v;
			break;
		case RIGHT:
			dx += v;
			break;
		case UP:
			dy -= v;
			break;
		case DOWN:
			dy += v;
			break;
		default:
			break;
		}	
		
	}

	@Override
	public void onMouseDown(int arg0, int arg1, Button arg2) {
		if(arg2 == Button.LEFT_BUTTON)
		{
			GameController.getInstance().createBullet(
					this.actor.getMyArtefact().getFloat(Attr.X), 
					this.actor.getMyArtefact().getFloat(Attr.Y), arg0, arg1, 100.f);
		}
		
	}

	@Override
	public void onMouseDrag(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMove(int arg0, int arg1) {
		float deltax = arg0 -  this.actor.getMyArtefact().getFloat(Attr.X);
		float deltay = arg1 -  this.actor.getMyArtefact().getFloat(Attr.Y);
		float angle = -GameConstant.pi/2;
		angle += (float)Math.atan2(deltax, deltay);
		this.actor.getMyArtefact().setFloat(Attr.ROTATION, (float)Math.toDegrees(angle));
	}

	@Override
	public void onMouseScroll(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseUp(int arg0, int arg1, Button arg2) {
		// TODO Auto-generated method stub
		
	}
}
