package model;

import fr.lirmm.game.artefact.Artefact;
import fr.lirmm.game.artefact.Attr;

public abstract class GameActor {
	
	protected boolean active;
	protected int id;
	protected float x;
	protected float y;
	protected float rotation;
	
	public void setPosition(float x,float y)
	{
		this.x = x;
		this.y = y;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	protected Artefact myArtefact ;	
	public GameActor(Artefact art)
	{
		this.myArtefact = art;
	}
	public Artefact getMyArtefact() {
		return myArtefact;
	}
	public void setMyArtefact(Artefact myArtefact) {
		this.myArtefact = myArtefact;
	}
	
	public void activate()
	{
	Artefact art = this.getMyArtefact();
	art.setFloat(Attr.X, this.x);
	art.setFloat(Attr.Y, this.y);
	art.setFloat(Attr.ROTATION, this.rotation);
	
	this.setActive(true);
	};
	
	public void deactivate()
	{
		this.setActive(false);
	}
	
}
