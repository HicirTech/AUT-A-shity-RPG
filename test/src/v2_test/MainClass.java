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
			World world = new World(true);
		}
		else if(checker=='n')
		{
			World world = new World(false);
		}
		
		
}
	static void welcoming()
	{
		System.out.println("o                                                                o");
		System.out.println("\n");
		System.out.println("		      A maze RPG, COMP603\n");
		System.out.println("  #please make sure you can see 4 o, then enter anykey to start#");
		System.out.println("          Enter 'a','s','d','w' to move the charater");
		System.out.println("         	 Persent by Luo Zeting 16938158");
		System.out.println("				and");
		System.out.println("     		        Hou Shujun 16925119\n\n");
		System.out.println("o                                                                o");
	}
}
