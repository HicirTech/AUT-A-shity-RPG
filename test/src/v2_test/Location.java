package v2_test;
/**
 * @author luozeting
 * @version 0.0.1
 */

public class Location {
	private int xAxis;
	private int yAxis;
	
	private boolean hasPlayer;
	private boolean hasWall;
	private boolean hasSword;
	private boolean hasMonster;
	private boolean hasUpStairs;
	private boolean hasDownStairs;
	private boolean hasTreader;
	
	Location()
	{
		this.setxAxis(-1);
		this.setyAxis(-1);
	}
	
	
	
	Location(int xAxis,int yAxis)
	{
		this.setxAxis(xAxis);
		this.setyAxis(yAxis);
		this.setHasPlayer(false);
	}

	public int[] getUpperLocation()
	{
		int[] a= {this.xAxis,this.yAxis};
		return a;
		
	}
	public int getxAxis() {
		return xAxis;
	}

	public void setxAxis(int xAxis) {
		this.xAxis=xAxis;
		
	}

	public int getyAxis() {
		return yAxis;
	}

	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	public boolean isHasPlayer() {
		return hasPlayer;
	}



	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}
	

	public boolean isHasWall() {
		return hasWall;
	}



	public void setHasWall(boolean hasWall) {
		this.hasWall = hasWall;
	}
	
	

	public boolean isHasUpStairs() {
		return hasUpStairs;
	}



	public void setHasUpStairs(boolean hasUpStairs) {
		this.hasUpStairs = hasUpStairs;
	}

	

	public boolean isHasDownStairs() {
		return hasDownStairs;
	}



	public void setHasDownStairs(boolean hasDownStairs) {
		this.hasDownStairs = hasDownStairs;
	}



	public boolean isHasMonster() {
		return hasMonster;
	}



	public void setHasMonster(boolean hasMonster) {
		this.hasMonster = hasMonster;
	}


	
	
	public boolean isHasSword() {
		return hasSword;
	}



	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}

	

	public boolean isHasTreader() {
		return hasTreader;
	}



	public void setHasTreader(boolean hasTreader) {
		this.hasTreader = hasTreader;
	}



	public String toString()
	{
			
		if(this.hasPlayer)
		{
			return " ▲ ";
		}
		else if(this.hasWall)
		{
			return "###";
		}
		else if(this.hasMonster)
		{	
			return " * ";
		}
		else if(this.hasSword)
		{
			return " S ";
		}
		else if(this.hasUpStairs)
		{
			return "↑↑↑";	
		}
		else if(this.hasDownStairs)
		{
			return "↓↓↓";
		}
		else if(this.hasTreader)
		{
			return " $ ";
		}
		else			
		{
			return "___";
		}
	
		
	}
	
}
