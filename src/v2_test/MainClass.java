package v2_test;

/**
 * main class use to run and show welcome
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
public class MainClass{
    
    public static void main(String args[])
    {
       welcoming();
       StartMemuControl GAME = new StartMemuControl();
       System.out.println(GAME.getClass()+"STARTED!!");
       
}
    /**
     * A welcoming will print to screen
     */
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
