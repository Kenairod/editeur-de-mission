package main;


import fr.lirmm.game.Game;
import fr.lirmm.game.Core.LogLevel;
import fr.lirmm.game.agent.Agent;
import fr.lirmm.game.gdx.EngineLibgdx;

public class Main extends EngineLibgdx{

	@Override
	public void create() {
		super.create();
		Game.core.setLogLevel(LogLevel.DEBUG);
		Agent gameDirector = new Agent();
		gameDirector.addScript(new BootstrapSript());
		Game.agents.addAgent(gameDirector);
	}
}
