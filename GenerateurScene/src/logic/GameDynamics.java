package logic;

import model.BenchGameModel;
import model.Bullet;
import model.Monster;
import controller.BenchGameController;
import controller.GameController;
import fr.lirmm.game.Game;
import fr.lirmm.game.agent.Script;
import fr.lirmm.game.artefact.Attr;

public class GameDynamics extends Script {

	@Override
	public void onUpdate(int arg0) {
		// TODO Auto-generated method stub
		checkCollision();
	}

	private void checkCollision() {
		Bullet[] bullets = GameController.getInstance().getGameModel().bulletsTable;
		Monster[] monsters = GameController.getInstance().getGameModel().monstersTable;
		
		//naive approach for collision without collision groups
		for(int i =0; i  < bullets.length; i ++)
		{
			if(bullets[i].isActive())
			{
				for(int j = 0 ; j < monsters.length;j++)
				{
				if(monsters[j].isActive())
				{
					if (checkCollision(bullets[i],monsters[j] ))
						{
						monsters[j].deactivate();
						Game.scenes.getCurrentScene().removeArtefact(monsters[j].getMyArtefact());
						};
				}
				}
			}
			
		}
	}

	private boolean checkCollision(Bullet bullet, Monster monster) 
	{
		float x0 = monster.getMyArtefact().getFloat(Attr.X);
		float y0 = monster.getMyArtefact().getFloat(Attr.Y);
		float x1 = x0 + 64;
		float y1 = y0 - 64;
		float c_x = bullet.getMyArtefact().getFloat(Attr.X) + 16;
		float c_y = bullet.getMyArtefact().getFloat(Attr.Y) - 16;
		boolean r = (c_x>x0 && c_x<x1 && c_y > y1 && c_y < y0);
		return r;
	}

}
