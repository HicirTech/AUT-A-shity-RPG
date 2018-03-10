package test;

public class Level {	
	private int level; 
	
	private Location inLevelLocation[][];
	
	
	Level(int Level)
	{
		
		this.setLevel(level);
		this.inLevelLocation = new Location[8][8];
		for(int yAxis=0;yAxis!=8;yAxis++)
		{
			for(int xAxis=0;xAxis!=8;xAxis++)
			{
				this.getInLevelLocation()[xAxis][yAxis]=new Location(xAxis,yAxis);
			}
		}
		
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
		String returning=String.format("This is level: %d\n",this.getLevel());
		for(int yAxis=0;yAxis!=8;yAxis++)
		{
			for(int xAxis=0;xAxis!=8;xAxis++)
			{
				returning=returning+""+this.getInLevelLocation()[xAxis][yAxis].toString();
			}
			returning=returning+'\n';
		}
		
		return returning;
	}
	
	
}
