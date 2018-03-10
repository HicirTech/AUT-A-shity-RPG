package test;

public class Monster extends Character{
	
	Monster(Level thisLevel)
	{
		super(thisLevel);
		this.getCurrentLevel().getInLevelLocation()[7][7].setHasMonster(true);
		this.setCurrentX(7);
		this.setCurrentY(7);
	}
	public void Attack(Player player)
	{
		player.setHeath(player.getHeath()-5);
		player.setMoney(getMoney()+20);
		this.getCurrentLevel().getInLevelLocation()[this.getCurrentX()][this.getCurrentY()].setHasMonster(false);
	}
}
