package v2_test;

public class Level {	
	private int level; 
	private Location inLevelLocation[][];
	private Player player;
	
	Level()
	{
		this.inLevelLocation = new Location[10][10];
		for(int yAxis=0;yAxis!=10;yAxis++)
		{
			for(int xAxis=0;xAxis!=10;xAxis++)
			{
				this.getInLevelLocation()[xAxis][yAxis]=new Location(xAxis,yAxis);
			}
		}		
	}

	public void setPlayer(Player player)
	{
		this.player=player;
	}

	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public Location[][] getInLevelLocation() {
		return inLevelLocation;
	}


	public void setInLevelLocation(Location[][] inLevelLocation) {
		this.inLevelLocation = inLevelLocation;
	}
	
	public String toString()
	{
		String returning= "�X�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�[\n";
		
		for(int yAxis=0;yAxis!=10;yAxis++)
		{
			for(int xAxis=0;xAxis!=10;xAxis++)
			{
				if(xAxis==0)
				{
					returning=returning+"�U"+this.getInLevelLocation()[xAxis][yAxis].toString();
				}
				else if(xAxis==9)
				{
					returning=returning+this.getInLevelLocation()[xAxis][yAxis].toString()+"�U";
				}
				else
				
				returning=returning+""+this.getInLevelLocation()[xAxis][yAxis].toString();
			}
			if(yAxis==1)
			{
				returning = returning +"Player information:"+"		"+"Lables:";
			}
			else if(yAxis==2)
			{
				returning = returning +"heath: "+player.getHeath()+"			"+"��:Player";
			}
			else if(yAxis==3)
			{
				returning = returning +"money: "+player.getMoney()+"			"+"��:Monster";
			}
			else if(yAxis==4)
			{
				returning = returning +"Your location is: Level "+player.getCurrentLevel().getLevel()+"	"+"�{:Walls";
			}
			else if(yAxis==5&&this.player.isHasSword())
			{
				returning = returning +"You are using: "+player.getCurrentSword()+" ������:GoUp";
			}
			else if (yAxis==5)
			{
				returning = returning +"				"+"������:GoUp";
			}
			else if(yAxis==6)
			{
				returning = returning + "Your ATK is: "+this.player.getAttack()+"		"+"������:GoDown";;
			}
			returning = returning +"\n";	
		}
		
		return returning+"�^�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�T�a";
	}
	
	
}
