package v2_test;
import java.util.*;
public class World {
	private Player player;
	private Monster m[];
	private Level inGameLevel[];
	private Sword s;
	World()
	{
		levelManager();
		this.player=new Player(this.getInGameLevel()[0]);
		this.player.setCurrentLevel(this.inGameLevel[0]);
		this.inGameLevel[0].setPlayer(player);
		do{
			try{
			player.move(new Scanner(System.in).next().charAt(0));
			goUpChecker();
			goDownChecker();
			monsterEvent(0);
			swordPickUpEevent();
			System.out.println(this.getPlayer().getCurrentLevel());
			}
			catch(NullPointerException e)
			{
				player.move(new Scanner(System.in).next().charAt(0));
				goUpChecker();
				goDownChecker();
				monsterEvent(0);
				swordPickUpEevent();
				System.out.println(this.getPlayer().getCurrentLevel());
			}
		}while(player.getHeath()!=0);
		
	}
	
	public void swordPickUpEevent()
	{
		if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasSword())
		{
			s.pickUp(player);
		}
	}
	
	public void monsterEvent(int monsterNo)
	{
		if(player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasMonster())
		{
			m[monsterNo].Attack(player);
			m[monsterNo].setHeath(m[monsterNo].getHeath()-player.getAttack());
			
			if(m[monsterNo].getHeath()<=0)
			{
				m[monsterNo].die(player);
				m[monsterNo]=null;
			}			
		}
	}
	public void goUpChecker()
	{
		if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasUpStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1]);
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(true);
			//levelManager();
		}
	}
	public void goDownChecker()
	{
		if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasDownStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1]);
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(true);
		//	levelManager();
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Character player) {
		player = player;
	}
	public Level[] getInGameLevel() {
		return inGameLevel;
	}
	public void setInGameLevel(Level[] inGameLevel) {
		this.inGameLevel = inGameLevel;
	}
	
	
	public void levelManager()
	{
		
		this.inGameLevel = new Level[30];	
		m = new Monster[30];
		for(int levelSet=0;levelSet!=30;levelSet++)
		{
			this.inGameLevel[levelSet]=new Level();
			this.inGameLevel[levelSet].setLevel(levelSet);
			m[levelSet] = new Monster(inGameLevel[levelSet]);
		}
		level0:
		{
			s = new Sword_Napoleon(this.inGameLevel[0]);
		for(int b = 0;b!=6;b++)
		{
			this.inGameLevel[0].getInLevelLocation()[b][1].setHasWall(true);
		}
		this.inGameLevel[0].getInLevelLocation()[7][1].setHasWall(true);
		this.inGameLevel[0].getInLevelLocation()[8][1].setHasUpStairs(true);
		this.inGameLevel[0].getInLevelLocation()[1][8].setHasDownStairs(true);
		this.inGameLevel[0].getInLevelLocation()[5][5].setHasMonster(true);
		s.setSword(8, 9);
		m[0].setMonster(5,5);
		}
		level1:
		{
			for(int b = 0;b!=6;b++)
			{
				this.inGameLevel[1].getInLevelLocation()[1][b].setHasWall(true);
			}
			this.inGameLevel[1].getInLevelLocation()[7][1].setHasWall(true);
		}
		this.inGameLevel[1].getInLevelLocation()[8][1].setHasUpStairs(true);
		this.inGameLevel[1].getInLevelLocation()[1][8].setHasDownStairs(true);
	}
	
}
