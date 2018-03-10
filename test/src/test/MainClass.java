package test;
import java.util.*;

public class MainClass {

	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		Level a = new Level(1);
		Player player= new Player(a);
		a.setPlayer(player);
		Monster m = new Monster(a);
		
		System.out.println("please make sure you can see 4 ¡ø, then enter [A] to start");
		System.out.println("¡ø                                                                ¡ø");
		System.out.println("\n\n\n\n\n\n");
		System.out.println("¡ø                                                                ¡ø");
		if(scan.next().charAt(0)=='A')
		{
			for(int b = 0;b!=6;b++)
			{
				a.getInLevelLocation()[b][1].setHasWall(true);
			}
			a.getInLevelLocation()[7][1].setHasWall(true);
			do
			{
				System.out.println(a);
				player.move(new Scanner(System.in).next().charAt(0));
				try
				{
					if(player.getCurrentX()==m.getCurrentX()&&player.getCurrentY()==m.getCurrentY())
					{
						m.Attack(player);
						m=null;
					}
				}
				catch(NullPointerException e)
				{
					player.move(new Scanner(System.in).next().charAt(0));
				}
			}while(true);
		}
}
}
