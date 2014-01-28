package controller;

import java.util.Random;

import missions.Mission1;
import model.BenchGameModel;
import model.Rambo;
import ai.BulletAI;
import ai.MonsterAI;
import ai.PlayerAI;
import fr.lirmm.game.Game;
import fr.lirmm.game.agent.Agent;
import fr.lirmm.game.artefact.Artefact;

public class GameController implements BenchGameController {
	getXML.GetXML XML = new getXML.GetXML(main.StartGame.MAINXML);
	private Random rand = new Random();
	private static GameController singleton;
	protected BenchGameModel model;
	public static  BenchGameController getInstance()
	{
		if(singleton == null)
		{
			singleton = new GameController();
		}
		return singleton;
	}
	private GameController()
	{
		this.model = new BenchGameModel(BenchGameModel.MAX_MONSTERS, BenchGameModel.MAX_BULLETS);
	}
	
	@Override
	public void playerMoveByDirection(float dx, float dy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerHeading(float dx, float dy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerShootAt(float playerx, float playery, float directionx,
			float directiony) {
		// TODO Auto-generated method stub

	}

	@Override
	public int createMonster(float x0, float y0) {

		int i= this.model.createMonster(x0,y0);
		if(i>=0)
		{
			Artefact a = this.model.monstersTable[i].getMyArtefact();
			Game.scenes.getCurrentScene().addArtefact(a);
			MonsterAI temp = (MonsterAI) factory.Factory.createAI("monster");
			temp.setAI(this.model.monstersTable[i],new int[]{200+(rand.nextInt(500)),100+rand.nextInt(500),100+rand.nextInt(500),100+rand.nextInt(500)}, 
					new float[]{x0+0, x0+300.f, x0+300.f, x0+0}, 
					new float[]{y0+0,y0+0,y0+ 300, y0+300});
			Agent ai = temp;
			Game.agents.addAgent(ai);
		}
		return i;
		
	}
	
	

	@Override
	public void destroyMonster(int id) {

		this.model.monstersTable[id].deactivate();
		Artefact a = this.model.monstersTable[id].getMyArtefact();
		Game.scenes.getCurrentScene().removeArtefact(a);
		Mission1.Mkilled++;
		
		
		Random random = new Random(800);
		float x1 = random.nextFloat();
		float y1 = random.nextFloat();
		float x2 = random.nextFloat();
		float y2 = random.nextFloat();
		createMonster(x1 ,y1);
		createMonster(x2 ,y2);
		
		}

	@Override
	public void monsterMoveBy(int monsterindex, float dx, float dy, float time) {
		// TODO Auto-generated method stub

	}

	@Override
	public int createBullet
	(
			float x0, float y0, 
			float x1, float y1,
			float velocity
	) 
	
	{

		float heading = (float) Math.atan2(x1-x0,y1-y0);
		int i= this.model.createBullet(x0,y0,heading);
		Mission1.bulShot++;
		if(i >=0)
		{	Artefact b = this.model.bulletsTable[i].getMyArtefact();
			BulletAI temp = (BulletAI) factory.Factory.createAI("bullet");
			temp.setAI(i,this.model.bulletsTable[i],x1,y1);
			Agent ai = temp;
			Game.scenes.getCurrentScene().addArtefact(b);
			Game.agents.addAgent(ai);
		} 
		return i;
	}

	@Override
	public void destroyBullet(int id) 
	{
		this.model.bulletsTable[id].deactivate();
		Game.scenes.getCurrentScene().removeArtefact(this.model.bulletsTable[id].getMyArtefact());

	}

	@Override
	public void explosion(float x, float y) {
		// TODO Auto-generated method stub

	}
	@Override
	public Rambo createRambo() {
		Rambo r = new Rambo();
		PlayerAI temp = (PlayerAI) factory.Factory.createAI("rambo");
		temp.setAI(r);
		Agent ai = temp;
		Game.agents.addAgent(ai);
		Game.scenes.getCurrentScene().addArtefact(r.getMyArtefact());
		return r;
	}
	@Override
	public BenchGameModel getGameModel() {
		return this.model;
	}

}
