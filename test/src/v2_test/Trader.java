package v2_test;

public class Trader extends Character{

	Trader(Level currentLevel)
	{
		super.setCurrentLevel(currentLevel);		
		this.setHeath(0);
		this.setMoney(0);
	}
	public void setTrader(int xAxis,int yAxis)
	{
		this.setCurrentX(xAxis);
		this.setCurrentY(yAxis);
		super.getCurrentLevel().getInLevelLocation()[xAxis][xAxis].setHasTreader(true);
	}
	
	public void playerBuyHeath(Player player)
	{
		player.setHeath(player.getHeath()+5);
		player.setHeath(player.getMoney()-20);
		super.getCurrentLevel().getInLevelLocation()[getCurrentX()][getCurrentY()].setHasTreader(false);
	}
	
	@Override
	public void move(char way) {}

}
