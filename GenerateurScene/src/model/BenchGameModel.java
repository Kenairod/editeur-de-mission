package model;

public class BenchGameModel 
{

	public static int MAX_MONSTERS = 10;
	public static int MAX_BULLETS = 2;
	public Monster[] monstersTable;
	public Bullet[] bulletsTable;
	
	public BenchGameModel(int monsters, int bullets)
	{
		monstersTable = new Monster[monsters];
		for (int i = 0; i < monstersTable.length; i++) {
			monstersTable[i] = new Monster();
		}
		bulletsTable = new Bullet[bullets];
		for (int i = 0; i < bulletsTable.length; i++) {
			bulletsTable[i] = new Bullet();
		}
	}

	public int createMonster(float x0, float y0) {
		int res = -1;
		for(int i = 0 ; i < monstersTable.length;i++)
		{
			Monster m =  monstersTable[i];
			if(! m.isActive())
			{
				res = i;
				m.setId(res);
				m.setPosition(x0,y0);
				m.activate();
				break;
			}
		}
		return res;
	}

	public int createBullet(float x0, float y0, float heading) {
		int res = -1;
		for(int i = 0 ; i < bulletsTable.length;i++)
		{
			Bullet m =  bulletsTable[i];
			if(! m.isActive())
			{
				res = i;
				m.setId(res);
				m.setPosition(x0,y0);
				m.activate();
				break;
			}
		}
		return res;
	}
	
	
}
