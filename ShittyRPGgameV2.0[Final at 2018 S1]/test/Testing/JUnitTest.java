package Testing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import v2_test.DatabaseControl;
import v2_test.EndGameFrame;
import v2_test.GameFrame;
import v2_test.GamePanelStart;
import v2_test.HelpFrame;
import v2_test.StoryPanel;
import v2_test.World;

/**
 *
 * @author wws6045
 */
public class JUnitTest {

/**
 * this is the testing class use JUnit to test database frames and locations and player move
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */
    @Test
    public void databaseConnectionTest()
    {
        DatabaseControl test = new DatabaseControl();
        assert test!=null;
        test.establishConnection();
        if(!test.hasTable())
        {
                test.CreateTable();
        }
        test.input(1, 2, 3, 4, 5, 6);
        assert test.getLastLevel()==1&&test.getLastX()==2
                        &&test.getLastY()==3&&test.getLastMoney()==4
                        &&test.getLastHp()==5&&test.getLastAtk()==6;
        try {
                test.getConnection().createStatement().execute("DROP TABLE Location");
                test.getConnection().commit();
        } catch (SQLException e) {
                System.out.println("DATABASE ERROR!");
        }
        assert test.hasTable()==false;
    }

    @Test
    public void FrameBuildTest(){
        EndGameFrame frame1 = new EndGameFrame();
        GameFrame frame2 = new GameFrame();
        GamePanelStart  frame3 = new GamePanelStart();
        HelpFrame frame4 = new HelpFrame();
        World world = new World(false);
        StoryPanel frame5  =  new StoryPanel(world.getPlayerLevel());
        assert frame1!=null&&frame2!=null&&frame3!=null&&frame4!=null&&frame5!=null;
        System.out.println("Frame buildable test!");
    }

    @Test
    public void worldBuildTest()
    {
        World test1= new  World(false);
        System.out.println(test1+"new world build!");
        World test2 = new World(true);
        System.out.println(test2+"loaded world build!");
    }

    @Test
    public void playerCanMoveAndplayerWillNotOverEdgeTest()
    {
        World test1= new  World(false);
        test1.getPlayer().setCurrentX(10);
        test1.getPlayer().setCurrentY(10);
        for(int i=0;i!=14;i++)
        {
            test1.getPlayer().move('d');
            test1.getPlayer().move('s');
        }
        assert test1.getPlayer().getCurrentX()<11&&test1.getPlayer().getCurrentY()<11;
        test1= new  World(false);
        test1.getPlayer().setCurrentX(0);
        test1.getPlayer().setCurrentY(0);
        for(int i=0;i!=14;i++)
        {
            test1.getPlayer().move('a');
            test1.getPlayer().move('w');
        }
        assert test1.getPlayer().getCurrentX()>-1&&test1.getPlayer().getCurrentY()>-1;
        test1= new  World(false);
        int testX= test1.getPlayer().getCurrentX();
        test1.getPlayer().move('d');
        assert test1.getPlayer().getCurrentX()!=testX;
    }

    @Test
    public void locationUsableTest()
    {
        World test1 =new World(true);
        for(int z=0;z!=10;z++)
        {
            for(int x=0;x!=11;x++)
            {
                for(int y=0;y!=11;y++)
                {
                    System.out.println(test1.getInGameLevel()[z].getInLevelLocation()[x][y].toString()+"Z:"+z+"X:"+x+"Y:"+y+" OK!");

                }
            }

        }

    } 
}
