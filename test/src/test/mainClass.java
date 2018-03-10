package test;
import java.util.*;

public class mainClass {

	public static void main(String args[])
	{
	
		Level a = new Level(1);
		player player= new player(a);
		for(int b = 0;b!=6;b++)
		{
			a.getInLevelLocation()[b][1].setHasWall(true);
		}
		a.getInLevelLocation()[7][1].setHasWall(true);
		for(int i=0; i!=99;i++)
		{
			System.out.println(a.toString());
			player.move(new Scanner(System.in).next().charAt(0));
		}
		System.out.println(player);
		System.out.println(a.toString());
	}
}
