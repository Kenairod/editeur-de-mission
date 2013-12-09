package scene;

import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import main.StartGame;
import controller.GameController;
import fr.lirmm.game.Game;
import fr.lirmm.game.agent.Script;
import fr.lirmm.game.artefact.Artefact;

public class Scene1Builder extends Script{

	@Override
	public void onUpdate(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBegin() {
		super.onBegin();
		if(Game.scenes.getCurrentScene()!=null)
		{
			List<Artefact> art = Game.scenes.getCurrentScene().getArtefacts().getArtefacts();
			for (Artefact artefact : art) {
				Game.scenes.getCurrentScene().removeArtefact(artefact);
			}
		}
		
		Random random = new Random();
		String toLoad = "";
		int x = 0;
		int y = 0;
		getXML.GetXML XML = new getXML.GetXML(StartGame.MAINXML); // lis le fichier XML
		int nbMonsters = XML.getNbMonsters(); // recupere repet de l'artefact monstre
		for (int i = 0; i<nbMonsters ; i++){ // boucle pour generer le string de création
			toLoad = toLoad + "monster ";
			x = random.nextInt(400);
			y = random.nextInt(300);
			toLoad = toLoad + x + " " + y +" ;";
		}
		loaddLevel(toLoad); // charge le nombr de monstres demandés
		GameController.getInstance().createRambo();
	}

	private void loaddLevel(String level) 
	{
		
		StringTokenizer st = new StringTokenizer(level,";");
		
		while(st.hasMoreTokens())
		{
			String line = st.nextToken();
			StringTokenizer linetokens = new StringTokenizer(line, " ");
			String type = linetokens.nextToken();
			float x = Float.parseFloat(linetokens.nextToken());
			float y = Float.parseFloat(linetokens.nextToken());
			if("monster".equals(type))
			{
				GameController.getInstance().createMonster(x, y);
				Game.core.debug("Scene 1 Builder", "monster has been created at " +  x + " , " + y );
			}
		}
	}
}
