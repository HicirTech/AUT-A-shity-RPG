package v2_test;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * this is the class used to control a connection of the database
 * 
 * @author Luo Zeting ID:16938158
 * @version 1.1
 */

public class DatabaseControl {

	private final String DB= "RPGAME;"; //a RPGGAME database
	private final String url = "jdbc:derby:"+DB+"create=true"; 
	private final String usernameDerby="LuoZeting";
	private final String passwordDerby="16938158";
	private Connection connection;

        /**
         * this method will try to 'reset' a database,\
         * it will drop the location table and create it again
         * by this way we can eliminate most of database error
         */
	public void reSetdatabse(){
		try {
                    this.connection.createStatement().executeUpdate("DROP TABLE Location");
                    this.connection.commit();
		} catch (SQLException e) {
                    System.out.println("DATABASE:[RESET]: DO NOT HAVE Location TABLE!!!");
		}
		this.CreateTable();
	}
        /**
         * this method will check do we have tables in the database
         * @return is have database
         */
	public boolean hasTable(){
		return this.getResults()!=null;
	}
        
        /**
         * this method will try to get a resultSet which contain information about player
         * @return a resultSet contains information of player
         */
	private ResultSet getResults(){
		ResultSet result = null;
		try {
                    result= this.connection.createStatement().executeQuery("SELECT level, xAxis, yAxis, money, hp, atk FROM LOCATION");
		} catch (SQLException e) {
                    System.out.println("DATABASE:[RESULTSET]: DO NOT HAVE Location TABLE");
		}		
		return result;
	}
        
        /**
         * this method will store information of player to database
         * @param level of player
         * @param x of player
         * @param y of player
         * @param money of player
         * @param hp of player
         * @param atk of player
         */
	public void input(int level,int x,int y, int money,int hp,int atk){
		try {
                    this.connection.createStatement().executeUpdate("INSERT INTO Location VALUES"
                                    + "("+level+","+x+","+y	+","+money+","+hp+","+atk+ ")");
                    this.connection.commit();
                    System.out.println("DATABASE:[INPUT]:COMMIT SUCCESSED!");
		} catch (SQLException e) {
                    //if input fail?
                    System.out.println("DATABASE:[INPUT]:INPUT ERROR!");
		}
	}
        
        /**
         * this method will return player level in integer
         * @return level of player
         */
	public int getLastLevel(){
		String level="";
		try {			
                    ResultSet re =getResults();
                    while(re.next())
                    {
                            level=re.getString("level");
                    }
		} catch (SQLException e1) {			
			System.out.println("DATABASE:[GET]:ERROR");
		}		
		return Integer.valueOf(level);
	}
                
        /**
         * this method will return player x in integer
         * @return x of player
         */
	public int getLastX(){
		String x="";
		try {			
                    ResultSet re=getResults(); 
                    while(re.next())
                    {
                    x=re.getString("xAxis");
                    }
		} catch (SQLException e1) {
                        System.out.println("DATABASE:[GET]:ERROR");

		}		
		return Integer.valueOf(x);
	}
        
        /**
         * this method will return player y in integer
         * @return y of player
         */
	public int getLastY(){
		String y="";
		try {			
                    ResultSet re=getResults(); 
                    while(re.next())
                    {
                    y=re.getString("yAxis");
                    }
		} catch (SQLException e1) {
                        System.out.println("DATABASE:[GET]:ERROR");
		}		
		return Integer.valueOf(y);
	}
        
        /**
         * this method will return player money in integer
         * @return money of player
         */
	public int getLastMoney(){
		String a="";
		try {			
                    ResultSet re =getResults();
                    while(re.next())
                    {
                    a=re.getString("money");
                    }
		} catch (SQLException e1) {
                        System.out.println("DATABASE:[GET]:ERROR");
		}		
		return Integer.valueOf(a);
	}
	
        /**
         * this method will return player HP in integer
         * @return HP of player
         */
	public int getLastHp(){
		String hp="";
		try {			
                    ResultSet re = getResults();
                    while(re.next())
                    {
                    hp=re.getString("hp");
                    }
		} catch (SQLException e1) {
                        System.out.println("DATABASE:[GET]:ERROR");
		}		
		return Integer.valueOf(hp);
	}
	public int getLastAtk(){
		String a=null;
		try {			
                    ResultSet re=getResults(); 
                    while(re.next())
                    {
                    a=re.getString("atk");
                    }
		} catch (SQLException e1) {			
                    System.out.println("DATABASE:[GET]:ERROR");

		}		
		return Integer.valueOf(a);
	}
	
	
	/**
         * this method will create a table in database for store player information
         */
	public void CreateTable(){
				try {
					connection.createStatement().executeUpdate("CREATE TABLE Location ("
							+ "level int,xAxis int,"
							+ "yAxis int,money int,"
							+ "hp int,atk int)");
						connection.commit();
				} catch (SQLException e) {
					System.out.println("DATABASE:MAKEING TABLE ERROR:TRY RESET TABLE!!!!");
					this.reSetdatabse();
				}				
				System.out.println("DATABASE:LOCATION TABLE SETUP");
	}
	  
    /**
     * this method will set up the connection to database
     */
    public void establishConnection(){
        try {
        	connection=DriverManager.getConnection(url, usernameDerby, passwordDerby);
            System.out.println(url+"   connected....");
            System.out.println(connection);
        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    /**
     * this method will return the connection of the game
     * @return 
     */
    public Connection getConnection() {
            return connection;
    }
    
	
    
}
