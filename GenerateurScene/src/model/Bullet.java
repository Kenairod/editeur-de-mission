package model;

import fr.lirmm.game.Game;
import fr.lirmm.game.artefact.Artefact;
import fr.lirmm.game.artefact.Attr;
import fr.lirmm.game.artefact.content.ContentType;
import global.GameConstant;


public class Bullet extends GameActor{

	private static int counter;

	private static Artefact buildArtefact() {
		Artefact artefact = Game.artefacts.create("monster"+counter++, ContentType.SPRITE, GameConstant.TEXTURE_BULLET);
		artefact.setFloat(Attr.X, 0.f);
		artefact.setFloat(Attr.Y, 0.f);
		artefact.setFloat(Attr.WIDTH, (float)32);
		artefact.setFloat(Attr.HEIGHT,(float)32);
		return artefact;
	}
	public Bullet(Artefact art) {
		super(art);
		// TODO Auto-generated constructor stub
	}

	public Bullet()
	{
		this(buildArtefact());
	}
	public void setRotation(float heading) {
		this.rotation = heading;
		
	}
}
