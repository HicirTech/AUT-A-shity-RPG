package test;
import java.awt.color.*;
public class player {
	Level thisLevel;
	int x,y;
	
	player(Level thisLevela)
	{
		thisLevel= thisLevela;
		this.thisLevel.getInLevelLocation()[0][0].setHasPlayer(true);
		this.x=0;
		this.y=0;
	}
	
	public void move(char way)
	{
		
		if(way=='w')
		{
			thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(false);
			y--;
			if(!thisLevel.getInLevelLocation()[this.x][this.y].isHasWall())
			{
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}
			else
			{
				y++;
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}
					
					
		}
		else if(way=='a')
		{
			thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(false);
			x--;
			if(!thisLevel.getInLevelLocation()[this.x][this.y].isHasWall())
			{
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}
			else
			{
				x++;
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}
		}
		else if(way=='d')
		{
			thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(false);
			x++;
			if(!thisLevel.getInLevelLocation()[this.x][this.y].isHasWall())
			{
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}
			else
			{
				x--;
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}			
		}
		else if(way=='s')
		{
			thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(false);
			y++;
			if(!thisLevel.getInLevelLocation()[this.x][this.y].isHasWall())
			{
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}
			else
			{
				y--;
				thisLevel.getInLevelLocation()[this.x][this.y].setHasPlayer(true);
			}
		}
		
	}
	
	public String toString()
	{
		return " ¡ø ";
	}
}
