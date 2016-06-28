package main;

import logic.GameDynamics;
import scene.Scene1Builder;
import ai.Mission;
import factory.MissionFactory;
import fr.lirmm.game.Game;
import fr.lirmm.game.action.scenes.SceneChangeAction;
import fr.lirmm.game.agent.Agent;
import fr.lirmm.game.agent.Script;
import fr.lirmm.game.scene.Scene;
import global.GameConstant;

public class BootstrapSript extends Script {


	
	public void onUpdate(int delta) {
				if( 
						Game.assets.isLoadedTexture(GameConstant.TEXTURE_BG)
						&&
						Game.assets.isLoadedTexture(GameConstant.TEXTURE_RAMBO)
						&&
						Game.assets.isLoadedTexture(GameConstant.TEXTURE_MONSTRE)
						&&
						Game.assets.isLoadedTexture(GameConstant.TEXTURE_EXPLOSION)
						&&
						Game.assets.isLoadedTexture(GameConstant.TEXTURE_BULLET)
				)
		{
			System.out.println("Textures has been loaded !");
			Game.actions.submit(new SceneChangeAction(GameConstant.SCENE_JEU));			
			Game.agents.removeAgent(this.agent);
			
		}
	}
	@Override
	public void onBegin() {
		super.onBegin();
		Game.assets.loadTexture(GameConstant.TEXTURE_BG);
		Game.assets.loadTexture(GameConstant.TEXTURE_RAMBO);
		Game.assets.loadTexture(GameConstant.TEXTURE_MONSTRE);
		Game.assets.loadTexture(GameConstant.TEXTURE_EXPLOSION);
		Game.assets.loadTexture(GameConstant.TEXTURE_BULLET);
		
		//Game.assets.loadFont(GameConstant.FONT_STANDARD);
		Scene scene = new Scene(GameConstant.SCENE_JEU);
		Agent sceneDirector = new Agent();
		sceneDirector.addScript(new Scene1Builder());
		sceneDirector.addScript(new GameDynamics());
		scene.addAgent(sceneDirector);
		MissionFactory.CreateMission();
		Game.scenes.addScene(scene);		
		
	}
}
