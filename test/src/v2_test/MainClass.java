package v2_test;
import java.util.*;

public class MainClass {
	public static void main(String args[])
	{
		welcoming();
		
		System.out.println("Do you want load from Save?[Y/N]");
		char checker =new Scanner(System.in).next().toLowerCase().charAt(0);
		if(checker=='y')
		{
			System.out.println("Enter any key to Start your game");
			World world = new World(true);
			
		}
		else if(checker=='n')
		{
			System.out.println("Enter any key to Start your game");
			World world = new World(false);
		}
		
		
}
	static void welcoming()
	{
		System.out.println("o                                                                o");
		System.out.println("\n\n\n\n");
		System.out.println("  please make sure you can see 4 o, then enter anykey to start\n");
		System.out.println("          Enter 'a','s','d','w' to move the charater");
		System.out.println("\n\n");
		System.out.println("o                                                                o");
	}
}
