package model;

import fr.lirmm.game.Game;
import fr.lirmm.game.artefact.Artefact;
import fr.lirmm.game.artefact.Attr;
import fr.lirmm.game.artefact.content.ContentType;
import global.GameConstant;

public class Rambo extends GameActor
{

	public Rambo(Artefact art) {
		super(art);
	}

	public Rambo()
	{
		this(buildArtefact());
	}


	private static Artefact buildArtefact() {
		Artefact artefact = Game.artefacts.create("rambo", ContentType.SPRITE, GameConstant.TEXTURE_RAMBO);
		artefact.setFloat(Attr.X, 0.f);
		artefact.setFloat(Attr.Y, 0.f);
		artefact.setFloat(Attr.WIDTH, (float)64);
		artefact.setFloat(Attr.HEIGHT,(float)64);
		return artefact;
	}
}
