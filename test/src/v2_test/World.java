package v2_test;
import java.util.*;
public class World {
	
	private Player player;
	private Monster m[];
	private Trader t[];
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
			checkList();
			System.out.println(this.getPlayer().getCurrentLevel());
			}
			catch(NullPointerException e)
			{
				player.move(new Scanner(System.in).next().charAt(0));
				checkList();
				System.out.println(this.getPlayer().getCurrentLevel());
			}
		}while(player.getHeath()>0);
		
		
	}
	
	public void swordPickUpEevent()
	{
		if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasSword())
		{
			s.pickUp(player);
		}
	}
	public void meetTraderEvent(int traderNo)
	{
		if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasTreader())
		{
			System.out.println("Trader asking for 5 Golds for boot up you heath");
			if(new Scanner(System.in).next().charAt(0)=='o')
			{
				t[traderNo].playerBuyHeath(player);
				t[traderNo]=null;

			}
			else
			{
				System.out.println("Trader feels sad");
			}
				
			
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
	
	public void levelChangeChecker()
	{
		if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasUpStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForLower().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForLower().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentLevel().lookForLower().getxAxis()][getPlayer().getCurrentLevel().lookForLower().getyAxis()].setHasPlayer(true);
		}
		else if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasDownStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForUpper().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForUpper().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentLevel().lookForUpper().getxAxis()][getPlayer().getCurrentLevel().lookForUpper().getyAxis()].setHasPlayer(true);
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
		t = new Trader[10];
		for(int levelSet=0;levelSet!=30;levelSet++)
		{
			this.inGameLevel[levelSet]=new Level();
			this.inGameLevel[levelSet].setLevel(levelSet);
			m[levelSet] = new Monster(inGameLevel[levelSet]);
			if(levelSet%5==0)
			{
				t[(levelSet/3)] = new Trader(inGameLevel[levelSet]);
			}
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
		m[0].setAttack(99);
		t[0].setTrader(3,4);
		}
		level1:
		{
			this.inGameLevel[1].getInLevelLocation()[0][0].setHasUpStairs(true);
			for(int i=0;i!=10;i++)
			{
				this.inGameLevel[1].getInLevelLocation()[i][1].setHasWall(true);
				if(i<3&&i!=1)
				{
				this.inGameLevel[1].getInLevelLocation()[i][4].setHasWall(true);
				this.inGameLevel[1].getInLevelLocation()[i][7].setHasWall(true);
				}
				if(i>5)
				{
				this.inGameLevel[1].getInLevelLocation()[i][6].setHasWall(true);
				}
				if(i>5&&i!=8)
				{
					this.inGameLevel[1].getInLevelLocation()[i][4].setHasWall(true);
				}
				if(i>3&&i!=5&&i!=9)
				{
				this.inGameLevel[1].getInLevelLocation()[i][8].setHasWall(true);
				}
				
			}
			for(int i=2;i!=11;i++)
			{
				if(i>2)
				{
					this.inGameLevel[1].getInLevelLocation()[3][i].setHasWall(true);
				}
				if(i<7)
				{
					this.inGameLevel[1].getInLevelLocation()[9][i].setHasWall(true);
				}
				if(i<7&&i!=5)
				{
				this.inGameLevel[1].getInLevelLocation()[5][i].setHasWall(true);
				}
				else if (i>7)
				{
					this.inGameLevel[1].getInLevelLocation()[7][i].setHasWall(true);
				}
			}
			this.inGameLevel[1].getInLevelLocation()[10][8].setHasWall(true);
		}
		this.inGameLevel[1].getInLevelLocation()[10][10].setHasDownStairs(true);
	}
	
	public void checkList()
	{
		levelChangeChecker();
		for(int traderNo=0;traderNo!=t.length;traderNo++)
		{
			meetTraderEvent(traderNo);
		}
		
		for(int monsterNO=0;monsterNO!=m.length;monsterNO++)
		{
			monsterEvent(monsterNO);
		}
		swordPickUpEevent();
	}
	
}
/*
-------------------------wasted code---------------------------------
public void goUpChecker()
	{
		if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasUpStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForLower().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForLower().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentLevel().lookForLower().getxAxis()][getPlayer().getCurrentLevel().lookForLower().getyAxis()].setHasPlayer(true);
		}
	}
	
	public void goDownChecker()
	{
		if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasDownStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForUpper().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForUpper().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentLevel().lookForUpper().getxAxis()][getPlayer().getCurrentLevel().lookForUpper().getyAxis()].setHasPlayer(true);
		}
	}

*/