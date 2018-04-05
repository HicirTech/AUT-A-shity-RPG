package v2_test;
import java.util.*;
import java.io.*;

public class World {
	
	private Player player;
	private Monster m[] = new Monster[30];
	private Level inGameLevel[];
	private Sword s;
	private PrintWriter playersaver;
	private Scanner  stairUp;
	private	Scanner stairDown;
	private Scanner monster;
	private Scanner map;
	private Scanner playerLoad;

	World(boolean load)
	{
		
		if(load==false)
		{
			try
			{
			map = new Scanner (new File("map.txt"));
			monster=new Scanner(new File("monster.txt"));
			stairUp= new Scanner (new File("stairUp.txt"));
			stairDown= new Scanner (new File("stairDown.txt"));
			}
			catch (FileNotFoundException e) {System.out.println("File error, please check your game files");}
			
		MapManager();
		MonsterManager();
		this.player=new Player(this.getInGameLevel()[0]);
		this.player.setCurrentLevel(this.inGameLevel[0]);
		this.inGameLevel[0].setPlayer(player);
		this.SwordManager();
		WorldStart();
		}
	
		else
		{
			
			try
			{
				map = new Scanner (new File("map.txt"));
				monster=new Scanner(new File("monster.txt"));
				stairUp= new Scanner (new File("stairUp.txt"));
				stairDown= new Scanner (new File("stairDown.txt"));
				playerLoad= new Scanner (new File("player.txt"));
				
				
				this.MapManager();
				this.MonsterManager();
				try
				{
				String next ;
				next=playerLoad.nextLine();
				this.player=new Player(this.getInGameLevel()[Integer.valueOf(next.split(",")[0])],Integer.valueOf(next.split(",")[1]),
				Integer.valueOf(next.split(",")[2]),Integer.valueOf(next.split(",")[3]),Integer.valueOf(next.split(",")[4]),Integer.valueOf(next.split(",")[5]));
				this.inGameLevel[Integer.valueOf(next.split(",")[0])].setPlayer(player);
				playerLoad.close();
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Gamer Save not error! Setting up new game");
					this.player=new Player(this.getInGameLevel()[0]);
					this.player.setCurrentLevel(this.inGameLevel[0]);
					this.inGameLevel[0].setPlayer(player);
				}
				this.SwordManager();
				WorldStart();
			}
			catch (FileNotFoundException e) {
				System.out.println("Gamer Save not found! Setting up new game");
				try
				{
					//playersaver = new PrintWriter (new File("file.txt"));
					map = new Scanner (new File("map.txt"));
					monster=new Scanner(new File("monster.txt"));
					stairUp= new Scanner (new File("stairUp.txt"));
					stairDown= new Scanner (new File("stairDown.txt"));
				}
				catch (FileNotFoundException a) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MapManager();
				MonsterManager();
				this.player=new Player(this.getInGameLevel()[0]);
				this.player.setCurrentLevel(this.inGameLevel[0]);
				this.inGameLevel[0].setPlayer(player);
				WorldStart();
			
				
			}}
		
		}
	

	
	private void WorldStart()
	{
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
		}while(this.getPlayer().getHeath()>0);		
		System.out.println("You died, the world is over!");
	}

	private void swordPickUpEevent()
	{
		if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasSword())
		{
			s.pickUp(player);
		}
	}
	private void savePointEevent()
	{
		if (player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasSavePoint())
		{
			try
			{playersaver = new PrintWriter (new File("player.txt"));
			playersaver.println(
					this.player.getCurrentLevel().getLevel()+","+
					this.player.getCurrentX()+","+
					this.player.getCurrentY()+","+
					this.player.getMoney()+","+
					String.format("%d",this.player.getHeath())+","+
					String.format("%d",this.player.getAttack()));
			playersaver.flush();
			playersaver.close();
			}catch (FileNotFoundException e) {}
			
		}
	}
	public void saveEvent()
	{
		try
		{playersaver = new PrintWriter (new File("player.txt"));
		playersaver.println(
				this.player.getCurrentLevel().getLevel()+","+
				this.player.getCurrentX()+","+
				this.player.getCurrentY()+","+
				this.player.getMoney()+","+
				(int)this.player.getHeath()+","+
				(int)this.player.getAttack());
		playersaver.flush();
		playersaver.close();
		}catch (FileNotFoundException e) {}		
	}
	private void monsterEvent(int monsterNo)
	{
		try
		{if(player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasMonster())
		{
			
			m[monsterNo].Attack(player);
			m[monsterNo].setHeath(m[monsterNo].getHeath()-player.getAttack());	
			if(!(m[monsterNo].getHeath()>0))
			{
				m[monsterNo].die(player);
				this.getInGameLevel()[player.getCurrentLevel().getLevel()].	getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasMonster(false);
				this.getInGameLevel()[player.getCurrentLevel().getLevel()].	getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].setHasPlayer(true);
				m[monsterNo]=null;
			}			
		}
		}
		catch(NullPointerException e){}
	}
	
	private void levelChangeChecker()
	{
		if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasUpStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()+1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForLower().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForLower().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentLevel().lookForLower().getxAxis()][getPlayer().getCurrentLevel().lookForLower().getyAxis()].setHasPlayer(true);
			this.SwordManager();
			this.saveEvent();
		}
		else if(getPlayer().getCurrentLevel().getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].isHasDownStairs())
		{
			getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1].setPlayer(getPlayer());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentX()][getPlayer().getCurrentY()].setHasPlayer(false);
			getPlayer().setCurrentLevel(getInGameLevel()[getPlayer().getCurrentLevel().getLevel()-1]);
			this.player.setCurrentX(getPlayer().getCurrentLevel().lookForUpper().getxAxis());
			this.player.setCurrentY(getPlayer().getCurrentLevel().lookForUpper().getyAxis());
			this.getInGameLevel()[this.getPlayer().getCurrentLevel().getLevel()].getInLevelLocation()[getPlayer().getCurrentLevel().lookForUpper().getxAxis()][getPlayer().getCurrentLevel().lookForUpper().getyAxis()].setHasPlayer(true);
			this.SwordManager();
			this.saveEvent();
		}
	
	}
	
		
	public Player getPlayer() {
		return player;
	}
	
	public Level[] getInGameLevel() {
		return inGameLevel;
	}
	public void setInGameLevel(Level[] inGameLevel) {
		this.inGameLevel = inGameLevel;
	}
	
	
	private final void MapManager()
	{
		this.inGameLevel = new Level[30];	
		
		for(int levelSet=0;levelSet!=30;levelSet++)
		{
			this.inGameLevel[levelSet]=new Level();
			this.inGameLevel[levelSet].setLevel(levelSet);
		}		
		String next ;
		do
		{
			try
			{
			next=map.nextLine();
				if(next!=null)
				{
					this.inGameLevel[Integer.valueOf(next.split(",")[0])].getInLevelLocation()[Integer.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasWall(true);	
				}
				else
				{
					break;
				}
			}
			catch(NoSuchElementException e )
			{
				break;
			}
		}while(true);
		
		
		this.upStairManager();
		this.downStairManager();
		this.MonsterManager();
					
	}
	private final void upStairManager()
	{
	String next ;
		do
		{
			try
			{
			next=stairUp.nextLine();
				if(next!=null)
				{
					this.inGameLevel[Integer.valueOf(next.split(",")[0])].getInLevelLocation()[Integer.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasUpStairs(true);	
				}
				else
				{
					break;
				}
			}
			catch(NoSuchElementException e )
			{
				break;
			}
		}while(true);
	
		
	}
	private  final void downStairManager()
	{
	String next ;

		do
		{
			try
			{
			next=stairDown.nextLine();
				if(next!=null)
				{
					this.inGameLevel[Integer.valueOf(next.split(",")[0])].getInLevelLocation()[Integer.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasDownStairs(true);	
				}
				else
				{
					break;
				}
			}
			catch(NoSuchElementException e )
			{
				break;
			}
		}while(true);
	
		
	}
	private final void MonsterManager()
	{		
		String next ;
			do
			{
				try
				{
					next=monster.nextLine();
					if(next!=null)
					{
						m[Integer.valueOf(next.split(",")[5])]= new Monster(this.getInGameLevel()[Integer.valueOf(next.split(",")[0])]
								,Integer.valueOf(next.split(",")[3]),
								Integer.valueOf(next.split(",")[4]));		
						m[Integer.valueOf(next.split(",")[5])].setMonster(Integer.valueOf(next.split(",")[1]),
								Integer.valueOf(next.split(",")[2]));
						getInGameLevel()[Integer.valueOf(next.split(",")[0])].
						getInLevelLocation()[Integer.valueOf(next.split(",")[1])][Integer.valueOf(next.split(",")[2])].setHasMonster(true);
					}
					else
					{
						break;
					}
					
				}
				catch(NoSuchElementException e )
				{
					break;
				}
			}while(true);
		
	}
	private void SwordManager()
	{
		s = null;
		switch(this.getPlayer().getCurrentLevel().getLevel())
		{
		case(0):
		{
			//this.getInGameLevel()[0].getInLevelLocation()[5][6].setHasSword(true);
			
			s = new Sword_old(this.player.getCurrentLevel());
			s.setSword(5, 6);
			break;
		}
		case(1):
		{
			
			s = new Sword_Napoleon(this.player.getCurrentLevel());
			s.setSword(6, 3);
			break;
		}
		case(4):
		{

			s = new Sword_Master(this.player.getCurrentLevel());
			s.setSword(8, 2);
			break;
		}
		case(7):
		{
			s = new Sword_DS(this.player.getCurrentLevel());
			s.setSword(5, 2);
			break;
		}
		case(9):
			s = new Sword_Programer(this.player.getCurrentLevel());
			s.setSword(5, 7);
			break;
		}		
	
		
	}
	
	
	private void checkList()
	{
		this.levelChangeChecker();
		this.savePointEevent();
		for(int monsterNO=0;monsterNO!=m.length;monsterNO++)
		{
			this.monsterEvent(monsterNO);
		}
		this.swordPickUpEevent();
	}
	
}
