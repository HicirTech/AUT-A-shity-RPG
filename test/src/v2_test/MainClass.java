package v2_test;
import java.util.*;

public class MainClass {
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		/*Level a = new Level();
		Player player= new Player();
		a.setPlayer(player);
		Monster m = new Monster(a);
		Sword s = new Sword_Napoleon(a);*/
		System.out.println("please make sure you can see 4 ¡ø, then enter anykey to start");
		System.out.println("¡ø                                                                ¡ø");
		System.out.println("\n\n\n\n\n\n");
		System.out.println("¡ø                                                                ¡ø");
		 World theWorld = new World();
		/*
		do
		{
			theWorld.getPlayer().move(new Scanner(System.in).next().charAt(0));
			/*if(theWorld.getPlayer().getCurrentLevel().getInLevelLocation()[theWorld.getPlayer().getCurrentX()][theWorld.getPlayer().getCurrentY()].isHasUpStairs())
			{
				theWorld.getInGameLevel()[theWorld.getPlayer().getCurrentLevel().getLevel()+1].setPlayer(theWorld.getPlayer());
				theWorld.getPlayer().setCurrentLevel(theWorld.getInGameLevel()[theWorld.getPlayer().getCurrentLevel().getLevel()+1]);
				
			}
			System.out.println(theWorld.getPlayer().getCurrentLevel());
		}while(true);
		
		/*	if(scan.next().charAt(0)=='A')
		{
		
			do
			{
				System.out.println(a);
				player.move(new Scanner(System.in).next().charAt(0));
				try
				{
					//if(player.getCurrentX()==m.getCurrentX()&&player.getCurrentY()==m.getCurrentY())
					if(player.getCurrentLevel().getInLevelLocation()[player.getCurrentX()][player.getCurrentY()].isHasMonster())
					{
						m.Attack(player);
						m.setHeath(m.getHeath()-player.getAttack());
						
						if(m.getHeath()<=0)
						{
							m.die(player);
							m=null;
						}			
					}
					else 
				}
				catch(NullPointerException e)
				{
					player.move(new Scanner(System.in).next().charAt(0));
				}
			}while(true);
		}
		*/
}
}
