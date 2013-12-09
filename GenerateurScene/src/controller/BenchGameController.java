package controller;

import model.BenchGameModel;
import model.Rambo;

public interface BenchGameController 
{
	/*player actions*/
	public Rambo createRambo();
	public void playerMoveByDirection(float dx, float dy);
	public void playerHeading(float dx, float dy);	
	public void playerShootAt(float playerx,float playery,float directionx,float directiony);
	/* monster actions*/
	public int createMonster(float x0, float y0);
	public void destroyMonster(int id);
	public void monsterMoveBy(int monsterindex, float dx,float dy,float time);
	/* bullet actions*/
	public int createBullet(float x0, float y0, float x1, float y1, float velocity);
	public void destroyBullet(int id);
	/* explosion */
	public void explosion(float x, float y);
	
	
	/* */
	public BenchGameModel getGameModel();
	}
	
