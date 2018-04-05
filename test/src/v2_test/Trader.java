package v2_test;
/**
 * Trader is a type of character, it is not in the game yet,but at stage 2 will add into game
 * Trader will enable player to trade item, or use money to buy heath 
 * @author Luo Zeting ID:16938158
 *
 */
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
	/**
	 * in future trader may be able to move
	 */
	@Override
	public void move(char way) {}

}
